package pochacm.service.face;

import java.util.List;

import org.springframework.ui.Model;

import pochacm.dto.Invoice;
import pochacm.dto.Item;
import pochacm.dto.ItemCategory;
import pochacm.dto.OrderUnit;
import pochacm.dto.Paging;
import pochacm.dto.PrimaryUnit;
import pochacm.dto.Recipe;
import pochacm.dto.SecondaryUnit;

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
	List<Invoice> getInvoiceList(Paging paging);

	
	List<Item> selectItemsByInvoiceNum(Invoice invoice);






	Invoice getInvoiceByInvoiceNum(Invoice invoice);


	Object getItemInfoByItem(Item item);


	List<OrderUnit> getOrderUnitList();


	List<PrimaryUnit> getPrimaryUnitList();


	List<SecondaryUnit> getSecondaryUnitList();


	List<ItemCategory> getItemCategoryList();


	List<String> getCategoryByKeyword(String keyword);



	Recipe getRecipeDtoWithRecipeName(String menuName);


	List<Recipe> getMenuSearchList(Recipe recipe);



	List<Recipe> makeRecipeListFromParams(Model model);


	void updateItemInformation(Item item);









	
}
