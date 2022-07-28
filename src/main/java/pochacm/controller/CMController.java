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
import org.springframework.web.servlet.ModelAndView;

import pochacm.dto.Invoice;
import pochacm.dto.Item;
import pochacm.dto.Paging;
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
			ModelAndView mav, String curPage, Model model) {
		
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
		if(category == null || (!"subject".equals(category) && !"name".equals(category)) ) {
	       category = "";
	    }
	    
	    if(keyword == null || "".equals(keyword) || keyword.trim().isEmpty() ) {
	       keyword = "";
	    }
	    
	    Map<String, String> map = new HashMap<>();
	    
	    map.put("category",category);
	    map.put("keyword",keyword);
	    
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
		
		return "cm/invoiceList";
	}
	
	@GetMapping("/invoiceView")
	public String invoiceView(HttpSession session, Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /invoiceView [GET]", idx++);
		
		Invoice invoice = new Invoice();
		if(session.getAttribute("invoiceNum") != null || session.getAttribute("invoiceNum").equals("")) {
			invoice.setInvoiceNum((int) session.getAttribute("invoiceNum"));
		}
		
		List<Item> itemList = cmService.selectItemsByInvoiceNum(invoice);
		
		model.addAttribute("itemList",itemList);
				
		return "cm/invoiceView";
	}
	
	@GetMapping("/sales")
	public String salesList(HttpSession session, String curPage, 
			String search, String sort, Model model) {
	
		//logger index
		int idx = 0;
		logger.info("#{}. /sales [GET]", idx++);

		//if he/she does not login, back to login page
		if(session.getAttribute("userNum") == null) {
			logger.info("#{}. Not Logined", idx++);
			return "redirect:/login";
		}
		
		return "cm/salesList";
	}

}
