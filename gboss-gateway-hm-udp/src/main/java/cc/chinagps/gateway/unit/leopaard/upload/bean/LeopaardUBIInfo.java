package cc.chinagps.gateway.unit.leopaard.upload.bean;

import java.util.ArrayList;
import java.util.List;

import cc.chinagps.gateway.unit.leopaard.util.LeopaardParseUtil;
import cc.chinagps.gateway.util.Util;

public class LeopaardUBIInfo {
	private final static int cycle = 15;
	private List<Integer> hourOils; // ˲ʱ�ͺģ���λ��0.1��/Сʱ��
	private int remainOil; // ʣ����������λ��0.1����
	private int remainPercentOil; // ʣ�������ٷֱȣ���λ��0.1%��
	private int totalDistance; // ����̣���λ�����ף�
	private List<Integer> rotationSpeeds; // ������ת�٣���λ��ת/�֣�
	private List<Integer> speeds; // OBD�ٶȣ���λ��0.1ǧ��/Сʱ��
	private List<Integer> wheelAngles;// ������λ����Ϣ
	private List<Integer> wheelRotationSpeeds;// �����̵�ת��
	private List<Integer> wheelAccelerateSpeeds;// ������������ٶ�
	private List<Integer> gearsSigns;// ��λ�ź�

	private int lfTyreStatusBit;// ��ǰ��̥ѹ״̬��Чλ
	private int lfTyreStatus;// ��ǰ��̥ѹ״̬
	private int lfTyrePressure;// ��ǰ��̥ѹ

	private int rfTyreStatusBit;// ��ǰ��̥ѹ״̬��Чλ
	private int rfTyreStatus;// ��ǰ��̥ѹ״̬
	private int rfTyrePressure;// ��ǰ��̥ѹ

	private int lbTyreStatusBit;// �����̥ѹ״̬��Чλ
	private int lbTyreStatus;// �����̥ѹ״̬
	private int lbTyrePressure;// �����̥ѹ

	private int rbTyreStatusBit;// �Һ���̥ѹ״̬��Чλ
	private int rbTyreStatus;// �Һ���̥ѹ״̬
	private int rbTyrePressure;// �Һ���̥ѹ

	private int ubiDataLen;// �Ǳ�3���ݳ���

	public int getUbiDataLen() {
		return ubiDataLen;
	}

	public void setUbiDataLen(int ubiDataLen) {
		this.ubiDataLen = ubiDataLen;
	}
	
	public int getRemainPercentOil() {
		return remainPercentOil;
	}

	public void setRemainPercentOil(int remainPercentOil) {
		this.remainPercentOil = remainPercentOil;
	}

	public List<Integer> getHourOils() {
		return hourOils;
	}

	public void setHourOils(List<Integer> hourOils) {
		this.hourOils = hourOils;
	}

	public int getRemainOil() {
		return remainOil;
	}

	public void setRemainOil(int remainOil) {
		this.remainOil = remainOil;
	}

	public int getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(int totalDistance) {
		this.totalDistance = totalDistance;
	}

	public List<Integer> getRotationSpeeds() {
		return rotationSpeeds;
	}

	public void setRotationSpeeds(List<Integer> rotationSpeeds) {
		this.rotationSpeeds = rotationSpeeds;
	}

	public List<Integer> getSpeeds() {
		return speeds;
	}

	public void setSpeeds(List<Integer> speeds) {
		this.speeds = speeds;
	}

	public List<Integer> getWheelAngles() {
		return wheelAngles;
	}

	public void setWheelAngles(List<Integer> wheelAngles) {
		this.wheelAngles = wheelAngles;
	}

	public List<Integer> getWheelRotationSpeeds() {
		return wheelRotationSpeeds;
	}

	public void setWheelRotationSpeeds(List<Integer> wheelRotationSpeeds) {
		this.wheelRotationSpeeds = wheelRotationSpeeds;
	}

	public List<Integer> getWheelAccelerateSpeeds() {
		return wheelAccelerateSpeeds;
	}

	public void setWheelAccelerateSpeeds(List<Integer> wheelAccelerateSpeeds) {
		this.wheelAccelerateSpeeds = wheelAccelerateSpeeds;
	}

	public List<Integer> getGearsSigns() {
		return gearsSigns;
	}

	public void setGearsSigns(List<Integer> gearsSigns) {
		this.gearsSigns = gearsSigns;
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

	public static LeopaardUBIInfo parse(byte[] data, int position) throws Exception {
		LeopaardUBIInfo leopaardUBIInfo = new LeopaardUBIInfo();
		int startPos = position;
		
		List<Integer> hourOilList = new ArrayList<Integer>();
		for (int i = 0; i < cycle; i++) {
			hourOilList.add((int) data[position++]);
		}
		leopaardUBIInfo.setHourOils(hourOilList);

		int remainOil = Util.getShort(data, position);//TripAƽ���ͺ�
		//leopaardUBIInfo.setRemainOil(remainOil * 10);
		leopaardUBIInfo.setRemainPercentOil(remainOil * 10);
		position += 2;
		position += 2;//TripBƽ���ͺ��Թ�
		position ++;//�Ǳ���ʾģʽ�Թ�
		int totalDistance = LeopaardParseUtil.getIntFrom3Byte(data, position);
		leopaardUBIInfo.setTotalDistance(totalDistance * 100);
		position += 3;

		List<Integer> rotationSpeedList = new ArrayList<Integer>();
		for (int i = 0; i < cycle; i++) {
			rotationSpeedList.add((int) Util.getShort(data, position));
			position += 2;
		}
		leopaardUBIInfo.setRotationSpeeds(rotationSpeedList);
		
		List<Integer> speedList = new ArrayList<Integer>();
		for (int i = 0; i < cycle; i++) {
			speedList.add((int) Util.getShort(data, position));
			position += 2;
		}
		leopaardUBIInfo.setSpeeds(speedList);
		
		List<Integer> wheelAngleList = new ArrayList<Integer>();
		for (int i = 0; i < cycle; i++) {
			wheelAngleList.add((int) Util.getShort(data, position));
			position += 2;
		}
		leopaardUBIInfo.setWheelAngles(wheelAngleList);
		
		List<Integer> wheelRotationList = new ArrayList<Integer>();
		for (int i = 0; i < cycle; i++) {
			wheelRotationList.add((int) Util.getShort(data, position));
			position += 2;
		}
		leopaardUBIInfo.setWheelRotationSpeeds(wheelRotationList);
		
		List<Integer> wheelAccelerateList = new ArrayList<Integer>();
		for (int i = 0; i < cycle; i++) {
			wheelAccelerateList.add((int) Util.getShort(data, position));
			position += 2;
		}
		leopaardUBIInfo.setWheelAccelerateSpeeds(wheelAccelerateList);
		
		List<Integer> gearsSignList = new ArrayList<Integer>();
		for (int i = 0; i < cycle; i++) {
			gearsSignList.add((int) data[position++]);
		}
		leopaardUBIInfo.setGearsSigns(gearsSignList);

		int lfTyreStatusBit = data[position++];// ��ǰ��̥ѹ״̬��Чλ
		leopaardUBIInfo.setLfTyreStatusBit(lfTyreStatusBit);
		int lfTyreStatus = data[position++];// ��ǰ��̥ѹ״̬
		leopaardUBIInfo.setLfTyreStatus(lfTyreStatus);

		int lbTyreStatusBit = data[position++];// �����̥ѹ״̬��Чλ
		leopaardUBIInfo.setLbTyreStatusBit(lbTyreStatusBit);
		int lbTyreStatus = data[position++];// �����̥ѹ״̬
		leopaardUBIInfo.setLbTyreStatus(lbTyreStatus);

		int rfTyreStatusBit = data[position++];// ��ǰ��̥ѹ״̬��Чλ
		leopaardUBIInfo.setRfTyreStatusBit(rfTyreStatusBit);
		int rfTyreStatus = data[position++];// ��ǰ��̥ѹ״̬
		leopaardUBIInfo.setRfTyreStatus(rfTyreStatus);

		int rbTyreStatusBit = data[position++];// �Һ���̥ѹ״̬��Чλ
		leopaardUBIInfo.setRbTyreStatusBit(rbTyreStatusBit);
		int rbTyreStatus = data[position++];// �Һ���̥ѹ״̬
		leopaardUBIInfo.setRbTyreStatus(rbTyreStatus);

		int lfTyrePressure = data[position++];// ��ǰ��̥ѹ
		leopaardUBIInfo.setLfTyrePressure(lfTyrePressure*4);
		int lbTyrePressure = data[position++];// �����̥ѹ
		leopaardUBIInfo.setLbTyrePressure(lbTyrePressure*4);
		int rfTyrePressure = data[position++];// ��ǰ��̥ѹ
		leopaardUBIInfo.setRfTyrePressure(rfTyrePressure*4);
		int rbTyrePressure = data[position++];// �Һ���̥ѹ
		leopaardUBIInfo.setRbTyrePressure(rbTyrePressure*4);

		int dataLen = position - startPos;
		leopaardUBIInfo.setUbiDataLen(dataLen);

		return leopaardUBIInfo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LeopaardUBIInfo [hourOils=");
		builder.append(hourOils);
		builder.append(", remainOil=");
		builder.append(remainOil);
		builder.append(", totalDistance=");
		builder.append(totalDistance);
		builder.append(", rotationSpeeds=");
		builder.append(rotationSpeeds);
		builder.append(", speeds=");
		builder.append(speeds);
		builder.append(", wheelAngles=");
		builder.append(wheelAngles);
		builder.append(", wheelRotationSpeeds=");
		builder.append(wheelRotationSpeeds);
		builder.append(", wheelAccelerateSpeeds=");
		builder.append(wheelAccelerateSpeeds);
		builder.append(", gearsSigns=");
		builder.append(gearsSigns);
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
		builder.append(", ubiDataLen=");
		builder.append(ubiDataLen);
		builder.append("]");
		return builder.toString();
	}

}
