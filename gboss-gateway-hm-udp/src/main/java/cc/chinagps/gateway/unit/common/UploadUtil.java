package cc.chinagps.gateway.unit.common;

import cc.chinagps.gateway.unit.UnitServer;
import cc.chinagps.gateway.unit.UnitSocketSession;
import cc.chinagps.gateway.unit.beans.Loginout;
import cc.chinagps.gateway.unit.udp.UdpServer;

public class UploadUtil {
	/**
	 * �����¼��¼��Ϣ
	 */
	public static void handleLoginout(UnitServer server,
			UnitSocketSession session, Loginout loginout){
		server.getExportMQ().addUnitLoginOut(loginout);
	}
	
	/**
	 * UdpServer�����¼��¼��Ϣ
	 * @param server
	 * @param session
	 * @param loginout
	 */
	public static void handleLoginout(UdpServer server,
			UnitSocketSession session, Loginout loginout){
		server.getExportMQ().addUnitLoginOut(loginout);
	}
}