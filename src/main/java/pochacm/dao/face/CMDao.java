package pochacm.dao.face;

import java.util.List;

import pochacm.dto.Invoice;
import pochacm.dto.Paging;

public interface CMDao {

	int selectCntAllInvoice(String search);

	List<Invoice> selectAllInvoice(Paging paging);


}
