package cc.chinagps.gateway.seat.cmd;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.seg.lib.net.server.tcp.SocketSession;
import org.seg.lib.util.SegPackageUtil;

import cc.chinagps.gateway.buff.CommandBuff;
import cc.chinagps.gateway.buff.CommandBuff.Command;
import cc.chinagps.gateway.buff.CommandBuff.CommandResponse;
import cc.chinagps.gateway.buff.CommandBuff.CommandResponse.Builder;
import cc.chinagps.gateway.unit.UnitServer;
import cc.chinagps.gateway.unit.UnitSocketSession;
import cc.chinagps.gateway.unit.udp.UdpServer;
import cc.chinagps.gateway.unit.useat.util.USeatUtil;
import cc.chinagps.gateway.util.Constants;

public class CmdResponseUtil {
	private static final SimpleDateFormat sdf_local = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * תΪ����ʱ��
	 */
	public static String formatToLocal(Date date){
		synchronized (sdf_local) {
			return sdf_local.format(date);
		}
	}
	
	/**
	 * ���õ�Ӧ��ɹ�
	 */
	public static void simpleResponseSuccessByCachedCommand(ServerToUnitCommand cache){
		Command usercmd = cache.getUserCommand();
		
		Builder builder = CommandBuff.CommandResponse.newBuilder();
		builder.setSn(usercmd.getSn());
		builder.setCallLetter(cache.getCallLetter());
		builder.setCmdId(usercmd.getCmdId());
		builder.setResult(Constants.RESULT_SUCCESS);
		
		//byte[] data = builder.build().toByteArray();
		CmdResponseUtil.simpleCommandResponse(cache, builder);
	}
	
	/**
	 * ����ָ���Ӧ��ͳ�ò���(sn. callLetter, cmdId, result)
	 */
	public static void updateResponseProtoSuccessByUserCommand(Builder builder, String callLetter, Command usercmd){
		updateResponseProtoByUserCommand(builder, callLetter, usercmd, Constants.RESULT_SUCCESS);
	}
	
	/**
	 * ����ָ���Ӧ��ͳ�ò���(sn. callLetter, cmdId, resultΪʧ��)
	 */
	public static void updateResponseProtoFailedByUserCommand(Builder builder, String callLetter, Command usercmd){
		updateResponseProtoByUserCommand(builder, callLetter, usercmd, Constants.RESULT_FAIL_OTHERS);
	}
	
	/**
	 * ����ָ���Ӧ��ͳ�ò���(sn. callLetter, cmdId, result)
	 */
	public static void updateResponseProtoByUserCommand(Builder builder, String callLetter, Command usercmd, int result){
		builder.setSn(usercmd.getSn());
		builder.setCallLetter(callLetter);
		builder.setCmdId(usercmd.getCmdId());
		builder.setResult(result);
	}
	
	/**
	 * �����û�ָ����Ϣ�����ظ��û��Ľ������Ϣ����ָ�������
	 */
	private static Builder getCommandResponse(String callLetter, CommandBuff.Command userCmd, int result, String info){
		Builder builder = CommandBuff.CommandResponse.newBuilder();
		builder.setSn(userCmd.getSn());
		builder.setCallLetter(callLetter);
		builder.setCmdId(userCmd.getCmdId());
		builder.setResult(result);
		
		if(info != null){
			//builder.addParams(info);
			builder.setMsg(info);
		}
		
		//return builder.build().toByteArray();
		return builder;
	}
	
	/**
	 * ��-ͨ�� ָ���Ӧ
	 * (����֮ǰ��Ӧ)
	 */
	public static void simpleCommandResponse(SocketSession userSession, UnitServer unitServer, String callLetter, CommandBuff.Command userCmd, int result, String info){
		//byte[] bodyData = getCommandResponse(callLetter, userCmd, result, info);
		Builder builder = getCommandResponse(callLetter, userCmd, result, info);
		if(userSession != null){
			//socket
			byte[] bodyData = builder.build().toByteArray();
			userSession.sendPackage(Constants.INNER_CMD_ID_RESPONSE, SegPackageUtil.getSerialNumber(), bodyData);
		}else{
			//mq
			unitServer.getExportMQ().addCommandResponse(builder.build());
		}
	}
	
	/**
	 * udp���
	 * ��-ͨ�� ָ���Ӧ
	 * (����֮ǰ��Ӧ)
	 */
	public static void simpleCommandResponse(SocketSession userSession, UdpServer udpServer, String callLetter, CommandBuff.Command userCmd, int result, String info){
		//byte[] bodyData = getCommandResponse(callLetter, userCmd, result, info);
		Builder builder = getCommandResponse(callLetter, userCmd, result, info);
		if(userSession != null){
			//socket
			byte[] bodyData = builder.build().toByteArray();
			userSession.sendPackage(Constants.INNER_CMD_ID_RESPONSE, SegPackageUtil.getSerialNumber(), bodyData);
		}else{
			//mq
			udpServer.getExportMQ().addCommandResponse(builder.build());
		}
	}
	
	/**
	 * ��-ͨ�� ָ���Ӧ
	 * (����֮ǰ��Ӧ)
	 * @throws IOException 
	 */
	public static void simpleCommandResponse(UnitSocketSession userSession, UnitServer unitServer, String callLetter, CommandBuff.Command userCmd, int result, String info) throws IOException{
		//byte[] bodyData = getCommandResponse(callLetter, userCmd, result, info);
		Builder builder = getCommandResponse(callLetter, userCmd, result, info);
		if(userSession != null){
			//socket
			byte[] bodyData = builder.build().toByteArray();
			byte[] data = USeatUtil.segSeatPackageEncoder.encode(false, false, false, Constants.SYSTEM_ID_INT, Constants.INNER_CMD_ID_RESPONSE, SegPackageUtil.getSerialNumber(), bodyData, null);
			userSession.sendData(data);
		}else{
			//mq
			unitServer.getExportMQ().addCommandResponse(builder.build());
		}
	}
	
	/**
	 * udp���
	 * ��-ͨ�� ָ���Ӧ
	 * (����֮ǰ��Ӧ)
	 * @throws IOException 
	 */
	public static void simpleCommandResponse(UnitSocketSession userSession, UdpServer udpServer, String callLetter, CommandBuff.Command userCmd, int result, String info) throws IOException{
		//byte[] bodyData = getCommandResponse(callLetter, userCmd, result, info);
		Builder builder = getCommandResponse(callLetter, userCmd, result, info);
		if(userSession != null){
			//socket
			byte[] bodyData = builder.build().toByteArray();
			byte[] data = USeatUtil.segSeatPackageEncoder.encode(false, false, false, Constants.SYSTEM_ID_INT, Constants.INNER_CMD_ID_RESPONSE, SegPackageUtil.getSerialNumber(), bodyData, null);
			userSession.sendData(data);
		}else{
			//mq
			udpServer.getExportMQ().addCommandResponse(builder.build());
		}
	}
	
	/**
	 * ��-ͨ�� ָ���Ӧ
	 * (����֮���Ӧ)
	 * @throws  
	 */
	public static void simpleCommandResponse(ServerToUnitCommand cache, CommandResponse.Builder builder){
		/*
		SocketSession userSession = cache.getUserSession();
		if(userSession == null){
			//��Ӧ��mq
			cache.getUnitServer().getExportMQ().addCommandResponse(data);
		}else{
			//��Ӧ��socket
			userSession.sendPackage(Constants.INNER_CMD_ID_RESPONSE, SegPackageUtil.getSerialNumber(), data);
		}*/
		SocketSession userSession = cache.getUserSession();
		if(userSession != null){
			byte[] data = builder.build().toByteArray();
			userSession.sendPackage(Constants.INNER_CMD_ID_RESPONSE, SegPackageUtil.getSerialNumber(), data);
			return;
		}
		
		UnitSocketSession useatSession = cache.getUSeatSession();
		if(useatSession != null){
			try {
				byte[] data = builder.build().toByteArray();
				byte[] pkg = USeatUtil.segSeatPackageEncoder.encode(false, false, false, Constants.SYSTEM_ID_INT, Constants.INNER_CMD_ID_RESPONSE, SegPackageUtil.getSerialNumber(), data, null);
				useatSession.sendData(pkg);
			} catch (IOException e) {
				cache.getUnitServer().exceptionCaught(e, "[I]");
			}
			
			return;
		}
		
		//��Ӧ��mq
		cache.getUnitServer().getExportMQ().addCommandResponse(builder.build());
	}
}