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
import cc.chinagps.gateway.unit.seg.util.SegPkgUtil;

/**
 * ����TCP/UDPͨ�Ų���Ӧ��
 * ��ѯTCP/UDPͨ�Ų���Ӧ��
 * �������緽ʽAPNӦ��
 * ����TCP/UDPͨ�Ų���Ӧ��(��APN)
 * TN����Э��(����������ƷЭ��)Ӧ��
 */
public class CmdC7 extends CheckLoginHandler{
	@Override
	public boolean handlerPackageSessionChecked(SegPackage pkg, UnitServer server,
			UnitSocketSession session) throws Exception {
		byte[] body = pkg.getBody();
		String strBody = new String(body, SegPkgUtil.PKG_CHAR_ENCODING);
		if(strBody.startsWith("(RPM,")){
			//��ѯ TCP/UDPͨ�Ų���Ӧ��
			String callLetter = session.getUnitInfo().getCallLetter();
			String sn = CmdUtil.getCmdCacheSn(callLetter, CmdUtil.CMD_ID_QUERY_TCP_UDP_PARAMS);
			ServerToUnitCommand cache = server.getServerToUnitCommandCache().getAndRemoveCommand(sn);
			if(cache != null) {
				responseQueryNetParam(cache, callLetter, strBody, 1);
			}
			
			return true;
		}else if(strBody.startsWith("(FNS,RPM,")){
			//����TCP/UDPͨ�Ų���Ӧ��
			//String sn = pkg.getSerialNumber();
			String callLetter = session.getUnitInfo().getCallLetter();
			String sn = CmdUtil.getCmdCacheSn(callLetter, CmdUtil.CMD_ID_SET_TCP_UDP_PARAMS);
			ServerToUnitCommand cache = server.getServerToUnitCommandCache().getAndRemoveCommand(sn);
			if(cache != null) {
				//responseSetOrQueryNetParam(cache, callLetter, strBody, 2);			
				CmdResponseUtil.simpleResponseSuccessByCachedCommand(cache);
			}
			
			return true;
		}else if(strBody.startsWith("(FNS,RPD,")){
			//�������緽ʽAPNӦ��
			//String sn = pkg.getSerialNumber();
			String callLetter = session.getUnitInfo().getCallLetter();
			String sn = CmdUtil.getCmdCacheSn(callLetter, CmdUtil.CMD_ID_SET_APN);
			ServerToUnitCommand cache = server.getServerToUnitCommandCache().getAndRemoveCommand(sn);
			if(cache != null) {
				Command usercmd = cache.getUserCommand();
				Builder builder = CommandBuff.CommandResponse.newBuilder();
				//ͨ�ò���
				CmdResponseUtil.updateResponseProtoSuccessByUserCommand(builder, callLetter, usercmd);
				
				String apn = strBody.substring(9, strBody.length() - 1);
				builder.addParams(apn);
				//byte[] data = builder.build().toByteArray();
				CmdResponseUtil.simpleCommandResponse(cache, builder);
			}
			
			return true;
		}else if(strBody.startsWith("(FNS,CGP,")){
			//����TCP/UDPͨ�Ų���Ӧ��(��APN)
			//String sn = pkg.getSerialNumber();
			String callLetter = session.getUnitInfo().getCallLetter();
			String sn = CmdUtil.getCmdCacheSn(callLetter, CmdUtil.CMD_ID_SET_TCP_UDP_APN_PARAMS);
			ServerToUnitCommand cache = server.getServerToUnitCommandCache().getAndRemoveCommand(sn);
			if(cache != null) {
//				Command usercmd = cache.getUserCommand();
//				String[] infos = strBody.split(",");
//				String apn = infos[1];
//				String call = infos[2].substring(3);
//				String sip = infos[3];
//				String serverPort = infos[4];
//				String localPort = infos[5];
//				String tcp_or_udp = infos[6];
//				String mode = infos[7];
//				String interval = infos[8];
//				
//				Builder builder = CommandBuff.CommandResponse.newBuilder();
//				//ͨ�ò���
//				CmdResponseUtil.updateResponseProtoSuccessByUserCommand(builder, callLetter, usercmd);
//				//params
//				builder.addParams(apn);
//				builder.addParams(call);
//				builder.addParams(SegPkgUtil.unitIPToUserIP(sip));
//				builder.addParams(serverPort);
//				builder.addParams(localPort);
//				builder.addParams(tcp_or_udp);
//				builder.addParams(mode);
//				
//				builder.addParams(Integer.valueOf(interval, 16).toString());
//				if(infos.length >= 11){
//					String userName = infos[9];
//					String password = infos[10];
//					
//					builder.addParams(userName);
//					builder.addParams(password);
//				}
//				
//				byte[] data = builder.build().toByteArray();
//				CmdResponseUtil.simpleCommandResponse(cache, data);
				
				CmdResponseUtil.simpleResponseSuccessByCachedCommand(cache);
			}
			
			return true;
		}else if(strBody.startsWith("(TNA,001,")){
			//TN����Э��(����������ƷЭ��)Ӧ��
			Cmd68.handlerTNAck(pkg, server, session, strBody);
			return true;
		}else if(strBody.startsWith("(FNS,TRG,")){
			//��ѯ�ӻ����Ӧ��
			String callLetter = session.getUnitInfo().getCallLetter();
			String sn = CmdUtil.getCmdCacheSn(callLetter, CmdUtil.CMD_ID_QUERY_TRG);
			ServerToUnitCommand cache = server.getServerToUnitCommandCache().getAndRemoveCommand(sn);
			if(cache != null) {
				Command usercmd = cache.getUserCommand();
				Builder builder = CommandBuff.CommandResponse.newBuilder();
				//ͨ�ò���
				CmdResponseUtil.updateResponseProtoSuccessByUserCommand(builder, callLetter, usercmd);
				
				String trgId = strBody.substring(9, strBody.length() - 1);
				builder.addParams(trgId);
				//byte[] data = builder.build().toByteArray();
				CmdResponseUtil.simpleCommandResponse(cache, builder);
			}
			return true;
		}else if(strBody.startsWith("(FNS,DEL")){
			//ɾ���ӻ����Ӧ��
			String callLetter = session.getUnitInfo().getCallLetter();
			String sn = CmdUtil.getCmdCacheSn(callLetter, CmdUtil.CMD_ID_DEL_TRG);
			ServerToUnitCommand cache = server.getServerToUnitCommandCache().getAndRemoveCommand(sn);
			if(cache != null) {
				Command usercmd = cache.getUserCommand();
				Builder builder = CommandBuff.CommandResponse.newBuilder();
				//ͨ�ò���
				CmdResponseUtil.updateResponseProtoSuccessByUserCommand(builder, callLetter, usercmd);
				
				String trgId = strBody.substring(8, strBody.length() - 1);
				builder.addParams(trgId);
				//byte[] data = builder.build().toByteArray();
				CmdResponseUtil.simpleCommandResponse(cache, builder);
			}
			return true;
		}
		
		return false;
	}
	
	/**
	 * ��ѯ TCP/UDPͨ�Ų���Ӧ��
	 */
	private void responseQueryNetParam(ServerToUnitCommand cache, String callLetter, String strBody, int startIndex){
		Command usercmd = cache.getUserCommand();
		String[] infos = strBody.substring(1, strBody.length() - 1).split(",");
		//String call = infos[startIndex];
		String sip = infos[startIndex + 1];
		String serverPort = infos[startIndex + 2];
		String localPort = infos[startIndex + 3];
		String tcp_or_udp = infos[startIndex + 4];
		String mode = infos[startIndex + 5];
		String interval = infos[startIndex + 6];
		
		Builder builder = CommandBuff.CommandResponse.newBuilder();
		//ͨ�ò���
		CmdResponseUtil.updateResponseProtoSuccessByUserCommand(builder, callLetter, usercmd);
		//params
		builder.addParams("");
		builder.addParams("");
		builder.addParams(SegPkgUtil.unitIPToUserIP(sip));
		builder.addParams(serverPort);
		builder.addParams(localPort);
		builder.addParams(tcp_or_udp);
		builder.addParams(mode);
		
		builder.addParams(Integer.valueOf(interval, 16).toString());
		if(infos.length >= startIndex + 8){
			String userName = infos[startIndex + 7];
			String password = infos[startIndex + 8];
			
			builder.addParams(userName);
			builder.addParams(password);
		}
		
		//byte[] data = builder.build().toByteArray();
		CmdResponseUtil.simpleCommandResponse(cache, builder);
	}
}