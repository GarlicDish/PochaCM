package pochacm.service.impl;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.DefaultCategoryDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import pochacm.dto.ChartCart;
import pochacm.dto.Invoice;
import pochacm.dto.SalesAPI;
import pochacm.service.face.SummaryService;

@Service
public class SummaryServiceImpl implements SummaryService {

    private final String apiKEY = "ApiKey 9746d308-027a-4774-aedd-66ac56bc3b95";
    private final String apiURL = "https://api.abacus.co/invoices";
    
	private static final Logger logger = LoggerFactory.getLogger(SummaryServiceImpl.class);

	@Override
	public String[][] getMonday(Date date) {
		
		//logger index
		int idx = 0;
		logger.info("#{}. getMonday()", idx++);
		
		Date[][] weekDate = new Date[5][7];
		
		Date temp = null;
//		logger.info("#{}. c : {}", idx++, c);
		for(int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++)	{
				
				temp = date;
				
				Calendar c = null;
				c = Calendar.getInstance();
				
				if (date != null ) {
					c.setTime(temp);
				}
				
				c.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
				c.set(Calendar.MILLISECOND, 0);
				c.set(Calendar.SECOND, 0);
				c.set(Calendar.MINUTE, 0);
				c.set(Calendar.HOUR, 0);
		        c.add(Calendar.DATE, j);
		        c.add(Calendar.DATE, -(i*7));
		        temp = c.getTime();
				
				weekDate[i][j] = temp;
			}
		}
		logger.info("#{}. getMonday() weekDate : {}", idx++, weekDate);
		
		String[][] dateToString = new String[5][7];
		
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		for(int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++)	{
				dateToString[i][j] = formatter.format(weekDate[i][j]);
			}
		}
		logger.info("#{}. getFiveWeeksTotal() dateToString : {}", idx++, dateToString);
		
		return dateToString;
	}

	@Override
	public List<SalesAPI> getAWeekSalesAPI(String[][] weekDate) {
		
		//logger index
		int idx = 0;
		logger.info("#{}. getThisWeekTotalSales()", idx++);
		logger.info("#{}. getThisWeekTotalSales() weekDate : {}", idx++, weekDate);
		
		List<SalesAPI> salesAPI = new ArrayList<SalesAPI>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//Trim monday date (remove the time information)
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(weekDate[4][0]));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR, 0);
		Date mondayDate = cal.getTime();

		//Query Parameter
		int total = 0;
		int limit = 100;
		logger.info("#{}. getThisWeekTotalSales() limit : {}", idx++, limit);
		
		//date setting as String Type
		String resultDate = "";
		int page = 1;
		
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd'T00:00:00.00Z'");
		formatter.setTimeZone(TimeZone.getTimeZone("CET"));
		resultDate = formatter.format(mondayDate);
		logger.info("#{}. getThisWeekTotalSales() resultDate : {}", idx++, resultDate);
		
		// header
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", apiKEY);
		
		// restTemplate
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		
			// uri addresss with query parameters
			URI uri = UriComponentsBuilder
						.fromUriString(apiURL)
						.queryParam("limit", limit)
						.queryParam("page", page)
						.queryParam("lastUpdated", resultDate)
						.encode()
						.build()
						.toUri();
			logger.info("#{}. getThisWeekTotalSales() uri : {}", idx++, uri);
	
	
			HttpEntity<Object> entity = new HttpEntity<>(header);
			ResponseEntity<SalesAPI> result = restTemplate.exchange(uri, HttpMethod.GET, entity, SalesAPI.class);
	
			try {
	
				restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
					public boolean hasError(ClientHttpResponse response) throws IOException {
						
						HttpStatus statusCode = response.getStatusCode();
						
						return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
					}
				});
	
			} catch (final HttpClientErrorException e) {
				System.out.println(e.getStatusCode());
				System.out.println(e.getResponseBodyAsString());
			}
			
			total = result.getBody().getPagination().getTotal();
		
		logger.info("#{}. getThisWeekTotalSales() total : {}", idx++, total);
		
		//get Date and Each Sum
		for(int j = 1;j<total/limit + 2;j++) {
			
			page = j;

			// uri addresss with query parameters
			URI uri2 = UriComponentsBuilder
						.fromUriString(apiURL)
						.queryParam("limit", limit)
						.queryParam("page", page)
						.queryParam("lastUpdated", resultDate)
						.encode()
						.build()
						.toUri();
			logger.info("#{}. getThisWeekTotalSales() uri : {}", idx++, uri2);
	
			HttpEntity<Object> entity2 = new HttpEntity<>(header);
			ResponseEntity<SalesAPI> result2 = restTemplate.exchange(uri2, HttpMethod.GET, entity2, SalesAPI.class);
	
			try {
	
				restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
					public boolean hasError(ClientHttpResponse response) throws IOException {
						
						HttpStatus statusCode = response.getStatusCode();
						
						return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
					}
				});
	
			} catch (final HttpClientErrorException e) {
				System.out.println(e.getStatusCode());
				System.out.println(e.getResponseBodyAsString());
			}
			
			salesAPI.add(result2.getBody());
			
		} // for(int j = 0;j<total[i]/limit + 2;j++) END
//		logger.info("#{}. getThisWeekTotalSales() salesAPI : {}", idx++, salesAPI);
		
		logger.info("#{}. getThisWeekTotalSales() END", idx++);
		
		return salesAPI;
	}
	
	@Override
	public ChartCart getFourWeeksAverage(ChartCart chartCart) {
		
		//logger index
		int idx = 0;
		logger.info("#{}. getFourWeeksAverage()", idx++);
		
		logger.info("#{}. chartCart : {}", idx++, chartCart);
		
		Date[] weekdayDate = new Date[7];
		
		//get date 4 weeks before
		for(int i = 1; i < 5; i++) {
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(chartCart.getDate()[i]);
			cal.add(Calendar.DATE,-28);
			cal.set(Calendar.MILLISECOND, 0);
	        cal.set(Calendar.SECOND, 0);
	        cal.set(Calendar.MINUTE, 0);
	        cal.set(Calendar.HOUR, 0);
			chartCart.getDate()[i] = cal.getTime();
		}
		logger.info("#{}. chartCart : {}", idx++, chartCart);

//		cal.setTime(mondayDate);
//		cal.add(Calendar.DATE, 1);
//		mondayDate = cal.getTime();

		
		for (int i = 0; i<7;i++) {
			weekdayDate[i] = new Date(chartCart.getDate()[i].getTime()+(1000*60*60*24)*i);
		}
		logger.info("#{}. weekdayDate : {}", idx++, weekdayDate);
		
		double[] eachSum = {0,0,0,0,0,0,0};
		logger.info("#{}. eachSum : {}", idx++, eachSum);
		
		int[] total = new int[7];
		
		int limit = 100;
		
		logger.info("#{}. limit : {}", idx++, limit);
		
		for(int i = 0; i<7;i++) {

			//date setting
			String resultDate = "";
			int page = 1;
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd'T00:00:00.00Z'");
			formatter.setTimeZone(TimeZone.getTimeZone("CET"));
			resultDate = formatter.format(weekdayDate[i]);
			logger.info("#{}. resultDate : {}", idx++, resultDate);
			
			// uri addresss with query parameters
			URI uri = UriComponentsBuilder
						.fromUriString(apiURL)
						.queryParam("limit", limit)
						.queryParam("page", page)
						.queryParam("lastUpdated", resultDate)
						.encode()
						.build()
						.toUri();
			logger.info("#{}. uri : {}", idx++, uri);
	
			// header
			HttpHeaders header = new HttpHeaders();
			header.add("Authorization", apiKEY);
	
			// restTemplate
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
	
			HttpEntity<Object> entity = new HttpEntity<>(header);
			ResponseEntity<SalesAPI> result = restTemplate.exchange(uri, HttpMethod.GET, entity, SalesAPI.class);
	
			try {
	
				restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
					public boolean hasError(ClientHttpResponse response) throws IOException {
						
						HttpStatus statusCode = response.getStatusCode();
						
						return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
					}
				});
	
			} catch (final HttpClientErrorException e) {
				System.out.println(e.getStatusCode());
				System.out.println(e.getResponseBodyAsString());
			}
			
			total[i] = result.getBody().getPagination().getTotal();
		} // for(Date i : weekdayDate) END
		
		logger.info("#{}. total : {}", idx++, total);
		
		for(int i = 0;i<7;i++) {
			for(int j = 1;j<total[i]/limit + 2;j++) {
				//date setting
				String resultDate = "";
				int page = j;
				logger.info("#{}. page : {}", idx++, page);
				
				SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd'T00:00:00.00Z'");
				formatter.setTimeZone(TimeZone.getTimeZone("CET"));
				resultDate = formatter.format(weekdayDate[i]);
				logger.info("#{}. resultDate : {}", idx++, resultDate);
				
				// uri addresss with query parameters
				URI uri = UriComponentsBuilder
							.fromUriString(apiURL)
							.queryParam("limit", limit)
							.queryParam("page", page)
							.queryParam("lastUpdated", resultDate)
							.encode()
							.build()
							.toUri();
				logger.info("#{}. uri : {}", idx++, uri);
		
				// header
				HttpHeaders header = new HttpHeaders();
				header.add("Authorization", apiKEY);
		
				// restTemplate
				RestTemplate restTemplate = new RestTemplate();
				restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		
				HttpEntity<Object> entity = new HttpEntity<>(header);
				ResponseEntity<SalesAPI> result = restTemplate.exchange(uri, HttpMethod.GET, entity, SalesAPI.class);
		
				try {
		
					restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
						public boolean hasError(ClientHttpResponse response) throws IOException {
							
							HttpStatus statusCode = response.getStatusCode();
							
							return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
						}
					});
		
				} catch (final HttpClientErrorException e) {
					System.out.println(e.getStatusCode());
					System.out.println(e.getResponseBodyAsString());
				}
				
				double temp = 0;
				for(Invoice k : result.getBody().getInvoices()) {
					 temp += k.getTotal();
				}
				logger.info("#{}. temp : {}", idx++, temp);
				eachSum[i] += temp;
			} // for(int j = 0;j<total[i]/limit + 2;j++) END
			
			logger.info("#{}. eachSum["+i+"] : {}", idx++, eachSum[i]);
			
		} //for(int i = 0;i<7;i++) END
		
		
		return chartCart;
	}

	@Override
	public List<SalesAPI> getSalesAPIList(Date criterionDate) {
		
		//logger index
		int idx = 0;
		logger.info("#{}. getSalesAPIList()", idx++);
		
		List<SalesAPI> tempList = new ArrayList<SalesAPI>();
		
		//date setting
		String resultDate = "";
		
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd'T00:00:00.00Z'");
		formatter.setTimeZone(TimeZone.getTimeZone("CET"));
		
		if (criterionDate == null) {
			criterionDate = new Date(System.currentTimeMillis());
			resultDate = formatter.format(criterionDate);
		} else {
			Calendar cal = Calendar.getInstance();
			
			cal.setTime(criterionDate);
			cal.add(Calendar.DATE, 1);
			resultDate = formatter.format(cal.getTime());
		}
		logger.info("#{}. resultDate : {}", idx++, resultDate);
		
		int limit = 50;
		int page = 1;
		logger.info("#{}. limit : {}", idx++, limit);
		logger.info("#{}. page : {}", idx++, page);
		
		
		// uri addresss with query parameter
		URI uri = UriComponentsBuilder
					.fromUriString(apiURL)
					.queryParam("limit", limit)
					.queryParam("page", page)
					.queryParam("lastUpdated", resultDate)
					.encode()
					.build()
					.toUri();
		logger.info("#{}. uri : {}", idx++, uri);

		// header
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", apiKEY);

		// restTemplate
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

		HttpEntity<Object> entity = new HttpEntity<>(header);
		ResponseEntity<SalesAPI> result = restTemplate.exchange(uri, HttpMethod.GET, entity, SalesAPI.class);

		try {

			restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
				public boolean hasError(ClientHttpResponse response) throws IOException {
					
					HttpStatus statusCode = response.getStatusCode();
					
					return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
				}
			});


		} catch (final HttpClientErrorException e) {
			System.out.println(e.getStatusCode());
			System.out.println(e.getResponseBodyAsString());
		}
		
		//change date format as 'yyyy-MM-dd HH:mm:ss'
		for(Invoice i : result.getBody().getInvoices()) {
        	
			String changedDate = i.getCreatedAt().substring(8,10)+ "-"+i.getCreatedAt().substring(5,7)+ "-"+i.getCreatedAt().substring(0,4)+" " +i.getCreatedAt().substring(11,19);
        	
        	i.setCreatedAt(changedDate);
		}
		
		// get total number of invoices
		int total = result.getBody().getPagination().getTotal();
		logger.info("#{}. result.getBody().getPagination().getTotal() : {}", idx++, result.getBody().getPagination().getTotal());
		logger.info("#{}. result.getBody() : {}", idx++, result.getBody());
		
		//add first element of SalesAPI List
		tempList.add(result.getBody());
		
		//add 
		if( total/limit +1 > 1) {
			
			logger.info("#{}. === START FOR ===", idx++);
			for(int i=2;i < total/limit + 2;i++) {
				int page2 = i;
				logger.info("#{}. limit : {}", idx++, limit);
				logger.info("#{}. page : {}", idx++, page2);
				
				
				// uri addresss with query parameter
				URI uri2 = UriComponentsBuilder
							.fromUriString(apiURL)
							.queryParam("limit", limit)
							.queryParam("page", page2)
							.queryParam("lastUpdated", resultDate)
							.encode()
							.build()
							.toUri();
				logger.info("#{}. uri : {}", idx++, uri2);

				// header
				HttpHeaders header2 = new HttpHeaders();
				header2.add("Authorization", apiKEY);
				
				// restTemplate
				RestTemplate restTemplate2 = new RestTemplate();
				restTemplate2.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

				HttpEntity<Object> entity2 = new HttpEntity<>(header2);
				ResponseEntity<SalesAPI> result2 = restTemplate2.exchange(uri2, HttpMethod.GET, entity2, SalesAPI.class);

				try {

					restTemplate2.setErrorHandler(new DefaultResponseErrorHandler() {
						public boolean hasError(ClientHttpResponse response) throws IOException {
							
							HttpStatus statusCode = response.getStatusCode();
							
							return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
						}
					});


				} catch (final HttpClientErrorException e) {
					System.out.println(e.getStatusCode());
					System.out.println(e.getResponseBodyAsString());
				}
				
				//change date format as 'yyyy-MM-dd HH:mm:ss'
				for(Invoice j : result2.getBody().getInvoices()) {
					String changedDate = j.getCreatedAt().substring(8,10)+ "-"+j.getCreatedAt().substring(5,7)+ "-"+j.getCreatedAt().substring(0,4)+" " +j.getCreatedAt().substring(11,19);
		        	
		        	j.setCreatedAt(changedDate);
				}
				
				// get total number of invoices
				logger.info("#{}. result2.getBody().getPagination().getTotal() : {}", idx++, result2.getBody().getPagination().getTotal());
				logger.info("#{}. result2.getBody() : {}", idx++, result2.getBody());
				
				//add first list of SalesAPI
				tempList.add(result2.getBody());
			}
			logger.info("#{}. === END FOR ===", idx++);
		}
		
		return tempList;
	}
	
	@Override
	public double[][] getFiveWeeksTotal(String[][] weekDates, List<SalesAPI> salesAPI) {

		//logger index
		int idx = 0;
		logger.info("#{}. getFiveWeeksTotal()", idx++);
		logger.info("#{}. getFiveWeeksTotal() weekDates : {}", idx++, weekDates);
		
		double[][] tempSumArray = new double[5][7];
		double tempSum;
		
		for(int k = 0; k < 5; k++) {
			for (int l = 0; l < 7; l++)	{
				tempSum = 0;
				for (SalesAPI i : salesAPI) {
					for(Invoice j : i.getInvoices()) {
						if(j.getCreatedAt().contains(weekDates[k][l])) {
//							logger.info("#{}. getFiveWeeksTotal() j : {}", idx++, j);
//							logger.info("#{}. getFiveWeeksTotal() j.getTotal() : {}", idx++, j.getTotal() );
							tempSum += j.getTotal();
						}
					}
				}
				tempSumArray[k][l] = Math.round(tempSum*100)/100.0;
			}
		}
		logger.info("#{}. getFiveWeeksTotal() tempSumArray : {}", idx++, tempSumArray);
		
		return tempSumArray;
	}
	
	@Override
	public String createChart(String[][] weekDates, double[][] totalArray, double[] goalArray,
			HttpSession session, HttpServletRequest request) {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < 7; i++) {
			dataset.setValue(totalArray[0][i], "This Week", weekDates[0][i]);
			dataset.setValue(goalArray[i], "Goal", weekDates[0][i]);
			// dataset.setValue(value, rowKey, columnKey);
		}

		JFreeChart chart = null; // chart dto
		String title = "Sales Goal"; // Title of the chart
		try {

			// 선그래프
			chart = ChartFactory.createLineChart(title, "Date", "Total Sales", dataset, PlotOrientation.VERTICAL, true,
					true, false);

			// title, font & size
			chart.getTitle().setFont(new Font("Calibri", Font.BOLD, 15));

			// legend, font & size
			chart.getLegend().setItemFont(new Font("Calibri", Font.PLAIN, 10));

			Font font = new Font("돋움", Font.PLAIN, 12);
			Color color = new Color(0, 0, 0);
			StandardChartTheme chartTheme = (StandardChartTheme) StandardChartTheme.createJFreeTheme(); // 테마 설정

			chartTheme.setExtraLargeFont(font); // 폰트 크기에 따라 테마를 다르게 설정
			chartTheme.setLargeFont(font);
			chartTheme.setRegularFont(font);
			chartTheme.setSmallFont(font);

			chartTheme.setAxisLabelPaint(color); // 축, 범례등의 색상을 변경
			chartTheme.setLegendItemPaint(color);
			chartTheme.setItemLabelPaint(color);
			chartTheme.apply(chart);

		} catch (Exception e) {
			e.printStackTrace();
		}

		ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
		// Rendering 된 정보를 저장하기 위한 구조 (JFreeChart.Draw()메소드를 이용하기 위해서는 한개 이상 생성)

		String fileName = "";
		try {
			fileName = ServletUtilities.saveChartAsJPEG(chart, 600, 300, info, session);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// temp디렉토리에 jpeg 형태의 이미지로 저장

		String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + fileName;
		// 그래프의 URL값을 가지고 온다.

		return graphURL;
	}

	@Override
	public ChartCart getChartCartFromSalesAPIList(List<SalesAPI> salesAPIList) {
		//logger index
		int idx = 0;
		logger.info("#{}. getChartCartFromSalesAPIList()", idx++);
		
		ChartCart tempCart = new ChartCart();
		
		double sum = 0;
		
		for (SalesAPI i : salesAPIList) {
			for(Invoice j : i.getInvoices()) {
				sum += j.getTotal(); 
			}
		}
		double totalSale = sum;
		
		logger.info("#{}. getChartCartFromSalesAPIList() totalSale : {}", idx++,totalSale);
		
		return tempCart;
	}

	@Override
	public double[] getGoalBasedOnFourWeekMean(double[][] totalArray) {
		
		//logger index
		int idx = 0;
		logger.info("#{}. getGoalBasedOnFourWeekMean()", idx++);
		logger.info("#{}. getGoalBasedOnFourWeekMean() totalArray : {}", idx++,totalArray);

		double[] tempGoal = new double[7];
		
		
		for( int j=0;j<7;j++) {
			double temp = 0;
			for(int i=1;i<5;i++) {
				temp += totalArray[i][j];
			}
			tempGoal[j] = Math.round(temp/4.0*100)/100.0;
		}
		
		return tempGoal;
	}

	






}
