package pochacm.dto;

public class Position {
	public int positionNum;
	public String positionName;
	@Override
	public String toString() {
		return "Position [positionNum=" + positionNum + ", positionName=" + positionName + "]";
	}
	public int getPositionNum() {
		return positionNum;
	}
	public void setPositionNum(int positionNum) {
		this.positionNum = positionNum;
	}
	
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	
	
}
