package pochacm.dto;

import java.sql.Date;

public class Sales {
	private int salesNum;
	private int userNum;
	private int recipeNum;
	private Date salesDate;
	private int salesQty;
	
	@Override
	public String toString() {
		return "Sales [salesNum=" + salesNum + ", userNum=" + userNum + ", recipeNum=" + recipeNum + ", salesDate="
				+ salesDate + ", salesQty=" + salesQty + "]";
	}
	public int getSalesNum() {
		return salesNum;
	}
	public void setSalesNum(int salesNum) {
		this.salesNum = salesNum;
	}
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public int getrecipeNum() {
		return recipeNum;
	}
	public void setrecipeNum(int recipeNum) {
		this.recipeNum = recipeNum;
	}
	public Date getSalesDate() {
		return salesDate;
	}
	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}
	public int getSalesQty() {
		return salesQty;
	}
	public void setSalesQty(int salesQty) {
		this.salesQty = salesQty;
	}
	
	
}
