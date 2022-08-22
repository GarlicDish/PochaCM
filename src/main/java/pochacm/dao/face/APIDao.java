package pochacm.dao.face;

import pochacm.dto.SalesInvoice;

public interface APIDao {

	void putAPItoDB(SalesInvoice i);

	int checkRefundChange(SalesInvoice i);

	int checkAPIExist(SalesInvoice i);

	void updateRefundChange(SalesInvoice i);

}
