package cc.chinagps.gateway.unit.leopaard.upload.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * ��������ÿ���¶���Ϣ
 */
public class BatteryUnitTemperatureInfo {
	private int unitSn = 1; // �����
	private int temperatureProbeNum; // �ð����������¶�̽������
	private List<Integer> temperatures = new ArrayList<Integer>(); // ÿ���¶�̽����¶��б�̽������һ�����ڵ�������������λ��ʮ��֮һ���϶ȣ�

	public int getUnitSn() {
		return unitSn;
	}

	public void setUnitSn(int unitSn) {
		this.unitSn = unitSn;
	}

	public int getTemperatureProbeNum() {
		return temperatureProbeNum;
	}

	public void setTemperatureProbeNum(int temperatureProbeNum) {
		this.temperatureProbeNum = temperatureProbeNum;
	}

	public List<Integer> getTemperatures() {
		return temperatures;
	}

	public void setTemperatures(List<Integer> temperatures) {
		this.temperatures = temperatures;
	}

}
