package cc.chinagps.gateway.unit.seg.upload.beans.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceDetail {
	private Date startTime;		//��Ӫ��ʼʱ��

	private Date endTime;		//��Ӫ����ʱ��
	
	private int unitPrice;		//����
	
	private int distance;		//���
	
	private int timeLength;		//��ʱʱ��
	
	private int serviceMoney;	//Ӫҵ���
	
	private int emptyDistance;	//�ճ����
	
	private String cardNo;		//���ѿ�����
	
	private int cardStartMoney;	//���ѿ�ԭ���
	
	private int cardEndMoney;	//���ѿ����
	
	private int emptyPowerOffCount;	//�ճ��ϵ����
	
	private int emptyPowerOffTime;	//�ճ��ϵ�ʱ��
	
	private int overSpeedDistance;	//�������
	
	private int overSpeedCount;		//���ٴ���
	
	private String permitNo;		//׼��֤����
	
	private int storeIndex;			//�Ƽ������״洢���
	
	public String getPermitNo() {
		return permitNo;
	}

	public void setPermitNo(String permitNo) {
		this.permitNo = permitNo;
	}
	
	public int getStoreIndex() {
		return storeIndex;
	}

	public void setStoreIndex(int storeIndex) {
		this.storeIndex = storeIndex;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getTimeLength() {
		return timeLength;
	}

	public void setTimeLength(int timeLength) {
		this.timeLength = timeLength;
	}

	public int getServiceMoney() {
		return serviceMoney;
	}

	public void setServiceMoney(int serviceMoney) {
		this.serviceMoney = serviceMoney;
	}

	public int getEmptyDistance() {
		return emptyDistance;
	}

	public void setEmptyDistance(int emptyDistance) {
		this.emptyDistance = emptyDistance;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public int getCardStartMoney() {
		return cardStartMoney;
	}

	public void setCardStartMoney(int cardStartMoney) {
		this.cardStartMoney = cardStartMoney;
	}

	public int getCardEndMoney() {
		return cardEndMoney;
	}

	public void setCardEndMoney(int cardEndMoney) {
		this.cardEndMoney = cardEndMoney;
	}

	public int getEmptyPowerOffCount() {
		return emptyPowerOffCount;
	}

	public void setEmptyPowerOffCount(int emptyPowerOffCount) {
		this.emptyPowerOffCount = emptyPowerOffCount;
	}

	public int getEmptyPowerOffTime() {
		return emptyPowerOffTime;
	}

	public void setEmptyPowerOffTime(int emptyPowerOffTime) {
		this.emptyPowerOffTime = emptyPowerOffTime;
	}

	public int getOverSpeedDistance() {
		return overSpeedDistance;
	}

	public void setOverSpeedDistance(int overSpeedDistance) {
		this.overSpeedDistance = overSpeedDistance;
	}

	public int getOverSpeedCount() {
		return overSpeedCount;
	}

	public void setOverSpeedCount(int overSpeedCount) {
		this.overSpeedCount = overSpeedCount;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder sb = new StringBuilder();
		sb.append("{startTime:").append(sdf.format(startTime));
		sb.append(", endTime:").append(sdf.format(endTime));
		sb.append(", unitPrice:").append(unitPrice);
		sb.append(", distance:").append(distance);
		sb.append(", timeLength:").append(timeLength);
		sb.append(", serviceMoney:").append(serviceMoney);
		sb.append(", emptyDistance:").append(emptyDistance);
		sb.append(", cardNo:").append(cardNo);
		sb.append(", startMoney:").append(cardStartMoney);
		sb.append(", endMoney:").append(cardEndMoney);
		sb.append(", emptyPowerOffCount:").append(emptyPowerOffCount);
		sb.append(", emptyPowerOffTime:").append(emptyPowerOffTime);
		sb.append(", overSpeedDistance:").append(overSpeedDistance);
		sb.append(", overSpeedCount:").append(overSpeedCount);
		sb.append(", permitNo:").append(permitNo);
		sb.append(", storeIndex:").append(storeIndex);
		sb.append("}");
		
		return sb.toString();
	}
}
