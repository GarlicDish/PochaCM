package pochacm.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Sales {
	private int salesNum;
	private int userNum;
	private int recipeNum;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date salesDate;
	private int salesQty;
	private int salesSourceNum;
	@Override
	public String toString() {
		return "Sales [salesNum=" + salesNum + ", userNum=" + userNum + ", recipeNum=" + recipeNum + ", salesDate="
				+ salesDate + ", salesQty=" + salesQty + ", salesSourceNum=" + salesSourceNum + "]";
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
	public int getRecipeNum() {
		return recipeNum;
	}
	public void setRecipeNum(int recipeNum) {
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
	public int getSalesSourceNum() {
		return salesSourceNum;
	}
	public void setSalesSourceNum(int salesSourceNum) {
		this.salesSourceNum = salesSourceNum;
	}
	
}
