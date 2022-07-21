package sjim.dto;

public class Unit {

	private int unitNum;
	private String primaryUnit;
	private String secondaryUnit;

	@Override
	public String toString() {
		return "Unit [unitNum=" + unitNum + ", primaryUnit=" + primaryUnit + ", secondaryUnit=" + secondaryUnit + "]";
	}
	public int getUnitNum() {
		return unitNum;
	}
	public void setUnitNum(int unitNum) {
		this.unitNum = unitNum;
	}
	public String getPrimaryUnit() {
		return primaryUnit;
	}
	public void setPrimaryUnit(String primaryUnit) {
		this.primaryUnit = primaryUnit;
	}
	public String getSecondaryUnit() {
		return secondaryUnit;
	}
	public void setSecondaryUnit(String secondaryUnit) {
		this.secondaryUnit = secondaryUnit;
	}
	
	
}
