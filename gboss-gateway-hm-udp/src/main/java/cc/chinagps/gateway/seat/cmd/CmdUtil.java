package cc.chinagps.gateway.seat.cmd;

public class CmdUtil {
	// �鳵
	public static final int CMD_ID_POSITION = 0x0001;
	// ����
	public static final int CMD_ID_TRACE = 0x0015;
	// ֹͣ����
	public static final int CMD_ID_STOP_TRACE = 0x0016;
	// ������ʱ����
	public static final int CMD_ID_START_UPLOAD_BY_INTERVAL = 0x0038;
	// �رն�ʱ����
	public static final int CMD_ID_STOP_UPLOAD_BY_INTERVAL = 0x0039;
	
	//�������ʱ����
	//public static final int CMD_ID_START_ACC_ON_DELIVERY = 0x0040;
	//�رյ��ʱ����
	//public static final int CMD_ID_STOP_ACC_ON_DELIVERY = 0x0041;
	
	//����Ϩ��ʱ����
	public static final int CMD_ID_START_ACC_OFF_DELIVERY = 0x0040;
	//�ر�Ϩ��ʱ����
	public static final int CMD_ID_STOP_ACC_OFF_DELIVERY = 0x0041;
	
	//���������ϴ�
	public static final int CMD_ID_START_UPLOAD_BY_DISTANCE = 0x0044;
	//�رն����ϴ�
	public static final int CMD_ID_STOP_UPLOAD_BY_DISTANCE = 0x0045;
	// ���͵�
	public static final int CMD_ID_CUT_OFF_OIL = 0x0006;
	// �ָ��͵�
	public static final int CMD_ID_RESUME_OIL = 0x0007;
	// �ն˸�λ
	public static final int CMD_ID_RESET = 0x0002;
	// �ն�����
	public static final int CMD_ID_RESTART = 0x0075;
	// �����������
	public static final int CMD_ID_SET_HEARTBEAT_INTERVAL = 0x0076;
	// �����ն����߶�ʱ����ʱ����
	public static final int CMD_ID_SET_SLEEP_WAKEUP_INTERVAL = 0x0077;
	// ����������ͺ�
	public static final int CMD_ID_SET_TOTAL_DISTANCE_OIL = 0x0078;
	// ����GPS�ش�����
	public static final int CMD_ID_SET_GPS_DELIVER_PARAM = 0x0079;
	// ����GPS�յ㲹������
	public static final int CMD_ID_SET_INFLECTION_PARAM = 0x007A;
	// ���ó��ٱ�������
	public static final int CMD_ID_SET_OVERSPEED_PARAM = 0x007B;
	// ����OBD�������
	public static final int CMD_ID_SET_OBD_ADAPT_PARAM = 0x007C;
	// ��ѯOBD�������
	public static final int CMD_ID_QUERY_OBD_ADAPT_PARAM = 0x007D;
	// ��ֹ�绰����
	public static final int CMD_ID_FORBID_TEL_IN = 0x000A;
	// ��ֹ�绰���
	public static final int CMD_ID_FORBID_TEL_OUT = 0x000B;
	// ��Ⱥͨ��
	public static final int CMD_ID_GROUP_TEL = 0x001A;
	// �ָ��绰����
	public static final int CMD_ID_RESUME_TEL = 0x000C;
	// ����
	public static final int CMD_ID_MONITOR = 0x0017;
	// ���ü�������
	public static final int CMD_ID_SET_MONITOR_TEL = 0x0019;
	// ����
	public static final int CMD_ID_LIMIT_SPEED = 0x001D;
	// ȡ������
	public static final int CMD_ID_CANCEL_LIMIT_SPEED = 0x001E;
	// �·�����
	public static final int CMD_ID_SEND_SM = 0x0035;
	// �ϴ��г���¼
	public static final int CMD_ID_UPLOAD_HISTORY = 0x0012;
	// ����TCP/UDPͨ�Ų���
	public static final int CMD_ID_SET_TCP_UDP_PARAMS = 0x0057;
	// ��ѯTCP/UDPͨ�Ų���
	public static final int CMD_ID_QUERY_TCP_UDP_PARAMS = 0x0058;
	// ����APN
	public static final int CMD_ID_SET_APN = 0x0051;
	// ����TCP/UDP + APNͨ�Ų���
	public static final int CMD_ID_SET_TCP_UDP_APN_PARAMS = 0xF052;
	// ���ķ���绰
	public static final int CMD_ID_SET_SERRVICE_TEL = 0x0025;
	// ���ö���Ϣ�������ĺ���
	public static final int CMD_ID_SET_SM_CENTER_NUM = 0x0026;
	// ���ö��ź����ط���
	public static final int CMD_ID_SET_SM_IN_NUM = 0x002F;
	// ���ö��ź����ط���
	public static final int CMD_ID_SET_SM_OUT_NUM = 0xF056;
	// ����GPRS����ʱ����
	public static final int CMD_ID_SET_RECONNECT_INTERVAL = 0x005B;
	// (TN)���õ�����Ŀ�Ĳ�ѯ��
	public static final int CMD_ID_SET_TARGET_QUERY_POINT = 0x0292;
	// ������
	public static final int CMD_ID_LOCK_DOOR = 0x0004;
	// ������
	public static final int CMD_ID_OPEN_DOOR = 0x0005;
	
	// �ҳ�
	public static final int CMD_ID_SEARCH_VEHICLE = 0x0013;
	// �س���
	public static final int CMD_ID_CLOSE_WINDOW = 0x000D;
	// ������
	public static final int CMD_ID_OPEN_WINDOW = 0x000E;
	// ��ȡ����
	public static final int CMD_ID_READ_DEFINED_TYPE = 0x00A0;
	// �궨����
	public static final int CMD_ID_DEFINED_TYPE = 0x00A1;
	// ���������
	public static final int CMD_ID_CLEAR_FAULT_CODE = 0x00A2;
	// ͸��ָ��
	public static final int CMD_ID_PASS_THROUGH = 0xFFFF;
	
	//��������
	//���ù���/��Ԯ�������
	public static final int CMD_ID_SET_HELP_TEL = 0x00A6;
	//��ѯ����/��Ԯ�������
	public static final int CMD_ID_QUERY_HELP_TEL = 0x00A7;
	
	
	public static final int CMD_ID_UNIT_UPDATE_FTP = 0x00B8;
	
	//FTP���������ļ���
	public static final int CMD_ID_UPGRADE_FTP_MULTIFILE = 0x00B9;
	
	//���ù����ϱ�
	public static final int CMD_ID_SET_UPLOAD_FAULT = 0x00AA;
	//���ù��ϲ��ϱ�
	public static final int CMD_ID_SET_NOT_UPLOAD_FAULT = 0x00AB;
	//��ѯ�����Ƿ��ϱ�
	public static final int CMD_ID_QUERY_UPLOAD_FAULT_OR_NOT = 0x00AC;
	
	//�������Ϩ�𱨸�
	public static final int CMD_ID_START_UPLOAD_WHEN_ACC_ON_OFF = 0x0042;
	//�رյ��Ϩ�𱨸�
	public static final int CMD_ID_STOP_UPLOAD_WHEN_ACC_ON_OFF = 0x0043;
	
	//��������״̬�仯����
	public static final int CMD_ID_START_UPLOAD_WHEN_VEHICLE_STATUS_CHANGED = 0x0048;
	//�رճ���״̬�仯����
	public static final int CMD_ID_STOP_UPLOAD_WHEN_VEHICLE_STATUS_CHANGED = 0x0049;
	
	//�����ػ�����
	public static final int CMD_ID_START_UPLOAD_WHEN_POWER_OFF = 0x004A;
	//�رչػ�����
	public static final int CMD_ID_STOP_UPLOAD_WHEN_POWER_OFF = 0x004B;
	
	//�������߱���
	public static final int CMD_ID_START_UPLOAD_WHEN_SLEEP = 0x004C;
	//�ر����߱���
	public static final int CMD_ID_STOP_UPLOAD_WHEN_SLEEP = 0x004D;
	//���õ�ص�Ԫ����
	public static final int CMD_ID_SET_ECU_CONFIG = 0x00B3;
	
	//��ѯ��ص�Ԫ����
	public static final int CMD_ID_QUERY_ECU_CONFIG = 0x00B4;
	
	//������ѯ
	//��������
	public static final int CMD_ID_QUERY_CALL_CENTER = 0x0027;
	//����ҵ�����ĺ���
	public static final int CMD_ID_QUERY_SM_CENTER = 0x0030;
	//��ʱ�ϱ�����
	public static final int CMD_ID_QUERY_UPLOAD_PARAM = 0x0070;
	//ACC�仯�ϱ�����
	public static final int CMD_ID_QUERY_ACC_CHANFE_PARAM = 0x0071;
	//�����ϱ�����
	public static final int CMD_ID_QUERY_SLEEP_PARAM = 0x0072;
	//�ػ��ϱ�����
	public static final int CMD_ID_QUERY_POWEROFF_PARAM = 0x0073;
	//����״̬�仯�ϱ�����
	public static final int CMD_ID_QUERY_CAR_BODY_CHANGE_PARAM = 0x0074;
	//OBD����
	public static final int CMD_ID_QUERY_FAULT = 0x00A3;
	//��ѯ�����������
	public static final int CMD_ID_QUERY_CALL_LIMIT = 0x00A4;
	//֪ͨ�ն�����
	public static final int CMD_ID_UPDATE_UNIT = 0x00A5;
	//��ѯ����״̬
	public static final int CMD_ID_QUERY_UPDATE_STATUS = 0x00B2;
	//������ѯend
	
	//͸����Ӳ��
	public static final int CMD_ID_TO_HARDWARE = 0xFF;
	
	//TBOX����ָ��
	//�ص�
	public static final int CMD_ID_BOX_CLOSE_LIGHT = 0x0062;
	//����
	public static final int CMD_ID_BOX_OPEN_LIGHT = 0x0061;
	//����β��
	public static final int CMD_ID_BOX_OEPN_TAIL_DOOR = 0x0063;
	//�ؿյ�
	public static final int CMD_ID_BOX_CLOSE_AIR_CONDITIONING = 0x0066;
	//���յ�
	public static final int CMD_ID_BOX_OEPN_AIR_CONDITIONING = 0x0065;
	//���ÿյ�
	//public static final int CMD_ID_BOX_SET_AIR_CONDITIONING = 0x0064;
	
	//����������
	public static final int CMD_ID_BOX_START_ENGINE = 0x0069;
	//�رշ�����
	public static final int CMD_ID_BOX_STOP_ENGINE = 0x006A;
	//TBOX����ָ��end
	
	//��ά��ģʽ
	public static final int CMD_ID_OPEN_REPAIR_MODE = 0x0067;
	//�ر�ά��ģʽ
	public static final int CMD_ID_CLOSE_REPAIR_MODE = 0x0068;
	
	//һ��������ָ��
	//�����û�����
	public static final int CMD_ID_SET_USER_PASSWORD = 0x00AE;
	//���ñ����ֻ�����
	public static final int CMD_ID_SET_ALARM_TEL = 0x00B0;
	//����һ������������
	public static final int CMD_ID_SET_ALARM_PARAM = 0x0300;
	
	//��ѯ���ٲ���
	public static final int CMD_ID_QUERY_LIMIT_SPEED_PARAM = 0x00AD;
	//��ѯ�û�����
	public static final int CMD_ID_QUERY_USER_PASSWORD = 0x00AF;
	//��ѯ�����ֻ�����
	public static final int CMD_ID_QUERY_ALARM_TEL = 0x00B1;
	//��ѯһ������������
	public static final int CMD_ID_QUERY_ALARM_PARAM = 0x0301;
	
	// wifi��֤
	public static final int CMD_ID_WIFI_AUTH = 0x0100;
	
	//��ѯ�ӻ����
	public static final int CMD_ID_QUERY_TRG = 0x00E1;
	
	//ɾ���ӻ����
	public static final int CMD_ID_DEL_TRG = 0x00E2;
	
	//���������Ͽ�ʱ�ӻ��ϴ����
	public static final int CMD_ID_SET_TRG_INTERVAL = 0x00E3;
	
	//�򿪻�վ�����ϱ� 
	public static final int CMD_ID_OPEN_BASESTATION_UPLOAD = 0x004E;
	
	//�رջ�վ�����ϱ� 
	public static final int CMD_ID_CLOSE_BASESTATION_UPLOAD = 0x004F;
	
	//��ȡ�նˣ�TBOX����Ϣ
	public static final int CMD_ID_QUERY_TBOX_INFO = 0x00B5;
	
	//���ò෭У������
	public static final int CMD_ID_SET_ROLL_PARAM = 0x00B6;
	
	//��ѯ�෭У������
	public static final int CMD_ID_QUERY_ROLL_PARAM = 0x00B7;
	
	//��ѯ�յ����ò���
	public static final int CMD_ID_QUERY_AIR_CONDITIONING_PRARM = 0x0064;
	
	//�����·�����
	public static final int CMD_ID_DELIVER_ORDER = 0x0311;
	
	//������������
	public static final int CMD_ID_FINISH_ORDER = 0x0312;
	
	//����Զ�̿��Ƴ���
	public static final int CMD_ID_REMOTE_CONTROL_VEHICLE = 0x0313;
	
	public static final int CMD_ID_PICK_UP_CAR = 0x0314;
	
	public static final int CMD_ID_RETURN_CAR = 0x0315;
	
	//����������������3Gģ��
	public static final int CMD_ID_OPEN_SOUND_CONNECT_3G = 0x00F0;
	
	//��ֹ������������3Gģ��
	public static final int CMD_ID_CLOSE_SOUND_CONNECT_3G = 0x00F1;
	
	//��ѯ������������3Gģ��״̬
	public static final int CMD_ID_QUERY_SOUND_CONNECT_3G = 0x00F2;
	
	
	public static final int CMD_ID_OPEN_TBOX_SAVE_TRAFFIC = 0x00D3;
	
	
	public static final int CMD_ID_CLOSE_TBOX_SAVE_TRAFFIC = 0x00D4;
	
	//��ǰ��ˢ
	public static final int CMD_ID_OPEN_FRONT_WINDSHIELD_WIPER = 0x00F3;
	
	//�ر�ǰ��ˢ
	public static final int CMD_ID_CLOSE_FRONT_WINDSHIELD_WIPER = 0x00F4;
	
	//�򿪺���ˢ
	public static final int CMD_ID_OPEN_BACK_WINDSHIELD_WIPER = 0x00F5;
		
	//�رպ���ˢ
	public static final int CMD_ID_CLOSE_BACK_WINDSHIELD_WIPER = 0x00F6;
	
	//չ�����Ӿ�
	public static final int CMD_ID_EXPAND_BACK_MIRROR = 0x00F7;
	
	//�������Ӿ�
	public static final int CMD_ID_SHRINK_BACK_MIRROR = 0x00F8;
	
	/**
	 * ����Զ������ 
	 */
	public static final int CMD_ID_ENTIRE_CAR_OTA_UPGRADE = 0x00D5;
	
	/**
	 *  �������ն˲�ѯ����״̬
	 */
	public static final int CMD_ID_ENTIRE_CAR_OTA_UPGRADE_STATUS = 0x00D6;
	
	/**====================����start==========================*/
	//��ѯָ��
	public static final int CMD_ID_LEOPAARD_QUERY_CMD = 0x0320;
		
	//����ָ��
	public static final int CMD_ID_LEOPAARD_SET_CMD = 0x0321;
	
	//���촰
	public static final int CMD_ID_LEOPAARD_OPEN_SUNROOF = 0x0322;
	
	//���촰
	public static final int CMD_ID_LEOPAARD_CLOSE_SUNROOF = 0x0323;
	
	//����
	public static final int CMD_ID_LEOPAARD_OPEN_WINDOW = 0x0324;
		
	//�ش�
	public static final int CMD_ID_LEOPAARD_CLOSE_WINDOW = 0x0325;
	
	public static final int CMD_ID_LEOPAARD_HOT_AIRCONDITION = 0x0326;
	
	public static final int CMD_ID_LEOPAARD_COLD_AIRCONDITION = 0x0327;
	
	public static final int CMD_ID_LEOPAARD_FAULT_DIAGNOSIS = 0x0328;
	
	
	/**====================����end==========================*/
	/**
	 * ����ָ���sn (��̨��Ӧsn���·�sn����Ӧ)
	 */
	public static String getCmdCacheSn(String call, int cmdId) {
		return call + "_" + cmdId;
	}
}