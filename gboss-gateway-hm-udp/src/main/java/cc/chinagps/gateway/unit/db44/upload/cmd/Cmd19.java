package cc.chinagps.gateway.unit.db44.upload.cmd;

import cc.chinagps.gateway.buff.CommandBuff;
import cc.chinagps.gateway.buff.CommandBuff.Command;
import cc.chinagps.gateway.buff.CommandBuff.CommandResponse.Builder;
import cc.chinagps.gateway.seat.cmd.CmdResponseUtil;
import cc.chinagps.gateway.seat.cmd.CmdUtil;
import cc.chinagps.gateway.seat.cmd.ServerToUnitCommand;
import cc.chinagps.gateway.unit.UnitServer;
import cc.chinagps.gateway.unit.UnitSocketSession;
import cc.chinagps.gateway.unit.db44.pkg.DB44Package;
import cc.chinagps.gateway.unit.db44.upload.DB44GPSInfo;
import cc.chinagps.gateway.unit.db44.upload.DB44UploadUtil;

/**
 * ���͵�/�ָ��͵� Ӧ��
 */
public class Cmd19 extends CheckLoginHandler{
	@Override
	public boolean handlerPackageSessionChecked(DB44Package pkg,
			UnitServer server, UnitSocketSession session) throws Exception {
		byte[] protocol = pkg.getProtocol();
		DB44GPSInfo gps = DB44GPSInfo.parse(protocol, 0);
		
		//���͵�/�ָ��͵�  
		byte result = protocol[30];
		boolean success = (result & 0x80) != 0;
		int cmdId;
		if((result & 1) != 0){
			//���͵�
			cmdId = CmdUtil.CMD_ID_CUT_OFF_OIL;
		}else{
			//�ָ��͵�  
			cmdId = CmdUtil.CMD_ID_RESUME_OIL;
		}
		
		String callLetter = session.getUnitInfo().getCallLetter();
		String sn = CmdUtil.getCmdCacheSn(callLetter, cmdId);
		ServerToUnitCommand cache = server.getServerToUnitCommandCache().getAndRemoveCommand(sn);
		if(cache != null) {
			Command usercmd = cache.getUserCommand();
			
			Builder builder = CommandBuff.CommandResponse.newBuilder();
			//���û�Ӧ��ͨ�ò���
			CmdResponseUtil.updateResponseProtoSuccessByUserCommand(builder, callLetter, usercmd);
			
			if(!success){
				builder.setResult(0);
			}
			//���� ��
			
			//byte[] data = builder.build().toByteArray();
			CmdResponseUtil.simpleCommandResponse(cache, builder);
		}
		
		DB44UploadUtil.handleGPS(pkg, server, session, gps);
		return true;
	}
}