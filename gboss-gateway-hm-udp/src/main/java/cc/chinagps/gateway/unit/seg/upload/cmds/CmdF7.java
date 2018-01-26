package cc.chinagps.gateway.unit.seg.upload.cmds;

import cc.chinagps.gateway.unit.UnitServer;
import cc.chinagps.gateway.unit.UnitSocketSession;
import cc.chinagps.gateway.unit.seg.upload.UploadHandler;
import cc.chinagps.gateway.unit.seg.util.SegPkgUtil;

/**
 * ��ǿ�Ͱ���GPRS���ְ�
 */
public class CmdF7 implements UploadHandler{
	@Override
	public boolean handlerPackage(byte[] source,
			UnitServer server, UnitSocketSession session) throws Exception {
		byte[] unes = SegPkgUtil.unescape(source);
		if(unes.length == 5 && unes[2] == 1){
			//��ǿ�Ͱ���GPRS���ְ�
			byte[] es = SegPkgUtil.escape(unes);
			session.sendData(es);
			return true;
		}
		
		return false;
	}
}