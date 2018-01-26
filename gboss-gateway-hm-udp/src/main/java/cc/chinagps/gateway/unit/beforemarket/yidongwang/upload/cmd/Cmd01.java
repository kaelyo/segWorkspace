package cc.chinagps.gateway.unit.beforemarket.yidongwang.upload.cmd;

import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.seg.lib.util.SegPackageUtil;
import org.seg.lib.util.Util;

import cc.chinagps.gateway.buff.InnerDataBuff;
import cc.chinagps.gateway.log.LogManager;
import cc.chinagps.gateway.memcache.MemcacheManager;
import cc.chinagps.gateway.unit.UnitServer;
import cc.chinagps.gateway.unit.UnitSocketSession;
import cc.chinagps.gateway.unit.beans.Loginout;
import cc.chinagps.gateway.unit.beans.UnitInfo;
import cc.chinagps.gateway.unit.beforemarket.common.UploadHandler;
import cc.chinagps.gateway.unit.beforemarket.common.pkg.BeforeMarketPackage;
import cc.chinagps.gateway.unit.beforemarket.common.util.BeforeMarketConstants;
import cc.chinagps.gateway.unit.beforemarket.common.util.BeforeMarketPkgUtil;
import cc.chinagps.gateway.unit.beforemarket.common.util.BeforeMarketTimeFormatUtil;
import cc.chinagps.gateway.unit.common.UnitCommon;
import cc.chinagps.gateway.unit.common.UploadUtil;
import cc.chinagps.gateway.util.Constants;
import cc.chinagps.gateway.util.HexUtil;
import cc.chinagps.gateway.util.SystemConfig;

public class Cmd01 implements UploadHandler{
	private Logger logger_debug = Logger.getLogger(LogManager.LOGGER_NAME_DEBUG);
	
	private int init_c1 =  0x73656778;
	private int init_d1 = 0x5354475A;
	
	public Cmd01(){
		init_c1 = Integer.valueOf(SystemConfig.getSystemProperty("hm_init_c1"));
		init_d1 = Integer.valueOf(SystemConfig.getSystemProperty("hm_init_d1"));
	}
	
	private void ackLoginFail(BeforeMarketPackage ppkg, UnitSocketSession session, short result) throws ParseException{
		byte[] rbody = new byte[13];
		rbody[0] = 0x1;
		
		byte[] bs_result = Util.getShortByte(result);
		System.arraycopy(bs_result, 0, rbody, 1, 2);
		
		byte[] b_d1 = Util.getIntByte(init_d1);
		System.arraycopy(b_d1, 0, rbody, 3, 4);
		
		byte[] time = BeforeMarketTimeFormatUtil.getCurrentTimeBCD();
		System.arraycopy(time, 0, rbody, 7, 6);
		
		BeforeMarketPackage rpkg = new BeforeMarketPackage();
		rpkg.setHead(ppkg.getHead());
		rpkg.setBody(rbody);
		
		byte[] res = rpkg.encode(init_c1, init_d1);
		session.sendData(res);
	}
	
	@Override
	public boolean handlerPackage(BeforeMarketPackage ppkg, UnitServer server,
			UnitSocketSession session) throws Exception {
		byte[] body = ppkg.getBody();
		byte sub_cmdId = body[0];
		
		if((sub_cmdId & 0xFF) == 0x1){
			//��¼
			String imei = ppkg.getHead().getTerminalId();
			Object objCall = MemcacheManager.getMemcachedClient().get(MemcacheManager.IMEI_CALLLETTER_KEY_HEAD + imei);
			
			if(objCall == null){
				ackLoginFail(ppkg, session, (short) 0xFFFF);
				
				if(logger_debug.isDebugEnabled()){
					StringBuilder logInfo = new StringBuilder();
					logInfo.append("��̨��¼ʧ��(ydw), imei:").append(imei);
					logInfo.append(", Դ��:").append(HexUtil.byteToHexStr(ppkg.getSource()));
					logger_debug.debug(logInfo.toString());
				}
				
				return true;
			}
			
			String callLetter = objCall.toString();			
			UnitSocketSession existSession = server.searchUnitByCallLetter(callLetter);
			if(existSession != null && existSession != session){
				existSession.close();
			}
			
			int c1 = Util.getInt(body, 31);
			//����session��Ϣ
			boolean isNew;
			if(session.getUnitInfo() != null){
				isNew = false;
				String oldCall = session.getUnitInfo().getCallLetter();
				
				session.getUnitInfo().setCallLetter(callLetter);
				session.getUnitInfo().setIMEI(imei);
				
				server.getSessionManager().removeCallSession(oldCall);
				server.getSessionManager().addCallSession(callLetter, session);
			}else{
				isNew = true;
				UnitInfo unitInfo = new UnitInfo();
				unitInfo.setCallLetter(callLetter);
				unitInfo.setIMEI(imei);
				session.setUnitInfo(unitInfo);
				session.setLoginTime(System.currentTimeMillis() + 1);	//��ֹkey�ظ� +1 ms
				
				server.getSessionManager().addCallSession(callLetter, session);
			}
			
			//Trace����(����)
			UnitCommon.sendUploadTrace(ppkg.getSource(), server, session);
			//��¼��¼��Ϣ��¼
			Loginout loginout = new Loginout();
			loginout.setIsLogin(isNew? Loginout.LOGIN: Loginout.CHANGE_LOGIN);
			//loginout.setLoginTime(isNew? session.getCreateTime(): System.currentTimeMillis());
			loginout.setLoginTime(session.getLoginTime());
			
			loginout.setCallLetter(callLetter);
			UploadUtil.handleLoginout(server, session, loginout);
			
			//�ظ�(��¼�ظ����ܵ�C1, D1�ó�ʼ��C1, D1)
			byte[] rbody = new byte[13];
			rbody[0] = 0x1;
			int d1 = BeforeMarketPkgUtil.getRandomD1();
			
			byte[] b_d1 = Util.getIntByte(d1);
			System.arraycopy(b_d1, 0, rbody, 3, 4);
			
			byte[] time = BeforeMarketTimeFormatUtil.getCurrentTimeBCD();
			System.arraycopy(time, 0, rbody, 7, 6);
			
			BeforeMarketPackage rpkg = new BeforeMarketPackage();
			rpkg.setHead(ppkg.getHead());
			rpkg.setBody(rbody);
			
			byte[] res = rpkg.encode(init_c1, init_d1);
			session.sendData(res);
			
			session.setAttribute(BeforeMarketConstants.BM_SESSION_KEY_ENCRYPT_C1, c1);
			session.setAttribute(BeforeMarketConstants.BM_SESSION_KEY_ENCRYPT_D1, d1);
				
			//֪ͨ�ڲ��ͻ���
			byte[] bodyData = InnerDataBuff.Unit.newBuilder().setCallLetter(callLetter).build().toByteArray();
			server.getSeatServer().getTCPServer().broadcastPackage(false, false, false,
					Constants.INNER_CMD_ID_VEHICLE_ONLINE_NOTIFY, SegPackageUtil.getSerialNumber(),
					bodyData, UnitCommon.unitLoginChangeFilter);
			
			//����memcache
			//����������������routing
			if(!Constants.IS_UPDATE_SERVER){
				long now = System.currentTimeMillis();
				Date expDate = new Date(now + MemcacheManager.ROUTING_EXPIRED);
				boolean success = MemcacheManager.getMemcachedClient().set(MemcacheManager.ROUTING_KEY_HEAD + callLetter, Constants.SYSTEM_ID, expDate);
				if(success){
					session.getUnitInfo().setLastUpdateRouteTime(now);
				}
			}
			
			if(logger_debug.isDebugEnabled()){
				StringBuilder logInfo = new StringBuilder();
				logInfo.append("��̨��¼, ����:").append(callLetter);
				logInfo.append(", Դ��:").append(HexUtil.byteToHexStr(ppkg.getSource()));
				logger_debug.debug(logInfo.toString());
			}
			
			return true;
		}else if((sub_cmdId & 0xFF) == 0x2){
			//��·���
			BeforeMarketPackage rpkg = new BeforeMarketPackage();
			rpkg.setHead(ppkg.getHead());
			rpkg.setBody(body);
			
			int c1 = BeforeMarketPkgUtil.getC1(session);
			int d1 = BeforeMarketPkgUtil.getD1(session);
			byte[] source = rpkg.encode(c1, d1);
			
			session.sendData(source);
			return true;
		}else if((sub_cmdId & 0xFF) == 0x3){
			//��¼	
			session.close();
			return true;
		}
		
		return false;
	}
}