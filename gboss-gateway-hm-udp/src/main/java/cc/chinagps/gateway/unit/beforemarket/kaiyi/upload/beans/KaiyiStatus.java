package cc.chinagps.gateway.unit.beforemarket.kaiyi.upload.beans;

import java.util.HashSet;
import java.util.Set;

public class KaiyiStatus {
	private Set<Integer> statusList = new HashSet<Integer>();
	
	//�Ƿ��Ǿ���
	private boolean isAlarm;
	
	//�Ƿ���һ�ྯ��
	private boolean isSpecialAlarm;

	public boolean isAlarm() {
		return isAlarm;
	}

	public void setAlarm(boolean isAlarm) {
		this.isAlarm = isAlarm;
	}

	public boolean isSpecialAlarm() {
		return isSpecialAlarm;
	}

	public void setToSpecialAlarm(){
		isAlarm = true;
		isSpecialAlarm = true;
	}

	public Set<Integer> getStatusList() {
		return statusList;
	}

	public void addStatus(int status) {
		statusList.add(status);
	}

	public static final int DATA_LENGTH = 7;
	
	public static KaiyiStatus parse(byte[] data, int offset){
		KaiyiStatus status = new KaiyiStatus();
		
		int position = offset;
		
		byte[] flags = new byte[7];
		for(int i = 0; i < flags.length; i++){
			flags[i] = data[position + i];
		}
		
		parseStatus1(status, flags[0]);
		parseStatus2(status, flags[1]);
		parseStatus3(status, flags[2]);
		parseStatus4(status, flags[3]);
		parseStatus5(status, flags[4]);
		parseStatus6(status, flags[5]);
		parseStatus7(status, flags[6]);
		
		return status;
	}
	
	public static void main(String[] args) {
		byte[] data = new byte[8];
		data[0] = (byte) data.length;
		KaiyiStatus status = KaiyiStatus.parse(data, 0);
		System.out.println(status);
	}
	
	//7 -- 0x80
	//6 -- 0x40
	//5 -- 0x20
	//4 -- 0x10
	//3 -- 0x8
	//2 -- 0x4
	//1 -- 0x2
	//0 -- 0x1
	private static final byte[] STATUS_AND = {0x1, 0x2, 0x4, 0x8, 0x10, 0x20, 0x40, (byte) 0x80};
	
	/**
	 * ����״̬1
	 */
	private static void parseStatus1(KaiyiStatus status, byte flag){
		if((flag & STATUS_AND[7]) != 0){
			//�����ն����类�ж�*
			status.addStatus(4);
			status.setToSpecialAlarm();
		}
		
		if((flag & STATUS_AND[6]) != 0){
			//����*
			status.addStatus(6);
			status.setToSpecialAlarm();
		}
		
		if((flag & STATUS_AND[4]) != 0){
			//SOS*
			status.addStatus(79);
			status.setToSpecialAlarm();
		}
		
		if((flag & STATUS_AND[1]) != 0){
			//���ٱ���
			status.addStatus(14);
			status.setAlarm(true);
		}
		
		if((flag & STATUS_AND[0]) != 0){
			//Խ�籨��
			status.addStatus(13);
			status.setAlarm(true);
		}
	}
	
	/**
	 * ����״̬2
	 */
	private static void parseStatus2(KaiyiStatus status, byte flag){
		if((flag & STATUS_AND[4]) != 0){
			//��ײ����*
			status.addStatus(12);
			status.setToSpecialAlarm();
		}
		
		if((flag & STATUS_AND[3]) != 0){
			//�෭����*
			status.addStatus(78);
			status.setToSpecialAlarm();
		}
		
		if((flag & STATUS_AND[2]) != 0){
			//�𶯱���**
			status.addStatus(18);
			status.setToSpecialAlarm();
		}
		
		if((flag & STATUS_AND[0]) != 0){
			//�ն�����
			status.addStatus(68);
		}
	}
	
	/**
	 * ����״̬
	 */
	private static void parseStatus3(KaiyiStatus status, byte flag){
		if((flag & STATUS_AND[7]) != 0){
			//��ײ������
			status.addStatus(46);
		}
		
		if((flag & STATUS_AND[6]) != 0){
			//�������
			status.addStatus(37);
		}
		
		if((flag & STATUS_AND[5]) != 0){
			//CANͨѶ����**
			status.addStatus(45);
			status.setAlarm(true);
		}
		
		if((flag & STATUS_AND[4]) != 0){
			//ͨ��ģ�����
			status.addStatus(47);
		}
		
//		if((flag & STATUS_AND[3]) != 0){
//			//GPS���߶�·**
//			status.addStatus(65);
//			status.setAlarm(true);
//		}
//		
//		if((flag & STATUS_AND[2]) != 0){
//			//GPS���߿�·**
//			status.addStatus(64);
//			status.setAlarm(true);
//		}
		
		if((flag & STATUS_AND[3]) != 0){
			//��������ٶȴ���������**
			status.addStatus(48);
			status.setAlarm(true);
		}
		
		if((flag & STATUS_AND[2]) != 0){
			//GPS���߹���**
			status.addStatus(66);
			status.setAlarm(true);
		}
		
		if((flag & STATUS_AND[1]) != 0){
			//GPS��λʱ�����**
			status.addStatus(10);
			status.setAlarm(true);
		}
		
		if((flag & STATUS_AND[0]) != 0){
			//GPS���ջ�û�����**ģ�����
			status.addStatus(16);
			status.setAlarm(true);
		}
	}
	
	/**
	 * ����״̬
	 */
	private static void parseStatus4(KaiyiStatus status, byte flag){
		if((flag & STATUS_AND[7]) != 0){
			//Զ���
			status.addStatus(42);
		}else{
			status.addStatus(43);
		}
		
		if((flag & STATUS_AND[6]) != 0){
			//�����
			status.addStatus(89);
		}else{
			status.addStatus(90);
		}
		
		if((flag & STATUS_AND[5]) != 0){
			//С��
			status.addStatus(91);
		}else{
			status.addStatus(92);
		}
		
		if((flag & STATUS_AND[4]) != 0){
			//�����
			status.addStatus(85);
		}else{
			status.addStatus(86);
		}
		
		if((flag & STATUS_AND[3]) != 0){
			//ǰ���
			status.addStatus(87);
		}else{
			status.addStatus(88);
		}
		
		//bit2 bit1(ת��� 0:�ر� , 1:��ת��, 2:��ת��, 3:Σ�յ�)
		int turn = (flag >> 1) & 0x3;
		switch (turn) {
			case 0:	
				status.addStatus(115);
				break;
			case 1:
				status.addStatus(116);
				break;
			case 2:
				status.addStatus(117);
				break;
			case 3:
				status.addStatus(118);
				break;
			default:
				break;
		}
	}
	
	/**
	 * ����״̬
	 */
	private static void parseStatus5(KaiyiStatus status, byte flag){
		if((flag & STATUS_AND[7]) != 0){
			//��ǰ�ţ�˾����
			status.addStatus(107);
		}else{
			status.addStatus(108);
		}
		
		if((flag & STATUS_AND[6]) != 0){
			//��ǰ��
			status.addStatus(105);
		}else{
			status.addStatus(106);
		}
		
		if((flag & STATUS_AND[5]) != 0){
			//�����
			status.addStatus(103);
		}else{
			status.addStatus(104);
		}
		
		if((flag & STATUS_AND[4]) != 0){
			//�Һ���
			status.addStatus(101);
		}else{
			status.addStatus(102);
		}
		
		if((flag & STATUS_AND[3]) != 0){
			//��β��
			status.addStatus(19);
		}else{
			status.addStatus(67);
		}
		
		if((flag & STATUS_AND[2]) != 0){
			//�п�����
			status.addStatus(25);
		}else{
			status.addStatus(21);
		}
	}
	
	/**
	 * ��λ
	 */
	private static void parseStatus6(KaiyiStatus status, byte flag){
		if((flag & STATUS_AND[3]) != 0){
			//P
			status.addStatus(111);
		}else if((flag & STATUS_AND[2]) != 0){
			//N
			status.addStatus(112);
		}else if((flag & STATUS_AND[1]) != 0){
			//R
			status.addStatus(113);
		}else if((flag & STATUS_AND[0]) != 0){
			//D
			status.addStatus(114);
		}
	}
	
	/**
	 * ����
	 */
	private static void parseStatus7(KaiyiStatus status, byte flag){
		if((flag & STATUS_AND[7]) != 0){
			//ɲ��
			status.addStatus(39);
		}
		
		if((flag & STATUS_AND[6]) != 0){
			//�����ر�
			status.addStatus(75);
		}else{
			status.addStatus(74);
		}
		
		/*
		if((flag & STATUS_AND[5]) != 0){
			//1����Ϩ��     0 ��������
			status.addStatus(23);
		}else{
			status.addStatus(33);
		}*/
		
		//bit5 bit4 bit3 (��Դ  0:Off, 1:Acc, 2:On, 3:Start)
		int power = (flag >> 3) & 0x7;
		switch (power) {
			case 0:
				status.addStatus(23);
				break;
			case 1:
				status.addStatus(33);
				break;
			case 2:
				status.addStatus(93);
				break;
			case 3:
				status.addStatus(94);
				break;
			default:
				break;
		}
		
		if((flag & STATUS_AND[1]) != 0){
			//�յ�����
			status.addStatus(72);
		}else{
			status.addStatus(73);
		}
		
		if((flag & STATUS_AND[0]) != 0){
			//��ƿǷѹ** 
			status.addStatus(3);
			status.setAlarm(true);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{isAlarm:").append(isAlarm);
		sb.append(", isSpecialAlarm:").append(isSpecialAlarm);
		sb.append(", statusList:").append(statusList);
		sb.append("}");
		
		return sb.toString();
	}
}