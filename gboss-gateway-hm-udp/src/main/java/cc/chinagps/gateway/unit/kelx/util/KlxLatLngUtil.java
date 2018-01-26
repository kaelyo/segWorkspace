package cc.chinagps.gateway.unit.kelx.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.seg.lib.util.Util;

public class KlxLatLngUtil {
	public static final int SCALE = 6;
	public static final BigDecimal D60 = new BigDecimal(60);
	public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
	
	public static double getLng(byte[] data, int offset, byte flag){
		String str = Util.bcd2str(data, offset, 4);
		String str_d = str.substring(0, 3);
		String str_m_int = str.substring(3, 5);
		String str_m_float = str.substring(5);
		
		BigDecimal dec_d = new BigDecimal(str_d);
		BigDecimal dec_m = new BigDecimal(str_m_int + "." + str_m_float);
		dec_m = dec_m.divide(D60, SCALE, ROUNDING_MODE);
		dec_d = dec_d.add(dec_m);

		boolean isEast = (flag & 0x8) != 0;
		if(!isEast){
			dec_d = dec_d.negate();
		}
		
		return dec_d.doubleValue();
	}
	
	public static double getLat(byte[] data, int offset, byte flag){
		String str = Util.bcd2str(data, offset, 4);
		String str_d = str.substring(0, 2);
		String str_m_int = str.substring(2, 4);
		String str_m_float = str.substring(4);
		
		BigDecimal dec_d = new BigDecimal(str_d);
		BigDecimal dec_m = new BigDecimal(str_m_int + "." + str_m_float);
		dec_m = dec_m.divide(D60, SCALE, ROUNDING_MODE);
		dec_d = dec_d.add(dec_m);
		
		boolean isNorth = (flag & 0x4) != 0;
		if(!isNorth){
			dec_d = dec_d.negate();
		}
		
		return dec_d.doubleValue();
	}
	
	public static void main(String[] args) {
		byte[] data_lng = {0x11,0x60, 0x40, 0x00};
		byte flag = 0xC;
		
		double lng = getLng(data_lng, 0, flag);
		System.out.println(lng);
		
		byte[] data_lat = {0x33,0x32, 0x00, 0x00};
		double lat = getLat(data_lat, 0, flag);
		System.out.println(lat);
	}
}