package pochacm.dto;

public class SecondaryUnit {
	private int secondaryUnitNum;
	private int itemNum;
	private String secondaryUnit;
	private int secondaryUnitQty;
	
	@Override
	public String toString() {
		return "SecondaryUnit [secondaryUnitNum=" + secondaryUnitNum + ", itemNum=" + itemNum + ", secondaryUnit="
				+ secondaryUnit + ", secondaryUnitQty=" + secondaryUnitQty + "]";
	}
	public int getSecondaryUnitNum() {
		return secondaryUnitNum;
	}
	public void setSecondaryUnitNum(int secondaryUnitNum) {
		this.secondaryUnitNum = secondaryUnitNum;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	public String getSecondaryUnit() {
		return secondaryUnit;
	}
	public void setSecondaryUnit(String secondaryUnit) {
		this.secondaryUnit = secondaryUnit;
	}
	public int getSecondaryUnitQty() {
		return secondaryUnitQty;
	}
	public void setSecondaryUnitQty(int secondaryUnitQty) {
		this.secondaryUnitQty = secondaryUnitQty;
	}
	
	
	
}
