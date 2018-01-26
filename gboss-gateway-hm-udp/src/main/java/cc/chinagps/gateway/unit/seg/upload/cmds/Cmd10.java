package cc.chinagps.gateway.unit.seg.upload.cmds;

import cc.chinagps.gateway.buff.CommandBuff;
import cc.chinagps.gateway.buff.CommandBuff.Command;
import cc.chinagps.gateway.buff.CommandBuff.CommandResponse.Builder;
import cc.chinagps.gateway.seat.cmd.CmdResponseUtil;
import cc.chinagps.gateway.seat.cmd.CmdUtil;
import cc.chinagps.gateway.seat.cmd.ServerToUnitCommand;
import cc.chinagps.gateway.unit.UnitServer;
import cc.chinagps.gateway.unit.UnitSocketSession;
import cc.chinagps.gateway.unit.seg.pkg.SegPackage;
import cc.chinagps.gateway.unit.seg.upload.SegUploadUtil;
import cc.chinagps.gateway.unit.seg.util.SegPkgUtil;
import cc.chinagps.gateway.util.Constants;

/**
 * ������Ӧ��
 * ������Ӧ��
 * (*)��ʱ��������ȷ��
 * 
 * �ҳ�Ӧ��(OBD)
 * ��ȡ����Ӧ��(OBD)
 * �궨����Ӧ��(OBD)
 * ���������Ӧ��(OBD)
 */
public class Cmd10 extends CheckLoginHandler{
	@Override
	public boolean handlerPackageSessionChecked(SegPackage pkg, UnitServer server,
			UnitSocketSession session) throws Exception {
		String strBody = new String(pkg.getBody(), SegPkgUtil.PKG_CHAR_ENCODING);
		if(strBody.startsWith("(DCC")){
			//������Ӧ��
			Cmd90.handlerLockDoorAck(pkg, server, session, strBody);
			return true;
		}else if(strBody.startsWith("(DOC")){
			//������Ӧ��
			Cmd90.handlerOpenDoorAck(pkg, server, session, strBody);
			return true;
		}else if(strBody.startsWith("(FNS,ITV,FF") || strBody.startsWith("(FNS,ITV,00")){
			//����/�رն�ʱ����Ӧ��
			Cmd93.handlerUploadGPSByIntervalSetting(pkg, server, session, strBody);
			return true;
		}else if(strBody.startsWith("(FDC")){
			//�ҳ�Ӧ��(OBD)
			SegUploadUtil.commonResponseWithGPS(pkg, server, session, strBody, CmdUtil.CMD_ID_SEARCH_VEHICLE);
			return true;
		}else if(strBody.startsWith("(SAF,162,")){
			//��ȡ����Ӧ��(OBD)
			String callLetter = session.getUnitInfo().getCallLetter();
			String sn = CmdUtil.getCmdCacheSn(callLetter, CmdUtil.CMD_ID_READ_DEFINED_TYPE);
			ServerToUnitCommand cache = server.getServerToUnitCommandCache().getAndRemoveCommand(sn);
			if(cache != null) {
				Command usercmd = cache.getUserCommand();
				
				Builder builder = CommandBuff.CommandResponse.newBuilder();
				String resStatus = strBody.substring(9, 11);
				boolean success = "00".equals(resStatus);
				
				CmdResponseUtil.updateResponseProtoByUserCommand(builder, callLetter, usercmd, success? Constants.RESULT_SUCCESS: Constants.RESULT_UNIT_ACK_FAIL);				
				if(success){
					String resSn = strBody.substring(12, 36);
					String resVer = strBody.substring(36, 38);
					String resType = strBody.substring(38, 42);
					String resRes = strBody.substring(42, 44);
					String resSupDis = strBody.substring(44, 46);
					String resSupTPMS = strBody.substring(46, 48);
					
					builder.addParams(resSn);
					builder.addParams(resVer);
					builder.addParams(resType);
					builder.addParams(resRes);
					builder.addParams(resSupDis);
					builder.addParams(resSupTPMS);
				}
				
				//byte[] data = builder.build().toByteArray();
				CmdResponseUtil.simpleCommandResponse(cache, builder);
			}
			
			return true;
		}else if(strBody.startsWith("(SAF,164,")){
			//�궨����Ӧ��(OBD)
			String callLetter = session.getUnitInfo().getCallLetter();
			String sn = CmdUtil.getCmdCacheSn(callLetter, CmdUtil.CMD_ID_DEFINED_TYPE);
			ServerToUnitCommand cache = server.getServerToUnitCommandCache().getAndRemoveCommand(sn);
			if(cache != null) {
				Command usercmd = cache.getUserCommand();
				
				Builder builder = CommandBuff.CommandResponse.newBuilder();
				String resStatus = strBody.substring(9, 11);
				boolean success = "00".equals(resStatus);
				CmdResponseUtil.updateResponseProtoByUserCommand(builder, callLetter, usercmd, success? Constants.RESULT_SUCCESS: Constants.RESULT_UNIT_ACK_FAIL);				
				if(success){
					String resType = strBody.substring(12, 16);
					builder.addParams(resType);
				}
				
				//byte[] data = builder.build().toByteArray();
				CmdResponseUtil.simpleCommandResponse(cache, builder);
			}
			
			return true;
		}else if(strBody.startsWith("(SAF,141,")){
			//���������Ӧ��(OBD)
			String callLetter = session.getUnitInfo().getCallLetter();
			String sn = CmdUtil.getCmdCacheSn(callLetter, CmdUtil.CMD_ID_DEFINED_TYPE);
			ServerToUnitCommand cache = server.getServerToUnitCommandCache().getAndRemoveCommand(sn);
			if(cache != null) {
				Command usercmd = cache.getUserCommand();				
				Builder builder = CommandBuff.CommandResponse.newBuilder();
				String resStatus = strBody.substring(9, 11);				
				CmdResponseUtil.updateResponseProtoByUserCommand(builder, callLetter, usercmd, "00".equals(resStatus)? Constants.RESULT_SUCCESS: Constants.RESULT_UNIT_ACK_FAIL);
				
				//byte[] data = builder.build().toByteArray();
				CmdResponseUtil.simpleCommandResponse(cache, builder);
			}
			
			return true;
		}else if(strBody.startsWith("(OPO")){
			//�ر�ά��ģʽӦ��(OBD)
			SegUploadUtil.commonResponseWithGPS(pkg, server, session, strBody, CmdUtil.CMD_ID_CLOSE_REPAIR_MODE);
			return true;
		}else if(strBody.startsWith("(CLO")){
			//����ά��ģʽӦ��(OBD)
			SegUploadUtil.commonResponseWithGPS(pkg, server, session, strBody, CmdUtil.CMD_ID_OPEN_REPAIR_MODE);
			return true;
		}
		
		return false;
	}
}