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

public interface ManagementDao {

	int selectCntAllItems();

	List<Map<String, String>> selectAllItems(Paging paging);

	Object selectItemInfoByItemNum(Items item);

	List<OrderUnit> getOrderUnitList();

	List<SecondaryUnit> getSecondaryUnitList();

	List<Category> getItemCategoryList();

	List<PrimaryUnit> getPrimaryUnitList();

	void updateItemInformation(Items item);

	Brand selectBrandNumByBrandName(Brand brand);

	int selectCntAllMenus();

	List<Map<String, String>> selectAllMenus(Paging paging);

	int selectCntAllBrands();

	List<Map<String, String>> selectAllBrands(Paging paging);

	List<Map<String, String>> selectAllCategorys(Paging paging);

	List<Map<String, String>> selectAllOrderUnits(Paging paging);

	List<Map<String, String>> selectAllPrimaryUnits(Paging paging);

	List<Map<String, String>> selectAllSecondaryUnits(Paging paging);

	int selectCntAllCategorys();

	int selectCntAllOrderUnits();

	int selectCntAllPrimaryUnits();

	int selectCntAllSecondaryUnits();

	List<Brand> getBrandList();
	
	

}
