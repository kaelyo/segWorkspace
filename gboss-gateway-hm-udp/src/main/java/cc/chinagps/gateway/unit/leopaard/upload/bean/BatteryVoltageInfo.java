package cc.chinagps.gateway.unit.leopaard.upload.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *�������ص�ѹ��Ϣ
 */
public class BatteryVoltageInfo {
	private int batteryNum;//������������
	private int batteryPackageNum;//�������ذ�����
	private List<BatteryUnitVoltageInfo> batteryUnitVoltageInfos = new ArrayList<BatteryUnitVoltageInfo>();//ÿ�����ص�ѹ��Ϣ�б� 
	
	public int getBatteryNum() {
		return batteryNum;
	}

	public void setBatteryNum(int batteryNum) {
		this.batteryNum = batteryNum;
	}

	public int getBatteryPackageNum() {
		return batteryPackageNum;
	}

	public void setBatteryPackageNum(int batteryPackageNum) {
		this.batteryPackageNum = batteryPackageNum;
	}

	public List<BatteryUnitVoltageInfo> getBatteryUnitVoltageInfos() {
		return batteryUnitVoltageInfos;
	}

	public void setBatteryUnitVoltageInfos(List<BatteryUnitVoltageInfo> batteryUnitVoltageInfos) {
		this.batteryUnitVoltageInfos = batteryUnitVoltageInfos;
	}

}
