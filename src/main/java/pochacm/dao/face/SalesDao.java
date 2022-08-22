package pochacm.dao.face;

import java.util.List;
import java.util.Map;

import pochacm.dto.Paging;
import pochacm.dto.Recipe;
import pochacm.dto.SalesInvoice;
import pochacm.dto.SalesSource;

public interface SalesDao {

	int selectCntAllSales(Paging paging);

	List<SalesInvoice> selectAllSales(Paging paging);

	List<SalesSource> getSalesSourceList();

	Recipe selectMenuBymenuName(Recipe recipe);

	int insertSales(SalesInvoice sales);

	List<SalesInvoice> selectSalesListBySalesDate(String date);

	void deleteSalesBySalesNum(SalesInvoice sales);

	int cntSalesBySalesDate(SalesInvoice sales);

	SalesInvoice getSalesBySalesNum(SalesInvoice sales);

	List<Map<String, String>> getAllSalesBySalesDate(SalesInvoice sales);

}
