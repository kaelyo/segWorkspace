package cc.chinagps.gateway.unit.seg.upload.cmds;

import org.apache.log4j.Logger;

import cc.chinagps.gateway.buff.CommandBuff;
import cc.chinagps.gateway.buff.CommandBuff.Command;
import cc.chinagps.gateway.buff.CommandBuff.CommandResponse.Builder;
import cc.chinagps.gateway.log.LogManager;
import cc.chinagps.gateway.seat.cmd.CmdResponseUtil;
import cc.chinagps.gateway.seat.cmd.CmdUtil;
import cc.chinagps.gateway.seat.cmd.ServerToUnitCommand;
import cc.chinagps.gateway.unit.UnitServer;
import cc.chinagps.gateway.unit.UnitSocketSession;
import cc.chinagps.gateway.unit.seg.pkg.SegPackage;
import cc.chinagps.gateway.unit.seg.upload.SegUploadUtil;
import cc.chinagps.gateway.unit.seg.upload.beans.SegGPSInfo;
import cc.chinagps.gateway.unit.seg.util.SegPkgUtil;
import cc.chinagps.gateway.util.Constants;

public class CmdB4 extends CheckLoginHandler{
	private Logger logger_debug = Logger.getLogger(LogManager.LOGGER_NAME_DEBUG);
	@Override
	public boolean handlerPackageSessionChecked(SegPackage pkg, UnitServer server,
			UnitSocketSession session) throws Exception {
		byte[] body = pkg.getBody();
		String strBody = new String(body, SegPkgUtil.PKG_CHAR_ENCODING);
		int len = strBody.length();
		if(strBody.startsWith("(FNS,HUANCAR,")){
			String callLetter = session.getUnitInfo().getCallLetter();
			int cmdId = CmdUtil.CMD_ID_RETURN_CAR;
			String sn = CmdUtil.getCmdCacheSn(callLetter, cmdId);
			ServerToUnitCommand cache = server.getServerToUnitCommandCache().getAndRemoveCommand(sn);
			if(cache != null) {
				Command usercmd = cache.getUserCommand();
				
				Builder builder = CommandBuff.CommandResponse.newBuilder();
				String res = strBody.substring(13, 14);
				if ("0".equals(res))
					// ���û�Ӧ��ͨ�ò���
					CmdResponseUtil.updateResponseProtoSuccessByUserCommand(builder, callLetter, usercmd);
				else {
					if (res != null)
						CmdResponseUtil.updateResponseProtoByUserCommand(builder, callLetter, usercmd,
								Integer.valueOf(res));
					else
						CmdResponseUtil.updateResponseProtoFailedByUserCommand(builder, callLetter, usercmd);
				}
				if (len > 14) {
					SegGPSInfo gps = SegGPSInfo.parse(strBody, 14);
					logger_debug.debug("[seg][B4] CmdResponse gps:" + gps);
					SegUploadUtil.setUpResponseByGPSInfo(callLetter, builder, gps);
				}
				CmdResponseUtil.simpleCommandResponse(cache, builder);
				logger_debug.debug("[seg][B4] CmdResponse:"+builder.build());
			}
			
			return true;
		}
		
		return false;
	}
}