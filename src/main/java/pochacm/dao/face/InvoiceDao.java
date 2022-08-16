package pochacm.dao.face;

import java.util.List;
import java.util.Map;

import pochacm.dto.Invoice;
import pochacm.dto.InvoiceItem;
import pochacm.dto.Item;
import pochacm.dto.Brand;
import pochacm.dto.Category;
import pochacm.dto.OrderUnit;
import pochacm.dto.Paging;
import pochacm.dto.PrimaryUnit;
import pochacm.dto.Recipe;
import pochacm.dto.SecondaryUnit;
import pochacm.dto.Supplier;

public interface InvoiceDao {

	int selectCntAllInvoice(String keyword, String category);

	List<Map<String, String>> selectAllInvoice(Paging paging);

	List<Map<String, String>> selectItemsByInvoiceNum(Invoice invoice);

	int selectCntAllInvoice(Paging paging);

	Object selectItemInfoByItemNum(Item item);

	List<OrderUnit> getOrderUnitList();

	List<PrimaryUnit> getPrimaryUnitList();

	List<SecondaryUnit> getSecondaryUnitList();

	List<Category> getItemCategoryList();

	List<Recipe> selectMenuSearchList(Recipe recipe);

	void updateItemInformation(Item item);

	void deleteInvoice(Invoice invoice);

	List<Brand> selectAllBrand();

	List<Supplier> selectAllSupplier();

	List<OrderUnit> selectAllOrderUnit();

	List<Category> selectAllCategory();

	List<Item> selectItemSearchList(Item item);

	Map<String, String> selectInvoiceInfoByInvoiceNum(Invoice invoice);

	void deleteInvoiceItemByNum(InvoiceItem invoiceItem);

	int selectInvoiceItemByInvoiceNum(InvoiceItem invoiceItem);

}
