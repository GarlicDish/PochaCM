package pochacm.service.face;

import java.util.List;

import pochacm.dto.Invoice;
import pochacm.util.Paging;

public interface CMService {

	/**
	 * invoice paging with search words info & current page
	 * @param curPage
	 * @param search
	 * @return
	 */
	Paging getInvoicePaging(String curPage, String search);

	/**
	 * get invoice list after paging
	 * @param paging
	 * @return
	 */
	List<Invoice> getInvoiceList(Paging paging);
	
}
