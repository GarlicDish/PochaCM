package pochacm.service.face;

import java.util.List;

import pochacm.dto.Invoice;
import pochacm.dto.Item;
import pochacm.dto.Paging;
import pochacm.dto.Sales;

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


	
}
