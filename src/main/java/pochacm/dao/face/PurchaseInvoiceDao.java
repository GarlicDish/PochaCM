package pochacm.dao.face;

import java.util.List;
import java.util.Map;

import pochacm.dto.PurchaseInvoice;
import pochacm.dto.InvoiceItem;
import pochacm.dto.Items;
import pochacm.dto.Brand;
import pochacm.dto.Category;
import pochacm.dto.OrderUnit;
import pochacm.dto.Paging;
import pochacm.dto.PrimaryUnit;
import pochacm.dto.Recipe;
import pochacm.dto.SecondaryUnit;
import pochacm.dto.Supplier;

public interface PurchaseInvoiceDao {

	int selectCntAllInvoice(String keyword, String category);

	List<Map<String, String>> selectAllInvoice(Paging paging);

	List<Map<String, String>> selectItemsByInvoiceSerial(PurchaseInvoice invoice);

	int selectCntAllInvoice(Paging paging);

	Object selectItemInfoByItemNum(Items item);

	List<OrderUnit> getOrderUnitList();

	List<PrimaryUnit> getPrimaryUnitList();

	List<SecondaryUnit> getSecondaryUnitList();

	List<Category> getItemCategoryList();

	List<Recipe> selectMenuSearchList(Recipe recipe);

	void updateItemInformation(Items item);

	void deleteInvoice(PurchaseInvoice invoice);

	List<Brand> selectAllBrand();

	List<Supplier> selectAllSupplier();

	List<OrderUnit> selectAllOrderUnit();

	List<Category> selectAllCategory();

	List<Items> selectItemSearchList(Items item);

	Map<String, String> selectInvoiceInfoByInvoiceSerial(PurchaseInvoice invoice);

	void deleteInvoiceItemByNum(InvoiceItem invoiceItem);

	int selectInvoiceItemByInvoiceNum(InvoiceItem invoiceItem);

	List<Items> selectItemCodeSearchList(Items item);

	void insertItemInfo(Items item);

	void insertInvoiceInfo(PurchaseInvoice invoice);

	void insertInvoiceAndItemInfo(InvoiceItem invoiceItem);

	String selectSupplierNameBySupplierNum(PurchaseInvoice invoice);

	List<Map<String, String>> selectItemListByInvoiceSerial(PurchaseInvoice invoice);

	void updateInvoiceInfo(PurchaseInvoice invoice);

	void insertInvoiceItemInfo(InvoiceItem invoiceItem);

	void updateInvoiceItemInfo(InvoiceItem invoiceItem);

	int selectInvoiceItem(InvoiceItem iin);

}
