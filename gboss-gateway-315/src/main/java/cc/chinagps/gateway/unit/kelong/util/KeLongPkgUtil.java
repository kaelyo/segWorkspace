package cc.chinagps.gateway.unit.kelong.util;

import cc.chinagps.gateway.unit.UnitSocketSession;
import cc.chinagps.gateway.unit.kelong.define.KeLongDefines;

public class KeLongPkgUtil {
	/**
	 * ��ȡָ��sn
	 */
	public static short getSn(UnitSocketSession unitSession){
		synchronized (unitSession) {
			Object obj = unitSession.getAttribute(KeLongDefines.OBD_SESSION_KEY_SN);
			Short v;
			if(obj == null){
				v = 1;
			}else{
				v = (Short) obj;
				v++;
			}
			
			unitSession.setAttribute(KeLongDefines.OBD_SESSION_KEY_SN, v);
			return v;
		}
	}
	/**
	 * ��ȡ�·�ָ���sn
	 */
	public static String getCmdCacheSn(String callLetter, short headSN){
		return callLetter + "_" + headSN;
	}
}
