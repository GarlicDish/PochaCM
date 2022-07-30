package pochacm.controller;


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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pochacm.dto.Invoice;
import pochacm.dto.Item;
import pochacm.dto.ItemCategory;
import pochacm.dto.OrderUnit;
import pochacm.dto.Paging;
import pochacm.dto.PrimaryUnit;
import pochacm.dto.Sales;
import pochacm.dto.SecondaryUnit;
import pochacm.service.face.CMService;

@Controller
public class CMController {
	
	private static final Logger logger = LoggerFactory.getLogger(CMController.class);
	
	@Autowired CMService cmService;
	
	@GetMapping("/summary")
	public String viewSummary(HttpServletRequest req) {
		//logger index
		int idx = 0;
		logger.info("#{}. Entering summary page [GET]", idx++);
		
		return "cm/summary";
	}
	
	@GetMapping("/invoice")
	public String invoiceList(
			HttpSession session, HttpServletRequest request, 
			String curPage, Model model) {
		
		//logger index
		int idx = 0;
		logger.info("#{}. /invoice [GET]", idx++);
		
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
		if(category == null || category.equals("") ) {
	       category = "";
	    }
	    
	    if(keyword == null || "".equals(keyword) || keyword.trim().isEmpty() ) {
	       keyword = "";
	    }
	    
	    Paging paging = new Paging();
	    
	    paging.setKeyword(keyword);
	    paging.setCategory(category);
	    paging.setCurPage(Integer.parseInt(curPage));
	    
		//create Paging dto with curPage & search 
		paging = cmService.getInvoicePaging(paging);
		logger.info("#{}. invoiceList : {}", idx++, paging);
		
		//make a model with invoice list
		List<Invoice> invoiceList = cmService.getInvoiceList(paging);
		
		logger.info("#{}. invoiceList : {}", idx++, invoiceList);
		
		model.addAttribute("invoiceList", invoiceList);
		logger.info("#{}. model.getAttribute(\"invoiceList\") : {}", idx++, model.getAttribute("invoiceList"));
		
		model.addAttribute("paging", paging);
		logger.info("#{}. model.getAttribute(\"paging\") : {}", idx++, model.getAttribute("paging"));
		
		model.addAttribute("category", category);
		logger.info("#{}. model.getAttribute(\"category\") : {}", idx++, model.getAttribute("category"));
		
		model.addAttribute("keyword", keyword);
		logger.info("#{}. model.getAttribute(\"keyword\") : {}", idx++, model.getAttribute("keyword"));
		
		return "cm/invoiceList";
	}
	
	@GetMapping("/invoice/view")
	public String invoiceView(HttpSession session, HttpServletRequest request, Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /invoiceView [GET]", idx++);
		logger.info("#{}. request.getParameter(\"invoiceNum\") : {}", idx++,request.getParameter("invoiceNum"));
		
		//Parameter delivery
		String category = request.getParameter("category");
		String keyword = request.getParameter("keyword");
				
		Invoice invoice = new Invoice();
		if(request.getParameter("invoiceNum") != null || request.getParameter("invoiceNum").equals("")) {
			invoice.setInvoiceNum(Integer.parseInt(request.getParameter("invoiceNum")));
		}
		invoice = cmService.getInvoiceByInvoiceNum(invoice);
		List<Item> itemList = cmService.selectItemsByInvoiceNum(invoice);
		
		model.addAttribute("itemList",itemList);
		logger.info("#{}. itemList : {}", idx++, itemList);
		
		model.addAttribute("category",category);
		logger.info("#{}. category : {}", idx++, category);
		
		model.addAttribute("keyword",keyword);
		logger.info("#{}. keyWord : {}", idx++, keyword);
		
		model.addAttribute("invoice",invoice);
		logger.info("#{}. invoice : {}", idx++, invoice);
				
		return "cm/invoiceView";
	}
	
	@GetMapping(value="/item/view")
	public String itemView(HttpSession session, HttpServletRequest request, Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /itemView [GET]", idx++);
		
		//Parameter delivery
		String itemNum = request.getParameter("itemNum");
		logger.info("#{}. itemNum : {}", idx++, itemNum);
		
		Item item = new Item();
		
		item.setItemNum(Integer.parseInt(itemNum));
		logger.info("#{}. item : {}", idx++, item);
		
		model.addAttribute("itemInfo", cmService.getItemInfoByItem(item));
		logger.info("#{}. model.getAttribute(\"itemInfo\") : {}", idx++, model.getAttribute("itemInfo"));
		
		return "cm/itemView";
	}
	
	@GetMapping(value="/item/update")
	public String itemUpdate(HttpSession session, HttpServletRequest req, Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /item/update [GET]", idx++);
		
		//Parameter delivery
		String itemNum = req.getParameter("itemNum");
		logger.info("#{}. itemNum : {}", idx++, itemNum);
		
		Item item = new Item();
		
		item.setItemNum(Integer.parseInt(itemNum));
		logger.info("#{}. item : {}", idx++, item);
		
		//Get Order Unit, Primary Unit, Secondary Unit Lists
		
		List<ItemCategory> icList = cmService.getItemCategoryList();
		List<OrderUnit> ouList = cmService.getOrderUnitList();
		List<PrimaryUnit> puList = cmService.getPrimaryUnitList();
		List<SecondaryUnit> suList = cmService.getSecondaryUnitList();
		
		model.addAttribute("itemInfo", cmService.getItemInfoByItem(item));
		logger.info("#{}. model.getAttribute(\"itemInfo\") : {}", idx++, model.getAttribute("itemInfo"));
		
		model.addAttribute("icList", icList);
		logger.info("#{}. icList : {}", idx++, icList);
		
		model.addAttribute("ouList", ouList);
		logger.info("#{}. ouList : {}", idx++, ouList);
		
		model.addAttribute("puList", puList);
		logger.info("#{}. puList : {}", idx++, puList);
		
		model.addAttribute("suList", suList);
		logger.info("#{}. suList : {}", idx++, suList);
		
		return "cm/itemUpdate";
	}
	
	@RequestMapping(value="/item/update", method=RequestMethod.POST)
	public String itemUpdateSubmit(HttpSession session, HttpServletRequest req, Model model) {
		
		if (session.getAttribute("positionNum") == "2") {
			return "redirect:/item/view?itemNum=";
		}
		
		String itemNum = "";
		
		return "redirct : /cm/itemView?itemNum="+itemNum;
	}
	
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
		paging = cmService.getSalesPaging(paging);
		logger.info("#{}. SalesList : {}", idx++, paging);
				
		//make a model with Sales list
		List<Sales> salesList = cmService.getSalesList(paging);
		
		logger.info("#{}. SalesList : {}", idx++, salesList);
		
		model.addAttribute("salesList", salesList);
		logger.info("#{}. model.getAttribute(\"salesList\") : {}", idx++, model.getAttribute("salesList"));
		
		model.addAttribute("paging", paging);
		logger.info("#{}. model.getAttribute(\"paging\") : {}", idx++, model.getAttribute("paging"));
		
		
		return "cm/salesList";
	}
	
	
	//------------------------AJAX ------------------------------
//	@GetMapping("/item/getCategory")
//	@ResponseBody
//	public List<String> getCategory(String keyword){
//		//logger index
//		int idx = 0;
//		logger.info("#{}. /item/getCategory [AJAX] [GET]", idx++);
//		logger.info("#{}. keyword : {}", idx++, keyword);
//		
//		return cmService.getCategoryByKeyword(keyword);
//	}

	
}
