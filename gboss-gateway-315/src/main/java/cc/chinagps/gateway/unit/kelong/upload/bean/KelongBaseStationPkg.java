package cc.chinagps.gateway.unit.kelong.upload.bean;

import java.util.ArrayList;

/**
 *@todo����¡�ն˻�վ�� 
 *@author��cj
 *@time��2017��5��25��
 *
 */
public class KelongBaseStationPkg {
	private KeLongBaseStationInfo keLongBaseStationInfo;
	private KeLongOBDInfo keLongOBDInfo;
	private byte status;
	private byte reserve;
		
	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public byte getReserve() {
		return reserve;
	}

	public void setReserve(byte reserve) {
		this.reserve = reserve;
	}

	public KeLongBaseStationInfo getKeLongBaseStationInfo() {
		return keLongBaseStationInfo;
	}

	public void setKeLongBaseStationInfo(KeLongBaseStationInfo keLongBaseStationInfo) {
		this.keLongBaseStationInfo = keLongBaseStationInfo;
	}

	public KeLongOBDInfo getKeLongOBDInfo() {
		return keLongOBDInfo;
	}

	public void setKeLongOBDInfo(KeLongOBDInfo keLongOBDInfo) {
		this.keLongOBDInfo = keLongOBDInfo;
	}

	/**
	 * @todo:������¡�ն˵Ļ�վ��:��վ������+obd��Ϣ
	 * @author:cj
	 * @param:
	 * @return:
	 * @remark:
	 */
	public static KelongBaseStationPkg parse(byte[] data, int position) throws Exception {
		KelongBaseStationPkg  kelongBaseStationPkg = new KelongBaseStationPkg();
		//һ.������վ������Ϣ
		KeLongBaseStationInfo baseStaion = new KeLongBaseStationInfo();
		baseStaion = KeLongBaseStationInfo.parse(data, position);
		//��.����OBD������Ϣ
		KeLongOBDInfo klObdInfo = new KeLongOBDInfo();
		position += baseStaion.getDataLen();
		klObdInfo = KeLongOBDInfo.parse(data, position);
		position += klObdInfo.getDataLen();
		kelongBaseStationPkg.setStatus(data[position++]);
		kelongBaseStationPkg.setReserve(data[position++]);
		
		kelongBaseStationPkg.setKeLongBaseStationInfo(baseStaion);
		kelongBaseStationPkg.setKeLongOBDInfo(klObdInfo);		
		return kelongBaseStationPkg;
	}
	
	/**
	 * @todo:���ݻ�վ����װgps��Ϣ��;
	 * @author:cj
	 * @param:
	 * @return:
	 * @remark:
	 */
	public static KeLongGPSInfo makeupGpsInfoPkg(KelongBaseStationPkg baseObdPkg){		
		KeLongBaseStationInfo baseStaion = baseObdPkg.getKeLongBaseStationInfo();
		KeLongOBDInfo klObdInfo = baseObdPkg.getKeLongOBDInfo();
		//��ʼ��ץ���¡Gps����
		KeLongGPSInfo keLongGPSInfo = new KeLongGPSInfo();
		keLongGPSInfo.setHistory(false);
		keLongGPSInfo.setGpsTime(baseStaion.getBaseStationTime());
		keLongGPSInfo.setGpsTimeStr(baseStaion.getBaseStationTimeStr());
		keLongGPSInfo.setLocStatus((byte)0);
		keLongGPSInfo.setLoc(false);//����λ		
		keLongGPSInfo.setSpeed(klObdInfo.getSpeed());
		
		ArrayList<Integer> statusList = new ArrayList<Integer>();
		byte status = baseObdPkg.getStatus();
		if((status&1)!=0){
			statusList.add(33);
		}else{
			statusList.add(23);
		}
		keLongGPSInfo.setStatus(statusList);//����״̬��Ϣ�����Ϩ��״̬
		
		keLongGPSInfo.setKeLongBaseStationInfo(baseStaion);
		keLongGPSInfo.setKeLongOBDInfo(klObdInfo);
		//keLongGPSInfo.setLat(lat);
		//keLongGPSInfo.setLat(lon);
		//keLongGPSInfo.setCourse(0);
		//keLongGPSInfo.setHeight(0);
		//keLongGPSInfo.setSatelliteCount(KeLongBaseStationInfo.getSatelliteCount(baseStaion));
		//keLongGPSInfo.setPdop(10);
		//keLongGPSInfo.setHdop(10);
		//keLongGPSInfo.setVdop(10);
		//keLongGPSInfo.setDistanceMode(0);
		//keLongGPSInfo.setLocationTime();

		return keLongGPSInfo;
	}
	
	@Override
	public String toString() {
		return "KelongBaseStationPkg [keLongBaseStationInfo=" + keLongBaseStationInfo + ", keLongOBDInfo="
				+ keLongOBDInfo + ", status=" + status + ", reserve=" + reserve + "]";
	}

	public static void main(String[] args) {
		KelongBaseStationPkg aa = new KelongBaseStationPkg();
		KeLongBaseStationInfo baseStationInfo =new KeLongBaseStationInfo();
		KeLongOBDInfo obdInfo = new KeLongOBDInfo();
		aa.setKeLongBaseStationInfo(baseStationInfo);
		aa.setKeLongOBDInfo(obdInfo);
		System.out.println(aa.toString());
		
	}
}
