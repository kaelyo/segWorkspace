package cc.chinagps.gateway.unit.beans;

public class BaseStationInfo {
	//mcc �ƶ����Ҵ��루�й�Ϊ460�� 
	private String mcc;
	
	//mnc �ƶ�������루�й��ƶ�Ϊ0���й���ͨΪ1���й�����Ϊ2��
	private String mnc;
	
	//lac λ�������� 
	private int lac;
	
	//cid ��վ��� 
	private int cid;
	
	//bsss ��վ�ź�ǿ�� 
	private int bsss;

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getMnc() {
		return mnc;
	}

	public void setMnc(String mnc) {
		this.mnc = mnc;
	}

	public int getLac() {
		return lac;
	}

	public void setLac(int lac) {
		this.lac = lac;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getBsss() {
		return bsss;
	}

	public void setBsss(int bsss) {
		this.bsss = bsss;
	}

	@Override
	public String toString() {
		return "BaseStationInfo [mcc=" + mcc + ", mnc=" + mnc + ", lac=" + lac + ", cid=" + cid + ", bsss=" + bsss
				+ "]";
	}
	
}