package pochacm.service.face;

import java.util.List;

import pochacm.dto.Invoice;
import pochacm.dto.Item;
import pochacm.dto.Paging;

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


	
}
