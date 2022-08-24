package pochacm.service.impl;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import pochacm.dao.face.APIDao;
import pochacm.dto.Invoice;
import pochacm.dto.SalesAPI;
import pochacm.dto.SalesInvoice;
import pochacm.dto.SalesShowAPI;
import pochacm.service.face.APIService;

@Service
public class APIServiceImpl implements APIService {
	private static final Logger logger = LoggerFactory.getLogger(APIServiceImpl.class);

	@Autowired APIDao apiDao;
	
    private final String apiKEY = "ApiKey 9746d308-027a-4774-aedd-66ac56bc3b95";
    private final String apiURL = "https://api.abacus.co/invoices";
    
	@Override
	public void insertSalesAPI(int totalNumber) {
		
		//logger index
		int idx = 0;
		logger.info("#{}. insertSalesAPI()", idx++);
		
		int limit = 100;
		logger.info("#{}. limit : {}", idx++, limit);
		
		// put today's date
		String resultDate = "2020-10-31T00:00:00.00Z";
			
		logger.info("#{}. resultDate : {}", idx++, resultDate);
		
		for(int k = 1; k < (totalNumber / limit) + 2;k++) {
			//=============================== GET API ==========================
			
			// uri addresss with query parameter
	        URI uri = UriComponentsBuilder
	                .fromUriString(apiURL)
	                .queryParam("limit", limit)
	                .queryParam("page", k)
	                .queryParam("lastUpdated", resultDate)
	                .encode()
	                .build()
	                .toUri();
	        logger.info("#{}. uri : {}", idx++, uri);
	        
	        //header
	        HttpHeaders header  = new HttpHeaders();
	        header.add("Authorization", apiKEY);
	        
	        //restTemplate
	        RestTemplate restTemplate = new RestTemplate();
	        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
	        
	        HttpEntity<Object> entity = new HttpEntity<>(header);
	        
	        try {
	        	ResponseEntity<SalesAPI> result = restTemplate.exchange(uri, HttpMethod.GET, entity, SalesAPI.class);
	        
	        
	        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
	        	public boolean hasError(ClientHttpResponse response) throws IOException {
	        		HttpStatus statusCode = response.getStatusCode();
	        		return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
	        	}
	        });
	//        logger.info("#{}. result.getBody() - BEFORE CHANGE : {}", idx++, result.getBody());
	        
	        //=========================== GET API FINISHED ========================
	        
	        
	        //=========================== TRIM API  ========================
	        
	        //change date format from ISO 8601 to Australian Time Format
	        for(Invoice i : result.getBody().getInvoices()) {
	        	logger.info("#{}. GET NEXT PAGE", idx++);
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
	        	try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        	
	        	logger.info("#{}. GET NEXT PAGE END", idx++);
	        } //for - END
	        
	//        logger.info("#{}. result.getBody() - AFTER CHANGE : {}", idx++, result.getBody());
	        
	        //make as SalesInvoice List
	        List<SalesInvoice> salesInvoiceList = new ArrayList<SalesInvoice>();
	        
	        for(int i = 0; i < result.getBody().getInvoices().size(); i++) {
	        	for(int j = 0; j< result.getBody().getInvoices().get(i).getItems().size();j++) {
	        		logger.info("#{}. ADD INVOICE LIST START", idx++);
	        		
	        		SalesInvoice temp = new SalesInvoice();
	        		ArrayList<Invoice> rbi = result.getBody().getInvoices();
	        		
	        		temp.setMenu(rbi.get(i).getItems().get(j).getItemName());
	        		temp.setPrice(rbi.get(i).getItems().get(j).getPrice());
	        		temp.setQty(rbi.get(i).getItems().get(j).getQuantity());
	        		temp.setSalesDate(rbi.get(i).getCreatedAt());
	        		temp.setSalesInvoiceSerialNum(rbi.get(i).getInvoiceNumber());
	        		temp.setSalesSource(rbi.get(i).getPayments().get(0).getPaymentMethod());
	        		temp.setTax(rbi.get(i).getItems().get(j).getTax());
	        		temp.setIsRefunded(rbi.get(i).getItems().get(j).isRefund());
	        		
	        		logger.info("#{}. temp : {}", idx++, temp);
	        		
	        		salesInvoiceList.add(temp);
	        		logger.info("#{}. ADD INVOICE LIST END", idx++);
	        	}
	        }
	        
	        logger.info("#{}. salesInvoiceList : {}", idx++, salesInvoiceList);
	        // update or insert information
	        for(SalesInvoice i : salesInvoiceList ) {
	        	logger.info("#{}. START TO PUT DATA IN DB", idx++);
	        	
	        	if ( apiDao.checkAPIExist(i) > 0 ) {
	        		if ( apiDao.checkRefundChange(i) == 0 ) {
	        			apiDao.updateRefundChange(i);
	        		}
	        	} else {
	        		apiDao.putAPItoDB(i);
	        	}
	        	logger.info("#{}. END TO PUT DATA IN DB", idx++);
	        }
	        }
		    catch (final HttpClientErrorException e) {
		        System.out.println(e.getStatusCode());
		        System.out.println(e.getResponseBodyAsString());
		    }
		}
	}// insertSalesAPI() END

	@Override
	public int getTotalPage() {
		
		//logger index
		int idx = 0;
		logger.info("#{}. getTotalPage()", idx++);
		
		// put today's date
		String resultDate = "2020-10-31T00:00:00.00Z";
			
		logger.info("#{}. resultDate : {}", idx++, resultDate);
		
		//=============================== GET API ==========================
		
		// uri addresss with query parameter
        URI uri = UriComponentsBuilder
                .fromUriString(apiURL)
                .queryParam("limit", 100)
                .queryParam("page", 1)
                .queryParam("lastUpdated", resultDate)
                .encode()
                .build()
                .toUri();
        logger.info("#{}. uri : {}", idx++, uri);
        
        //header
        HttpHeaders header  = new HttpHeaders();
        header.add("Authorization", apiKEY);
        
        //restTemplate
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        
        HttpEntity<Object> entity = new HttpEntity<>(header);
        ResponseEntity<SalesAPI> result = restTemplate.exchange(uri, HttpMethod.GET, entity, SalesAPI.class);
        
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
        	public boolean hasError(ClientHttpResponse response) throws IOException {
        		HttpStatus statusCode = response.getStatusCode();
        		return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
        	}
        });
//		        logger.info("#{}. result.getBody() - BEFORE CHANGE : {}", idx++, result.getBody());
        
        //=========================== GET API FINISHED ========================
		
		return result.getBody().getPagination().getTotal();
	}
	
}
