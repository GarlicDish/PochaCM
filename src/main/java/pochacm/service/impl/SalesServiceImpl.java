package pochacm.service.impl;

import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import pochacm.dao.face.SalesDao;
import pochacm.dto.Invoice;
import pochacm.dto.Paging;
import pochacm.dto.Recipe;
import pochacm.dto.SalesAPI;
import pochacm.dto.SalesInvoice;
import pochacm.dto.SalesShowAPI;
import pochacm.dto.SalesSource;
import pochacm.service.face.SalesService;

@Service
public class SalesServiceImpl implements SalesService {

	private final String apiKEY = "ApiKey 9746d308-027a-4774-aedd-66ac56bc3b95";
	private final String apiURL = "https://api.abacus.co/invoices";

	private static final Logger logger = LoggerFactory.getLogger(SalesServiceImpl.class);

	@Autowired
	SalesDao salesDao;

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public Paging getSalesPaging(Paging paging) {
		// logger index
		int idx = 0;
		logger.info("#{}. getSalesPaging", idx++);

		int page = 0;

		if (Integer.toString(paging.getCurPage()) != null && !"".equals(paging.getCurPage())) {
			page = paging.getCurPage();
			logger.info("#{}. paging.getCurPage() : {}", idx++, paging.getCurPage());
		} else {
			logger.warn("there is no paging.getCurPage() or null value");
		}

		String keyword = paging.getKeyword();
		String category = paging.getCategory();

		// select total count of invoice
		int totalCount = salesDao.selectCntAllSales(paging);

		logger.info("#{}. totalCount : {}", idx++, totalCount);

		// create Paging dto - calculate paging
		Paging pagingReturn = new Paging(totalCount, page);

		logger.info("#{}. pagingReturn : {}", idx++, pagingReturn);
		pagingReturn.setCategory(category);
		pagingReturn.setKeyword(keyword);
		logger.info("#{}. pagingReturn : {}", idx++, pagingReturn);

		return pagingReturn;
	}

	@Override
	public List<SalesInvoice> getSalesList(Paging paging) {
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
	public int insertSales(SalesInvoice sales) {
		return salesDao.insertSales(sales);
	}

	@Override
	public void deleteSalesBySalesNum(SalesInvoice sales) {
		salesDao.deleteSalesBySalesNum(sales);
	}

	@Override
	public SalesInvoice getSalesFromMap(HashMap<String, Object> map) {

		try {
			SalesInvoice sales = new SalesInvoice();
			Date date1;
			date1 = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).parse((String) map.get("salesDate"));
//		sales.setSalesDate(date1);
//		sales.setSalesQty(Integer.parseInt((String) map.get("salesQty")));
//		sales.setSalesSourceNum(Integer.parseInt((String) map.get("salesSourceNum")));

			return sales;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<SalesInvoice> getSalesListBySalesDate(String date) {
		return salesDao.selectSalesListBySalesDate(date);
	}

	@Override
	public int cntSalesBySalesDate(SalesInvoice sales) {
		return salesDao.cntSalesBySalesDate(sales);
	}

	@Override
	public SalesInvoice getSalesBySalesNum(SalesInvoice sales) {
		return salesDao.getSalesBySalesNum(sales);
	}

	@Override
	public List<Map<String, String>> getAllSalesBySalesDate(SalesInvoice sales) {
		return salesDao.getAllSalesBySalesDate(sales);
	}

	@Override
	public SalesAPI getAPI(Paging paging) {

		int idx = 0;
		logger.info("#{}. getAPI ", idx++);

		//date setting
		String resultDate = "2020-10-31T00:00:00.00Z";
		
		int limit = paging.getPageCount();
		int page = paging.getCurPage();
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
		
		for(Invoice i : result.getBody().getInvoices()) {
			//query parameter
        	String invoiceNumber = i.getInvoiceNumber();
        	
        	// uri addresss
        	URI uri2 = UriComponentsBuilder
        			.fromUriString(apiURL)
        			.path("/"+invoiceNumber)
        			.encode()
        			.build()
        			.toUri();
        	logger.info("#{}. uri : {}", idx++, uri2);
        	
        	//header parameter
        	HttpHeaders header2  = new HttpHeaders(); 
        	header2.add("Authorization", apiKEY);
        	
        	RestTemplate restTemplate2 = new RestTemplate();
        	restTemplate2.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        	
        	HttpEntity<Object> entity2 = new HttpEntity<>(header2);
        	ResponseEntity<SalesShowAPI> result2 = restTemplate2.exchange(uri2, HttpMethod.GET, entity2, SalesShowAPI.class);
        	
        	restTemplate2.setErrorHandler(new DefaultResponseErrorHandler() {
        		public boolean hasError(ClientHttpResponse response) throws IOException {
        			HttpStatus statusCode = response.getStatusCode();
        			response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        			return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
        		}
        	});
        	
        	i.setPayments(result2.getBody().getInvoice().getPayments());
        	i.setItems(result2.getBody().getInvoice().getItems());
        	
			String changedDate = i.getCreatedAt().substring(8,10)+ "-"+i.getCreatedAt().substring(5,7)+ "-"+i.getCreatedAt().substring(0,4)+" " +i.getCreatedAt().substring(11,19);
        	
        	i.setCreatedAt(changedDate);
		}
		
		
		
		return result.getBody();

	}

	@Override
	public SalesShowAPI getSalesViewByInvoiceNumber(String invoiceNumber) {
		
		int idx = 0;
		logger.info("#{}. getSalesViewByInvoiceNumber ", idx++);
		
		// uri addresss
        URI uri = UriComponentsBuilder
                .fromUriString(apiURL)
                .path("/"+invoiceNumber)
                .encode()
                .build()
                .toUri();

        logger.info("#{}. uri : {}", idx++, uri);
        
        HttpHeaders headers  = new HttpHeaders();
        headers.add("Authorization", apiKEY);
        logger.info("#{}. headers : {}", idx++, headers);
        
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<SalesShowAPI> result = restTemplate.exchange(uri, HttpMethod.GET, entity, SalesShowAPI.class);
        
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
        	public boolean hasError(ClientHttpResponse response) throws IOException {
        		HttpStatus statusCode = response.getStatusCode();
        		return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
        	}
        });
        
       
		String changedDate = result.getBody().getInvoice().getCreatedAt().substring(8,10)
								+ "-"+result.getBody().getInvoice().getCreatedAt().substring(5,7)
								+ "-"+result.getBody().getInvoice().getCreatedAt().substring(0,4)
								+" " +result.getBody().getInvoice().getCreatedAt().substring(11,19);
        	
		result.getBody().getInvoice().setCreatedAt(changedDate);
        
		//trim total & surcharge because of character of float type
		result.getBody().getInvoice().setTotal((float) (Math.round(result.getBody().getInvoice().getTotal()*100)/100.0));
		result.getBody().getInvoice().setSurcharge((float) (Math.round(result.getBody().getInvoice().getSurcharge()*100)/100.0));
		
		return result.getBody();
	}
}
