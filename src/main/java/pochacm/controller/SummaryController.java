package pochacm.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pochacm.dto.SalesAPI;
import pochacm.service.face.SummaryService;

@Controller
public class SummaryController {
	
	private static final Logger logger = LoggerFactory.getLogger(SummaryController.class);

	@Autowired SummaryService summaryService;
	
	@GetMapping("/summary")
	public String viewSummary(HttpServletResponse response, HttpServletRequest request, HttpSession session, @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /summary [GET]", idx++);
		logger.info("#{}. /summary [GET] date : {}", idx++, date);
		
		//GET dates of a week
		String[][] weekDates = summaryService.getMonday(date);
		logger.info("#{}. /summary [GET] mondayDate : {}", idx++, weekDates);
		
		//Get SalesAPI List of 1 week based on selected date
		List<SalesAPI> salesAPI = summaryService.getAWeekSalesAPI(weekDates);
		
		//Get Each day's total
		double[][] totalArray = new double[5][7];
		
		totalArray = summaryService.getFiveWeeksTotal(weekDates, salesAPI);
		
		//get Goal Sales total (based on mean of former 4 weeks)
		double[] goalArray = new double[7];
		
		goalArray = summaryService.getGoalBasedOnFourWeekMean(totalArray);

		//Make Line Chart and get the url for img
		
		String[][] trimmedWeekDates = new String[5][7];
		
		for(int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++)	{
				trimmedWeekDates[i][j] = weekDates[i][j].substring(8,10) + "-" + weekDates[i][j].substring(5,7) + "-" + weekDates[i][j].substring(0,4);
			}
		}
		
		model.addAttribute("trimmedWeekDates", trimmedWeekDates);
		model.addAttribute("totalArray", totalArray);
		model.addAttribute("goalArray", goalArray);
		
		logger.info("#{}. /summary [GET] trimmedWeekDates : {}", idx++, trimmedWeekDates);
		logger.info("#{}. /summary [GET] totalArray : {}", idx++, totalArray);
		logger.info("#{}. /summary [GET] goalArray : {}", idx++, goalArray);
				
		
		return "/summary/summary";
	}
}
