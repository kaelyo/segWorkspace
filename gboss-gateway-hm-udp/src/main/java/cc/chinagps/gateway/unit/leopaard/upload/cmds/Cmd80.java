package cc.chinagps.gateway.unit.leopaard.upload.cmds;

import org.apache.log4j.Logger;
import org.seg.lib.util.Util;

import cc.chinagps.gateway.aplan.pkg.APlanUtil;
import cc.chinagps.gateway.buff.CommandBuff;
import cc.chinagps.gateway.buff.CommandBuff.Command;
import cc.chinagps.gateway.buff.CommandBuff.CommandResponse.Builder;
import cc.chinagps.gateway.log.LogManager;
import cc.chinagps.gateway.seat.cmd.CmdResponseUtil;
import cc.chinagps.gateway.seat.cmd.CmdUtil;
import cc.chinagps.gateway.seat.cmd.ServerToUnitCommand;
import cc.chinagps.gateway.unit.UnitServer;
import cc.chinagps.gateway.unit.UnitSocketSession;
import cc.chinagps.gateway.unit.leopaard.pkg.LeopaardPackage;
import cc.chinagps.gateway.unit.leopaard.util.LeopaardPkgUtil;
import cc.chinagps.gateway.util.Constants;

/**
 * ��ѯָ��Ӧ��
 */
public class Cmd80 extends CheckLoginHandler {
	private Logger logger_debug = Logger.getLogger(LogManager.LOGGER_NAME_DEBUG);

	@Override
	public boolean handlerPackageSessionChecked(LeopaardPackage ppkg, UnitServer server, UnitSocketSession session)
			throws Exception {

		byte[] body = ppkg.getDataUnit();
		int pos = 0;

		String responseTime = cc.chinagps.gateway.util.Util.getDateTime(body, pos, 6);
		logger_debug.debug("[leopaard] cmd80 responseTime:" + responseTime);
		pos += 6;
		int paramNum = body[pos++] & 0xFF;
		int index = 0;
		String callLetter = session.getUnitInfo().getCallLetter();
		String cacheSN = LeopaardPkgUtil.getCmdCacheSn(callLetter, ppkg.getHead().getSerialNo());
		ServerToUnitCommand cache = server.getServerToUnitCommandCache().getAndRemoveCommand(cacheSN);
		Builder builder = CommandBuff.CommandResponse.newBuilder();
		builder.setCallLetter(callLetter);
		builder.addParams(responseTime);
		String params = null;
		while (index < paramNum) {
			int cmdId = body[pos++] & 0xFF;
			switch (cmdId) {
			// �������ĺ���
			case 0x0B:
				String serviceCenterCall = APlanUtil.getCString(body, pos, 16);
				pos += 16;
				builder.addParams(cmdId + "@" + serviceCenterCall);
				break;
			// ����ҵ�����ĺ��롢���к���
			case 0x0C:
				String smCallUp = APlanUtil.getCString(body, pos, 25);
				pos += 25;
				builder.addParams(cmdId + "@" + smCallUp);
				break;
			// ����ҵ�����ĺ��롢���к���
			case 0x0D:
				String smCallDown = APlanUtil.getCString(body, pos, 25);
				pos += 25;
				builder.addParams(cmdId + "@" + smCallDown);
				break;
			// E-call ����绰
			case 0x0E:
				String eCall = APlanUtil.getCString(body, pos, 16);
				pos += 16;
				builder.addParams(cmdId + "@" + eCall);
				break;
			// I-call ����绰
			case 0x0F:
				String iCall = APlanUtil.getCString(body, pos, 16);
				pos += 16;
				builder.addParams(cmdId + "@" + iCall);
				break;
			case 0x10:// acc�Ƿ��ϱ�
				byte isAccDeliver = body[pos++];
				builder.addParams(cmdId + "@" + isAccDeliver);
				break;
			case 0x11:// �����Ƿ��ϱ�
				byte isSleepDeliver = body[pos++];
				builder.addParams(cmdId + "@" + isSleepDeliver);
				break;
			case 0x12:// �ػ��Ƿ��ϱ�
				byte isPowerOffDeliver = body[pos++];
				builder.addParams(cmdId + "@" + isPowerOffDeliver);
				break;
			case 0x13:// ����仯�Ƿ��ϱ�
				byte isVehicleChangeDeliver = body[pos++];
				builder.addParams(cmdId + "@" + isVehicleChangeDeliver);
				break;
			case 0x14:// �����Ƿ��ϱ�
				byte isFaultDeliver = body[pos++];
				builder.addParams(cmdId + "@" + isFaultDeliver);
				break;
			case 0x16:// ���������Ϣ
				pos += 5;
				break;
			case 0x17:// ���ݲɼ���Ϣ
				byte collectParamsNum = body[pos++];
				params = cmdId + "@" + collectParamsNum;
				short time = Util.getShort(body, pos);
				pos += 2;
				params += "," + time;
				for (int i = 0; i < collectParamsNum; i++) {
					int param = Util.getShort(body, pos);
					params += "," + param;
					pos += 2;
				}
				builder.addParams(params);
				break;
			case 0x18:// ������������״̬
				byte isSoundDeviceConnected = body[pos++];
				builder.addParams(cmdId + "@" + isSoundDeviceConnected);
				break;
			case 0x19:// ��ʱ�ϱ���Ϣ
				short interval = (short) (Util.getShort(body, pos) & 0xFFFF);
				builder.addParams(cmdId + "@" + interval);
				break;
			case 0x1A:// �����ն���Ϣ
				break;
			case 0x1B:// ���������������
				String masterIp = cc.chinagps.gateway.util.Util.getIP(body, pos);
				pos += 4;
				short masterPort = (short) (Util.getShort(body, pos) & 0xFFFF);
				pos += 2;
				builder.addParams(cmdId + "@" + masterIp + "," + masterPort);
				break;
			case 0x1C:// �ӷ������������
				String slaveIp = cc.chinagps.gateway.util.Util.getIP(body, pos);
				pos += 4;
				short slavePort = (short) (Util.getShort(body, pos) & 0xFFFF);
				pos += 2;
				builder.addParams(cmdId + "@" + slaveIp + "," + slavePort);
				break;
			case 0x20:// ��ʱ�ϱ�����
				byte deliverContent = body[pos++];
				builder.addParams(cmdId + "@" + deliverContent);
				break;
			case 0x21://
				String apn_tbox = APlanUtil.getCString(body, pos, 32);
				pos += 32;
				builder.addParams(cmdId + "@" + apn_tbox);
				break;
			case 0x22://
				String apn_HU = APlanUtil.getCString(body, pos, 32);
				pos += 32;
				builder.addParams(cmdId + "@" + apn_HU);
				break;
			case 0x23:
				byte pepsEnable = body[pos++];
				builder.addParams(cmdId + "@" + pepsEnable);
				break;
			case 0x24:
				byte pepsAuthEnable = body[pos++];
				builder.addParams(cmdId + "@" + pepsAuthEnable);
				break;
			default:
				break;
			}
			index++;
		}
		int unit_ack_result = Util.getShort(body, pos) & 0xFFFF;
		pos += 2;
		if (unit_ack_result == 0) {
			builder.setResult(Constants.RESULT_SUCCESS);
		} else {
			builder.setResult(Constants.RESULT_UNIT_ACK_FAIL);
		}

		builder.addParams(String.valueOf(unit_ack_result));
		if (cache != null) {
			Command usercmd = cache.getUserCommand();
			builder.setSn(usercmd.getSn());
			builder.setCmdId(usercmd.getCmdId());

			CmdResponseUtil.simpleCommandResponse(cache, builder);
		} else {
			builder.setSn("");
			builder.setCallLetter(callLetter);
			builder.setCmdId(CmdUtil.CMD_ID_LEOPAARD_QUERY_CMD);

			builder.setUnitsn(ppkg.getHead().getSerialNo() & 0xFFFF);
			CmdResponseUtil.simpleCommandResponse(cache, builder);
			server.getExportMQ().addCommandResponse(builder.build());
		}

		return true;
	}
}