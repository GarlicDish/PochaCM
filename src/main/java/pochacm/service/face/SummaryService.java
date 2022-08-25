package pochacm.service.face;

import java.util.Date;
import java.util.List;

import org.jfree.chart.JFreeChart;

import pochacm.dto.ChartCart;
import pochacm.dto.SalesAPI;

public interface SummaryService {

	JFreeChart createChart();

	List<SalesAPI> getSalesAPIList(Date criterionDate);

	ChartCart getChartCartFromSalesAPIList(List<SalesAPI> salesAPIList);

	String[][] getMonday(Date date);

	List<SalesAPI> getAWeekSalesAPI(String[][] weekDates);

	ChartCart getFourWeeksAverage(ChartCart chartCart);

	double[][] getFiveWeeksTotal(String[][] weekDates, List<SalesAPI> salesAPI);

	double[] getGoalBasedOnFourWeekMean(double[][] totalArray);

}
