package pochacm.dto;

public class SecondaryUnit {
	private int secondaryUnitNum;
	private String secondaryUnit;
	private int secondaryUnitQty;
	public int getsecondaryUnitNum() {
		return secondaryUnitNum;
	}
	public void setsecondaryUnitNum(int secondaryUnitNum) {
		this.secondaryUnitNum = secondaryUnitNum;
	}
	public String getsecondaryUnit() {
		return secondaryUnit;
	}
	public void setsecondaryUnit(String secondaryUnit) {
		this.secondaryUnit = secondaryUnit;
	}
	public int getsecondaryUnitQty() {
		return secondaryUnitQty;
	}
	public void setsecondaryUnitQty(int secondaryUnitQty) {
		this.secondaryUnitQty = secondaryUnitQty;
	}
	@Override
	public String toString() {
		return "secondaryUnit [secondaryUnitNum=" + secondaryUnitNum + ", secondaryUnit=" + secondaryUnit + ", secondaryUnitQty="
				+ secondaryUnitQty + "]";
	}
	
	
}
