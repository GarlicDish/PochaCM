package pochacm.controller;

import java.util.Date;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import pochacm.dto.Paging;
import pochacm.dto.Recipe;
import pochacm.dto.Sales;
import pochacm.service.face.InvoiceService;
import pochacm.service.face.SalesService;

@Controller
public class SalesController {

	private static final Logger logger = LoggerFactory.getLogger(SalesController.class);
	
	@Autowired SalesService salesService;
	
	@GetMapping("/sales")
	public String salesList(
			HttpSession session, HttpServletRequest request, 
			ModelAndView mav, String curPage, Model model) {
		
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
		
		//Parameter delivery
		String category = request.getParameter("category");
		String keyword = request.getParameter("keyword");
		
		logger.info("#{}. category : {}", idx++, category);
		logger.info("#{}. keyWord : {}", idx++, keyword);
		logger.info("#{}. curPage : {}", idx++, curPage);
	    
		//prevent to insert null value to Mapper
		if(category == null || (!"subject".equals(category) && !"name".equals(category)) ) {
	       category = "";
	    }
	    
	    if(keyword == null || "".equals(keyword) || keyword.trim().isEmpty() ) {
	       keyword = "";
	    }
	    
	    Map<String, String> map = new HashMap<String, String>();
	    
	    map.put("category",category);
	    map.put("keyword",keyword);
	    
	    Paging paging = new Paging();
	    
	    paging.setKeyword(keyword);
	    paging.setCategory(category);
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
		
		model.addAttribute("salesList", salesList);
		logger.info("#{}. model.addAttribute(\"salesList\", salesList) : {}", idx++, model.addAttribute("salesList", salesList));
		
		return "cm/salesView";
	}
	
	@GetMapping("/sales/delete")
	public String deleteSales(String salesNum, String salesDate) {
		//logger index
		int idx = 0;
		logger.info("#{}. /sales/delete [GET]", idx++);
		logger.info("#{}. salesNum : {}", idx++, salesNum);
		
		salesService.deleteSalesBySalesNum(salesNum);
		
		return "redirect: /sales";
	}
}
