package cc.chinagps.gateway.unit.beforemarket.hm.upload.beans;

public class HMFaultLightInfo {
	//���ݳ���(�ֽ�)
	private int dataLength;
		
	//is lost
	private boolean isABSLost;
	
	private boolean isESPLost;
	
	private boolean isEMSLost;
	
	private boolean isPEPSLost;
	
	private boolean isTCULost;
	
	private boolean isBCMLost;
	
	private boolean isICMLost;
	
	//has faults
	private boolean hasEBDFaults;
	
	private boolean hasABSFaults;
	
	private boolean hasESPFaults;
	
	private boolean hasSVSFaults;
	
	private boolean hasMILFaults;
	
	private boolean hasTCUFaults;
	
	private boolean hasPEPSFaults;
	
	private boolean hasTBOXFaults;

	public int getDataLength() {
		return dataLength;
	}

	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}
	
	public boolean isABSLost() {
		return isABSLost;
	}

	public void setABSLost(boolean isABSLost) {
		this.isABSLost = isABSLost;
	}

	public boolean isESPLost() {
		return isESPLost;
	}

	public void setESPLost(boolean isESPLost) {
		this.isESPLost = isESPLost;
	}

	public boolean isEMSLost() {
		return isEMSLost;
	}

	public void setEMSLost(boolean isEMSLost) {
		this.isEMSLost = isEMSLost;
	}

	public boolean isPEPSLost() {
		return isPEPSLost;
	}

	public void setPEPSLost(boolean isPEPSLost) {
		this.isPEPSLost = isPEPSLost;
	}

	public boolean isTCULost() {
		return isTCULost;
	}

	public void setTCULost(boolean isTCULost) {
		this.isTCULost = isTCULost;
	}

	public boolean isBCMLost() {
		return isBCMLost;
	}

	public void setBCMLost(boolean isBCMLost) {
		this.isBCMLost = isBCMLost;
	}

	public boolean isICMLost() {
		return isICMLost;
	}

	public void setICMLost(boolean isICMLost) {
		this.isICMLost = isICMLost;
	}

	public boolean isHasEBDFaults() {
		return hasEBDFaults;
	}

	public void setHasEBDFaults(boolean hasEBDFaults) {
		this.hasEBDFaults = hasEBDFaults;
	}

	public boolean isHasABSFaults() {
		return hasABSFaults;
	}

	public void setHasABSFaults(boolean hasABSFaults) {
		this.hasABSFaults = hasABSFaults;
	}

	public boolean isHasESPFaults() {
		return hasESPFaults;
	}

	public void setHasESPFaults(boolean hasESPFaults) {
		this.hasESPFaults = hasESPFaults;
	}

	public boolean isHasSVSFaults() {
		return hasSVSFaults;
	}

	public void setHasSVSFaults(boolean hasSVSFaults) {
		this.hasSVSFaults = hasSVSFaults;
	}

	public boolean isHasMILFaults() {
		return hasMILFaults;
	}

	public void setHasMILFaults(boolean hasMILFaults) {
		this.hasMILFaults = hasMILFaults;
	}

	public boolean isHasTCUFaults() {
		return hasTCUFaults;
	}

	public void setHasTCUFaults(boolean hasTCUFaults) {
		this.hasTCUFaults = hasTCUFaults;
	}

	public boolean isHasPEPSFaults() {
		return hasPEPSFaults;
	}

	public void setHasPEPSFaults(boolean hasPEPSFaults) {
		this.hasPEPSFaults = hasPEPSFaults;
	}

	public boolean isHasTBOXFaults() {
		return hasTBOXFaults;
	}

	public void setHasTBOXFaults(boolean hasTBOXFaults) {
		this.hasTBOXFaults = hasTBOXFaults;
	}
	
	public static HMFaultLightInfo parse(byte[] data, int offset) throws Exception{
		HMFaultLightInfo faultLightInfo = new HMFaultLightInfo();
		//ǰ1�ֽ�Ϊ����
		int position = offset;
		int dataLength = data[position] & 0xFF;
		position += 1;
		
		//flag1
		byte flag1 = data[position];
		position += 1;
		
		boolean isABSLost = (flag1 & 0x80) != 0;
		boolean isESPLost = (flag1 & 0x40) != 0;
		boolean isEMSLost = (flag1 & 0x20) != 0;
		boolean isPEPSLost = (flag1 & 0x10) != 0;
		boolean isTCULost = (flag1 & 0x8) != 0;
		boolean isBCMLost = (flag1 & 0x4) != 0;
		boolean isICMLost = (flag1 & 0x2) != 0;
		
		//flag2
		byte flag2 = data[position];
		position += 1;
		
		boolean hasEBDFaults = (flag2 & 0x80) != 0;
		boolean hasABSFaults = (flag2 & 0x40) != 0;
		boolean hasESPFaults = (flag2 & 0x20) != 0;
		boolean hasSVSFaults = (flag2 & 0x10) != 0;
		boolean hasMILFaults = (flag2 & 0x8) != 0;
		boolean hasTCUFaults = (flag2 & 0x4) != 0;
		boolean hasPEPSFaults = (flag2 & 0x2) != 0;
		boolean hasTBOXFaults = (flag2 & 0x1) != 0;
		
		faultLightInfo.setDataLength(dataLength);
		
		faultLightInfo.setABSLost(isABSLost);
		faultLightInfo.setESPLost(isESPLost);
		faultLightInfo.setEMSLost(isEMSLost);
		faultLightInfo.setPEPSLost(isPEPSLost);
		faultLightInfo.setTCULost(isTCULost);
		faultLightInfo.setBCMLost(isBCMLost);
		faultLightInfo.setICMLost(isICMLost);
		
		faultLightInfo.setHasEBDFaults(hasEBDFaults);
		faultLightInfo.setHasABSFaults(hasABSFaults);
		faultLightInfo.setHasESPFaults(hasESPFaults);
		faultLightInfo.setHasSVSFaults(hasSVSFaults);
		faultLightInfo.setHasMILFaults(hasMILFaults);
		faultLightInfo.setHasTCUFaults(hasTCUFaults);
		faultLightInfo.setHasPEPSFaults(hasPEPSFaults);
		faultLightInfo.setHasTBOXFaults(hasTBOXFaults);
		
		return faultLightInfo;
	}
}