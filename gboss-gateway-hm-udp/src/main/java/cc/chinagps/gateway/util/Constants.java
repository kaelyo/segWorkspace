package cc.chinagps.gateway.util;

public class Constants {
	public static final byte[] ZERO_BYTES_DATA = new byte[0];
	
	public static final String CHAR_ENCODING_ASCII = "ascii";
	
	public static final String VERSION = "v1.5.0(00006y)_20150928";		//�汾
	
	public static final short INNER_CMD_ID_CMD = 111;					//�·�ָ��
	
	public static final short INNER_CMD_ID_RESPONSE = 112;				//ָ���Ӧ
	
	public static final short INNER_CMD_ID_LOAD_VEHICLE = 113;			//���س����б�
	
	public static final short INNER_CMD_ID_LOAD_VEHICLE_ACK = 114;		//���س����б��Ӧ
	
	public static final short INNER_CMD_ID_VEHICLE_ONLINE_NOTIFY = 115;	//��������֪ͨ
	
	public static final short INNER_CMD_ID_VEHICLE_OFFLINE_NOTIFY = 116;//��������֪ͨ
	
	public static final short INNER_CMD_ID_SHOW_STATUS = 117;			//��ʾ״̬
	
	public static final short INNER_CMD_ID_SHOW_STATUS_ACK = 118;		//��ʾ״̬��Ӧ
	
	public static final short INNER_CMD_ID_ADD_TRACE = 119;				//���Trace
	
	public static final short INNER_CMD_ID_ADD_TRACE_ACK = 120;			//���Trace��Ӧ
	
	public static final short INNER_CMD_ID_SEND_TRACE = 121;			//����Trace����
	
	public static final short INNER_CMD_ID_REMOVE_TRACE = 122;			//�Ƴ�Trace
	
	public static final short INNER_CMD_ID_REMOVE_TRACE_ACK = 123;		//�Ƴ�Trace��Ӧ
	
	/**
	 * ָ���Ӧ
	 */
	public static final int RESULT_SUCCESS = 0;				//�ɹ�
	public static final int RESULT_FAIL_OTHERS = -1;		//ʧ��
	public static final int RESULT_UNIT_OFFLINE = 10;		//��̨������
	public static final int RESULT_SEND_DATA_FAIL = 17;		//��������ʧ��
	public static final int RESULT_WRONG_FORMAT = 16;		//���ݸ�ʽ����
	public static final int RESULT_TIMEOUT = 18;			//�ȴ���̨��Ӧ��ʱ
	public static final int RESULT_UNIT_ACK_FAIL = 6;		//��̨�����ظ�ʧ��
	public static final int RESULT_UNIT_EXE_FAIL = 32;		//��ִ̨��ʧ��
	
	//seat session
	public static final String SESSION_KEY_RECEIVE_UNIT_LOGIN_CHANGE = "unit_login_change";
	
	public static final String SESSION_KEY_TRACE_UNIT = "trace_unit";
	
	//ϵͳ���
	public static final String SYSTEM_ID;
	public static final int SYSTEM_ID_INT;
	public static final boolean IS_UPDATE_SERVER;
	
	static{
		SYSTEM_ID = SystemConfig.getSystemProperty("system_id");
		SYSTEM_ID_INT = Integer.valueOf(SYSTEM_ID);
		
		IS_UPDATE_SERVER = Boolean.valueOf(SystemConfig.getSystemProperty("is_update_server"));
	}
}