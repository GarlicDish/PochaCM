package pochacm.service.face;

import java.util.HashMap;
import java.util.List;

import pochacm.dto.Paging;
import pochacm.dto.Recipe;
import pochacm.dto.Sales;

public interface SalesService {
	Paging getSalesPaging(Paging paging);

	List<Sales> getSalesList(Paging paging);

	Object getSalesSourceList();

	Recipe getRecipeByRecipeName(Recipe map);

	int insertSales(Sales sales);
	void deleteSalesBySalesNum(String salesNum);
	List<Sales> getSalesListBySalesDate(String date);

	Sales getSalesFromMap(HashMap<String, Object> map);

}
