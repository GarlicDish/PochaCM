package pochacm.service.impl;

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
import pochacm.dao.face.SalesDao;
import pochacm.dto.Paging;
import pochacm.dto.Recipe;
import pochacm.dto.SalesAPI;
import pochacm.dto.SalesInvoice;
import pochacm.dto.SalesSource;
import pochacm.service.face.SalesService;

@Service
public class SalesServiceImpl implements SalesService {
	
	private static final Logger logger = LoggerFactory.getLogger(SalesServiceImpl.class);
	
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
	public SalesAPI getAPI(Paging paging, String dateParam) {
		
		
		return null;
	}
	
}
