package pochacm.service.face;

import java.util.List;

import pochacm.dto.Invoice;
import pochacm.dto.Item;
import pochacm.dto.ItemCategory;
import pochacm.dto.OrderUnit;
import pochacm.dto.Paging;
import pochacm.dto.PrimaryUnit;
import pochacm.dto.Recipe;
import pochacm.dto.Sales;
import pochacm.dto.SecondaryUnit;

public interface CMService {

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
	List<Invoice> getInvoiceList(Paging paging);

	
	List<Item> selectItemsByInvoiceNum(Invoice invoice);


	Paging getSalesPaging(Paging paging);


	List<Sales> getSalesList(Paging paging);


	Invoice getInvoiceByInvoiceNum(Invoice invoice);


	Object getItemInfoByItem(Item item);


	List<OrderUnit> getOrderUnitList();


	List<PrimaryUnit> getPrimaryUnitList();


	List<SecondaryUnit> getSecondaryUnitList();


	List<ItemCategory> getItemCategoryList();


	List<String> getCategoryByKeyword(String keyword);


	Object getSalesSourceList();


	Recipe getRecipeByRecipeName(String menuName);


	List<Recipe> getMenuSearchList(Recipe recipe);


	
}
