package cc.chinagps.gateway.unit.kelong.upload.cmds;

import org.apache.log4j.Logger;

import cc.chinagps.gateway.log.LogManager;
import cc.chinagps.gateway.unit.UnitServer;
import cc.chinagps.gateway.unit.UnitSocketSession;
import cc.chinagps.gateway.unit.kelong.define.KeLongDefines;
import cc.chinagps.gateway.unit.kelong.pkg.KeLongPackage;
import cc.chinagps.gateway.unit.kelong.upload.bean.DataStorage;
import cc.chinagps.gateway.unit.kelong.upload.bean.KeLongCANInfo;
import cc.chinagps.gateway.unit.kelong.upload.bean.KeLongGPSInfo;
import cc.chinagps.gateway.unit.kelong.upload.bean.KelongBaseStationPkg;
import cc.chinagps.gateway.unit.kelong.util.KeLongUploadUtil;
import cc.chinagps.gateway.util.HexUtil;

/**
 * 
* @ClassName: Cmd0011
* @Description: ��վ��λ���ݰ�
* @author dy
* @date 2017��5��23�� ����12:23:39
*
 */
public class Cmd0011 extends CheckLoginHandler {
	private Logger logger_debug = Logger.getLogger(LogManager.LOGGER_NAME_DEBUG);
	
	/**
	 * 
	 * @todo:������վ����վ��Ϣ;
	 * @author:cj
	 * @param:
	 * @return:
	 * @remark:
	 */
	@Override
	public boolean handlerPackageSessionChecked(KeLongPackage pkg, UnitServer server, UnitSocketSession session)
			throws Exception {
		try {
			byte data[] = pkg.getData();
			int pos = 0 ;
			int gpsNum = data[pos++];

			for (int i = 0; i < gpsNum; i++) {
				KelongBaseStationPkg klBaseStationPkg = KelongBaseStationPkg.parse(data, pos);
				pos += 55;
				logger_debug.debug("[KeLong][cmd0011]KeLongBasestationPkg:" + klBaseStationPkg.toString()+",source:"+ HexUtil.byteToHexStr(pkg.getSource()));
				//һ.����baseStation������				
				String callLetter = session.getUnitInfo().getCallLetter();
				if(null!=callLetter && !"".equals(callLetter)){
					DataStorage.getBaseStationMap().put(callLetter, klBaseStationPkg.getKeLongBaseStationInfo());
				}
				//��.��¡��վ��ת��¡gps��Ϣ��   ����:Ϊ��ֹ������ʱ�䲻��λ����gps�ϱ�
				//1.��װ��¡gps��Ϣ
				KeLongGPSInfo klGpsInfo= KelongBaseStationPkg.makeupGpsInfoPkg(klBaseStationPkg);				
				boolean isHistory = klGpsInfo.isHistory();
				//2.�ӻ�����ȡCAN�е����� 
				if(null != DataStorage.getCanMap().get(callLetter)){					
					KeLongCANInfo can= DataStorage.getCanMap().get(callLetter);
					//can�����е�ʱ���GPSʱ�����3����֮��
					if(Math.abs(klGpsInfo.getGpsTime()-can.getCanTime())<=KeLongDefines.CONSTANT){						
						klGpsInfo.getKeLongOBDInfo().setRemainOil(can.getRemainingFuel());
					}
				}
				logger_debug.debug("[KeLong][cmd0011]makeupKlGpsInfo:" + klGpsInfo);
				//3.����ת����Ŀ�¡gps��Ϣ��				
				KeLongUploadUtil.handleGPS(pkg, server, session, klGpsInfo, isHistory);
			}
			//4.������Ӧ;
			KeLongUploadUtil.commonMsgAck(session, pkg, (short) 0x8000, (short) 0x0011, (byte) 0x00);
		} catch (Exception e) {
			System.out.println("e:" + e.getMessage());
			// TODO: handle exception
			KeLongUploadUtil.commonMsgAck(session, pkg, (short) 0x8000, (short) 0x0011, (byte) 0x01);
			throw e;
		}
		return true;
	}
	
}