package pochacm.dto;

import java.util.Arrays;

public class ChartCart {

	private String[] Date;
	private double[] totalSale;
	private double[] goalSale;
	
	@Override
	public String toString() {
		return "ChartCart [Date=" + Arrays.toString(Date) + ", totalSale=" + Arrays.toString(totalSale) + ", goalSale="
				+ Arrays.toString(goalSale) + "]";
	}
	public String[] getDate() {
		return Date;
	}
	public void setDate(String[] date) {
		Date = date;
	}
	public double[] getTotalSale() {
		return totalSale;
	}
	public void setTotalSale(double[] totalSale) {
		this.totalSale = totalSale;
	}
	public double[] getGoalSale() {
		return goalSale;
	}
	public void setGoalSale(double[] goalSale) {
		this.goalSale = goalSale;
	}
	
	
	
}
