package cc.chinagps.gateway.unit.seg.upload.cmds;

import cc.chinagps.gateway.unit.UnitSocketSession;
import cc.chinagps.gateway.unit.UnitServer;
import cc.chinagps.gateway.unit.seg.pkg.SegPackage;
import cc.chinagps.gateway.unit.seg.upload.UploadHandler;

/**
 * ����Ϊ�������ĸ�ʽ����ͳһתΪSegPackage���󣬱��ں�������
 * (����·���Ӳ������������ĸ�ʽ, ��Ҫ��鳵̨��¼��ʹ��CheckLoginHandler)
 */
public abstract class BaseUploadHandler implements UploadHandler{
	@Override
	public boolean handlerPackage(byte[] source, UnitServer server,
			UnitSocketSession session) throws Exception {
		SegPackage p = SegPackage.parse(source);
		return handlerPackage(p, server, session);
	}
	
	public abstract boolean handlerPackage(SegPackage pkg, UnitServer server,
			UnitSocketSession session) throws Exception;
}