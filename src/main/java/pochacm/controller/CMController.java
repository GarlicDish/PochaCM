package pochacm.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pochacm.dto.Invoice;
import pochacm.dto.Paging;
import pochacm.service.face.CMService;

@Controller
public class CMController {
	
	private static final Logger logger = LoggerFactory.getLogger(CMController.class);
	
	@Autowired CMService cmService;
	
	@GetMapping("/summary")
	public String viewSummary(HttpRequest req) {
		//logger index
		int idx = 0;
		logger.info("#{}. Entering summary page [GET]", idx++);
		
		
		
		return "cm/summary";
	}
	
	@GetMapping("/invoice")
	public String viewInvoiceList(
			HttpSession session, String curPage, 
			String search, String sort, Model model) {
		
		//logger index
		int idx = 0;
		logger.info("#{}. /invoice [GET]", idx++);
		
		//if he/she does not login, back to login page
		if(session.getAttribute("userNum") == null) {
			logger.info("#{}. Not Logined", idx++);
			return "redirect:/login";
		}
		
		logger.info("#{}. sort : {}", idx++, sort);
		logger.info("#{}. search : {}", idx++, search);
		
		//create Paging dto with curPage & search 
		Paging paging = cmService.getInvoicePaging(curPage,search);
		//make a model with invoice list
		List<Invoice> invoiceList = cmService.getInvoiceList(paging);
		logger.info("#{}. invoiceList : {}", idx++, invoiceList);
		
//		logger.info("#{}. curPage : {}", idx++, curPage);
//		logger.info("#{}. search : {}", idx++, search);
//		logger.info("#{}. sort : {}", idx++, sort);
//		
//		
//		//setting for get Monday and Sunday information
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");	
//		
//		//get today
//		Calendar cal = Calendar.getInstance();
//		logger.info("#{}. mDay : {}", idx++, cal);
//		
//		
//		//get this week's SUNDAY
//		cal.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
//		String sunday = df.format(cal.getTime());
//		
//		logger.info("#{}. sunday : {}", idx++, sunday);
//		
//		//Get this week's Monday for paging as weekly
//		cal.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
//		String saturday = df.format(cal.getTime());
//		
//		logger.info("#{}. saturday : {}", idx++, saturday);
		
		model.addAttribute("invoiceList", invoiceList);
		logger.info("#{}. model.getAttribute(\"invoiceList\") : {}", idx++, model.getAttribute("invoiceList"));
		
		model.addAttribute("paging", paging);
		logger.info("#{}. model.getAttribute(\"paging\") : {}", idx++, model.getAttribute("paging"));
		
		model.addAttribute("search", search);
		logger.info("#{}. model.getAttribute(\"search\") : {}", idx++, model.getAttribute("search"));
		
		model.addAttribute("sort",sort);
		logger.info("#{}. model.getAttribute(\"sort\") : {}", idx++, model.getAttribute("sort"));
		
		return "cm/invoiceList";
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
