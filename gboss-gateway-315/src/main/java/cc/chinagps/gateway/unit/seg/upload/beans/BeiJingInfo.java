package cc.chinagps.gateway.unit.seg.upload.beans;

/**
 * ����gps������Ϣ
 *
 */
public class BeiJingInfo {
	private int devid; // �豸id
	private int rfid; // ��Ա������
	private int voltage; // ��ص�ѹֵ����λ��0.1V��
	private int electricity; // �����ٷֱ�(�ٷ�֮һ)
	private int gsmvalue; // GSM�ź�ǿ��
	private int satellitecount; // ������
	private int mileage;// ������̣�km��
	private String chargeStatus;// ���״̬��0��δ��磬1������У�2�������ɣ�3�������ϣ�
	private String fillGunStatus;// ��ǹ����״̬ 0 ������ 1δ���� 2 ������
	private String accStatus;// ACC״̬ 0 ACC�� 1 ACC��
	private String oilStatus;// ��·״̬ 0 �պ� 1 �Ͽ�
	private String defendStatus;// ����״̬ 0 ���� 1���
	private String centralLockStatus;// �п���״̬ 0δ�� 1����
	private String doorStatus;// ����״̬����һ���ֽ�Ϊ��ʻԱ�ţ��ڶ����ֽ�Ϊ����ʻԱ�ţ��������ֽ�Ϊ��ʻԱ���ţ����ĸ��ֽ�Ϊ����ʻԱ���ţ������Ϊ������
								// 0δ�� 1 ��
	private String lightStatus;// ����״̬ ��һ���ֽ�ΪС�ƣ��ڶ����ֽ�Ϊ��ƣ��������ֽ�Ϊ����ƣ����ĸ��ֽ�Ϊǰ��� 0 ��
								// 1��
	private String windowStatus;// ����״̬ 0 ȫ�غ�1 δ�غ�
	
	private int smallVoltage;

	public int getSmallVoltage() {
		return smallVoltage;
	}

	public void setSmallVoltage(int smallVoltage) {
		this.smallVoltage = smallVoltage;
	}

	public int getDevid() {
		return devid;
	}

	public void setDevid(int devid) {
		this.devid = devid;
	}

	public int getRfid() {
		return rfid;
	}

	public void setRfid(int rfid) {
		this.rfid = rfid;
	}

	public int getVoltage() {
		return voltage;
	}

	public void setVoltage(int voltage) {
		this.voltage = voltage;
	}

	public int getElectricity() {
		return electricity;
	}

	public void setElectricity(int electricity) {
		this.electricity = electricity;
	}

	public int getGsmvalue() {
		return gsmvalue;
	}

	public void setGsmvalue(int gsmvalue) {
		this.gsmvalue = gsmvalue;
	}

	public int getSatellitecount() {
		return satellitecount;
	}

	public void setSatellitecount(int satellitecount) {
		this.satellitecount = satellitecount;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public String getChargeStatus() {
		return chargeStatus;
	}

	public void setChargeStatus(String chargeStatus) {
		this.chargeStatus = chargeStatus;
	}

	public String getFillGunStatus() {
		return fillGunStatus;
	}

	public void setFillGunStatus(String fillGunStatus) {
		this.fillGunStatus = fillGunStatus;
	}

	public String getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}

	public String getOilStatus() {
		return oilStatus;
	}

	public void setOilStatus(String oilStatus) {
		this.oilStatus = oilStatus;
	}

	public String getDefendStatus() {
		return defendStatus;
	}

	public void setDefendStatus(String defendStatus) {
		this.defendStatus = defendStatus;
	}

	public String getCentralLockStatus() {
		return centralLockStatus;
	}

	public void setCentralLockStatus(String centralLockStatus) {
		this.centralLockStatus = centralLockStatus;
	}

	public String getDoorStatus() {
		return doorStatus;
	}

	public void setDoorStatus(String doorStatus) {
		this.doorStatus = doorStatus;
	}

	public String getLightStatus() {
		return lightStatus;
	}

	public void setLightStatus(String lightStatus) {
		this.lightStatus = lightStatus;
	}

	public String getWindowStatus() {
		return windowStatus;
	}

	public void setWindowStatus(String windowStatus) {
		this.windowStatus = windowStatus;
	}

	@Override
	public String toString() {
		return "BeiJingInfo [devid=" + devid + ", rfid=" + rfid + ", voltage="
				+ voltage + ", electricity=" + electricity + ", gsmvalue="
				+ gsmvalue + ", satellitecount=" + satellitecount
				+ ", mileage=" + mileage + ", chargeStatus=" + chargeStatus
				+ ", fillGunStatus=" + fillGunStatus + ", accStatus="
				+ accStatus + ", oilStatus=" + oilStatus + ", defendStatus="
				+ defendStatus + ", centralLockStatus=" + centralLockStatus
				+ ", doorStatus=" + doorStatus + ", lightStatus=" + lightStatus
				+ ", windowStatus=" + windowStatus + ", smallVoltage="
				+ smallVoltage + "]";
	}

}
