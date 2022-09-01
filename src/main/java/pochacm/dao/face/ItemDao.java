package pochacm.dao.face;

import java.util.List;
import java.util.Map;

import pochacm.dto.Brand;
import pochacm.dto.Category;
import pochacm.dto.Items;
import pochacm.dto.OrderUnit;
import pochacm.dto.Paging;
import pochacm.dto.PrimaryUnit;
import pochacm.dto.SecondaryUnit;

public interface ItemDao {

	int selectCntAllItems();

	List<Map<String, String>> selectAllItems(Paging paging);

	Object selectItemInfoByItemNum(Items item);

	List<OrderUnit> getOrderUnitList();

	List<SecondaryUnit> getSecondaryUnitList();

	List<Category> getItemCategoryList();

	List<PrimaryUnit> getPrimaryUnitList();

	void updateItemInformation(Items item);

	Brand selectBrandNumByBrandName(Brand brand);
	
	

}
