package cc.chinagps.gateway.unit.db44.upload;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cc.chinagps.gateway.unit.common.Constants;
import cc.chinagps.gateway.unit.db44.pkg.DB44Package;
import cc.chinagps.gateway.unit.db44.util.DB44TimeFormatUtil;
import cc.chinagps.gateway.util.CRC16;
import cc.chinagps.gateway.util.HexUtil;

/**
 * GPS��Ϣ
 * DB44��ʽ
 */
public class DB44GPSInfo {
	private boolean isLoc;
	
	private Date gpsTime;
	
	private double lng;
	
	private double lat;
	
	private int speed;
	
	private int course;
	
	private int height;
	
	private int totalDistance;
	
	private boolean isAlarm;
	
	private boolean isSpecialAlarm;
	
	private List<Integer> status = new ArrayList<Integer>();
	
	private int history;
	
	public int getHistory() {
		return history;
	}

	public void setHistory(int history) {
		this.history = history;
	}

	public boolean isLoc() {
		return isLoc;
	}

	public void setLoc(boolean isLoc) {
		this.isLoc = isLoc;
	}

	public Date getGpsTime() {
		return gpsTime;
	}

	public void setGpsTime(Date gpsTime) {
		this.gpsTime = gpsTime;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(int totalDistance) {
		this.totalDistance = totalDistance;
	}

	public boolean isAlarm() {
		return isAlarm;
	}

	public void setAlarm(boolean isAlarm) {
		this.isAlarm = isAlarm;
	}

	public boolean isSpecialAlarm() {
		return isSpecialAlarm;
	}

	public void setSpecialAlarm(boolean isSpecialAlarm) {
		this.isSpecialAlarm = isSpecialAlarm;
	}

	public List<Integer> getStatus() {
		return status;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder sb = new StringBuilder();
		sb.append("{gpsTime:" + sdf.format(gpsTime));
		sb.append(", isLoc:" + isLoc);
		sb.append(", lat:" + lat);
		sb.append(", lng:" + lng);
		sb.append(", speed:" + speed);
		sb.append(", course:" + course);
		sb.append(", height:" + height);
		sb.append(", totalDistance:" + totalDistance);
		//sb.append(", oilPercent:" + oilPercent);
		//sb.append(", temperature1:" + temperature1);
		//sb.append(", temperature2:" + temperature2);
		sb.append(", status:" + status);
		//sb.append(", extendsStatus(").append(extendsStatus.size()).append("):");
		//sb.append(getExtendInfo(extendsStatus));
		sb.append("}");
		
		return sb.toString();
	}
	
	public static DB44GPSInfo parse(byte[] data, int offset) throws Exception{
		int position = offset;
		String gpsTimeStr = "20" + org.seg.lib.util.Util.bcd2str(data, position, 6);
		position += 6;
		String lngStr = org.seg.lib.util.Util.bcd2str(data, position, 4);
		position += 4;
		String latStr = org.seg.lib.util.Util.bcd2str(data, position, 4);
		position += 4;
		int speed = data[position] & 0xFF;
		position += 1;
		int course = 2 * (data[position] & 0xFF);
		position += 1;
		int height = org.seg.lib.util.Util.getShort(data, position) & 0xFFFF;
		position += 2;
		String totalDistanceStr = org.seg.lib.util.Util.bcd2str(data, position, 4);
		position += 4;
		//status(8)
		
		Date gpsTime;
		try{
			gpsTime = DB44TimeFormatUtil.parseGMT8(gpsTimeStr);
		}catch (ParseException e) {
			gpsTime = Constants.ERROR_DATE;
		}
		double lng = getLngOrLat(lngStr);
		double lat = getLngOrLat(latStr);
		int totalDistance = Integer.valueOf(totalDistanceStr) * 100;
		
		DB44GPSInfo gps = new DB44GPSInfo();
		gps.setGpsTime(gpsTime);
		gps.setLng(lng);
		gps.setLat(lat);
		gps.setSpeed(speed);
		gps.setCourse(course);
		gps.setHeight(height);
		gps.setTotalDistance(totalDistance);
		
		analysisStatus(gps, data, position);
		
		return gps;
	}
	
	/**
	 * ����״̬
	 */
	private static void analysisStatus(DB44GPSInfo gps, byte[] status, int offset){
		byte status1 = status[offset];
		byte status2 = status[offset + 1];
		byte status3 = status[offset + 2];
		byte status4 = status[offset + 3];
		
		analysisStatus1(gps, status1);
		analysisStatus2(gps, status2);
		analysisStatus3(gps, status3);
		analysisStatus4(gps, status4);
	}
	
	/**
	 * ������һ��״̬
	 */
	private static void analysisStatus1(DB44GPSInfo gps, byte status){
		byte bit0 = (byte) (status & 1);
		byte bit1 = (byte) ((status >> 1) & 1);
		byte bit2 = (byte) ((status >> 2) & 1);
		byte bit3 = (byte) ((status >> 3) & 1);
		byte bit4 = (byte) ((status >> 4) & 1);
		byte bit5 = (byte) ((status >> 5) & 1);
		byte bit6 = (byte) ((status >> 6) & 1);
		
		if(bit0 == 0){
			//����
			gps.setLng(-gps.getLng());
		}
		
		if(bit1 == 0){
			//��γ
			gps.setLat(-gps.getLat());
		}
		
		if(bit2 == 1){
			//��������(��������δ������������ȫ�����д�״̬,ֻ��һ�������鴦��,�˴�����Ϊ����)
			gps.getStatus().add(5);
		}
		
		if(bit3 == 1){
			//����(����״̬��)
		}
		
		if(bit4 == 1){
			//���ٱ���
			gps.setAlarm(true);
			gps.getStatus().add(14);
		}
		
		if(bit5 == 1){
			//�𶯱���
			gps.setAlarm(true);
			gps.getStatus().add(18);
		}
		
		if(bit6 == 1){
			//����Դ�ϵ�
			gps.setAlarm(true);
			gps.getStatus().add(4);
		}
	}
	
	/**
	 * �����ڶ���״̬
	 */
	private static void analysisStatus2(DB44GPSInfo gps, byte status){
		byte bit0 = (byte) (status & 1);
		byte bit1 = (byte) ((status >> 1) & 1);
		byte bit2 = (byte) ((status >> 2) & 1);
		byte bit3 = (byte) ((status >> 3) & 1);
		byte bit4 = (byte) ((status >> 4) & 1);
		byte bit5 = (byte) ((status >> 5) & 1);
		
		if(bit0 == 1){
			//ɲ��
			gps.getStatus().add(39);
		}
		
		if(bit1 == 1){
			//����
			gps.getStatus().add(166);
		}
		
		if(bit2 == 1){
			//��ת���
			gps.getStatus().add(40);
		}
		
		if(bit3 == 1){
			//��ת���
			gps.getStatus().add(41);
		}
		
		if(bit4 == 1){
			//Զ���
			gps.getStatus().add(165);
		}
		
		if(bit5 == 1){
			//ACC
			gps.getStatus().add(171);
		}
		
	}
	
	/**
	 * ����������״̬
	 */
	private static void analysisStatus3(DB44GPSInfo gps, byte status){
		byte bit0 = (byte) (status & 1);
		byte bit1 = (byte) ((status >> 1) & 1);
		byte bit2 = (byte) ((status >> 2) & 1);
		byte bit3 = (byte) ((status >> 3) & 1);
		byte bit4 = (byte) ((status >> 4) & 1);
		byte bit5 = (byte) ((status >> 5) & 1);
		byte bit6 = (byte) ((status >> 6) & 1);
		
		if(bit0 == 1){
			//���Ƕ�λ����
			gps.setLoc(true);
			gps.getStatus().add(248);
		}
		
		if(bit1 == 1){
			//���Ƕ�λ���� ��·(����״̬)
		}
		
		if(bit2 == 1){
			//���Ƕ�λ���� ��·(����״̬)
		}
		
		if(bit3 == 1){
			//��λģ��  �쳣
			gps.getStatus().add(251);
		}
		
		if(bit4 == 1){
			//ͨ��ģ��  �쳣(����״̬)
		}
		
		if(bit5 == 1){
			//������ ����
			gps.setAlarm(true);
			gps.getStatus().add(159);
		}
		
		if(bit6 == 1){
			//������ ����
			gps.setAlarm(true);
			gps.getStatus().add(158);
		}
	}
	
	/**
	 * �������ĸ�״̬
	 */
	private static void analysisStatus4(DB44GPSInfo gps, byte status){
		byte bit0 = (byte) (status & 1);
		byte bit1 = (byte) ((status >> 1) & 1);
		byte bit2 = (byte) ((status >> 2) & 1);
		byte bit3 = (byte) ((status >> 3) & 1);
		
		byte bit4 = (byte) ((status >> 4) & 1);
		byte bit5 = (byte) ((status >> 5) & 1);
		byte bit6 = (byte) ((status >> 6) & 1);
		
		if(bit0 == 1){
			//���õ�� �쳣
			gps.getStatus().add(37);
		}
		
		if(bit1 == 1){
			//����դ��  Խ��
			gps.setAlarm(true);
			gps.getStatus().add(81);
		}
		
		if(bit2 == 1){
			//������ ON (��ACC��171���׸�죬��ʱ����)
			//gps.getStatus().add(33);
		}else{
			//gps.getStatus().add(23);
		}
		
		if(bit3 == 1){
			//ƣ�ͼ�ʻ ����
			gps.setAlarm(true);
			gps.getStatus().add(252);
		}
		
		if(bit4 == 1){
			//��ֹʱ����ʻ
			gps.setAlarm(true);
			gps.getStatus().add(24);
		}
		
		if(bit5 == 1){
			//�����ſ�������
			gps.setAlarm(true);
			gps.getStatus().add(19);
		}
		
		if(bit6 == 1){
			//ͣ��ʱ���������
			gps.setAlarm(true);
			gps.getStatus().add(153);
		}
	}
	
	private static final BigDecimal D60_000 = new BigDecimal(60000);
	private static final int SCALE = 6;
	private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
	
	/**
	 * �ַ�����γ��(DDDFF.FFF)ת��double
	 * 11249273 => 112.821217
	 */
	private static double getLngOrLat(String lngorlat){
		BigDecimal du = new BigDecimal(lngorlat.substring(0, 3));
		BigDecimal fen = new BigDecimal(lngorlat.substring(3));
		BigDecimal fen_to_du = fen.divide(D60_000, SCALE, ROUNDING_MODE);
		
		du = du.add(fen_to_du);
		return du.doubleValue();
	}
	
	//23 166 171 248 252
	public static void main(String[] args) throws Exception {
		//String hex = "7E527A515744517350516873414141414951554A445241416773384147565A2F6C6530734B574A685A6838707155596734586B6876624E656B347261336B415A6B5A62316951513D3D7F";
		//String hex = "7E5232455744517350516873414141414951554A445241416773384147565A2F6C5768734B574A685A6838707155596734586B6876624E656B347261336B415A6B5A56314D57773D3D7F";
		String hex = "7E52784D574451776D2F526F414141414951554A44524141677338414751342F6954483164436C6A774B34664E4B4557496646343362776A2F744F61577435414735436E747364513D7F";
		byte[] source = HexUtil.hexToBytes(hex);
		DB44Package pkg = DB44Package.parse(source);
		
		System.out.println("pkg:" + pkg);
		byte[] protocol = pkg.getProtocol();
		int crc16 = CRC16.crc16(protocol);
		System.out.println("protocol:" + HexUtil.byteToHexStr(protocol));
		System.out.println("crc16:" + Integer.toHexString(crc16));
		DB44GPSInfo gps = DB44GPSInfo.parse(protocol, 0);
		System.out.println(gps);
		
		//07 00 01 00 c0 81 00 9b
	}
}