package pochacm.controller;

import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import pochacm.dto.Paging;
import pochacm.dto.Recipe;
import pochacm.dto.SalesInvoice;
import pochacm.dto.SalesAPI;
import pochacm.dto.SalesShowAPI;
import pochacm.service.face.SalesService;

@Controller
public class SalesController {

	private static final Logger logger = LoggerFactory.getLogger(SalesController.class);
	
	@Autowired SalesService salesService;
	
	@GetMapping("/sales")
	public String salesList(HttpSession session,@DateTimeFormat(pattern = "yyyy-MM-dd") Date dateParam, String curPage, Model model) {
		
		//logger index
		int idx = 0;
		logger.info("#{}. /sales [GET]", idx++);
		
		logger.info("#{}. curPage : {}", idx++, curPage);
		logger.info("#{}. dateParam : {}", idx++, dateParam);
		
		//adjust curPage
		if(curPage == null || curPage.equals("")) {
			curPage = "1";
		}
		logger.info("#{}. curPage : {}", idx++, curPage);
		
		//make paging
		Paging paging = new Paging();
		
		logger.info("#{}. paging : {}", idx++, paging);
		paging.setCurPage(Integer.parseInt(curPage));
		paging.setPageCount(15);
		logger.info("#{}. paging : {}", idx++, paging);
		
//		get API with paging info & date
		SalesAPI salesAPI = salesService.getAPI(paging, dateParam);
		
		paging.setTotalCount(salesAPI.getPagination().getTotal());
		
		paging = new Paging(paging.getTotalCount(),paging.getCurPage());
		logger.info("#{}. paging : {}", idx++, paging);
		
		logger.info("#{}. salesAPI : {}", idx++, salesAPI);
		
		model.addAttribute("salesAPI", salesAPI);
		logger.info("#{}. salesAPI : {}", idx++, salesAPI);
		
		model.addAttribute("paging", paging);
		logger.info("#{}. model.getAttribute(\"paging\") : {}", idx++, model.getAttribute("paging"));
		
		return "sales/salesList";
	}
	
	
	@GetMapping("/sales/view")
	public String viewSales(String invoiceNumber, Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /sales/view [GET]", idx++);
		logger.info("#{}. invoiceNumber : {}", idx++, invoiceNumber);

		SalesShowAPI salesAPIShow = salesService.getSalesViewByInvoiceNumber(invoiceNumber);
        
		model.addAttribute("salesAPIShow",salesAPIShow);
        logger.info("#{}. salesAPIShow : {}", idx++, salesAPIShow);
        
        return "sales/salesView";
	}
	
//	@PostMapping("/sales/delete")
//	public String deleteSales(SalesInvoice sales, @DateTimeFormat(pattern="yyyy-MM-dd") Date salesDate) {
//		//logger index
//		int idx = 0;
//		logger.info("#{}. /sales/delete [POST]", idx++);
//		logger.info("#{}. sales : {}", idx++, sales);
//		logger.info("#{}. salesDate : {}", idx++, salesDate);
//		sales.setSalesDate(salesDate);
//		logger.info("#{}. sales : {}", idx++, sales);
//
//		salesService.deleteSalesBySalesNum(sales);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		
//		String date = sdf.format(salesDate);
//		logger.info("#{}. date : {}", idx++, date);
//		
//		if (salesService.cntSalesBySalesDate(sales) > 0) {
//			return "redirect: /sales/view?salesDate="+date;
//		} else {
//			return "redirect: /sales";
//		}
//		
//	}
	
//	@GetMapping("/sales/update")
//	public String updateSales(@DateTimeFormat(pattern="yyyy-MM-dd") Date salesDate, Model model) {
//		//logger index
//		int idx = 0;
//		logger.info("#{}. /sales/update [GET]", idx++);
//		SalesInvoice sales = new SalesInvoice();
//		logger.info("#{}. salesDate : {}", idx++, salesDate);
//		sales.setSalesDate(salesDate);
//		logger.info("#{}. sales : {}", idx++, sales);
//		
//		List<Map<String,String>> salesList = salesService.getAllSalesBySalesDate(sales);
//		logger.info("#{}. salesList : {}", idx++, salesList);
//		
//		model.addAttribute("salesSourceList",salesService.getSalesSourceList());
//		model.addAttribute("salesDate", salesDate);
//		model.addAttribute("salesList",salesList);
//		
//		return "sales/salesUpdate";
//	}
//	@GetMapping("/sales/add")
//	public String salesAdd(Model model) {
//		//logger index
//		int idx = 0;
//		logger.info("#{}. /sales/add [GET]", idx++);
//		
//		model.addAttribute("salesSourceList",salesService.getSalesSourceList());
//		
//		return "sales/salesAdd";
//	}
//	
//	@PostMapping("/sales/add")
//	public String salesAddProc(HttpServletRequest req, HttpSession session, String date, Model model ) throws ParseException {
//		//logger index
//		int idx = 0;
//		logger.info("#{}. /sales/add [POST]", idx++);
//		date = req.getParameter("salesDate");
//
//		String[] arrayName = req.getParameterValues("menuName");
//		String[] arrayPrice = req.getParameterValues("menuPrice");
//		String[] arrayQty = req.getParameterValues("qty");
//		String[] arraySource = req.getParameterValues("salesSourceDiv");
//		
//		logger.info("#{}. userNum : {}", idx++, session.getAttribute("userNum"));
//		logger.info("#{}. date : {}", idx++, date);
//		logger.info("#{}. arrayName[] : {}", idx++, arrayName);
//		logger.info("#{}. arrayPrice[] : {}", idx++, arrayPrice);
//		logger.info("#{}. arrayQty[] : {}", idx++, arrayQty);
//		logger.info("#{}. arraySource[] : {}", idx++, arraySource);
//		
//		for(int i = 0; i < arrayName.length; i++) {
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			map.put("recipeName",arrayName[i]);
//			map.put("salesQty", arrayQty[i]);
//			map.put("salesDate",date);
//			map.put("salesSourceNum", arraySource[i]);
//			map.put("recipePrice", arrayPrice[i]);
//			
//			Recipe recipe = new Recipe();
//			recipe.setRecipeName((String) map.get("recipeName"));
//			recipe = salesService.getRecipeByRecipeName(recipe);
//			
//			SalesInvoice sales = salesService.getSalesFromMap(map);
//			sales.setRecipeNum(recipe.getRecipeNum());
//			sales.setUserNum( (int) session.getAttribute("userNum"));
//			
//			model.addAttribute("sales", sales);
//			model.addAttribute("recipe", recipe);
//			logger.info("#{}. recipe : {}", idx++, recipe);
//			logger.info("#{}. sales : {}", idx++, sales);
//			logger.info("#{}. map : {}", idx++, map);
//			
//			salesService.insertSales(sales);
//		}
//		
//		return "redirect: /sales";
//	}
}
