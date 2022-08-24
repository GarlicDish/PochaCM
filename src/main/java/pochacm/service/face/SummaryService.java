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

	Date getMonday(Date date);

	ChartCart getThisWeekTotalSales(Date mondayDate);

}
