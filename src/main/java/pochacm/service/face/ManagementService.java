package pochacm.service.face;

import java.util.List;
import java.util.Map;

import pochacm.dto.Brand;
import pochacm.dto.Category;
import pochacm.dto.Items;
import pochacm.dto.OrderUnit;
import pochacm.dto.Paging;
import pochacm.dto.PrimaryUnit;
import pochacm.dto.SecondaryUnit;

public interface ManagementService {

	Paging getItemPaging(Paging paging);

	List<Map<String, String>> getItemList(Paging paging);

	Object getItemInfoByItem(Items item);

	List<Category> getItemCategoryList();

	List<OrderUnit> getOrderUnitList();

	List<PrimaryUnit> getPrimaryUnitList();

	List<SecondaryUnit> getSecondaryUnitList();

	void updateItemInformation(Items item);

	/**
	 * get brand Number by brand name
	 * @param brand 
	 * 
	 * @return brandNum
	 */
	Brand getBrandNumByBrandName(Brand brand);

	/**
	 * get total number of menus to make paging
	 * @param paging
	 * @return
	 */
	Paging getMenuPaging(Paging paging);

	/**
	 * make menu list with paging
	 * @param paging
	 * @return
	 */
	List<Map<String, String>> getMenuList(Paging paging);

	Paging getBrandPaging(Paging paging);

	List<Map<String, String>> getBrandList(Paging paging);


	List<Map<String, String>> getCategoryList(Paging paging);

	Paging getCategoryPaging(Paging paging);

	Paging getOrderUnitPaging(Paging paging);

	List<Map<String, String>> getOrderUnitList(Paging paging);

	Paging getPrimaryUnitPaging(Paging paging);

	List<Map<String, String>> getPrimaryUnitList(Paging paging);

	Paging getSecondaryUnitPaging(Paging paging);

	List<Map<String, String>> getSecondaryUnitList(Paging paging);

	List<Brand> getBrandCategoryList();

}
