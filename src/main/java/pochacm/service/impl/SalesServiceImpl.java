package pochacm.service.impl;

import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import pochacm.dao.face.SalesDao;
import pochacm.dto.Invoice;
import pochacm.dto.Paging;
import pochacm.dto.Recipe;
import pochacm.dto.Sales;
import pochacm.dto.SalesAPI;
import pochacm.dto.SalesShowAPI;
import pochacm.dto.SalesSource;
import pochacm.service.face.SalesService;

@Service
public class SalesServiceImpl implements SalesService {
	
	private static final Logger logger = LoggerFactory.getLogger(SalesServiceImpl.class);
	
    private final String apiKEY = "ApiKey 9746d308-027a-4774-aedd-66ac56bc3b95";
    private final String apiURL = "https://api.abacus.co/invoices";
	
	@Autowired SalesDao salesDao;

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public Paging getSalesPaging(Paging paging) {
		//logger index
		int idx = 0;
		logger.info("#{}. getSalesPaging", idx++);
		
		int page = 0;
		
		if( Integer.toString(paging.getCurPage()) != null && !"".equals(paging.getCurPage()) ) {
			page = paging.getCurPage();
			logger.info("#{}. paging.getCurPage() : {}", idx++, paging.getCurPage());
		} else {
			logger.warn("there is no paging.getCurPage() or null value");
		}
		
		String keyword = paging.getKeyword();
		String category = paging.getCategory();
		
		//select total count of invoice
		int totalCount = salesDao.selectCntAllSales(paging);
		
		logger.info("#{}. totalCount : {}", idx++, totalCount);
		
		//create Paging dto - calculate paging
		Paging pagingReturn = new Paging(totalCount,page);
		
		logger.info("#{}. pagingReturn : {}", idx++, pagingReturn);
		pagingReturn.setCategory(category);
		pagingReturn.setKeyword(keyword);
		logger.info("#{}. pagingReturn : {}", idx++, pagingReturn);
		
		return pagingReturn;
	}
	@Override
	public List<Sales> getSalesList(Paging paging) {
		return salesDao.selectAllSales(paging);
	}
	
	@Override
	public List<SalesSource> getSalesSourceList() {
		return salesDao.getSalesSourceList();
	}
	@Override
	public Recipe getRecipeByRecipeName(Recipe recipe) {
		return salesDao.selectMenuBymenuName(recipe);
	}
	@Override
	public int insertSales(Sales sales) {
		return salesDao.insertSales(sales);
	}
	@Override
	public void deleteSalesBySalesNum(Sales sales) {
		salesDao.deleteSalesBySalesNum(sales);
	}
	
	@Override
	public Sales getSalesFromMap(HashMap<String, Object> map) {

		try {
		Sales sales = new Sales();
		Date date1;
		date1 = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).parse((String) map.get("salesDate"));
		sales.setSalesDate(date1);
		sales.setSalesQty(Integer.parseInt((String) map.get("salesQty")));
		sales.setSalesSourceNum(Integer.parseInt((String) map.get("salesSourceNum")));
		
		return sales;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}  
	}
	@Override
	public List<Sales> getSalesListBySalesDate(String date) {
		return salesDao.selectSalesListBySalesDate(date);
	}
	@Override
	public int cntSalesBySalesDate(Sales sales) {
		return salesDao.cntSalesBySalesDate(sales);
	}
	@Override
	public Sales getSalesBySalesNum(Sales sales) {
		return salesDao.getSalesBySalesNum(sales);
	}
	@Override
	public List<Map<String, String>> getAllSalesBySalesDate(Sales sales) {
		return salesDao.getAllSalesBySalesDate(sales);
	}
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public SalesAPI getAPI(Paging paging, String dateParam) {
		//logger index
		int idx = 0;
		logger.info("#{}. getAPI", idx++);
		
		logger.info("#{}. paging : {}", idx++, paging);
		logger.info("#{}. date : {}", idx++, dateParam);
		
		
		//Making parameter based on paging dto & today info
		// number of invoices per 1 page
		int limit = paging.getListCount();
		logger.info("#{}. limit : {}", idx++, limit);

		// current page
		int page = paging.getCurPage();
		logger.info("#{}. page : {}", idx++, page);
		
		String resultDate = "";
		
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd'T00:00:00.00Z'");
		formatter.setTimeZone(TimeZone.getTimeZone("CET"));
		
		//adjust date
		if (dateParam == null || dateParam.equals("")) {
			
			// put today's date
			Date date = new Date(System.currentTimeMillis());
			resultDate = formatter.format(date);
			
		} else {
			
			// put designated date
			resultDate = formatter.format(dateParam);
			
		}
		logger.info("#{}. resultDate : {}", idx++, resultDate);
		
		// uri addresss
        URI uri = UriComponentsBuilder
                .fromUriString(apiURL)
                .queryParam("limit", limit)
                .queryParam("page", page)
                .queryParam("lastUpdated", resultDate)
                .encode()
                .build()
                .toUri();
        logger.info("#{}. uri : {}", idx++, uri);
        
        
        //header
        HttpHeaders headers  = new HttpHeaders(); 
        headers.add("Authorization", apiKEY);
        logger.info("#{}. headers : {}", idx++, headers);
        
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<SalesAPI> result = restTemplate.exchange(uri, HttpMethod.GET, entity, SalesAPI.class);
        
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
        	public boolean hasError(ClientHttpResponse response) throws IOException {
        		HttpStatus statusCode = response.getStatusCode();
        		return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
        	}
        });
        
        //change date format from ISO 8601 to Australian Time Format
        for(Invoice i : result.getBody().getInvoices()) {
        	
        	String invoiceNumber = i.getInvoiceNumber();
        	// uri addresss
        	URI uri2 = UriComponentsBuilder
        			.fromUriString(apiURL)
        			.path("/"+invoiceNumber)
        			.encode()
        			.build()
        			.toUri();
        	logger.info("#{}. uri : {}", idx++, uri2);
        	
        	HttpHeaders headers2  = new HttpHeaders(); // 담아줄 header
        	headers2.add("Authorization", apiKEY);
        	logger.info("#{}. headers : {}", idx++, headers2);
        	
        	restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        	
        	HttpEntity<Object> ett2 = new HttpEntity<>(headers2);
        	ResponseEntity<SalesShowAPI> result2 = restTemplate.exchange(uri2, HttpMethod.GET, ett2, SalesShowAPI.class);
        	
        	restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
        		public boolean hasError(ClientHttpResponse response) throws IOException {
        			HttpStatus statusCode = response.getStatusCode();
        			response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        			return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
        		}
        	});
        	i.setPayments(result2.getBody().getInvoice().getPayments());
        	logger.info("#{}. result2.getBody().getInvoice() : {}", idx++, result2.getBody().getInvoice());
        	
        	String changedDate = i.getCreatedAt().substring(8,10)+ "-"+i.getCreatedAt().substring(5,7)+ "-"+i.getCreatedAt().substring(0,4)+" " +i.getCreatedAt().substring(11,19);
        	
        	logger.info("#{}. changedDate : {}", idx++, changedDate); //check for the date format
        	
        	i.setCreatedAt(changedDate);
        }
        
        
//      logger.info("#{}. 테스트 : {}", idx++, result.getBody().getInvoices().get(0).getCreatedAt());
//        logger.info("#{}. result.getStatusCode() : {}", idx++, result.getStatusCode());
//        logger.info("#{}. ###### result.getBody() : {}", idx++, result.getBody());

        return result.getBody();

	}
	
}
