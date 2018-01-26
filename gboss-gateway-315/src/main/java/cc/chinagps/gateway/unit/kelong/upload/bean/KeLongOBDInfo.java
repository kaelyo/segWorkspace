package cc.chinagps.gateway.unit.kelong.upload.bean;

import org.seg.lib.util.Util;

/**
 *@todo����¡Obd��Ϣ����; 
 *@author��cj
 *@time��2017��5��25��
 *
 */
public class KeLongOBDInfo {
	private int totalDistance; // ����̣���λ�����ף�
	private int rotationSpeed; // ������ת�٣���λ��ת/�֣�
	private int speed; // OBD�ٶȣ���λ��0.1ǧ��/Сʱ��
	private int batteryVoltage;// ������ƿ��ѹ,��λ0.01V����λС���㣩
	private int totalOil;// �ۼ����ͺģ���λmL��������
	private int totalDriveTime;// �ۼ�����ʻʱ�䣬��λ��
    private int remainOil;//ʣ����������can����ȡ��
    private int dataLen;
    
	public int getDataLen() {
		return dataLen;
	}

	public void setDataLen(int dataLen) {
		this.dataLen = dataLen;
	}

	public int getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(int totalDistance) {
		this.totalDistance = totalDistance;
	}

	public int getRotationSpeed() {
		return rotationSpeed;
	}

	public void setRotationSpeed(int rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getBatteryVoltage() {
		return batteryVoltage;
	}

	public void setBatteryVoltage(int batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public int getTotalOil() {
		return totalOil;
	}

	public void setTotalOil(int totalOil) {
		this.totalOil = totalOil;
	}

	public int getTotalDriveTime() {
		return totalDriveTime;
	}

	public void setTotalDriveTime(int totalDriveTime) {
		this.totalDriveTime = totalDriveTime;
	}

	public int getRemainOil() {
		return remainOil;
	}

	public void setRemainOil(int remainOil) {
		this.remainOil = remainOil;
	}	
	
	/**
	 * @todo:������¡OBD��Ϣ
	 * @author:cj
	 * @param:
	 * @return:
	 * @remark:
	 */
	public static KeLongOBDInfo parse(byte[] data, int pos) throws Exception {
		KeLongOBDInfo keLongOBDInfo = new KeLongOBDInfo();
		int startPos = pos;
		//��ƿ��ѹ
		int batteryVoltage = Util.getShort(data, pos) & 0xFFFF;
		keLongOBDInfo.setBatteryVoltage(batteryVoltage);
		pos += 2;
		//����
		int speed = Util.getShort(data, pos) & 0xFFFF;
		keLongOBDInfo.setSpeed(speed);
		pos += 2;
		//���ͻ�ת��
		int rotationSpeed = Util.getShort(data, pos) & 0xFFFF;
		keLongOBDInfo.setRotationSpeed(rotationSpeed);
		pos += 2;
		//�ۼ����
		int totalDistance = Util.getInt(data, pos) & 0xFFFFFFFF;
		keLongOBDInfo.setTotalDistance(totalDistance);
		pos += 4;
		//�ۼ��ͺ�
		int totalOil = Util.getInt(data, pos) & 0xFFFFFFFF;
		keLongOBDInfo.setTotalOil(totalOil);
		pos += 4;
		//�ۼ���ʻʱ��
		int totalDriveTime = Util.getInt(data, pos) & 0xFFFFFFFF;
		keLongOBDInfo.setTotalDriveTime(totalDriveTime);
		pos += 4;
		
		keLongOBDInfo.setDataLen(pos - startPos);
		return keLongOBDInfo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("KeLongOBDInfo [totalDistance=");
		builder.append(totalDistance);
		builder.append(", rotationSpeed=");
		builder.append(rotationSpeed);
		builder.append(", speed=");
		builder.append(speed);
		builder.append(", batteryVoltage=");
		builder.append(batteryVoltage);
		builder.append(", totalOil=");
		builder.append(totalOil);
		builder.append(", totalDriveTime=");
		builder.append(totalDriveTime);
		builder.append(", dataLen=");
		builder.append(dataLen);
		builder.append("]");
		return builder.toString();
	}
}
