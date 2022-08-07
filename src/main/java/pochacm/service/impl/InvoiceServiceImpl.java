package pochacm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import pochacm.dao.face.InvoiceDao;
import pochacm.dto.Invoice;
import pochacm.dto.Item;
import pochacm.dto.ItemCategory;
import pochacm.dto.OrderUnit;
import pochacm.dto.Paging;
import pochacm.dto.PrimaryUnit;
import pochacm.dto.Recipe;
import pochacm.dto.SecondaryUnit;
import pochacm.service.face.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	private static final Logger logger = LoggerFactory.getLogger(InvoiceServiceImpl.class);

	@Autowired InvoiceDao invoiceDao;
	
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
		int totalCount = invoiceDao.selectCntAllInvoice(paging);
		
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
		return invoiceDao.selectAllInvoice(paging);
	}

	//view invoice detail
	@Override
	public List<Item> selectItemsByInvoiceNum(Invoice invoice) {
		return invoiceDao.selectItemsByInvoiceNum(invoice);
	}	

	@Override
	public Invoice getInvoiceByInvoiceNum(Invoice invoice) {
		return invoiceDao.getInvoiceByInvoiceNum(invoice);
	}
	
	@Override
	public Object getItemInfoByItem(Item item) {
		return invoiceDao.selectItemInfoByItemNum(item);
	}
	

	

	@Override
	public List<OrderUnit> getOrderUnitList() {
		return invoiceDao.getOrderUnitList();
	}

	@Override
	public List<PrimaryUnit> getPrimaryUnitList() {
		return invoiceDao.getPrimaryUnitList();
	}

	@Override
	public List<SecondaryUnit> getSecondaryUnitList() {
		return invoiceDao.getSecondaryUnitList();
	}

	@Override
	public List<ItemCategory> getItemCategoryList() {
		return invoiceDao.getItemCategoryList();
	}

	@Override
	public List<String> getCategoryByKeyword(String keyword) {
		return invoiceDao.getCategoryByKeyword();
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
		return invoiceDao.selectMenuSearchList(recipe);
	}

	

	@Override
	public List<Recipe> makeRecipeListFromParams(Model model) {
		
		return null;
	}

	@Override
	public void updateItemInformation(Item item) {
		invoiceDao.updateItemInformation(item);
	}

	

	






}
