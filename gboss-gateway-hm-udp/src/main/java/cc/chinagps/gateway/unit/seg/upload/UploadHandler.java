package cc.chinagps.gateway.unit.seg.upload;

import cc.chinagps.gateway.unit.UnitSocketSession;
import cc.chinagps.gateway.unit.UnitServer;

public interface UploadHandler {
	/**
	 * @return flag true:�ܴ���  false:���ܴ���
	 */
	public boolean handlerPackage(byte[] source, UnitServer server, UnitSocketSession session) throws Exception;
}