package pochacm.service.face;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jfree.chart.JFreeChart;

import pochacm.dto.ChartCart;
import pochacm.dto.SalesAPI;

public interface SummaryService {

	String createChart(String[][] weekDates, double[][] totalArray, double[] goalArray, HttpSession session, HttpServletRequest request);

	List<SalesAPI> getSalesAPIList(Date criterionDate);

	ChartCart getChartCartFromSalesAPIList(List<SalesAPI> salesAPIList);

	String[][] getMonday(Date date);

	List<SalesAPI> getAWeekSalesAPI(String[][] weekDates);

	ChartCart getFourWeeksAverage(ChartCart chartCart);

	double[][] getFiveWeeksTotal(String[][] weekDates, List<SalesAPI> salesAPI);

	double[] getGoalBasedOnFourWeekMean(double[][] totalArray);

}
