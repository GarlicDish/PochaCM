package pochacm.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pochacm.dto.PurchaseInvoice;
import pochacm.dto.Items;
import pochacm.dto.Brand;
import pochacm.dto.Category;
import pochacm.dto.OrderUnit;
import pochacm.dto.Paging;
import pochacm.dto.Recipe;
import pochacm.dto.Supplier;
import pochacm.dto.PurchaseInvoiceItem;
import pochacm.service.face.ManagementService;
import pochacm.service.face.PurchaseInvoiceService;
import pochacm.service.face.SalesService;

@Controller
public class PurchaseInvoiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(PurchaseInvoiceController.class);
	
	@Autowired PurchaseInvoiceService invoiceService;
	@Autowired SalesService salesService;
	@Autowired ManagementService itemService;
	
	@GetMapping("/invoice")
	public String invoiceList(
			HttpSession session, Paging paging, Model model) {
		
		//logger index
		int idx = 0;
		logger.info("#{}. /invoice [GET]", idx++);
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
	public String invoiceView(HttpSession session, PurchaseInvoice invoice, Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /invoiceView [POST]", idx++);
		logger.info("#{}. invoice : {}", idx++,invoice);
		
		Map<String,String> invInfo = invoiceService.getInvoiceInfoByInvoiceSerial(invoice);
		List<Map<String,String>> itemList = invoiceService.selectItemsByInvoiceSerial(invoice);
		
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
	public String invoiceItemAdd(PurchaseInvoice invoice, PurchaseInvoiceItem invoiceItem, HttpSession session
			, @RequestParam(value="category") int[] cateArr
			, @RequestParam(value="brandNum") int[] brandArr
			, @RequestParam(value="itemName") String[] itemNameArr
			, @RequestParam(value="orderUnit") int[] ouArr
			, @RequestParam(value="unitPrice") float[] upArr
			, @RequestParam(value="itemNum") int[] itemNumArr
			, @RequestParam(value="itemCode") String[] itemCodeArr
			, @RequestParam(value="qty") int[] qtyArr) {
		
			//logger index
			int idx = 0;
			logger.info("#{}. /invoice/add [POST]", idx++);
			logger.info("#{}. itemNumArr : {}", idx++, itemNumArr);
			logger.info("#{}. brandArr : {}", idx++, brandArr);
			logger.info("#{}. cateArr : {}", idx++, cateArr);
			logger.info("#{}. itemNameArr : {}", idx++, itemNameArr);
			logger.info("#{}. ouArr : {}", idx++, ouArr);
			logger.info("#{}. upArr : {}", idx++, upArr);
			logger.info("#{}. invoice : {}", idx++, invoice);
			logger.info("#{}. qtyArr : {}", idx++, qtyArr);
		
			int userNum = (int) session.getAttribute("userNum");
			
			//make temporary Serial Number ( 3words from supplier name + ddMMyyyy)
			if (invoice.getPurchaseInvoiceSerial() == "" || invoice.getPurchaseInvoiceSerial().equals("")) {
				
				//change date format Date type to String type with proper format
				SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
				String invDate = sdf.format(invoice.getPurchaseInvoiceDate());
				logger.info("#{}. invDate : {}", idx++, invDate);
				
				String supName = invoiceService.getSupplierName(invoice);
				String newSerial = supName.substring(0, 3) + invDate;
				invoice.setPurchaseInvoiceSerial(newSerial);
			}
			invoice.setUserNum((int)session.getAttribute("userNum"));
			invoiceService.insertInvoiceInfo(invoice);
			
			invoiceItem.setPurchaseInvoiceNum(invoice.getPurchaseInvoiceNum());
			
			//make item list
			List<Items> itemList = new ArrayList<Items>();
			
			for(int i=0;i<cateArr.length;i++) {
				Items tempItem = new Items();
				
				tempItem.setItemCode(itemCodeArr[i]);
				tempItem.setItemNum(itemNumArr[i]);
				tempItem.setBrandNum(brandArr[i]);
				tempItem.setCateNum(cateArr[i]);
				tempItem.setItemName(itemNameArr[i]);
				tempItem.setOrderUnitNum(ouArr[i]);
				tempItem.setUnitPrice(upArr[i]);
				tempItem.setUserNum(userNum);
				logger.info("#{}. tempItem : {}", idx++, tempItem);
				
				itemList.add(tempItem);
			}
			
			logger.info("#{}. itemList : {}", idx++, itemList);
			for(int i=0;i<itemList.size();i++) {
			
			//If it's not registered item, register it.
			if( itemList.get(i).getItemNum() == 0 ) {
				invoiceService.insertItemInfo(itemList.get(i));
			}
			invoiceItem.setItemNum(itemList.get(i).getItemNum());
			//make invoice_item DB
			invoiceService.insertInvoiceAndItemInfo(invoiceItem);
			
		}
		
		return "redirect:/invoice/view?invoiceSerial="+invoice.getPurchaseInvoiceSerial();
	}
	
	@PostMapping("/invoice/delete")
	public String deleteInvoice(PurchaseInvoice invoice, Paging paging) {
		//logger index
		int idx = 0;
		logger.info("#{}. /invoice/delete [POST]", idx++);
		logger.info("#{}. invoice : {}", idx++, invoice);
		logger.info("#{}. paging : {}", idx++, paging);
		
		invoiceService.deleteInvoice(invoice);
		
		return "redirect:/invoice?curPage=" + paging.getCurPage();
	}
	
	@PostMapping("/invoice/invoiceItemDelete")
	public String deleteInvoiceItem(PurchaseInvoiceItem invoiceItem) {
		
		//logger index
		int idx = 0;
		logger.info("#{}. /invoice/invoiceItemDelete [POST]", idx++);
		logger.info("#{}. invoiceItem : {}", idx++, invoiceItem);
		
		invoiceService.deleteInvoiceItemByNum(invoiceItem);
		
		if (invoiceService.countInvoiceItemByInvoiceNum(invoiceItem)>0) {
			return "redirect:/invoice/view?invoiceNum="+invoiceItem.getPurchaseInvoiceNum();
		} else {
			return "redirect:/invoice";
		}
		
	}
	
	@GetMapping("/invoice/update")
	public String updateInvoice(PurchaseInvoice invoice, Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /invoice/update [POST]", idx++);
		logger.info("#{}. invoice : {}", idx++, invoice);
		
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
		
		Map<String,String> invInfo = invoiceService.getInvoiceInfoByInvoiceSerial(invoice);
		logger.info("#{}. invInfo : {}", idx++, invInfo);
		
		List<Map<String,String>> itemList = invoiceService.getItemListByInvoiceSerial(invoice);
		logger.info("#{}. itemList : {}", idx++, itemList);
		
		model.addAttribute("invInfo",invInfo);
		model.addAttribute("itemList",itemList);
		
		return "invoice/update";
	}
	
	@PostMapping("/invoice/update")
	public String invoiceUpdateProc(PurchaseInvoice invoice, HttpSession session
			, @RequestParam(value="itemNum") int[] itemNumArr
			, @RequestParam(value="qty") int[] qtyArr
			, @RequestParam(value="category") int[] cateArr
			, @RequestParam(value="brandNum") int[] brandArr
			, @RequestParam(value="itemName") String[] itemNameArr
			, @RequestParam(value="orderUnit") int[] ouArr
			, @RequestParam(value="unitPrice") float[] upArr
			, @RequestParam(value="itemCode") String[] itemCodeArr
			) {
		
			//logger index
			int idx = 0;
			logger.info("#{}. /invoice/add [POST]", idx++);
			logger.info("#{}. itemNumArr : {}", idx++, itemNumArr);
			logger.info("#{}. brandArr : {}", idx++, brandArr);
			logger.info("#{}. cateArr : {}", idx++, cateArr);
			logger.info("#{}. itemNameArr : {}", idx++, itemNameArr);
			logger.info("#{}. ouArr : {}", idx++, ouArr);
			logger.info("#{}. upArr : {}", idx++, upArr);
			logger.info("#{}. invoice : {}", idx++, invoice);
			logger.info("#{}. qtyArr : {}", idx++, qtyArr);
		
			
			//save invoice information same as parameter
			invoiceService.updateInvoiceInfo(invoice);
			
			//make item list
			List<Items> itemList = new ArrayList<Items>();
			
			for(int i=0;i<itemNumArr.length;i++) {
				Items tempItem = new Items();
				
				tempItem.setItemCode(itemCodeArr[i]);
				tempItem.setItemNum(itemNumArr[i]);
				tempItem.setBrandNum(brandArr[i]);
				tempItem.setCateNum(cateArr[i]);
				tempItem.setItemName(itemNameArr[i]);
				tempItem.setOrderUnitNum(ouArr[i]);
				tempItem.setUnitPrice(upArr[i]);
				tempItem.setUserNum(invoice.getUserNum());
				logger.info("#{}. tempItem : {}", idx++, tempItem);
				
				itemList.add(tempItem);
				
			}
			
			logger.info("#{}. itemList : {}", idx++, itemList);
			
			for(int i=0;i<itemList.size();i++) {
				//If it's not registered item, register it.
				if( itemList.get(i).getItemNum() == 0 ) {
					invoiceService.insertItemInfo(itemList.get(i));
				} else {
					//IF exist, update the content
					itemService.updateItemInformation(itemList.get(i));
				}

				PurchaseInvoiceItem iin = new PurchaseInvoiceItem();
				iin.setPurchaseInvoiceNum(invoice.getPurchaseInvoiceNum());
				iin.setItemNum(itemList.get(i).getItemNum());
				iin.setQty(qtyArr[i]);
				
				logger.info("#{}. iin : {}", idx++, iin);
				
				if(invoiceService.selectInvoiceItem(iin) > 0) {
					invoiceService.updateInvoiceItemInfo(iin);
				} else {
					invoiceService.insertInvoiceItemInfo(iin);
				}
				
			}
		
		return "redirect:/invoice/view?invoiceSerial="+invoice.getPurchaseInvoiceSerial();
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
	public List<Items> getItemSearchList(String itemName){
		//logger index
		int idx = 0;
		logger.info("#{}. /itemList [AJAX] [GET]", idx++);
		logger.info("#{}. itemName : {}", idx++, itemName);
		
		Items item = new Items();
		item.setItemName(itemName);
		
		List<Items> resultList = invoiceService.getItemListBySearch(item);
		logger.info("#{}. resultList : {}", idx++, resultList);
		
		return resultList;
	}
	@GetMapping("/itemCodeList")
	@ResponseBody
	public List<Items> getItemCodeSearchList(Items item){
		//logger index
		int idx = 0;
		logger.info("#{}. /itemList [AJAX] [GET]", idx++);
		logger.info("#{}. itemName : {}", idx++, item);
		
		List<Items> resultList = invoiceService.getItemCodeListBySearch(item);
		logger.info("#{}. resultList : {}", idx++, resultList);
		
		return resultList;
	}
	
}
