package cc.chinagps.gateway.unit.common;

import java.math.BigDecimal;
import java.util.Date;

public class Constants {
	/**�ٶ�ת��  1��=1.852ǧ��/Сʱ*/
	public static final BigDecimal M_SPEED_JIE_TO_KMH = new BigDecimal("1.852");
	
	/**GPSʱ�����ʱ,Ĭ��ʱ��*/
	public static final Date ERROR_DATE = new Date(0);
	
	/**�ٶȴ���ʱ,Ĭ���ٶ�*/
	public static final BigDecimal ERROR_SPEED = BigDecimal.ZERO;
}
