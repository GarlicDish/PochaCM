package pochacm.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pochacm.dto.Invoice;
import pochacm.dto.Item;
import pochacm.dto.Brand;
import pochacm.dto.Category;
import pochacm.dto.OrderUnit;
import pochacm.dto.Paging;
import pochacm.dto.PrimaryUnit;
import pochacm.dto.Recipe;
import pochacm.dto.SecondaryUnit;
import pochacm.dto.Supplier;
import pochacm.dto.InvoiceItem;
import pochacm.service.face.InvoiceService;
import pochacm.service.face.SalesService;

@Controller
public class InvoiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);
	
	@Autowired InvoiceService invoiceService;
	@Autowired SalesService salesService;
	
	
	@GetMapping("/invoice")
	public String invoiceList(
			HttpSession session, Paging paging, Model model) {
		
		//logger index
		int idx = 0;
		logger.info("#{}. /invoice [GET]", idx++);
		
		//if he/she does not login, back to login page
		if(session.getAttribute("userNum") == null) {
			logger.info("#{}. Not Logined", idx++);
			return "redirect:/login";
		}
		logger.info("#{}. invoiceList : {}", idx++, paging);
	    
		//create Paging dto with curPage & search
		paging = invoiceService.getInvoicePaging(paging);
		logger.info("#{}. invoiceList : {}", idx++, paging);
		
		//make a model with invoice list
		List<Map<String, String>> invoiceList = invoiceService.getInvoiceList(paging);
		
		logger.info("#{}. invoiceList : {}", idx++, invoiceList);
		
		model.addAttribute("invoiceList", invoiceList);
		logger.info("#{}. model.getAttribute(\"invoiceList\") : {}", idx++, model.getAttribute("invoiceList"));
		
		model.addAttribute("paging", paging);
		logger.info("#{}. model.getAttribute(\"paging\") : {}", idx++, model.getAttribute("paging"));
		
		return "invoice/list";
	}
	
	@GetMapping("/invoice/view")
	public String invoiceView(HttpSession session, Invoice invoice, Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /invoiceView [POST]", idx++);
		logger.info("#{}. invoice : {}", idx++,invoice);
		
		Map<String,String> invInfo = invoiceService.getInvoiceInfoByInvoiceNum(invoice);
		List<Map<String,String>> itemList = invoiceService.selectItemsByInvoiceNum(invoice);
		
		model.addAttribute("itemList",itemList);
		logger.info("#{}. itemList : {}", idx++, itemList);
		model.addAttribute("invInfo",invInfo);
		logger.info("#{}. invInfo : {}", idx++, invInfo);
				
		return "invoice/view";
	}
	
	@GetMapping("/invoice/add")
	public String invoiceAdd(Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /invoice/add [GET]", idx++);
		
		List<Brand> brandList = invoiceService.selectAllBrand();
		List<Supplier> supplierList = invoiceService.selectAllSupplier();
		List<OrderUnit> orderUnitList = invoiceService.selectAllOrderUnit();
		List<Category> categoryList = invoiceService.selectAllCategory();
		
		logger.info("#{}. brandList : {}", idx++, brandList);
		logger.info("#{}. supplierList : {}", idx++, supplierList);
		logger.info("#{}. orderUnitList : {}", idx++, orderUnitList);
		logger.info("#{}. categoryList : {}", idx++, categoryList);
		
		model.addAttribute("brandList",brandList);
		model.addAttribute("supplierList",supplierList);
		model.addAttribute("orderUnitList",orderUnitList);
		model.addAttribute("categoryList",categoryList);
		
		
		return "invoice/add";
	}
	
	@PostMapping("/invoice/add")
	public String invoiceItemAdd(Item item) {
		//logger index
		int idx = 0;
		logger.info("#{}. /invoice/add [POST]", idx++);
		
		
		return "redirect:/invoice/view?invoiceSerial=";
	}
	
	@GetMapping(value="/item/view")
	public String itemView(HttpSession session, Item item, Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /item/view [GET]", idx++);
		logger.info("#{}. item : {}", idx++, item);
		
		model.addAttribute("itemInfo", invoiceService.getItemInfoByItem(item));
		logger.info("#{}. model.getAttribute(\"itemInfo\") : {}", idx++, model.getAttribute("itemInfo"));
		
		return "item/view";
	}
	
	@GetMapping(value="/item/update")
	public String itemUpdate(HttpSession session, Item item, Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /item/update [GET]", idx++);
		
		logger.info("#{}. item : {}", idx++, item);
		
		//Get Order Unit, Primary Unit, Secondary Unit Lists
		
		List<Category> icList = invoiceService.getItemCategoryList();
		List<OrderUnit> ouList = invoiceService.getOrderUnitList();
		List<PrimaryUnit> puList = invoiceService.getPrimaryUnitList();
		List<SecondaryUnit> suList = invoiceService.getSecondaryUnitList();
		
		model.addAttribute("itemInfo", invoiceService.getItemInfoByItem(item));
		logger.info("#{}. model.getAttribute(\"itemInfo\") : {}", idx++, model.getAttribute("itemInfo"));
		
		model.addAttribute("icList", icList);
		//logger.info("#{}. icList : {}", idx++, icList);
		
		model.addAttribute("ouList", ouList);
		//logger.info("#{}. ouList : {}", idx++, ouList);
		
		model.addAttribute("puList", puList);
		//logger.info("#{}. puList : {}", idx++, puList);
		
		model.addAttribute("suList", suList);
		//logger.info("#{}. suList : {}", idx++, suList);
		
		return "item/update";
	}
	
	@RequestMapping(value="/item/update", method=RequestMethod.POST)
	public String itemUpdateSubmit(HttpSession session, Item item, Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /item/update [POST]", idx++);
		logger.info("#{}. item : {}", idx++, item);
		
		if (session.getAttribute("positionNum") == "2") {
			return "redirect:/item/view?itemNum=" + item.getItemNum();
		}
		
		invoiceService.updateItemInformation(item);
		
		return "redirect:/item/view?itemNum=" + item.getItemNum();
	}
	
	
	@PostMapping("/invoice/delete")
	public String deleteInvoice(Invoice invoice, Paging paging) {
		//logger index
		int idx = 0;
		logger.info("#{}. /item/delete [POST]", idx++);
		logger.info("#{}. invoice : {}", idx++, invoice);
		logger.info("#{}. paging : {}", idx++, paging);
		
		invoiceService.deleteInvoice(invoice);
		
		return "redirect:/invoice?curPage=" + paging.getCurPage();
	}
	
	@PostMapping("/invoice/invoiceItemDelete")
	public String deleteInvoiceItem(InvoiceItem invoiceItem) {
		
		//logger index
		int idx = 0;
		logger.info("#{}. /item/invoiceItemDelete [POST]", idx++);
		logger.info("#{}. invoiceItem : {}", idx++, invoiceItem);
		
		invoiceService.deleteInvoiceItemByNum(invoiceItem);
		
		if (invoiceService.countInvoiceItemByInvoiceNum(invoiceItem)>0) {
			return "redirect:/invoice/view?invoiceNum="+invoiceItem.getInvoiceNum();
		} else {
			return "redirect:/invoice";
		}
		
	}
	
	
	
	
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//------------------------AJAX ------------------------------
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	@GetMapping("/menuList")
	@ResponseBody
	public List<Recipe> getMenuSearchList(String menuName){
		//logger index
		int idx = 0;
		logger.info("#{}. /menuList [AJAX] [GET]", idx++);
		
		logger.info("#{}. menuName : {}", idx++, menuName);
		
		Recipe recipe = invoiceService.getRecipeDtoWithRecipeName(menuName);
		logger.info("#{}. recipe : {}", idx++, recipe);
		
		List<Recipe> resultList = invoiceService.getMenuSearchList(recipe);
		logger.info("#{}. resultList : {}", idx++, resultList);
		
		return resultList;
	}
	
	@GetMapping("/menuPrice")
	@ResponseBody
	public Recipe getMenuPrice(String menuName){
		//logger index
		int idx = 0;
		logger.info("#{}. /menuPrice [AJAX] [GET]", idx++);
		
		logger.info("#{}. menuName : {}", idx++, menuName);
		
		Recipe recipe = invoiceService.getRecipeDtoWithRecipeName(menuName);
		logger.info("#{}. recipe : {}", idx++, recipe);
		
		recipe = salesService.getRecipeByRecipeName(recipe);
		logger.info("#{}. recipe : {}", idx++, recipe);
		
		return recipe;
	}
	
	@GetMapping("/itemList")
	@ResponseBody
	public List<Item> getItemSearchList(String itemName){
		//logger index
		int idx = 0;
		logger.info("#{}. /itemList [AJAX] [GET]", idx++);
		logger.info("#{}. itemName : {}", idx++, itemName);
		
		Item item = new Item();
		item.setItemName(itemName);
		
		List<Item> resultList = invoiceService.getItemListBySearch(item);
		logger.info("#{}. resultList : {}", idx++, resultList);
		
		return resultList;
	}
	
}
