package cc.chinagps.gateway.unit.beforemarket.kaiyi.upload;

import cc.chinagps.gateway.buff.CommandBuff;
import cc.chinagps.gateway.buff.CommandBuff.Command;
import cc.chinagps.gateway.buff.CommandBuff.CommandResponse.Builder;
import cc.chinagps.gateway.buff.GBossDataBuff.AlarmInfo;
import cc.chinagps.gateway.buff.GBossDataBuff.FaultInfo;
import cc.chinagps.gateway.buff.GBossDataBuff.GpsBaseInfo;
import cc.chinagps.gateway.buff.GBossDataBuff.GpsInfo;
import cc.chinagps.gateway.buff.GBossDataBuff.OBDInfo;
import cc.chinagps.gateway.buff.GBossDataBuff.TravelInfo;
import cc.chinagps.gateway.buff.GBossDataBuff.UnitVersion;
import cc.chinagps.gateway.seat.cmd.CmdResponseUtil;
import cc.chinagps.gateway.seat.cmd.ServerToUnitCommand;
import cc.chinagps.gateway.unit.UnitServer;
import cc.chinagps.gateway.unit.UnitSocketSession;
import cc.chinagps.gateway.unit.beforemarket.common.pkg.BeforeMarketPackage;
import cc.chinagps.gateway.unit.beforemarket.common.util.BeforeMarketPkgUtil;
import cc.chinagps.gateway.unit.beforemarket.kaiyi.upload.beans.KaiyiFaultInfo;
import cc.chinagps.gateway.unit.beforemarket.kaiyi.upload.beans.KaiyiGPSInfo;
import cc.chinagps.gateway.unit.beforemarket.kaiyi.upload.beans.KaiyiOBDInfo;
import cc.chinagps.gateway.unit.beforemarket.kaiyi.upload.beans.KaiyiStatus;
import cc.chinagps.gateway.unit.beforemarket.kaiyi.upload.beans.KaiyiTravelInfo;
import cc.chinagps.gateway.unit.beforemarket.kaiyi.util.KaiyiProtobufUtil;
import cc.chinagps.gateway.util.Constants;

public class KaiyiUploadUtil {
	/**
	 * ����̨�ϴ���GPS��Ϣ
	 */
	public static void handleGPS(BeforeMarketPackage pkg, UnitServer server,
			UnitSocketSession session, KaiyiGPSInfo gps, KaiyiStatus status, 
			KaiyiOBDInfo obdInfo, boolean isHistory){
		String callLetter = session.getUnitInfo().getCallLetter();
		GpsInfo gpsInfo = KaiyiProtobufUtil.transformGPSInfo(callLetter, pkg, gps, 
				status, obdInfo, isHistory);

		handleGPS(pkg, server, session, gpsInfo);
	}
	
	/**
	 * ����̨�ϴ���GPS��Ϣ
	 */
	public static void handleGPS(BeforeMarketPackage pkg, UnitServer server,
			UnitSocketSession session, GpsInfo gpsInfo){
		String callLetter = session.getUnitInfo().getCallLetter();
		GpsBaseInfo gpsBase = gpsInfo.getBaseInfo();
		
		server.getExportMQ().addGPS(gpsInfo);
		server.getExportHBase().addGPS(callLetter, gpsBase, pkg.getSource());
	}
	
	public static int handleAlarm(BeforeMarketPackage pkg, UnitServer server,
			UnitSocketSession session, KaiyiGPSInfo gps, KaiyiStatus status,
			KaiyiOBDInfo obdInfo, boolean isHistory, int trigger){
		int result = 0;
		String callLetter = session.getUnitInfo().getCallLetter();
		AlarmInfo alarmInfo = KaiyiProtobufUtil.transformAlarmInfo(callLetter, pkg, gps, status, 
				obdInfo, isHistory, trigger);
		//���鴦��
		if(status != null && status.isSpecialAlarm()){
			//һ�ྯ��
			boolean success = server.getExportMQ().addSpecialAlarm(alarmInfo);
			if(!success){
				result = 0xFFFF;
			}
		}else{
			//����
			server.getExportMQ().addCommonAlarm(alarmInfo);
		}
		
		return result;
	}
	
	/**
	 * ����̨�ϴ���OBD��Ϣ(������Ϣ)
	 */
	public static void handleOBD(UnitServer server, String callLetter, long gpsTime, KaiyiOBDInfo hmOBDInfo, boolean isHistory){
		OBDInfo obdInfo = KaiyiProtobufUtil.transOBDInfo(callLetter, gpsTime, hmOBDInfo, isHistory);
		server.getExportMQ().addOBDInfo(obdInfo);
	}
	
	/**
	 * �����г̼�¼��Ϣ(OBD)
	 */
	public static void handleTravelInfo(UnitServer server, String callLetter, KaiyiTravelInfo travelInfo, 
			KaiyiGPSInfo endGPS, KaiyiStatus endStatus, boolean isHistory){
		TravelInfo value = KaiyiProtobufUtil.transformTravelInfo(callLetter, travelInfo, endGPS, endStatus, isHistory);
		server.getExportMQ().addTravelInfo(value);
	}
	
	/**
	 * ����ģ�������Ϣ(OBD)
	 */
	public static void handleFaultInfo(UnitServer server, String callLetter, KaiyiFaultInfo faultInfo, long gpsTime, boolean isHistory){
		FaultInfo value = KaiyiProtobufUtil.transformFaultInfo(callLetter, faultInfo, gpsTime, isHistory);
		server.getExportMQ().addFaultInfo(value);
	}
	
	/**
	 * ����汾����
	 */
	public static void handleVersion(UnitServer server, String callLetter, String version, int result){
		UnitVersion pversion = KaiyiProtobufUtil.transformVersion(callLetter, version, result);
		server.getExportMQ().addUnitVersion(pversion);
	}
	
	/**
	 * commonResponseUseHeadSN
	 */
	public static void commonResponseUseHeadSN(BeforeMarketPackage ppkg,
			UnitServer server, UnitSocketSession session, int unit_ack_result, int innerCmdId){
		String callLetter = session.getUnitInfo().getCallLetter();
		String cacheSN = BeforeMarketPkgUtil.getCmdCacheSn(callLetter, ppkg.getHead().getSn());
		ServerToUnitCommand cache = server.getServerToUnitCommandCache().getAndRemoveCommand(cacheSN);
		if(cache != null){
			Command usercmd = cache.getUserCommand();
			Builder builder = CommandBuff.CommandResponse.newBuilder();
			builder.setSn(usercmd.getSn());
			builder.setCallLetter(callLetter);
			builder.setCmdId(usercmd.getCmdId());
			if(unit_ack_result == 0){
				builder.setResult(Constants.RESULT_SUCCESS);
			}else{
				builder.setResult(Constants.RESULT_UNIT_ACK_FAIL);
				builder.addParams(String.valueOf(unit_ack_result));
			}
			
			//byte[] data = builder.build().toByteArray();
			CmdResponseUtil.simpleCommandResponse(cache, builder);
		}else{
			//δ�ҵ����棬�����ǻ��Ѻ������
			Builder builder = CommandBuff.CommandResponse.newBuilder();
			builder.setSn("");
			builder.setCallLetter(callLetter);
			builder.setCmdId(innerCmdId);
			
			if(unit_ack_result == 0){
				builder.setResult(Constants.RESULT_SUCCESS);
			}else{
				builder.setResult(Constants.RESULT_UNIT_ACK_FAIL);
				builder.addParams(String.valueOf(unit_ack_result));
			}
			
			builder.setUnitsn(ppkg.getHead().getSn() & 0xFFFF);
			//byte[] data = builder.build().toByteArray();
			server.getExportMQ().addCommandResponse(builder.build());
		}
	}
}