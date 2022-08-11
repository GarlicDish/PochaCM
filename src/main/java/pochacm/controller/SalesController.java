package pochacm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pochacm.dto.Paging;
import pochacm.dto.Recipe;
import pochacm.dto.Sales;
import pochacm.service.face.SalesService;

@Controller
public class SalesController {

	private static final Logger logger = LoggerFactory.getLogger(SalesController.class);
	
	@Autowired SalesService salesService;
	
	@GetMapping("/sales")
	public String salesList(
			HttpSession session, HttpServletRequest request, 
			 String curPage, Model model) {
		
		//logger index
		int idx = 0;
		logger.info("#{}. /sales [GET]", idx++);
		
		//if he/she does not login, back to login page
		if(session.getAttribute("userNum") == null) {
			logger.info("#{}. Not Logined", idx++);
			return "redirect:/login";
		}
		
		if(curPage == null || curPage.equals("")) {
			curPage = "1";
		}
		logger.info("#{}. curPage : {}", idx++, curPage);
	    
	    Paging paging = new Paging();
	    
	    paging.setCurPage(Integer.parseInt(curPage));
		//create Paging dto with curPage & search 
		paging = salesService.getSalesPaging(paging);
		logger.info("#{}. SalesList : {}", idx++, paging);
				
		//make a model with Sales list
		List<Sales> salesList = salesService.getSalesList(paging);
		
		logger.info("#{}. SalesList : {}", idx++, salesList);
		
		model.addAttribute("salesList", salesList);
		logger.info("#{}. model.getAttribute(\"salesList\") : {}", idx++, model.getAttribute("salesList"));
		
		model.addAttribute("paging", paging);
		logger.info("#{}. model.getAttribute(\"paging\") : {}", idx++, model.getAttribute("paging"));
		
		return "cm/salesList";
	}
	
	@GetMapping("/sales/add")
	public String salesAdd(Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /sales/add [GET]", idx++);
		
		model.addAttribute("salesSourceList",salesService.getSalesSourceList());
		
		return "cm/salesAdd";
	}
	
	@PostMapping("/sales/add")
	public String salesAddProc(HttpServletRequest req, HttpSession session, String date, Model model ) throws ParseException {
		//logger index
		int idx = 0;
		logger.info("#{}. /sales/add [POST]", idx++);
		date = req.getParameter("salesDate");

		String[] arrayName = req.getParameterValues("menuName");
		String[] arrayPrice = req.getParameterValues("menuPrice");
		String[] arrayQty = req.getParameterValues("qty");
		String[] arraySource = req.getParameterValues("salesSourceDiv");
		
		logger.info("#{}. userNum : {}", idx++, session.getAttribute("userNum"));
		logger.info("#{}. date : {}", idx++, date);
		logger.info("#{}. arrayName[] : {}", idx++, arrayName);
		logger.info("#{}. arrayPrice[] : {}", idx++, arrayPrice);
		logger.info("#{}. arrayQty[] : {}", idx++, arrayQty);
		logger.info("#{}. arraySource[] : {}", idx++, arraySource);
		
		for(int i = 0; i < arrayName.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("recipeName",arrayName[i]);
			map.put("salesQty", arrayQty[i]);
			map.put("salesDate",date);
			map.put("salesSourceNum", arraySource[i]);
			map.put("recipePrice", arrayPrice[i]);
			
			Recipe recipe = new Recipe();
			recipe.setRecipeName((String) map.get("recipeName"));
			recipe = salesService.getRecipeByRecipeName(recipe);
			
			Sales sales = salesService.getSalesFromMap(map);
			sales.setRecipeNum(recipe.getRecipeNum());
			sales.setUserNum( (int) session.getAttribute("userNum"));
			
			model.addAttribute("sales", sales);
			model.addAttribute("recipe", recipe);
			logger.info("#{}. recipe : {}", idx++, recipe);
			logger.info("#{}. sales : {}", idx++, sales);
			logger.info("#{}. map : {}", idx++, map);
			
			salesService.insertSales(sales);
		}
		
		return "redirect: /sales";
	}
	
	@GetMapping("/sales/view")
	public String viewSales(String salesDate, Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /sales/view [GET]", idx++);
		logger.info("#{}. salesDate : {}", idx++, salesDate);
		String date = salesDate.substring(0, 10);
		logger.info("#{}. date : {}", idx++, date);
		
		List<Sales> salesList = salesService.getSalesListBySalesDate(date);
		logger.info("#{}. salesList : {}", idx++, salesList);
		model.addAttribute("salesDate", salesDate);
		model.addAttribute("salesList", salesList);
		logger.info("#{}. model.addAttribute(\"salesList\", salesList) : {}", idx++, model.addAttribute("salesList", salesList));
		
		return "cm/salesView";
	}
	
	@PostMapping("/sales/delete")
	public String deleteSales(Sales sales, @DateTimeFormat(pattern="yyyy-MM-dd") Date salesDate) {
		//logger index
		int idx = 0;
		logger.info("#{}. /sales/delete [POST]", idx++);
		logger.info("#{}. sales : {}", idx++, sales);
		logger.info("#{}. salesDate : {}", idx++, salesDate);
		sales.setSalesDate(salesDate);
		logger.info("#{}. sales : {}", idx++, sales);

		salesService.deleteSalesBySalesNum(sales);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String date = sdf.format(salesDate);
		logger.info("#{}. date : {}", idx++, date);
		
		if (salesService.cntSalesBySalesDate(sales) > 0) {
			return "redirect: /sales/view?salesDate="+date;
		} else {
			return "redirect: /sales";
		}
		
	}
	
	@GetMapping("/sales/update")
	public String updateSales(@DateTimeFormat(pattern="yyyy-MM-dd") Date salesDate, Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /sales/update [GET]", idx++);
		Sales sales = new Sales();
		logger.info("#{}. salesDate : {}", idx++, salesDate);
		sales.setSalesDate(salesDate);
		logger.info("#{}. sales : {}", idx++, sales);
		
		List<Sales> salesList = salesService.getAllSalesBySalesDate(sales);
		logger.info("#{}. salesList : {}", idx++, salesList);
		
		model.addAttribute("sales",salesList);
		
		return "cm/salesUpdate";
	}
}
