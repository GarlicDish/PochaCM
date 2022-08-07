package pochacm.dao.face;

import java.util.HashMap;
import java.util.List;

import pochacm.dto.Paging;
import pochacm.dto.Recipe;
import pochacm.dto.Sales;
import pochacm.dto.SalesSource;

public interface SalesDao {

	int selectCntAllSales(Paging paging);

	List<Sales> selectAllSales(Paging paging);

	List<SalesSource> getSalesSourceList();

	Recipe selectMenuBymenuName(Recipe recipe);

	int insertSales(Sales sales);

	List<Sales> selectSalesListBySalesDate(String date);

	void deleteSalesBySalesNum(String salesNum);

}