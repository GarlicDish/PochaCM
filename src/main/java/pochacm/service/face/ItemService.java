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

public interface ItemService {

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

}
