package pochacm.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pochacm.dao.face.ManagementDao;
import pochacm.dto.Brand;
import pochacm.dto.Category;
import pochacm.dto.Items;
import pochacm.dto.OrderUnit;
import pochacm.dto.Paging;
import pochacm.dto.PrimaryUnit;
import pochacm.dto.SecondaryUnit;
import pochacm.service.face.ManagementService;

@Service
public class ManagementServiceImpl implements ManagementService {

	private static final Logger logger = LoggerFactory.getLogger(ManagementServiceImpl.class);

	@Autowired
	ManagementDao managementDao;

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public Paging getItemPaging(Paging paging) {

		// logger index
		int idx = 0;
		logger.info("#{}. getItemPaging()", idx++);
		logger.info("#{}. getItemPaging() paging : {}", idx++, paging);

		int page = 0;

		if (Integer.toString(paging.getCurPage()) != null && !"".equals(paging.getCurPage())) {
			page = paging.getCurPage();
			logger.info("#{}. getItemPaging() paging.getCurPage() : {}", idx++, paging.getCurPage());
		} else {
			logger.warn("there is no paging.getCurPage() or null value");
		}

		String keyword = paging.getKeyword();
		String category = paging.getCategory();

		// select total count of invoice
		int totalCount = managementDao.selectCntAllItems();

		logger.info("#{}. totalCount : {}", idx++, totalCount);

		// create Paging dto - calculate paging
		Paging pagingReturn = new Paging(totalCount, page);

		pagingReturn.setCategory(category);
		pagingReturn.setKeyword(keyword);
		logger.info("#{}. pagingReturn : {}", idx++, pagingReturn);

		return pagingReturn;
	}

	@Override
	public List<Map<String, String>> getItemList(Paging paging) {

		// logger index
		int idx = 0;
		logger.info("#{}. getItemList()", idx++);
		logger.info("#{}. getItemList() paging : {}", idx++, paging);

		return managementDao.selectAllItems(paging);
	}

	@Override
	public Object getItemInfoByItem(Items item) {
		return managementDao.selectItemInfoByItemNum(item);
	}

	@Override
	public List<OrderUnit> getOrderUnitList() {
		return managementDao.getOrderUnitList();
	}

	@Override
	public List<PrimaryUnit> getPrimaryUnitList() {
		return managementDao.getPrimaryUnitList();
	}

	@Override
	public List<SecondaryUnit> getSecondaryUnitList() {
		return managementDao.getSecondaryUnitList();
	}

	@Override
	public List<Category> getItemCategoryList() {
		return managementDao.getItemCategoryList();
	}

	@Override
	public void updateItemInformation(Items item) {
		managementDao.updateItemInformation(item);
	}

	@Override
	public Brand getBrandNumByBrandName(Brand brand) {
		
		// logger index
		int idx = 0;
		logger.info("#{}. getBrandNumByBrandName()", idx++);
		logger.info("#{}. getBrandNumByBrandName() brandName : {}", idx++, brand);
		
		return managementDao.selectBrandNumByBrandName(brand);
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public Paging getMenuPaging(Paging paging) {
		
		// logger index
		int idx = 0;
		logger.info("#{}. getMenuPaging()", idx++);
		logger.info("#{}. getMenuPaging() paging : {}", idx++, paging);

		int page = 0;

		if (Integer.toString(paging.getCurPage()) != null && !"".equals(paging.getCurPage())) {
			page = paging.getCurPage();
			logger.info("#{}. getMenuPaging() paging.getCurPage() : {}", idx++, paging.getCurPage());
		} else {
			logger.warn("there is no paging.getCurPage() or null value");
		}

		String keyword = paging.getKeyword();
		String category = paging.getCategory();

		// select total count of invoice
		int totalCount = managementDao.selectCntAllMenus();

		logger.info("#{}. getMenuPaging() totalCount : {}", idx++, totalCount);

		// create Paging dto - calculate paging
		Paging pagingReturn = new Paging(totalCount, page);

		pagingReturn.setCategory(category);
		pagingReturn.setKeyword(keyword);
		logger.info("#{}. getMenuPaging() pagingReturn : {}", idx++, pagingReturn);

		return pagingReturn;
	}

	@Override
	public List<Map<String, String>> getMenuList(Paging paging) {
		
		// logger index
		int idx = 0;
		logger.info("#{}. getMenuList()", idx++);
		logger.info("#{}. getMenuList() paging : {}", idx++, paging);
		
		return managementDao.selectAllMenus(paging);
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public Paging getBrandPaging(Paging paging) {
		
		// logger index
		int idx = 0;
		logger.info("#{}. getBrandPaging()", idx++);
		logger.info("#{}. getBrandPaging() paging : {}", idx++, paging);

		int page = 0;

		if (Integer.toString(paging.getCurPage()) != null && !"".equals(paging.getCurPage())) {
			page = paging.getCurPage();
			logger.info("#{}. getBrandPaging() paging.getCurPage() : {}", idx++, paging.getCurPage());
		} else {
			logger.warn("there is no paging.getCurPage() or null value");
		}

		String keyword = paging.getKeyword();
		String category = paging.getCategory();

		// select total count of invoice
		int totalCount = managementDao.selectCntAllBrands();

		logger.info("#{}. getMenuPaging() totalCount : {}", idx++, totalCount);

		// create Paging dto - calculate paging
		Paging pagingReturn = new Paging(totalCount, page);

		pagingReturn.setCategory(category);
		pagingReturn.setKeyword(keyword);
		logger.info("#{}. getMenuPaging() pagingReturn : {}", idx++, pagingReturn);

		return pagingReturn;
	}

	@Override
	public List<Map<String, String>> getBrandList(Paging paging) {
		
		// logger index
		int idx = 0;
		logger.info("#{}. getBrandList()", idx++);
		logger.info("#{}. getBrandList() paging : {}", idx++, paging);
		
		return managementDao.selectAllBrands(paging);
				
	}

	@Override
	public List<Map<String, String>> getCategoryList(Paging paging) {
		
		// logger index
		int idx = 0;
		logger.info("#{}. getBrandList()", idx++);
		logger.info("#{}. getBrandList() paging : {}", idx++, paging);
		
		return managementDao.selectAllCategorys(paging);
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public Paging getCategoryPaging(Paging paging) {
		// logger index
		int idx = 0;
		logger.info("#{}. getCategoryPaging()", idx++);
		logger.info("#{}. getCategoryPaging() paging : {}", idx++, paging);

		int page = 0;

		if (Integer.toString(paging.getCurPage()) != null && !"".equals(paging.getCurPage())) {
			page = paging.getCurPage();
			logger.info("#{}. getCategoryPaging() paging.getCurPage() : {}", idx++, paging.getCurPage());
		} else {
			logger.warn("there is no paging.getCurPage() or null value");
		}

		String keyword = paging.getKeyword();
		String category = paging.getCategory();

		// select total count of invoice
		int totalCount = managementDao.selectCntAllCategorys();

		logger.info("#{}. getCategoryPaging() totalCount : {}", idx++, totalCount);

		// create Paging dto - calculate paging
		Paging pagingReturn = new Paging(totalCount, page);

		pagingReturn.setCategory(category);
		pagingReturn.setKeyword(keyword);
		logger.info("#{}. getCategoryPaging() pagingReturn : {}", idx++, pagingReturn);

		return pagingReturn;
		
	}

	@Override
	public Paging getOrderUnitPaging(Paging paging) {
		// logger index
		int idx = 0;
		logger.info("#{}. getOrderUnitPaging()", idx++);
		logger.info("#{}. getOrderUnitPaging() paging : {}", idx++, paging);

		int page = 0;

		if (Integer.toString(paging.getCurPage()) != null && !"".equals(paging.getCurPage())) {
			page = paging.getCurPage();
			logger.info("#{}. getOrderUnitPaging() paging.getCurPage() : {}", idx++, paging.getCurPage());
		} else {
			logger.warn("there is no paging.getCurPage() or null value");
		}

		String keyword = paging.getKeyword();
		String category = paging.getCategory();

		// select total count of invoice
		int totalCount = managementDao.selectCntAllOrderUnits();

		logger.info("#{}. getOrderUnitPaging() totalCount : {}", idx++, totalCount);

		// create Paging dto - calculate paging
		Paging pagingReturn = new Paging(totalCount, page);

		pagingReturn.setCategory(category);
		pagingReturn.setKeyword(keyword);
		logger.info("#{}. getOrderUnitPaging() pagingReturn : {}", idx++, pagingReturn);

		return pagingReturn;
		
	}

	@Override
	public List<Map<String, String>> getOrderUnitList(Paging paging) {
		// logger index
		int idx = 0;
		logger.info("#{}. getBrandList()", idx++);
		logger.info("#{}. getBrandList() paging : {}", idx++, paging);
		
		return managementDao.selectAllOrderUnits(paging);
		
	}

	@Override
	public Paging getPrimaryUnitPaging(Paging paging) {
		// logger index
		int idx = 0;
		logger.info("#{}. getPrimaryUnitPaging()", idx++);
		logger.info("#{}. getPrimaryUnitPaging() paging : {}", idx++, paging);

		int page = 0;

		if (Integer.toString(paging.getCurPage()) != null && !"".equals(paging.getCurPage())) {
			page = paging.getCurPage();
			logger.info("#{}. getPrimaryUnitPaging() paging.getCurPage() : {}", idx++, paging.getCurPage());
		} else {
			logger.warn("there is no paging.getCurPage() or null value");
		}

		String keyword = paging.getKeyword();
		String category = paging.getCategory();

		// select total count of invoice
		int totalCount = managementDao.selectCntAllPrimaryUnits();

		logger.info("#{}. getPrimaryUnitPaging() totalCount : {}", idx++, totalCount);

		// create Paging dto - calculate paging
		Paging pagingReturn = new Paging(totalCount, page);

		pagingReturn.setCategory(category);
		pagingReturn.setKeyword(keyword);
		logger.info("#{}. getPrimaryUnitPaging() pagingReturn : {}", idx++, pagingReturn);

		return pagingReturn;
		
	}

	@Override
	public List<Map<String, String>> getPrimaryUnitList(Paging paging) {
		// logger index
		int idx = 0;
		logger.info("#{}. getBrandList()", idx++);
		logger.info("#{}. getBrandList() paging : {}", idx++, paging);
		
		return managementDao.selectAllPrimaryUnits(paging);
		
	}

	@Override
	public Paging getSecondaryUnitPaging(Paging paging) {
		// logger index
		int idx = 0;
		logger.info("#{}. getSecondaryUnitPaging()", idx++);
		logger.info("#{}. getSecondaryUnitPaging() paging : {}", idx++, paging);

		int page = 0;

		if (Integer.toString(paging.getCurPage()) != null && !"".equals(paging.getCurPage())) {
			page = paging.getCurPage();
			logger.info("#{}. getSecondaryUnitPaging() paging.getCurPage() : {}", idx++, paging.getCurPage());
		} else {
			logger.warn("there is no paging.getCurPage() or null value");
		}

		String keyword = paging.getKeyword();
		String category = paging.getCategory();

		// select total count of invoice
		int totalCount = managementDao.selectCntAllSecondaryUnits();

		logger.info("#{}. getSecondaryUnitPaging() totalCount : {}", idx++, totalCount);

		// create Paging dto - calculate paging
		Paging pagingReturn = new Paging(totalCount, page);

		pagingReturn.setCategory(category);
		pagingReturn.setKeyword(keyword);
		logger.info("#{}. getSecondaryUnitPaging() pagingReturn : {}", idx++, pagingReturn);

		return pagingReturn;
		
	}

	@Override
	public List<Map<String, String>> getSecondaryUnitList(Paging paging) {
		// logger index
		int idx = 0;
		logger.info("#{}. getBrandList()", idx++);
		logger.info("#{}. getBrandList() paging : {}", idx++, paging);
		
		return managementDao.selectAllSecondaryUnits(paging);
		
	}

	@Override
	public List<Brand> getBrandCategoryList() {
		return managementDao.getBrandList();
	}

}
