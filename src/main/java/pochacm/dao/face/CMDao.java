package pochacm.dao.face;

import java.util.List;

import pochacm.dto.Invoice;
import pochacm.dto.Item;
import pochacm.dto.Paging;

public interface CMDao {

	int selectCntAllInvoice(String keyword, String category);

	List<Invoice> selectAllInvoice(Paging paging);

	List<Item> selectItemsByInvoiceNum(Invoice invoice);

	int selectCntAllInvoice(Paging paging);

	Invoice getInvoiceByInvoiceNum(Invoice invoice);

	Object selectItemInfoByItemNum(Item item);


}
