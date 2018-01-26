package cc.chinagps.gateway.unit.leopaard.upload.bean;

import cc.chinagps.gateway.unit.leopaard.util.LeopaardParseUtil;
import cc.chinagps.gateway.util.Util;

public class LeopaardOBDInfo {
	private int remainOil; // ʣ����������λ��0.1����
	private int remainPercentOil; // ʣ�������ٷֱȣ���λ��0.1%��
	private int averageOil; // ƽ���ͺģ���λ��0.1��/�ٹ��
	private int hourOil; // ˲ʱ�ͺģ���λ��0.1��/Сʱ��
	private int totalDistance; // ����̣���λ�����ף�
	private int waterTemperature; // ��ȴҺ�¶�,ˮ�£���λ���棩
	private int reviseOil; // ȼ��������0.1%��
	private int rotationSpeed; // ������ת�٣���λ��ת/�֣�
	private int intakeAirTemperature; // �����¶ȣ���λ���棩
	private int airDischange; // ��������(g/s)
	private int speed; // OBD�ٶȣ���λ��0.1ǧ��/Сʱ��
	private int remainDistance; // ����(ʣ��)��̣���λ���ף�
	private byte signLevel;//ͨ��ģ���ź�ǿ��
	
	
	private int lfTyreStatusBit;//��ǰ��̥ѹ״̬��Чλ
	private int lfTyreStatus;//��ǰ��̥ѹ״̬
	private int lfTyrePressure;//��ǰ��̥ѹ
	
	private int rfTyreStatusBit;//��ǰ��̥ѹ״̬��Чλ
	private int rfTyreStatus;//��ǰ��̥ѹ״̬
	private int rfTyrePressure;//��ǰ��̥ѹ
	
	private int lbTyreStatusBit;//�����̥ѹ״̬��Чλ
	private int lbTyreStatus;//�����̥ѹ״̬
	private int lbTyrePressure;//�����̥ѹ
	
	private int rbTyreStatusBit;//�Һ���̥ѹ״̬��Чλ
	private int rbTyreStatus;//�Һ���̥ѹ״̬
	private int rbTyrePressure;//�Һ���̥ѹ
	
	
	// ����������Դ�����ӵĲ���
	private int gears; // ��λ��-1:����, 0:�յ�, 1:1��, 2:2��, 3:3��, 4:4��, 5:5��, 6:6��,
						// 7:7��, 15:ǰ����, 16:�ƶ���
	private boolean isDrive; // �����Ƿ���Ч
	private int driveRatio; // ����̤���г�ֵ: ��Чֵ��Χ��0��100����ʾ 0%��100%��,��С������Ԫ��1%
	private boolean isBreak; // �ƶ��Ƿ���Ч
	private int breakRatio; // �ƶ�̤���г�ֵ: ��Чֵ��Χ��0��100����ʾ 0%��100%��,��С������Ԫ��1%
	private int chargeStatus; // ��ŵ�״̬��1:���, 2:�ŵ�, -1:��Ч����
	private int motorCtrlTemperature; // ����������¶�, ����λ��ʮ��֮һ���϶ȣ�
	private int motorTemperature; // ����¶�, ����λ��ʮ��֮һ���϶ȣ�
	private int motorSpeed; // ���ת��
	private int motorVoltage; // �����ѹ����λ��0.1V)
	private int motorCurrent; // �����������λ��1����)
	private int airconTemperature; // �յ��趨�¶�, ����λ��ʮ��֮һ���϶ȣ�
	private int batteryTotalVoltage; // ���������ܵ�ѹ����λ��0.1V)
	private int batteryTotalCurrent; // ���������ܵ�������λ��1����)
	private int remaindCapacity; // ʣ����������λ��o.1��,ǧ��ʱ��
	private int resistance; // ��Ե���裨��λ��ŷĸ����
	
	private int odbDataLen;//�Ǳ�3���ݳ���

	public int getOdbDataLen() {
		return odbDataLen;
	}

	public void setOdbDataLen(int odbDataLen) {
		this.odbDataLen = odbDataLen;
	}

	public int getRemainOil() {
		return remainOil;
	}

	public void setRemainOil(int remainOil) {
		this.remainOil = remainOil;
	}

	public int getRemainPercentOil() {
		return remainPercentOil;
	}

	public void setRemainPercentOil(int remainPercentOil) {
		this.remainPercentOil = remainPercentOil;
	}

	public int getAverageOil() {
		return averageOil;
	}

	public void setAverageOil(int averageOil) {
		this.averageOil = averageOil;
	}
	


	public int getHourOil() {
		return hourOil;
	}

	public void setHourOil(int hourOil) {
		this.hourOil = hourOil;
	}

	public int getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(int totalDistance) {
		this.totalDistance = totalDistance;
	}

	public int getWaterTemperature() {
		return waterTemperature;
	}

	public void setWaterTemperature(int waterTemperature) {
		this.waterTemperature = waterTemperature;
	}

	public int getReviseOil() {
		return reviseOil;
	}

	public void setReviseOil(int reviseOil) {
		this.reviseOil = reviseOil;
	}

	public int getRotationSpeed() {
		return rotationSpeed;
	}

	public void setRotationSpeed(int rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
	}

	public int getIntakeAirTemperature() {
		return intakeAirTemperature;
	}

	public void setIntakeAirTemperature(int intakeAirTemperature) {
		this.intakeAirTemperature = intakeAirTemperature;
	}

	public int getAirDischange() {
		return airDischange;
	}

	public void setAirDischange(int airDischange) {
		this.airDischange = airDischange;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getRemainDistance() {
		return remainDistance;
	}

	public void setRemainDistance(int remainDistance) {
		this.remainDistance = remainDistance;
	}


	public byte getSignLevel() {
		return signLevel;
	}

	public void setSignLevel(byte signLevel) {
		this.signLevel = signLevel;
	}
	
	
	public int getLfTyreStatusBit() {
		return lfTyreStatusBit;
	}

	public void setLfTyreStatusBit(int lfTyreStatusBit) {
		this.lfTyreStatusBit = lfTyreStatusBit;
	}

	public int getLfTyreStatus() {
		return lfTyreStatus;
	}

	public void setLfTyreStatus(int lfTyreStatus) {
		this.lfTyreStatus = lfTyreStatus;
	}

	public int getLfTyrePressure() {
		return lfTyrePressure;
	}

	public void setLfTyrePressure(int lfTyrePressure) {
		this.lfTyrePressure = lfTyrePressure;
	}

	public int getRfTyreStatusBit() {
		return rfTyreStatusBit;
	}

	public void setRfTyreStatusBit(int rfTyreStatusBit) {
		this.rfTyreStatusBit = rfTyreStatusBit;
	}

	public int getRfTyreStatus() {
		return rfTyreStatus;
	}

	public void setRfTyreStatus(int rfTyreStatus) {
		this.rfTyreStatus = rfTyreStatus;
	}

	public int getRfTyrePressure() {
		return rfTyrePressure;
	}

	public void setRfTyrePressure(int rfTyrePressure) {
		this.rfTyrePressure = rfTyrePressure;
	}

	public int getLbTyreStatusBit() {
		return lbTyreStatusBit;
	}

	public void setLbTyreStatusBit(int lbTyreStatusBit) {
		this.lbTyreStatusBit = lbTyreStatusBit;
	}

	public int getLbTyreStatus() {
		return lbTyreStatus;
	}

	public void setLbTyreStatus(int lbTyreStatus) {
		this.lbTyreStatus = lbTyreStatus;
	}

	public int getLbTyrePressure() {
		return lbTyrePressure;
	}

	public void setLbTyrePressure(int lbTyrePressure) {
		this.lbTyrePressure = lbTyrePressure;
	}

	public int getRbTyreStatusBit() {
		return rbTyreStatusBit;
	}

	public void setRbTyreStatusBit(int rbTyreStatusBit) {
		this.rbTyreStatusBit = rbTyreStatusBit;
	}

	public int getRbTyreStatus() {
		return rbTyreStatus;
	}

	public void setRbTyreStatus(int rbTyreStatus) {
		this.rbTyreStatus = rbTyreStatus;
	}

	public int getRbTyrePressure() {
		return rbTyrePressure;
	}

	public void setRbTyrePressure(int rbTyrePressure) {
		this.rbTyrePressure = rbTyrePressure;
	}

	public int getGears() {
		return gears;
	}

	public void setGears(int gears) {
		this.gears = gears;
	}

	public boolean isDrive() {
		return isDrive;
	}

	public void setDrive(boolean isDrive) {
		this.isDrive = isDrive;
	}

	public int getDriveRatio() {
		return driveRatio;
	}

	public void setDriveRatio(int driveRatio) {
		this.driveRatio = driveRatio;
	}

	public boolean isBreak() {
		return isBreak;
	}

	public void setBreak(boolean isBreak) {
		this.isBreak = isBreak;
	}

	public int getBreakRatio() {
		return breakRatio;
	}

	public void setBreakRatio(int breakRatio) {
		this.breakRatio = breakRatio;
	}

	public int getChargeStatus() {
		return chargeStatus;
	}

	public void setChargeStatus(int chargeStatus) {
		this.chargeStatus = chargeStatus;
	}

	public int getMotorCtrlTemperature() {
		return motorCtrlTemperature;
	}

	public void setMotorCtrlTemperature(int motorCtrlTemperature) {
		this.motorCtrlTemperature = motorCtrlTemperature;
	}

	public int getMotorTemperature() {
		return motorTemperature;
	}

	public void setMotorTemperature(int motorTemperature) {
		this.motorTemperature = motorTemperature;
	}

	public int getMotorSpeed() {
		return motorSpeed;
	}

	public void setMotorSpeed(int motorSpeed) {
		this.motorSpeed = motorSpeed;
	}

	public int getMotorVoltage() {
		return motorVoltage;
	}

	public void setMotorVoltage(int motorVoltage) {
		this.motorVoltage = motorVoltage;
	}

	public int getMotorCurrent() {
		return motorCurrent;
	}

	public void setMotorCurrent(int motorCurrent) {
		this.motorCurrent = motorCurrent;
	}

	public int getAirconTemperature() {
		return airconTemperature;
	}

	public void setAirconTemperature(int airconTemperature) {
		this.airconTemperature = airconTemperature;
	}

	public int getBatteryTotalVoltage() {
		return batteryTotalVoltage;
	}

	public void setBatteryTotalVoltage(int batteryTotalVoltage) {
		this.batteryTotalVoltage = batteryTotalVoltage;
	}

	public int getBatteryTotalCurrent() {
		return batteryTotalCurrent;
	}

	public void setBatteryTotalCurrent(int batteryTotalCurrent) {
		this.batteryTotalCurrent = batteryTotalCurrent;
	}

	public int getRemaindCapacity() {
		return remaindCapacity;
	}

	public void setRemaindCapacity(int remaindCapacity) {
		this.remaindCapacity = remaindCapacity;
	}

	public int getResistance() {
		return resistance;
	}

	public void setResistance(int resistance) {
		this.resistance = resistance;
	}
	
	
	public static LeopaardOBDInfo parse(LeopaardOBDInfo leopaardOBDInfo,byte[] data,int position)throws Exception{
		if (leopaardOBDInfo == null)
			leopaardOBDInfo = new LeopaardOBDInfo();
		int startPos = position;
		int hourOil3 = data[position];// ˲ʱ�ͺģ���λ��ʮ��֮һ/H��
		leopaardOBDInfo.setHourOil(hourOil3 * 10);
		position++;
		int averageOil3 =  Util.getShort(data, position);// ƽ���ͺģ�ʮ��֮һ��/�ٹ��
		leopaardOBDInfo.setAverageOil(averageOil3 * 10);//TripAƽ���ͺ�
		position += 2;
		position += 2;//TripBƽ���ͺ��Թ�
		int remainOil3 = Util.getShort(data, position);
		//leopaardOBDInfo.setRemainOil(remainOil3 * 10);
		leopaardOBDInfo.setRemainPercentOil(remainOil3 * 10);
		position += 2;
		//int intakeAirTemperature = data[position];
		//leopaardOBDInfo.setIntakeAirTemperature(intakeAirTemperature);
		//position++;
		int totalDistance3 = LeopaardParseUtil.getIntFrom3Byte(data, position);
		leopaardOBDInfo.setTotalDistance(totalDistance3 * 100);
		position += 3;
		int waterTemperature3 = data[position];
		leopaardOBDInfo.setWaterTemperature(waterTemperature3);
		position++;
		int rotationSpeed3 = Util.getShort(data, position);
		leopaardOBDInfo.setRotationSpeed(rotationSpeed3);
		position += 2;
		int obdSpeed3 = Util.getShort(data, position);
		leopaardOBDInfo.setSpeed(obdSpeed3);
		position += 2;
		int remainDistance3 = Util.getShort(data, position);//TripA�������
		leopaardOBDInfo.setRemainDistance(remainDistance3 * 100);
		position += 2;
		position += 2;//TripB��������Թ�
		position++;//�Ǳ���ʾģʽ�Թ�
		byte signLevel3 = data[position++];
		leopaardOBDInfo.setSignLevel(signLevel3);
		
		int lfTyreStatusBit = data[position++];//��ǰ��̥ѹ״̬��Чλ
		leopaardOBDInfo.setLfTyreStatusBit(lfTyreStatusBit);
		int lfTyreStatus= data[position++];//��ǰ��̥ѹ״̬
		leopaardOBDInfo.setLfTyreStatus(lfTyreStatus);
		
		int lbTyreStatusBit = data[position++];//�����̥ѹ״̬��Чλ
		leopaardOBDInfo.setLbTyreStatusBit(lbTyreStatusBit);
		int lbTyreStatus= data[position++];//�����̥ѹ״̬
		leopaardOBDInfo.setLbTyreStatus(lbTyreStatus);
		
		int rfTyreStatusBit = data[position++];//��ǰ��̥ѹ״̬��Чλ
		leopaardOBDInfo.setRfTyreStatusBit(rfTyreStatusBit);
		int rfTyreStatus= data[position++];//��ǰ��̥ѹ״̬
		leopaardOBDInfo.setRfTyreStatus(rfTyreStatus);
		
		int rbTyreStatusBit = data[position++];//�Һ���̥ѹ״̬��Чλ
		leopaardOBDInfo.setRbTyreStatusBit(rbTyreStatusBit);
		int rbTyreStatus= data[position++];//�Һ���̥ѹ״̬
		leopaardOBDInfo.setRbTyreStatus(rbTyreStatus);
		
		int lfTyrePressure= data[position++];//��ǰ��̥ѹ 
		leopaardOBDInfo.setLfTyrePressure(lfTyrePressure*4);
		int lbTyrePressure= data[position++];//�����̥ѹ 
		leopaardOBDInfo.setLbTyrePressure(lbTyrePressure*4);
		int rfTyrePressure= data[position++];//��ǰ��̥ѹ 
		leopaardOBDInfo.setRfTyrePressure(rfTyrePressure*4);
		int rbTyrePressure= data[position++];//�Һ���̥ѹ 
		leopaardOBDInfo.setRbTyrePressure(rbTyrePressure*4);
		
		int dataLen = position - startPos;
		leopaardOBDInfo.setOdbDataLen(dataLen);
		
		return leopaardOBDInfo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LeopaardOBDInfo [remainOil=");
		builder.append(remainOil);
		builder.append(", remainPercentOil=");
		builder.append(remainPercentOil);
		builder.append(", averageOil=");
		builder.append(averageOil);
		builder.append(", hourOil=");
		builder.append(hourOil);
		builder.append(", totalDistance=");
		builder.append(totalDistance);
		builder.append(", waterTemperature=");
		builder.append(waterTemperature);
		builder.append(", reviseOil=");
		builder.append(reviseOil);
		builder.append(", rotationSpeed=");
		builder.append(rotationSpeed);
		builder.append(", intakeAirTemperature=");
		builder.append(intakeAirTemperature);
		builder.append(", airDischange=");
		builder.append(airDischange);
		builder.append(", speed=");
		builder.append(speed);
		builder.append(", remainDistance=");
		builder.append(remainDistance);
		builder.append(", signLevel=");
		builder.append(signLevel);
		builder.append(", lfTyreStatusBit=");
		builder.append(lfTyreStatusBit);
		builder.append(", lfTyreStatus=");
		builder.append(lfTyreStatus);
		builder.append(", lfTyrePressure=");
		builder.append(lfTyrePressure);
		builder.append(", rfTyreStatusBit=");
		builder.append(rfTyreStatusBit);
		builder.append(", rfTyreStatus=");
		builder.append(rfTyreStatus);
		builder.append(", rfTyrePressure=");
		builder.append(rfTyrePressure);
		builder.append(", lbTyreStatusBit=");
		builder.append(lbTyreStatusBit);
		builder.append(", lbTyreStatus=");
		builder.append(lbTyreStatus);
		builder.append(", lbTyrePressure=");
		builder.append(lbTyrePressure);
		builder.append(", rbTyreStatusBit=");
		builder.append(rbTyreStatusBit);
		builder.append(", rbTyreStatus=");
		builder.append(rbTyreStatus);
		builder.append(", rbTyrePressure=");
		builder.append(rbTyrePressure);
		builder.append(", gears=");
		builder.append(gears);
		builder.append(", isDrive=");
		builder.append(isDrive);
		builder.append(", driveRatio=");
		builder.append(driveRatio);
		builder.append(", isBreak=");
		builder.append(isBreak);
		builder.append(", breakRatio=");
		builder.append(breakRatio);
		builder.append(", chargeStatus=");
		builder.append(chargeStatus);
		builder.append(", motorCtrlTemperature=");
		builder.append(motorCtrlTemperature);
		builder.append(", motorTemperature=");
		builder.append(motorTemperature);
		builder.append(", motorSpeed=");
		builder.append(motorSpeed);
		builder.append(", motorVoltage=");
		builder.append(motorVoltage);
		builder.append(", motorCurrent=");
		builder.append(motorCurrent);
		builder.append(", airconTemperature=");
		builder.append(airconTemperature);
		builder.append(", batteryTotalVoltage=");
		builder.append(batteryTotalVoltage);
		builder.append(", batteryTotalCurrent=");
		builder.append(batteryTotalCurrent);
		builder.append(", remaindCapacity=");
		builder.append(remaindCapacity);
		builder.append(", resistance=");
		builder.append(resistance);
		builder.append("]");
		return builder.toString();
	}
	
}
