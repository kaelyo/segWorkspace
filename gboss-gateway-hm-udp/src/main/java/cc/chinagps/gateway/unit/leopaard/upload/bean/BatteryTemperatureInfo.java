package cc.chinagps.gateway.unit.leopaard.upload.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * ���������¶���Ϣ
 */
public class BatteryTemperatureInfo {
	private int temperatureProbeNum;// �������ذ��¶�̽������
	private int batteryPackageNum;// �������ذ�����
	private List<BatteryUnitTemperatureInfo> batteryUnitTemperatureInfos = new ArrayList<BatteryUnitTemperatureInfo>(); // ÿ�������¶���Ϣ�б�

	public int getTemperatureProbeNum() {
		return temperatureProbeNum;
	}

	public void setTemperatureProbeNum(int temperatureProbeNum) {
		this.temperatureProbeNum = temperatureProbeNum;
	}

	public int getBatteryPackageNum() {
		return batteryPackageNum;
	}

	public void setBatteryPackageNum(int batteryPackageNum) {
		this.batteryPackageNum = batteryPackageNum;
	}

	public List<BatteryUnitTemperatureInfo> getBatteryUnitTemperatureInfos() {
		return batteryUnitTemperatureInfos;
	}

	public void setBatteryUnitTemperatureInfos(List<BatteryUnitTemperatureInfo> batteryUnitTemperatureInfos) {
		this.batteryUnitTemperatureInfos = batteryUnitTemperatureInfos;
	}

}
