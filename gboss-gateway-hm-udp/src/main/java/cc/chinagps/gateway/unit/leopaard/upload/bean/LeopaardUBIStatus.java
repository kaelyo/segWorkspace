package cc.chinagps.gateway.unit.leopaard.upload.bean;

import java.util.HashSet;
import java.util.Set;

//ubi����״̬��Ϣ
public class LeopaardUBIStatus {
	private Set<Integer> statusList = new HashSet<Integer>();
	private int dataLen;

	public int getDataLen() {
		return dataLen;
	}

	public void setDataLen(int dataLen) {
		this.dataLen = dataLen;
	}

	public Set<Integer> getStatusList() {
		return statusList;
	}

	public void addStatus(int status) {
		statusList.add(status);
	}

	public static final int DATA_LENGTH = 6;

	public static LeopaardUBIStatus parse(byte[] data, int offset) {
		LeopaardUBIStatus status = new LeopaardUBIStatus();

		int position = offset;

		byte[] flags = new byte[DATA_LENGTH];
		for (int i = 0; i < flags.length; i++) {
			flags[i] = data[position + i];
		}

		parseStatus1(status, flags[0]);//����״̬
		parseStatus2(status, flags[1]);//����״̬
		parseStatus3(status, flags[2]);//����״̬
		parseStatus4(status, flags[3]);//����״̬
		parseStatus5(status, flags[4]);//��λ״̬
		parseStatus6(status, flags[5]);//����״̬

		return status;
	}

	public static void main(String[] args) {
	}

	// 7 -- 0x80
	// 6 -- 0x40
	// 5 -- 0x20
	// 4 -- 0x10
	// 3 -- 0x8
	// 2 -- 0x4
	// 1 -- 0x2
	// 0 -- 0x1
	private static final byte[] STATUS_AND = { 0x1, 0x2, 0x4, 0x8, 0x10, 0x20, 0x40, (byte) 0x80 };

	/**
	 * ����״̬1
	 */
	private static void parseStatus1(LeopaardUBIStatus status, byte flag) {
		if ((flag & STATUS_AND[7]) != 0) {
			// �����ն����类�ж�*
			status.addStatus(4);
		}

		if ((flag & STATUS_AND[6]) != 0) {
			// ����*
			status.addStatus(6);
		}
		
		if ((flag & STATUS_AND[5]) != 0) {
			// �춯����*���𶯱�����
			status.addStatus(18);
		}

		if ((flag & STATUS_AND[4]) != 0) {
			// ��ײ����
			status.addStatus(12);
		}

		if ((flag & STATUS_AND[3]) != 0) {
			// ����̥ѹ�쳣��������̥�ڻ���й����
			status.addStatus(54);
		}
		
		if ((flag & STATUS_AND[2]) != 0) {
			// Ƿѹ����
			status.addStatus(3);
		}
		
		if ((flag & STATUS_AND[1]) != 0) {
			// ���籨��
			status.addStatus(239);
		}

	}


	/**
	 * ����״̬
	 */
	private static void parseStatus2(LeopaardUBIStatus status, byte flag) {
		if ((flag & STATUS_AND[7]) != 0) {
			// Զ���
			status.addStatus(42);
		} else {
			status.addStatus(43);
		}

		if ((flag & STATUS_AND[6]) != 0) {
			// �����
			status.addStatus(89);
		} else {
			status.addStatus(90);
		}

		if ((flag & STATUS_AND[5]) != 0) {
			// С�ƣ�λ�õƣ�
			status.addStatus(91);
		} else {
			status.addStatus(92);
		}

		if ((flag & STATUS_AND[4]) != 0) {
			// �����
			status.addStatus(85);
		} else {
			status.addStatus(86);
		}

		if ((flag & STATUS_AND[3]) != 0) {
			// ǰ���
			status.addStatus(87);
		} else {
			status.addStatus(88);
		}

		// bit2 bit1(ת��� 0:�ر� , 1:��ת��, 2:��ת��, 3:Σ�յ�)
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
	private static void parseStatus3(LeopaardUBIStatus status, byte flag) {
		if ((flag & STATUS_AND[7]) != 0) {
			// ��ǰ�ţ�˾����
			status.addStatus(107);
		} else {
			status.addStatus(108);
		}

		if ((flag & STATUS_AND[6]) != 0) {
			// ��ǰ��
			status.addStatus(105);
		} else {
			status.addStatus(106);
		}

		if ((flag & STATUS_AND[5]) != 0) {
			// �����
			status.addStatus(103);
		} else {
			status.addStatus(104);
		}

		if ((flag & STATUS_AND[4]) != 0) {
			// �Һ���
			status.addStatus(101);
		} else {
			status.addStatus(102);
		}

		if ((flag & STATUS_AND[3]) != 0) {
			// ��β��
			status.addStatus(19);
		} else {
			status.addStatus(67);
		}

		/*if ((flag & STATUS_AND[3]) != 0) {
			// �п�����
			status.addStatus(25);
		} else {
			status.addStatus(21);
		}*/
		
		if ((flag & STATUS_AND[2]) != 0) {
			// �������ո�
			status.addStatus(248);
		} else {
			status.addStatus(249);
		}
		
	}
	

	/**
	 * ����״̬
	 */
	private static void parseStatus4(LeopaardUBIStatus status, byte flag) {
		if ((flag & STATUS_AND[7]) != 0) {
			// �п���
			status.addStatus(251);
		} else {
			status.addStatus(250);
		}

		if ((flag & STATUS_AND[6]) != 0) {
			// ��ǰ����
			status.addStatus(253);
		} else {
			status.addStatus(252);
		}

		if ((flag & STATUS_AND[5]) != 0) {
			// ��ǰ����
			status.addStatus(255);
		} else {
			status.addStatus(254);
		}

		if ((flag & STATUS_AND[4]) != 0) {
			// �������
			status.addStatus(257);
		} else {
			status.addStatus(256);
		}

		if ((flag & STATUS_AND[3]) != 0) {
			// �Һ�����
			status.addStatus(259);
		} else {
			status.addStatus(258);
		}

		
		if ((flag & STATUS_AND[2]) != 0) {
			// ��ɲ״̬
			status.addStatus(260);
		} else {
			status.addStatus(261);
		}
		
		if ((flag & STATUS_AND[1]) != 0) {
			// �˿Ͱ�ȫ��״̬
			status.addStatus(262);
		} else {
			status.addStatus(263);
		}
		
		if ((flag & STATUS_AND[0]) != 0) {
			// ��ʻԱ��ȫ��״̬
			status.addStatus(264);
		} else {
			status.addStatus(265);
		}
		
	}

	/**
	 * ��λ
	 */
	private static void parseStatus5(LeopaardUBIStatus status, byte flag) {
		
	}

	/**
	 * ����
	 */
	private static void parseStatus6(LeopaardUBIStatus status, byte flag) {
		if ((flag & STATUS_AND[7]) != 0) {
			// ��ȫ����״̬
			status.addStatus(240);
		}else{
			status.addStatus(241);
		}

		/*if ((flag & STATUS_AND[6]) != 0) {
			// �����ر�
			status.addStatus(75);
		} else {
			status.addStatus(74);
		}*/

		// bit5 bit4 bit3 (��Դ 0:Off, 1:Acc, 2:On, 3:Start)
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
		
		if ((flag & STATUS_AND[2]) != 0) {
			// PEPS״̬
			status.addStatus(242);
		} else {
			status.addStatus(243);
		}

		if ((flag & STATUS_AND[1]) != 0) {
			// �յ�����
			status.addStatus(72);
		} else {
			status.addStatus(73);
		}

		/*if ((flag & STATUS_AND[0]) != 0) {
			// ��ƿǷѹ**
			status.addStatus(3);
		}*/
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{statusList:").append(statusList);
		sb.append("}");

		return sb.toString();
	}
}
