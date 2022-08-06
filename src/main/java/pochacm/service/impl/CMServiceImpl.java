package pochacm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pochacm.dao.face.CMDao;
import pochacm.dto.Invoice;
import pochacm.dto.Item;
import pochacm.dto.ItemCategory;
import pochacm.dto.OrderUnit;
import pochacm.dto.Paging;
import pochacm.dto.PrimaryUnit;
import pochacm.dto.Recipe;
import pochacm.dto.Sales;
import pochacm.dto.SalesSource;
import pochacm.dto.SecondaryUnit;
import pochacm.service.face.CMService;

@Service
public class CMServiceImpl implements CMService {
	private static final Logger logger = LoggerFactory.getLogger(CMServiceImpl.class);

	@Autowired CMDao cmDao;
	
	//invoice Paging
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public Paging getInvoicePaging(Paging paging) {
		//logger index
		int idx = 0;
		logger.info("#{}. getInvoicePaging", idx++);
		
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
		int totalCount = cmDao.selectCntAllInvoice(paging);
		
		logger.info("#{}. totalCount : {}", idx++, totalCount);
		
		//create Paging dto - calculate paging
		Paging pagingReturn = new Paging(totalCount,page);
		
		logger.info("#{}. pagingReturn : {}", idx++, pagingReturn);
		pagingReturn.setCategory(category);
		pagingReturn.setKeyword(keyword);
		logger.info("#{}. pagingReturn : {}", idx++, pagingReturn);
		
		return pagingReturn;
	}

	//Get invoice list
	@Override
	public List<Invoice> getInvoiceList(Paging paging) {
		return cmDao.selectAllInvoice(paging);
	}

	//view invoice detail
	@Override
	public List<Item> selectItemsByInvoiceNum(Invoice invoice) {
		return cmDao.selectItemsByInvoiceNum(invoice);
	}

	@Override
	public Invoice getInvoiceByInvoiceNum(Invoice invoice) {
		return cmDao.getInvoiceByInvoiceNum(invoice);
	}
	
	@Override
	public Object getItemInfoByItem(Item item) {
		return cmDao.selectItemInfoByItemNum(item);
	}
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
		int totalCount = cmDao.selectCntAllSales(paging);
		
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
		return cmDao.selectAllSales(paging);
	}

	@Override
	public List<OrderUnit> getOrderUnitList() {
		return cmDao.getOrderUnitList();
	}

	@Override
	public List<PrimaryUnit> getPrimaryUnitList() {
		return cmDao.getPrimaryUnitList();
	}

	@Override
	public List<SecondaryUnit> getSecondaryUnitList() {
		return cmDao.getSecondaryUnitList();
	}

	@Override
	public List<ItemCategory> getItemCategoryList() {
		return cmDao.getItemCategoryList();
	}

	@Override
	public List<String> getCategoryByKeyword(String keyword) {
		return cmDao.getCategoryByKeyword();
	}

	@Override
	public List<SalesSource> getSalesSourceList() {
		return cmDao.getSalesSourceList();
	}

	@Override
	public Recipe getRecipeDtoWithRecipeName(String menuName) {
		//logger index
		int idx = 0;
		logger.info("#{}. getRecipeByRecipeName", idx++);
		
		Recipe recipeName = new Recipe();
		
		recipeName.setRecipeName(menuName);
		logger.info("#{}. recipeName : {}", idx++, recipeName);
		
		return recipeName;
	}

	@Override
	public List<Recipe> getMenuSearchList(Recipe recipe) {
		return cmDao.selectMenuSearchList(recipe);
	}

	@Override
	public Recipe getRecipeByRecipeName(Recipe recipe) {
		return cmDao.selectMenuBymenuName(recipe);
	}





}
