package pochacm.dto;

public class PrimaryUnit {

	private int primaryUnitNum;
	private int itemNum;
	private String primaryUnit;
	private int primaryUnitQty;

	@Override
	public String toString() {
		return "PrimaryUnit [primaryUnitNum=" + primaryUnitNum + ", itemNum=" + itemNum + ", primaryUnit=" + primaryUnit
				+ ", primaryUnitQty=" + primaryUnitQty + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public int getPrimaryUnitNum() {
		return primaryUnitNum;
	}

	public void setPrimaryUnitNum(int primaryUnitNum) {
		this.primaryUnitNum = primaryUnitNum;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
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

}
