package pochacm.dto;

import java.util.Arrays;
import java.util.Date;

public class ChartCart {

	private Date[] date = new Date[7];
	private double[] totalSale = new double[7];
	private double[] goalSale = new double[7];
	@Override
	public String toString() {
		return "ChartCart [date=" + Arrays.toString(date) + ", totalSale=" + Arrays.toString(totalSale) + ", goalSale="
				+ Arrays.toString(goalSale) + "]";
	}
	public Date[] getDate() {
		return date;
	}
	public void setDate(Date[] date) {
		this.date = date;
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
