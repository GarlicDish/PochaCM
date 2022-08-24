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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import oracle.net.aso.i;
import pochacm.dao.face.SummaryDao;
import pochacm.dto.ChartCart;
import pochacm.dto.Invoice;
import pochacm.dto.SalesAPI;
import pochacm.dto.SalesShowAPI;
import pochacm.service.face.SummaryService;

@Service
public class SummaryServiceImpl implements SummaryService {

    private final String apiKEY = "ApiKey 9746d308-027a-4774-aedd-66ac56bc3b95";
    private final String apiURL = "https://api.abacus.co/invoices";
    
	private static final Logger logger = LoggerFactory.getLogger(SummaryServiceImpl.class);

	@Override
	public Date getMonday(Date date) {
		
		//logger index
		int idx = 0;
		logger.info("#{}. getThisWeeksMonday()", idx++);
		
		Calendar c = null;
		c = Calendar.getInstance();
		
		if (date != null ) {
			c.setTime(date);
		}
		logger.info("#{}. c : {}", idx++, c);
		
		c.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		date = c.getTime();
		
		return date;
	}

	@Override
	public ChartCart getThisWeekTotalSales(Date mondayDate) {
		
		//logger index
		int idx = 0;
		logger.info("#{}. getThisWeekTotalSales()", idx++);
		
		logger.info("#{}. mondayDate : {}", idx++, mondayDate);
		
		List<Date> weekdayDate = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(mondayDate);
		cal.add(Calendar.DATE,1);
		mondayDate = cal.getTime();
//		cal.setTime(mondayDate);
//		cal.add(Calendar.DATE, 1);
//		mondayDate = cal.getTime();
		for (int i = 0; i<7;i++) {
			weekdayDate.add(new Date(mondayDate.getTime()+(1000*60*60*24)*i));
		}
		logger.info("#{}. weekdayDate : {}", idx++, weekdayDate);
		
		double[] eachSum = {0,0,0,0,0,0,0};
		logger.info("#{}. eachSum : {}", idx++, eachSum);
		
		int[] total = {0,0,0,0,0,0,0};
		
		int limit = 100;
		
		logger.info("#{}. limit : {}", idx++, limit);
		
		for(int i = 0; i<7;i++) {

			//date setting
			String resultDate = "";
			int page = 1;
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd'T00:00:00.00Z'");
			formatter.setTimeZone(TimeZone.getTimeZone("CET"));
			resultDate = formatter.format(weekdayDate.get(i));
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
				resultDate = formatter.format(weekdayDate.get(i));
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
		
		return null;
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
	public JFreeChart createChart() {
		
        
        
//        //파이 차트가 아닌경우
//        //파이 차트일때와는 클래스가 틀리다.
//         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//         for (ChartCart dto : list) {
//             dataset.setValue(dto.getMoney(), "과일", dto.getProduct_name());
//             //dataset.setValue(value, rowKey, columnKey); 형식으로 들어간다.
//             //금액, 과일, 상품이름이 들어간다.  (총 3개의 값이 들어감)
//         }
//         
//         
////파이 차트인 경우
////DefaultPieDataset dataset = new DefaultPieDataset();
////for (CartDTO dto : list) {
////dataset.setValue(dto.getProduct_name(), dto.getMoney());
//         //파이차트에는 x,y축이 없기 때문에 값이 2개만 들어간다.
//         //그러니까 rowKey값이 안들어간다.
////}
//         
//        //dataset.setValue(value, rowKey, columnKey); 형식은 이렇게 되지만, 값이 2개만 들어감
//         
//         JFreeChart chart = null; //차트 객체 선언
//         String title = "장바구니 통계"; //장바구니 타이틀의 제목
//         try {
//             
////    선그래프
////     chart = ChartFactory.createLineChart(
////     title, "상품명" , "금액" , dataset,
////     PlotOrientation.VERTICAL, true, true,
////     false);
//             
////    라인 (선) 그래프에서는 이런 값들이 들어간다.
////    title, categoryAxisLabel, valueAxisLabel, dataset
//                 
//             
////막대 그래프
//    chart = ChartFactory.createBarChart( //세로형식의 막대그래프를 만듦
//            title, 
//            "상품명", 
//            "금액", 
//            dataset,
//            PlotOrientation.VERTICAL, //세로로 차트를 만든다는 의미
//            true, true, false);
//             
//    
////파이 차트
//    //chart = ChartFactory.createPieChart(
//            //title,  //chart title
//            //dataset,    //data 
//            //true,         //include legend
//            //true,
//            //false);
//    
//    //제목, 타이틀의 폰트와 글씨크기를 설정
//            chart.getTitle().setFont(
//            new Font("돋움",Font.BOLD, 15));
//    
//    //범례, 범례의 폰트와 글씨크기를 설정
//            chart.getLegend().setItemFont(
//            new Font("돋움",Font.PLAIN, 10));
//            
//            Font font = new Font("돋움", Font.PLAIN,12);
//            Color color = new Color(0,0,0);
//            StandardChartTheme chartTheme =
//    (StandardChartTheme) StandardChartTheme.createJFreeTheme(); //테마 설정
//            
//            
//            chartTheme.setExtraLargeFont(font); //폰트 크기에 따라 테마를 다르게 설정 
//            chartTheme.setLargeFont(font);
//            chartTheme.setRegularFont(font);
//            chartTheme.setSmallFont(font);
//            
//            
//            chartTheme.setAxisLabelPaint(color); //축, 범례등의 색상을 변경
//            chartTheme.setLegendItemPaint(color);
//            chartTheme.setItemLabelPaint(color);
//            chartTheme.apply(chart);
//        
//        } catch (Exception e) {
//            
//            e.printStackTrace();
//            
//        }
//         
//         return chart;
		
		return null;
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
		
		logger.info("#{}. totalSale : {}", idx++,totalSale);
		
		return tempCart;
	}




}
