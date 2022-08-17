package pochacm.service.face;

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

public interface InvoiceService {

	/**
	 * Get Paging information based on category and keyword
	 * 
	 * @param paging
	 * @return
	 */
	
	Paging getInvoicePaging(Paging paging);

	/**
	 * get invoice list after paging
	 * @param paging
	 * @return
	 */
	List<Map<String, String>> getInvoiceList(Paging paging);
	
	List<Map<String, String>> selectItemsByInvoiceSerial(Invoice invoice);

	Object getItemInfoByItem(Item item);

	List<OrderUnit> getOrderUnitList();

	List<PrimaryUnit> getPrimaryUnitList();

	List<SecondaryUnit> getSecondaryUnitList();

	List<Category> getItemCategoryList();

	Recipe getRecipeDtoWithRecipeName(String menuName);

	List<Recipe> getMenuSearchList(Recipe recipe);

	void updateItemInformation(Item item);

	void deleteInvoice(Invoice invoice);

	List<Brand> selectAllBrand();

	List<Supplier> selectAllSupplier();

	List<OrderUnit> selectAllOrderUnit();

	List<Category> selectAllCategory();

	List<Item> getItemListBySearch(Item item);

	Map<String, String> getInvoiceInfoByInvoiceSerial(Invoice invoice);

	void deleteInvoiceItemByNum(InvoiceItem invoiceItem);

	int countInvoiceItemByInvoiceNum(InvoiceItem invoiceItem);

	List<Item> getItemCodeListBySearch(Item itemName);

	void insertItemInfo(Item item);

	void insertInvoiceInfo(Invoice invoice);

	void insertInvoiceAndItemInfo(InvoiceItem invoiceItem);

	String getSupplierName(Invoice invoice);

	List<Map<String, String>> getItemListByInvoiceSerial(Invoice invoice);

	void updateInvoiceInfo(Invoice invoice);

	void insertInvoiceItemInfo(InvoiceItem invoiceItem);

	void updateInvoiceItemInfo(InvoiceItem invoiceItem);

	int selectInvoiceItem(InvoiceItem iin);

	
}
