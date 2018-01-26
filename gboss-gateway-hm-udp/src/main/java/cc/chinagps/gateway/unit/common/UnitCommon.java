package cc.chinagps.gateway.unit.common;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.seg.lib.net.server.tcp.BroadcastFilter;
import org.seg.lib.net.server.tcp.CommonSocketSession;
import org.seg.lib.net.server.tcp.SocketSession;
import org.seg.lib.net.server.tcp.TCPServer;
import org.seg.lib.util.SegPackageUtil;

import cc.chinagps.gateway.aplan.pkg.APlanUtil;
import cc.chinagps.gateway.unit.UnitServer;
import cc.chinagps.gateway.unit.UnitSocketSession;
import cc.chinagps.gateway.unit.beforemarket.hm.download.HMDownloadCmdEncoder;
import cc.chinagps.gateway.unit.beforemarket.hm.upload.HMUploadCmdHandlers;
import cc.chinagps.gateway.unit.beforemarket.kaiyi.download.KaiyiDownloadCmdEncoder;
import cc.chinagps.gateway.unit.beforemarket.kaiyi.upload.KaiyiUploadCmdHandlers;
import cc.chinagps.gateway.unit.beforemarket.yidongwang.download.YdwDownloadCmdEncoder;
import cc.chinagps.gateway.unit.beforemarket.yidongwang.upload.YdwUploadCmdHandlers;
import cc.chinagps.gateway.unit.db44.download.DB44DownloadCmdEncoder;
import cc.chinagps.gateway.unit.db44.upload.DB44UploadCmdHandlers;
import cc.chinagps.gateway.unit.kelx.download.KlxDownloadCmdEncoder;
import cc.chinagps.gateway.unit.kelx.upload.KlxUploadCmdHandlers;
import cc.chinagps.gateway.unit.leopaard.download.LeopaardDownloadCmdEncoder;
import cc.chinagps.gateway.unit.leopaard.upload.LeopaardUploadCmdHandlers;
import cc.chinagps.gateway.unit.oem.download.OEMDownloadCmdEncoder;
import cc.chinagps.gateway.unit.oem.upload.OEMUploadCmdHandlers;
import cc.chinagps.gateway.unit.pengaoda.download.PengAoDaDownloadCmdEncoder;
import cc.chinagps.gateway.unit.pengaoda.upload.PengAoDaUploadCmdHandlers;
import cc.chinagps.gateway.unit.seg.download.SegDownloadCmdEncoder;
import cc.chinagps.gateway.unit.seg.upload.SegUploadCmdHandlers;
import cc.chinagps.gateway.unit.udp.UdpServer;
import cc.chinagps.gateway.unit.useat.util.USeatUtil;
import cc.chinagps.gateway.util.Constants;

public class UnitCommon {
	/**
	 * Ⱥ����A�ƻ��ͻ���
	 */
	public static void sendDataToAPlanClients(byte[] pkg, UnitServer server, UnitSocketSession session){
		byte[] encode = APlanUtil.encodeAPlanDeliverData(pkg, server.getAPlanServer(), session);
		server.getAPlanServer().broadcastData(encode);
	}
	
	/**
	 * ��udpseverȺ����A�ƻ��ͻ���
	 */
	public static void sendDataToAPlanClientsFromUdpServer(byte[] pkg, UdpServer server, UnitSocketSession session){
		byte[] encode = APlanUtil.encodeAPlanDeliverData(pkg, server.getAPlanServer(), session);
		server.getAPlanServer().broadcastData(encode);
	}
	
	
	private static final byte _k = 0x28;	//(
	private static final byte _I = 0x49;	//I
	private static final byte _T = 0x54;	//T
	private static final byte _V = 0x56;	//V
	
	private static final byte HEARBEAT_START = (byte) 0xF0;
	private static final byte LOGIN_START = (byte) 0x80;
	
	/**
	 * Ⱥ����A�ƻ�����ͻ���
	 */
	public static void sendDataToAPlanAlarmClients(byte[] pkg, UnitServer server, UnitSocketSession session){
		if(pkg.length >= 17 && pkg[13] == _k && pkg[14] == _I && pkg[15] == _T && pkg[16] == _V){
			//����(ITV
			return;
		}
		
		if(pkg.length >= 3 && pkg[1] == HEARBEAT_START){
			//����������
			return;
		}
		
		if(pkg.length >= 3 && pkg[1] == LOGIN_START){
			//���˵�¼��
			return;
		}
		
		byte[] encode = APlanUtil.encodeAPlanDeliverData(pkg, server.getAPlanAlarmServer(), session);
		server.getAPlanAlarmServer().broadcastData(encode);
	}
	
	/**
	 * ��udpserverȺ����A�ƻ�����ͻ���
	 */
	public static void sendDataToAPlanAlarmClientsFromUdpServer(byte[] pkg, UdpServer server, UnitSocketSession session){
		if(pkg.length >= 17 && pkg[13] == _k && pkg[14] == _I && pkg[15] == _T && pkg[16] == _V){
			//����(ITV
			return;
		}
		
		if(pkg.length >= 3 && pkg[1] == HEARBEAT_START){
			//����������
			return;
		}
		
		if(pkg.length >= 3 && pkg[1] == LOGIN_START){
			//���˵�¼��
			return;
		}
		
		byte[] encode = APlanUtil.encodeAPlanDeliverData(pkg, server.getAPlanAlarmServer(), session);
		server.getAPlanAlarmServer().broadcastData(encode);
	}
	
	public static final byte TYPE_UPLOAD = 0;
	public static final byte TYPE_DOWNLOAD = 1;
	public static final byte TYPE_OFF_LINE_EVENT = 2;
	public static final byte TYPE_EXCEPTION = 3;
	
	/**
	 * ���͸�trace�ͻ���
	 * type 0:��̨�ϴ�  1:�·�����̨
	 */
	private static void sendTrace(byte[] source, UnitServer server, UnitSocketSession session, byte type){
		if(session.getUnitInfo() != null){
			String callLetter = session.getUnitInfo().getCallLetter();
			Map<CommonSocketSession, Long> list = server.getTraceList(callLetter);
			if(list != null){
				byte[] data = new byte[source.length + 1];
				data[0] = type;
				System.arraycopy(source, 0, data, 1, source.length);
				Iterator<CommonSocketSession> it = list.keySet().iterator();
				while(it.hasNext()){
					CommonSocketSession key = it.next();
					byte[] pkg;
					try {
						pkg = USeatUtil.segSeatPackageEncoder.encode(false, false, false, Constants.SYSTEM_ID_INT, Constants.INNER_CMD_ID_SEND_TRACE, SegPackageUtil.getSerialNumber(), data, null);
						key.sendData(pkg);
					} catch (IOException e) {
						server.exceptionCaught(e);
					}
				}
			}
		}
	}
	
	/**
	 * ��udpserver���͸�trace�ͻ���
	 * type 0:��̨�ϴ�  1:�·�����̨
	 */
	private static void sendTrace(byte[] source, UdpServer server, UnitSocketSession session, byte type){
		if(session.getUnitInfo() != null){
			String callLetter = session.getUnitInfo().getCallLetter();
			Map<CommonSocketSession, Long> list = server.getTraceList(callLetter);
			if(list != null){
				byte[] data = new byte[source.length + 1];
				data[0] = type;
				System.arraycopy(source, 0, data, 1, source.length);
				Iterator<CommonSocketSession> it = list.keySet().iterator();
				while(it.hasNext()){
					CommonSocketSession key = it.next();
					byte[] pkg;
					try {
						pkg = USeatUtil.segSeatPackageEncoder.encode(false, false, false, Constants.SYSTEM_ID_INT, Constants.INNER_CMD_ID_SEND_TRACE, SegPackageUtil.getSerialNumber(), data, null);
						key.sendData(pkg);
					} catch (IOException e) {
						server.exceptionCaught(e);
					}
				}
			}
		}
	}
	
	/**
	 * �����������ݸ�trace�ͻ���
	 */
	public static void sendUploadTrace(byte[] source, UnitServer server, UnitSocketSession session){
		session.IncreaseUploadPackageCount();
		sendTrace(source, server, session, TYPE_UPLOAD);
	}
	
	/**
	 * ��udpserver�����������ݸ�trace�ͻ���
	 */
	public static void sendUploadTrace(byte[] source, UdpServer server, UnitSocketSession session){
		session.IncreaseUploadPackageCount();
		sendTrace(source, server, session, TYPE_UPLOAD);
	}
	
	/**
	 * �����������ݸ�trace�ͻ���
	 */
	public static void sendDownloadTrace(byte[] data, UnitServer server, UnitSocketSession session){
		session.IncreaseDownloadPackageCount();
		sendTrace(data, server, session, TYPE_DOWNLOAD);
	}
	
	/**
	 * ��udpserver�����������ݸ�trace�ͻ���
	 */
	public static void sendDownloadTrace(byte[] data, UdpServer server, UnitSocketSession session){
		session.IncreaseDownloadPackageCount();
		sendTrace(data, server, session, TYPE_DOWNLOAD);
	}
	
	/**
	 * ���Ͷ����¼���trace�ͻ���
	 */
	public static void sendOffLineEventTrace(UnitServer server, UnitSocketSession session){
		sendTrace(Constants.ZERO_BYTES_DATA, server, session, TYPE_OFF_LINE_EVENT);
	}
	
	/**
	 * ��udpserver���Ͷ����¼���trace�ͻ���
	 */
	public static void sendOffLineEventTraceFromUdpServer(UdpServer server, UnitSocketSession session){
		sendTrace(Constants.ZERO_BYTES_DATA, server, session, TYPE_OFF_LINE_EVENT);
	}
	
	/**
	 * ���ͳ����ϱ������쳣��trace�ͻ���
	 */
	public static void sendExceptionTrace(byte[] data, UnitServer server, UnitSocketSession session){
		session.IncreaseDownloadPackageCount();
		sendTrace(data, server, session, TYPE_EXCEPTION);
	}
	
	/**
	 * ��udpserver���ͳ����ϱ������쳣��trace�ͻ���
	 */
	public static void sendExceptionTraceFromUdpServer(byte[] data, UdpServer server, UnitSocketSession session){
		session.IncreaseDownloadPackageCount();
		sendTrace(data, server, session, TYPE_EXCEPTION);
	}
	
	/**
	 * Ⱥ������¼���ݸ��ڲ��ͻ��˹�����
	 */
	public static BroadcastFilter unitLoginChangeFilter;
	
	static {
		unitLoginChangeFilter = new BroadcastFilter() {
			@Override
			public boolean isSend(TCPServer server, SocketSession session) {
				if(!session.isLogin()){
					return false;
				}
				
				Object obj = session.getAttribute(Constants.SESSION_KEY_RECEIVE_UNIT_LOGIN_CHANGE);
				if(!Boolean.class.isInstance(obj)){
					return false;
				}
				
				Boolean data = (Boolean) obj;
				return data;
			}
		};
	}
	
	
	//����̨���д���
	public static SegUploadCmdHandlers segUploadHandlers = new SegUploadCmdHandlers();
	
	//����̨���д���
	public static SegDownloadCmdEncoder segDownloadCmdEncoder = new SegDownloadCmdEncoder();
	
	//DB44��̨���д���
	public static DB44UploadCmdHandlers db44UploadHandlers = new DB44UploadCmdHandlers();
	
	//DB44��̨���д���
	public static DB44DownloadCmdEncoder db44DownloadCmdEncoder = new DB44DownloadCmdEncoder();
	
	//����̨���д���
	public static HMUploadCmdHandlers hmUploadHandlers = new HMUploadCmdHandlers();
	
	//����̨���д���
	public static HMDownloadCmdEncoder hmDownloadHandlers = new HMDownloadCmdEncoder();
	
	//һ������̨���д���
	public static YdwUploadCmdHandlers ydwUploadHandlers = new YdwUploadCmdHandlers();
	
	//һ������̨���д���
	public static YdwDownloadCmdEncoder ydwDownloadHandlers = new YdwDownloadCmdEncoder();
	
	//���������д���
	public static KlxUploadCmdHandlers klxUploadHandlers = new KlxUploadCmdHandlers();
	
	//���������д���
	public static KlxDownloadCmdEncoder klxDownloadHandlers = new KlxDownloadCmdEncoder();
	
	//���´����д���
	public static PengAoDaUploadCmdHandlers pengAoDaUploadHandlers = new PengAoDaUploadCmdHandlers();
	
	//���´����д���
	public static PengAoDaDownloadCmdEncoder pengAoDaDownloadCmdEncoder = new PengAoDaDownloadCmdEncoder();
	
	//�������д���
	public static KaiyiUploadCmdHandlers kaiyiUploadHandlers = new KaiyiUploadCmdHandlers();
	
	//�������д���
	public static KaiyiDownloadCmdEncoder kaiyiDownloadCmdEncoder = new KaiyiDownloadCmdEncoder();
	
	public static OEMUploadCmdHandlers oemUploadCmdHandlers = new OEMUploadCmdHandlers();
	
	public static OEMDownloadCmdEncoder oemDownloadCmdEncoder = new OEMDownloadCmdEncoder();
	
	public static LeopaardUploadCmdHandlers leopaardUploadCmdHandlers = new LeopaardUploadCmdHandlers();
	
	public static LeopaardDownloadCmdEncoder leopaardDownloadCmdEncoder = new LeopaardDownloadCmdEncoder();
	
}