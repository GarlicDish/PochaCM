package pochacm.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pochacm.dao.face.ItemDao;
import pochacm.dto.Brand;
import pochacm.dto.Category;
import pochacm.dto.Items;
import pochacm.dto.OrderUnit;
import pochacm.dto.Paging;
import pochacm.dto.PrimaryUnit;
import pochacm.dto.SecondaryUnit;
import pochacm.service.face.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

	@Autowired
	ItemDao itemDao;

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
		int totalCount = itemDao.selectCntAllItems();

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

		return itemDao.selectAllItems(paging);
	}

	@Override
	public Object getItemInfoByItem(Items item) {
		return itemDao.selectItemInfoByItemNum(item);
	}

	@Override
	public List<OrderUnit> getOrderUnitList() {
		return itemDao.getOrderUnitList();
	}

	@Override
	public List<PrimaryUnit> getPrimaryUnitList() {
		return itemDao.getPrimaryUnitList();
	}

	@Override
	public List<SecondaryUnit> getSecondaryUnitList() {
		return itemDao.getSecondaryUnitList();
	}

	@Override
	public List<Category> getItemCategoryList() {
		return itemDao.getItemCategoryList();
	}

	@Override
	public void updateItemInformation(Items item) {
		itemDao.updateItemInformation(item);
	}

	@Override
	public Brand getBrandNumByBrandName(Brand brand) {
		
		// logger index
		int idx = 0;
		logger.info("#{}. getBrandNumByBrandName()", idx++);
		logger.info("#{}. getBrandNumByBrandName() brandName : {}", idx++, brand);
		
		return itemDao.selectBrandNumByBrandName(brand);
	}

}
