package pochacm.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pochacm.dao.face.SalesDao;
import pochacm.dto.Paging;
import pochacm.dto.Recipe;
import pochacm.dto.Sales;
import pochacm.dto.SalesSource;
import pochacm.service.face.SalesService;

@Service
public class SalesServiceImpl implements SalesService {
	
	private static final Logger logger = LoggerFactory.getLogger(SalesServiceImpl.class);
	
    private final RestTemplate restTemplate = new RestTemplate();

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
			// TODO Auto-generated catch block
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
	@Override
	public void getAPI(int limit, int page, String lastUpdated) {
			
			URL url = null;
			HttpURLConnection conn = null;
			String param = "?limit="+limit+"&page="+page+"&lastUpdated="+lastUpdated;
			String responseData = "";	    	   
			BufferedReader br = null;
			StringBuffer sb = null;
			String returnData = "";
			
		try {
			url = new URL(apiURL+param);
			
			conn = (HttpURLConnection)url.openConnection();
		
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization",apiKEY);
			conn.setRequestProperty("Content-Type","application/json");
			conn.setRequestProperty("Accept","application/json");
			conn.setConnectTimeout(5000);
			conn.setDoOutput(true);

			conn.connect();

			System.out.println(conn.getResponseMessage());
			
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));	
			sb = new StringBuffer();	       
			while ((responseData = br.readLine()) != null) {
				sb.append(responseData); //StringBuffer에 응답받은 데이터 순차적으로 저장 실시
			}
			
			returnData = sb.toString();
			
			String responseCode = String.valueOf(conn.getResponseCode());
			
			System.out.println("http 응답 코드 : "+responseCode);
			System.out.println("http 응답 데이터 : "+returnData);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
}
