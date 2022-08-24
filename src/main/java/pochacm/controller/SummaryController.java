package pochacm.controller;

import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import pochacm.dto.ChartCart;
import pochacm.dto.Invoice;
import pochacm.dto.OpenDrawerHistory;
import pochacm.dto.SalesAPI;
import pochacm.service.face.SummaryService;

@Controller
public class SummaryController {
	
	private static final Logger logger = LoggerFactory.getLogger(SummaryController.class);

	@Autowired SummaryService summaryService;
	
	@GetMapping("/summary")
	public String viewSummary(HttpServletResponse response, HttpSession session, @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		//logger index
		int idx = 0;
		logger.info("#{}. Entering summary page [GET]", idx++);
		logger.info("#{}. date : {}", idx++, date);
		
		//GET this week's monday date
		Date mondayDate = summaryService.getMonday(date);
		logger.info("#{}. mondayDate : {}", idx++, mondayDate);
		
		//Get totalSalesMoney for each day in this week.
		ChartCart chartCart = summaryService.getThisWeekTotalSales(mondayDate);
		
//		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			criterionDate = formatter.parse("2022-08-22");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
//		logger.info("#{}. START GETTING salesAPIList", idx++);
//		List<SalesAPI> salesAPIList = summaryService.getSalesAPIList(criterionDate);
//		logger.info("#{}. END GETTING salesAPIList", idx++);
//		logger.info("#{}. salesAPIList : {}", idx++, salesAPIList);
//
//		ChartCart chartCart = summaryService.getChartCartFromSalesAPIList(salesAPIList);
//		logger.info("#{}. chartCart : {}", idx++, chartCart);
		
//		try {
//			
//	        JFreeChart chart = summaryService.createChart(); 
//	        //서비스에서 생성한 차트를 받아와 변수에 저장, 차트를 얻어온다음에 바로 이미지파일로 보냄
//	        ChartUtils.writeChartAsPNG(response.getOutputStream(), chart, 900, 550);
//	        //차트를 받아와서 가로, 세로 길이를 설정해준다. view 필요없이 화면에 곧바로 출력이 된다.
//	        
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }    
		
				
		
		return "/summary/summary";
	}
}
