package pochacm.dto;

public class PrimaryUnit {
	private int primaryUnitNum;
	private String primaryUnit;
	private int primaryUnitQty;
	public int getPrimaryUnitNum() {
		return primaryUnitNum;
	}
	public void setPrimaryUnitNum(int primaryUnitNum) {
		this.primaryUnitNum = primaryUnitNum;
	}
	public String getPrimaryUnit() {
		return primaryUnit;
	}
	public void setPrimaryUnit(String primaryUnit) {
		this.primaryUnit = primaryUnit;
	}
	public int getPrimaryUnitQty() {
		return primaryUnitQty;
	}
	public void setPrimaryUnitQty(int primaryUnitQty) {
		this.primaryUnitQty = primaryUnitQty;
	}
	@Override
	public String toString() {
		return "PrimaryUnit [primaryUnitNum=" + primaryUnitNum + ", primaryUnit=" + primaryUnit + ", primaryUnitQty="
				+ primaryUnitQty + "]";
	}
	
	
}
