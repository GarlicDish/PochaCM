package pochacm.service.face;

import java.util.List;
import java.util.Map;

import pochacm.dto.PurchaseInvoice;
import pochacm.dto.PurchaseInvoiceItem;
import pochacm.dto.Items;
import pochacm.dto.Brand;
import pochacm.dto.Category;
import pochacm.dto.OrderUnit;
import pochacm.dto.Paging;
import pochacm.dto.Recipe;
import pochacm.dto.Supplier;

public interface PurchaseInvoiceService {

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
	
	List<Map<String, String>> selectItemsByInvoiceSerial(PurchaseInvoice invoice);

	Recipe getRecipeDtoWithRecipeName(String menuName);

	List<Recipe> getMenuSearchList(Recipe recipe);

	void deleteInvoice(PurchaseInvoice invoice);

	List<Brand> selectAllBrand();

	List<Supplier> selectAllSupplier();

	List<OrderUnit> selectAllOrderUnit();

	List<Category> selectAllCategory();

	List<Items> getItemListBySearch(Items item);

	Map<String, String> getInvoiceInfoByInvoiceSerial(PurchaseInvoice invoice);

	void deleteInvoiceItemByNum(PurchaseInvoiceItem invoiceItem);

	int countInvoiceItemByInvoiceNum(PurchaseInvoiceItem invoiceItem);

	List<Items> getItemCodeListBySearch(Items itemName);

	void insertItemInfo(Items item);

	void insertInvoiceInfo(PurchaseInvoice invoice);

	void insertInvoiceAndItemInfo(PurchaseInvoiceItem invoiceItem);

	String getSupplierName(PurchaseInvoice invoice);

	List<Map<String, String>> getItemListByInvoiceSerial(PurchaseInvoice invoice);

	void updateInvoiceInfo(PurchaseInvoice invoice);

	void insertInvoiceItemInfo(PurchaseInvoiceItem invoiceItem);

	void updateInvoiceItemInfo(PurchaseInvoiceItem invoiceItem);

	int selectInvoiceItem(PurchaseInvoiceItem iin);

	
}
