package cc.chinagps.gateway.unit.beforemarket.kaiyi.util;

import java.util.List;

import cc.chinagps.gateway.buff.GBossDataBuff;
import cc.chinagps.gateway.buff.GBossDataBuff.FaultInfo;
import cc.chinagps.gateway.buff.GBossDataBuff.GpsBaseInfo;
import cc.chinagps.gateway.buff.GBossDataBuff.OBDInfo;
import cc.chinagps.gateway.buff.GBossDataBuff.TravelInfo;
import cc.chinagps.gateway.buff.GBossDataBuff.UnitVersion;
import cc.chinagps.gateway.unit.beforemarket.common.pkg.BeforeMarketPackage;
import cc.chinagps.gateway.unit.beforemarket.kaiyi.upload.beans.KaiyiFaultInfo;
import cc.chinagps.gateway.unit.beforemarket.kaiyi.upload.beans.KaiyiGPSInfo;
import cc.chinagps.gateway.unit.beforemarket.kaiyi.upload.beans.KaiyiOBDInfo;
import cc.chinagps.gateway.unit.beforemarket.kaiyi.upload.beans.KaiyiStatus;
import cc.chinagps.gateway.unit.beforemarket.kaiyi.upload.beans.KaiyiTravelInfo;
import cc.chinagps.gateway.util.UnitProtocolTypes;

import com.google.protobuf.ByteString;

public class KaiyiProtobufUtil {
	/**
	 * gpsInfoתprotobuf gpsInfo
	 */
	public static cc.chinagps.gateway.buff.GBossDataBuff.GpsInfo transformGPSInfo(String callLetter, BeforeMarketPackage pkg, 
			KaiyiGPSInfo gps, KaiyiStatus status, KaiyiOBDInfo obdInfo, boolean isHistory){
		cc.chinagps.gateway.buff.GBossDataBuff.GpsInfo.Builder builder = GBossDataBuff.GpsInfo.newBuilder();
		builder.setCallLetter(callLetter);
		
		cc.chinagps.gateway.buff.GBossDataBuff.GpsBaseInfo baseBuilder = transGPSBaseInfo(callLetter, gps, status, obdInfo, isHistory);
		builder.setBaseInfo(baseBuilder);
		builder.setContent(ByteString.copyFrom(pkg.getSource()));
		builder.setHistory(isHistory? 1: 0);
		
		return builder.build();
	}
	
	/**
	 * alarmInfoתprotobuf alarmInfo
	 */
	public static cc.chinagps.gateway.buff.GBossDataBuff.AlarmInfo transformAlarmInfo(String callLetter, BeforeMarketPackage pkg, 
			KaiyiGPSInfo gps, KaiyiStatus status, KaiyiOBDInfo obdInfo, boolean isHistory, int trigger){
		cc.chinagps.gateway.buff.GBossDataBuff.AlarmInfo.Builder builder = GBossDataBuff.AlarmInfo.newBuilder();
		builder.setCallLetter(callLetter);
		
		cc.chinagps.gateway.buff.GBossDataBuff.GpsBaseInfo baseBuilder = transGPSBaseInfo(callLetter, gps, status, obdInfo, isHistory);
		builder.setBaseInfo(baseBuilder);
		builder.setContent(ByteString.copyFrom(pkg.getSource()));
		builder.setHistory(isHistory? 1: 0);
		builder.setUnittype(UnitProtocolTypes.UNIT_PROTOCOL_TYPE_KAIYI);
		builder.setTrigger(trigger);
		
		return builder.build();
	}
	
	/**
	 * gpsInfoתprotobuf gpsBaseInfo
	 */
	private static cc.chinagps.gateway.buff.GBossDataBuff.GpsBaseInfo transGPSBaseInfo(String callLetter, 
			KaiyiGPSInfo gps, KaiyiStatus status, KaiyiOBDInfo obdInfo, boolean isHistory){
		cc.chinagps.gateway.buff.GBossDataBuff.GpsBaseInfo.Builder baseBuilder = GBossDataBuff.GpsBaseInfo.newBuilder();
		baseBuilder.setGpsTime(gps.getGpsTime().getTime());
		baseBuilder.setLoc(gps.isLoc());
		baseBuilder.setLat((int) (gps.getLat() * 1000000));
		baseBuilder.setLng((int) (gps.getLng() * 1000000));
		baseBuilder.setSpeed(gps.getSpeed());
		baseBuilder.setCourse(gps.getCourse());
		
		if(status != null){
			baseBuilder.addAllStatus(status.getStatusList());
		}
		
		if(obdInfo != null){
			OBDInfo bobd = transOBDInfo(callLetter, gps.getGpsTime().getTime(), obdInfo, isHistory);
			baseBuilder.setObdInfo(bobd);
			
			if(obdInfo.getSignal() != null){
				baseBuilder.setSignal(obdInfo.getSignal());
			}
		}
		
		return baseBuilder.build();
	}
	
	/**
	 * OBDInfoתprotobuf OBDInfo
	 */
	public static cc.chinagps.gateway.buff.GBossDataBuff.OBDInfo transOBDInfo(String callLetter, long gpsTime, KaiyiOBDInfo obdInfo, boolean isHistory){
		cc.chinagps.gateway.buff.GBossDataBuff.OBDInfo.Builder obdBuilder = OBDInfo.newBuilder();
		obdBuilder.setHourOil(obdInfo.getHourOil() * 10);
		obdBuilder.setAverageOil(obdInfo.getAverageOil() * 10);
		obdBuilder.setTotalDistance(obdInfo.getTotalDistance() * 100);
		
		obdBuilder.setRemainOil(obdInfo.getRemainOil() * 10);

		obdBuilder.setWaterTemperature(obdInfo.getWaterTemperature());
		obdBuilder.setReviseOil(obdInfo.getReviseOil() * 10);
		obdBuilder.setRotationSpeed(obdInfo.getRotationSpeed());
		obdBuilder.setIntakeAirTemperature(obdInfo.getIntakeAirTemperature());
		obdBuilder.setSpeed(obdInfo.getSpeed());
		obdBuilder.setRemainDistance(obdInfo.getRemainDistance() * 100);
		
		obdBuilder.setCallLetter(callLetter);
		obdBuilder.setGpsTime(gpsTime);
		obdBuilder.setUnitType(UnitProtocolTypes.UNIT_PROTOCOL_TYPE_KAIYI);
		obdBuilder.setHistory(isHistory? 1: 0);
		
		return obdBuilder.build();
	}
	
	/**
	 * travelInfoתprotobuf
	 */
	public static TravelInfo transformTravelInfo(String callLetter, KaiyiTravelInfo travelInfo, 
			KaiyiGPSInfo endGPS, KaiyiStatus endStatus, boolean isHistory){
		cc.chinagps.gateway.buff.GBossDataBuff.TravelInfo.Builder builder =  cc.chinagps.gateway.buff.GBossDataBuff.TravelInfo.newBuilder();
		KaiyiGPSInfo startGPS = travelInfo.getStartGPS();
		
		builder.setCallLetter(callLetter);
		builder.setStartTime(startGPS.getGpsTime().getTime());
		builder.setEndTime(endGPS.getGpsTime().getTime());
		builder.setDistance(travelInfo.getDistance() * 100);
		builder.setMaxSpeed(travelInfo.getMaxSpeed());
		builder.setOverSpeedTime(travelInfo.getOverSpeedTime());
		builder.setQuickBrakeCount(travelInfo.getQuickBrakeCount());
		//builder.setEmergencyBrakeCount(0);		//��
		builder.setQuickSpeedUpCount(travelInfo.getQuickSpeedUpCount());
		//builder.setEmergencySpeedUpCount(0);		//��
		builder.setAverageSpeed(travelInfo.getAverageSpeed());
		builder.setMaxWaterTemperature(travelInfo.getMaxWaterTemperature());
		builder.setMaxRotationSpeed(travelInfo.getMaxRotationSpeed());
		builder.setVoltage(travelInfo.getVoltage());
		builder.setTotalOil(travelInfo.getTotalOil());
		builder.setAverageOil(travelInfo.getAverageOil());
		//builder.setTiredDrivingTime(0);						//��
		//builder.setAverageRotationSpeed(0);					//��
		//builder.setMaxOil(0);									//��
		//builder.setIdleTime(0);								//��
		
		GpsBaseInfo startGPSBase = transGPSBaseInfo(callLetter, startGPS, null, null, isHistory);
		builder.setStartGps(startGPSBase);
		
		GpsBaseInfo endGPSBase = transGPSBaseInfo(callLetter, endGPS, endStatus, null, isHistory);
		builder.setEndGps(endGPSBase);
		
		builder.setHistory(isHistory? 1: 0);

		return builder.build();
	}
	
	/**
	 * FaultInfoתprotobuf
	 */
	public static FaultInfo transformFaultInfo(String callLetter, KaiyiFaultInfo faultInfo, long gpsTime, boolean isHistory){
		cc.chinagps.gateway.buff.GBossDataBuff.FaultInfo.Builder builder = cc.chinagps.gateway.buff.GBossDataBuff.FaultInfo.newBuilder();
		builder.setCallLetter(callLetter);
		builder.setFaultTime(gpsTime);
		builder.setUnitType(UnitProtocolTypes.UNIT_PROTOCOL_TYPE_KAIYI);
		builder.setHistory(isHistory? 1: 0);
		
		List<String> list = faultInfo.getFaults();
		if(list != null && list.size() > 0){
			cc.chinagps.gateway.buff.GBossDataBuff.FaultDefine.Builder fdBuilder = cc.chinagps.gateway.buff.GBossDataBuff.FaultDefine.newBuilder();
			fdBuilder.addAllFaultCode(list);
			
			builder.addFaults(fdBuilder);
		}
		
		return builder.build();
	}
	
	/**
	 * versionתprotobuf
	 */
	public static UnitVersion transformVersion(String callLetter, String version, int result){
		cc.chinagps.gateway.buff.GBossDataBuff.UnitVersion.Builder builder = cc.chinagps.gateway.buff.GBossDataBuff.UnitVersion.newBuilder();
		builder.setCallLetter(callLetter);
		builder.setVersion(version);
		builder.setResult(result);
		
		return builder.build();
	}
}