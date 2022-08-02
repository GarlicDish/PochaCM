
package pochacm.dto;

public class SalesSource {

	private int salesSourceNum;
	private String salesSourceName;
	@Override
	public String toString() {
		return "SalesSource [salesSourceNum=" + salesSourceNum + ", salesSourceName=" + salesSourceName + "]";
	}
	public int getSalesSourceNum() {
		return salesSourceNum;
	}
	public void setSalesSourceNum(int salesSourceNum) {
		this.salesSourceNum = salesSourceNum;
	}
	public String getSalesSourceName() {
		return salesSourceName;
	}
	public void setSalesSourceName(String salesSourceName) {
		this.salesSourceName = salesSourceName;
	}
	
	
}
