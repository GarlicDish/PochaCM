package pochacm.dao.face;

import java.util.List;

import pochacm.dto.Invoice;
import pochacm.dto.Item;
import pochacm.dto.ItemCategory;
import pochacm.dto.OrderUnit;
import pochacm.dto.Paging;
import pochacm.dto.PrimaryUnit;
import pochacm.dto.Sales;
import pochacm.dto.SecondaryUnit;

public interface CMDao {

	int selectCntAllInvoice(String keyword, String category);

	List<Invoice> selectAllInvoice(Paging paging);

	List<Item> selectItemsByInvoiceNum(Invoice invoice);

	int selectCntAllInvoice(Paging paging);

	Invoice getInvoiceByInvoiceNum(Invoice invoice);

	Object selectItemInfoByItemNum(Item item);

	List<OrderUnit> getOrderUnitList();

	List<PrimaryUnit> getPrimaryUnitList();

	List<SecondaryUnit> getSecondaryUnitList();

	List<ItemCategory> getItemCategoryList();

	List<String> getCategoryByKeyword();

	int selectCntAllSales(Paging paging);

	List<Sales> selectAllSales(Paging paging);

	

}
