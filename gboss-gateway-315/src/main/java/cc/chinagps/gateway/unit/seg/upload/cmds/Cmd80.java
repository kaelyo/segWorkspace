package cc.chinagps.gateway.unit.seg.upload.cmds;

import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.seg.lib.util.SegPackageUtil;

import cc.chinagps.gateway.buff.InnerDataBuff;
import cc.chinagps.gateway.log.LogManager;
import cc.chinagps.gateway.memcache.MemcacheManager;
import cc.chinagps.gateway.unit.UnitSocketSession;
import cc.chinagps.gateway.unit.UnitServer;
import cc.chinagps.gateway.unit.beans.Loginout;
import cc.chinagps.gateway.unit.beans.UnitInfo;
import cc.chinagps.gateway.unit.common.UnitCommon;
import cc.chinagps.gateway.unit.common.UploadUtil;
import cc.chinagps.gateway.unit.seg.pkg.SegPackage;
import cc.chinagps.gateway.unit.seg.util.SegPkgUtil;
import cc.chinagps.gateway.util.Constants;
import cc.chinagps.gateway.util.SystemConfig;
import cc.chinagps.gateway.util.HexUtil;

/**
 * ��¼
 */
public class Cmd80 extends BaseUploadHandler{
	//private String serverId;
	//private MemcachedClient client;
	private static final byte GPRS_MODE = 0x30;
	private static final byte OTHER_MODE = 0x33;
	
	private byte defaultMode;
	
	public Cmd80(){
		defaultMode = Byte.valueOf(SystemConfig.getSystemProperty("unit_default_mode"));
		//serverId = Constants.SYSTEM_ID;
		//client = MemcacheManager.getMemcachedClient();
	}
	
	private static void checkCallLetter(byte[] call) throws IOException{
		//���ֳ���¼���ĺ���������
		//������,����������µ�¼(�����ַ�: 0 - 9)
		for(int i = 0; i < call.length; i++){
			if(call[i] < '0' || call[i] > '9'){
				throw new IOException("error callLetter!");
			}
		}
	}
	
	private Logger logger_debug = Logger.getLogger(LogManager.LOGGER_NAME_DEBUG);
	
	@Override
	public boolean handlerPackage(SegPackage pkg, UnitServer server,
			UnitSocketSession session) throws Exception{
		byte[] callBytes = pkg.getBody();
		checkCallLetter(callBytes);
		
		String callLetter = new String(callBytes, SegPkgUtil.PKG_CHAR_ENCODING);
		//�жϺ����Ƿ��ظ�
		UnitSocketSession existSession = server.searchUnitByCallLetter(callLetter);
		if(existSession != null && existSession != session){
			existSession.close();
		}
		
		byte mode = defaultMode;
		
		//��memcacheȡ��������
		/*Object baseInfo = MemcacheManager.getMemcachedClient().get(MemcacheManager.BASE_INFO_KEY_HEAD + callLetter);
		if(baseInfo != null){
			try{
				mode = Byte.valueOf(baseInfo.toString());
				if(mode != GPRS_MODE){
					mode = OTHER_MODE;
				}
			}catch (Throwable e) {
				server.exceptionCaught(e);
			}
		}*/
		Object baseInfo = MemcacheManager.getMemcachedClient().get(MemcacheManager.BASE_INFO_KEY_HEAD + callLetter);
		if(baseInfo != null){
			try{
				String[] params = baseInfo.toString().split(",", -1);
				mode = Byte.valueOf(params[2]);
				if(mode != GPRS_MODE){
					mode = OTHER_MODE;
				}
			}catch (Throwable e) {
				server.exceptionCaught(e);
			}
		}
		
		//����session��Ϣ
		boolean isNew;
		if(session.getUnitInfo() != null){
			isNew = false;
			String oldCall = session.getUnitInfo().getCallLetter();
			
			session.getUnitInfo().setCallLetter(callLetter);
			
			server.getSessionManager().removeCallSession(oldCall);
			server.getSessionManager().addCallSession(callLetter, session);
		}else{
			isNew = true;
			UnitInfo unitInfo = new UnitInfo();
			unitInfo.setCallLetter(callLetter);
			session.setUnitInfo(unitInfo);
			session.setLoginTime(System.currentTimeMillis() + 1);	//��ֹkey�ظ� +1 ms
			
			server.getSessionManager().addCallSession(callLetter, session);
		}
		
		//Trace����(����)
		UnitCommon.sendUploadTrace(pkg.getSource(), server, session);
		//��¼��¼��Ϣ��¼
		Loginout loginout = new Loginout();
		loginout.setIsLogin(isNew? Loginout.LOGIN: Loginout.CHANGE_LOGIN);
		//loginout.setLoginTime(isNew? session.getCreateTime(): System.currentTimeMillis());
		loginout.setLoginTime(session.getLoginTime());
		
		loginout.setCallLetter(callLetter);
		UploadUtil.handleLoginout(server, session, loginout);
		
		//�ظ�
		byte cmdId = (byte) 0x00;
		byte[] resBody = new byte[pkg.getBody().length + 1];
		resBody[0] = mode;
		System.arraycopy(pkg.getBody(), 0, resBody, 1, pkg.getBody().length);
		byte[] res = SegPkgUtil.encode(cmdId, pkg.getSerialNumberBytes(), resBody);
		session.sendData(res);
			
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
			logInfo.append(", Դ��:").append(HexUtil.byteToHexStr(pkg.getSource()));
			logger_debug.debug(logInfo.toString());
		}
		
		return true;
	}
}