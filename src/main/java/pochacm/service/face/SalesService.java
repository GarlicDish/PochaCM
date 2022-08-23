package pochacm.service.face;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.springframework.http.ResponseEntity;

import pochacm.dto.Paging;
import pochacm.dto.Recipe;
import pochacm.dto.SalesInvoice;
import pochacm.dto.SalesShowAPI;
import pochacm.dto.SalesAPI;

public interface SalesService {
	Paging getSalesPaging(Paging paging);

	List<SalesInvoice> getSalesList(Paging paging);

	Object getSalesSourceList();

	Recipe getRecipeByRecipeName(Recipe map);

	int insertSales(SalesInvoice sales);
	
	void deleteSalesBySalesNum(SalesInvoice sales);
	
	List<SalesInvoice> getSalesListBySalesDate(String date);

	SalesInvoice getSalesFromMap(HashMap<String, Object> map);

	int cntSalesBySalesDate(SalesInvoice sales);

	SalesInvoice getSalesBySalesNum(SalesInvoice sales);

	List<Map<String, String>> getAllSalesBySalesDate(SalesInvoice sales);

	SalesAPI getAPI(Paging paging, Date dateParam);

	SalesShowAPI getSalesViewByInvoiceNumber(String invoiceNumber);

}
