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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pochacm.dto.Brand;
import pochacm.dto.Category;
import pochacm.dto.Items;
import pochacm.dto.OrderUnit;
import pochacm.dto.Paging;
import pochacm.dto.PrimaryUnit;
import pochacm.dto.SecondaryUnit;
import pochacm.service.face.ItemService;

@Controller
public class ItemController {
	
	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired ItemService itemService;
	
	@GetMapping("/item")
	public String itemList(HttpSession session, Paging paging, Model model) {

		int idx = 0;
		logger.info("#{}. /item [GET] itemList()", idx++);
		logger.info("#{}. itemList() paging : {}", idx++, paging);

		//create Paging dto with curPage & search
		paging = itemService.getItemPaging(paging);
		logger.info("#{}. itemList() paging : {}", idx++, paging);
		
		//make a model with invoice list
		List<Map<String, String>> itemList = itemService.getItemList(paging);
		logger.info("#{}. itemList() itemList : {}", idx++, itemList);
		
		model.addAttribute("paging", paging);
		model.addAttribute("itemList", itemList);
	
		return "item/list";
	}
	
	@GetMapping(value="/item/view")
	public String itemView(HttpSession session, Items item, Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /item/view [GET]", idx++);
		logger.info("#{}. item : {}", idx++, item);
		
		model.addAttribute("itemInfo", itemService.getItemInfoByItem(item));
		logger.info("#{}. model.getAttribute(\"itemInfo\") : {}", idx++, model.getAttribute("itemInfo"));
		
		return "item/view";
	}
	
	@GetMapping(value="/item/update")
	public String itemUpdate(HttpSession session, Items item, Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /item/update [GET]", idx++);
		
		logger.info("#{}. item : {}", idx++, item);
		
		//Get Order Unit, Primary Unit, Secondary Unit Lists
		
		List<Category> icList = itemService.getItemCategoryList();
		List<OrderUnit> ouList = itemService.getOrderUnitList();
		List<PrimaryUnit> puList = itemService.getPrimaryUnitList();
		List<SecondaryUnit> suList = itemService.getSecondaryUnitList();
		List<Category> cateList = itemService.getItemCategoryList();
		
		model.addAttribute("itemInfo", itemService.getItemInfoByItem(item));
		logger.info("#{}. model.getAttribute(\"itemInfo\") : {}", idx++, model.getAttribute("itemInfo"));
		
		model.addAttribute("icList", icList);
		model.addAttribute("ouList", ouList);
		model.addAttribute("puList", puList);
		model.addAttribute("suList", suList);
		model.addAttribute("cateList", cateList);
		
		return "item/update";
	}
	
	@RequestMapping(value="/item/update", method=RequestMethod.POST)
	public String itemUpdateSubmit(HttpSession session, Items item, PrimaryUnit primaryUnit, SecondaryUnit secondaryUnit, Model model) {
		
		//logger index
		int idx = 0;
		logger.info("#{}. /item/update [POST]", idx++);
		logger.info("#{}. item : {}", idx++, item);
		logger.info("#{}. primaryUnit : {}", idx++, primaryUnit);
		logger.info("#{}. secondaryUnit : {}", idx++, secondaryUnit);
		
		if (session.getAttribute("positionNum") == "2") {
			return "redirect:/item/view?itemNum=" + item.getItemNum();
		}
		
		itemService.updateItemInformation(item);
		
		return "redirect:/item/view?itemNum=" + item.getItemNum();
	}
	
	//++++++++++++++++++++++++++ AJAX AREA +++++++++++++++++++++++++++++++++++
	//++++++++++++++++++++++++++ AJAX AREA +++++++++++++++++++++++++++++++++++
	//++++++++++++++++++++++++++ AJAX AREA +++++++++++++++++++++++++++++++++++
	//++++++++++++++++++++++++++ AJAX AREA +++++++++++++++++++++++++++++++++++
	//++++++++++++++++++++++++++ AJAX AREA +++++++++++++++++++++++++++++++++++
	@GetMapping("/brand/get")
	@ResponseBody
	public Brand getBrandNumByName (Brand brand) {
		
		int idx = 0;
		logger.info("#{}. /brand/get getBrandNumByName() [GET]", idx++);
		logger.info("#{}. brandName : {}", idx++, brand);
		
		brand = itemService.getBrandNumByBrandName(brand);
		logger.info("#{}. brand : {}", idx++, brand);
		
		return brand;
	}
	
}
