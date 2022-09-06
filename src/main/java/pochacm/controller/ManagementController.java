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
import pochacm.service.face.ManagementService;

@Controller
public class ManagementController {
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	@Autowired ManagementService managementService;
	
	@GetMapping("/item")
	public String itemList(HttpSession session, Paging paging, Model model) {

		int idx = 0;
		logger.info("#{}. /item [GET] itemList()", idx++);
		logger.info("#{}. itemList() paging : {}", idx++, paging);

		//create Paging dto with curPage & search
		paging = managementService.getItemPaging(paging);
		logger.info("#{}. itemList() paging : {}", idx++, paging);
		
		//make a model with invoice list
		List<Map<String, String>> itemList = managementService.getItemList(paging);
		logger.info("#{}. itemList() itemList : {}", idx++, itemList);
		
		model.addAttribute("paging", paging);
		model.addAttribute("itemList", itemList);
	
		return "management/itemList";
	}
	
	@GetMapping(value="/item/view")
	public String itemView(HttpSession session, Items item, Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /item/view [GET]", idx++);
		logger.info("#{}. item : {}", idx++, item);
		
		model.addAttribute("itemInfo", managementService.getItemInfoByItem(item));
		logger.info("#{}. model.getAttribute(\"itemInfo\") : {}", idx++, model.getAttribute("itemInfo"));
		
		return "management/itemView";
	}
	
	@GetMapping(value="/item/update")
	public String itemUpdate(HttpSession session, Items item, Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /item/update [GET]", idx++);
		
		logger.info("#{}. item : {}", idx++, item);
		
		//Get Order Unit, Primary Unit, Secondary Unit Lists
		
		List<OrderUnit> ouList = managementService.getOrderUnitList();
		List<PrimaryUnit> puList = managementService.getPrimaryUnitList();
		List<SecondaryUnit> suList = managementService.getSecondaryUnitList();
		List<Category> cateList = managementService.getItemCategoryList();
		List<Brand> brandList = managementService.getBrandCategoryList();
		
		model.addAttribute("itemInfo", managementService.getItemInfoByItem(item));
		logger.info("#{}. model.getAttribute(\"itemInfo\") : {}", idx++, model.getAttribute("itemInfo"));
		
		model.addAttribute("ouList", ouList);
		model.addAttribute("puList", puList);
		model.addAttribute("suList", suList);
		model.addAttribute("cateList", cateList);
		model.addAttribute("brandList", brandList);
		logger.info("#{}. model.getAttribute(\"cateList\") : {}", idx++, model.getAttribute("cateList"));
		
		return "management/itemUpdate";
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
		
		managementService.updateItemInformation(item);
		
		return "redirect:/item/view?itemNum=" + item.getItemNum();
	}
	
	
	@GetMapping("/menu")
	public String menuList(Paging paging, Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /menu menuList() [GET]", idx++);
		
		//create Paging dto with curPage & search
		paging = managementService.getMenuPaging(paging);
		logger.info("#{}. menuList() paging : {}", idx++, paging);
		
		//make a model with invoice list
		List<Map<String, String>> menuList = managementService.getMenuList(paging);
		logger.info("#{}. menuList() menuList : {}", idx++, menuList);
		
		model.addAttribute("paging", paging);
		model.addAttribute("menuList", menuList);
		
		return "management/menuList";
	}
	
	@GetMapping("/brand")
	public String brandList(Paging paging, Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /menu brandList() [GET]", idx++);
		
		//create Paging dto with curPage & search
		paging = managementService.getBrandPaging(paging);
		logger.info("#{}. brandList() paging : {}", idx++, paging);
		
		//make a model with invoice list
		List<Map<String, String>> brandList = managementService.getBrandList(paging);
		logger.info("#{}. brandList() brandList : {}", idx++, brandList);
		
		model.addAttribute("paging", paging);
		model.addAttribute("brandList", brandList);
		
		return "management/brandList";
	}
	
	@GetMapping("/category")
	public String categoryList(Paging paging, Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /menu categoryList() [GET]", idx++);
		
		//create Paging dto with curPage & search
		paging = managementService.getCategoryPaging(paging);
		logger.info("#{}. categoryList() paging : {}", idx++, paging);
		
		//make a model with invoice list
		List<Map<String, String>> categoryList = managementService.getCategoryList(paging);
		logger.info("#{}. categoryList() itemList : {}", idx++, categoryList);
		
		model.addAttribute("paging", paging);
		model.addAttribute("categoryList", categoryList);
		
		return "management/categoryList";
	}
	
	@GetMapping("/orderUnit")
	public String orderUnitList(Paging paging, Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /menu orderUnitList() [GET]", idx++);
		
		//create Paging dto with curPage & search
		paging = managementService.getOrderUnitPaging(paging);
		logger.info("#{}. orderUnitList() paging : {}", idx++, paging);
		
		//make a model with invoice list
		List<Map<String, String>> orderUnitList = managementService.getOrderUnitList(paging);
		logger.info("#{}. orderUnitList() orderUnitList : {}", idx++, orderUnitList);
		
		model.addAttribute("paging", paging);
		model.addAttribute("orderUnitList", orderUnitList);
		
		return "management/orderUnitList";
	}
	
	@GetMapping("/primaryUnit")
	public String primaryUnitList(Paging paging, Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /menu primaryUnitList() [GET]", idx++);
		
		//create Paging dto with curPage & search
		paging = managementService.getPrimaryUnitPaging(paging);
		logger.info("#{}. primaryUnitList() paging : {}", idx++, paging);
		
		//make a model with invoice list
		List<Map<String, String>> primaryUnitList = managementService.getPrimaryUnitList(paging);
		logger.info("#{}. primaryUnitList() primaryUnitList : {}", idx++, primaryUnitList);
		
		model.addAttribute("paging", paging);
		model.addAttribute("primaryUnitList", primaryUnitList);
		
		return "management/primaryUnitList";
	}
	
	@GetMapping("/secondaryUnit")
	public String secondaryUnitList(Paging paging, Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /menu secondaryUnitList() [GET]", idx++);
		
		//create Paging dto with curPage & search
		paging = managementService.getSecondaryUnitPaging(paging);
		logger.info("#{}. secondaryUnitList() paging : {}", idx++, paging);
		
		//make a model with invoice list
		List<Map<String, String>> secondaryUnitList = managementService.getSecondaryUnitList(paging);
		logger.info("#{}. secondaryUnitList() secondaryUnitList : {}", idx++, secondaryUnitList);
		
		model.addAttribute("paging", paging);
		model.addAttribute("secondaryUnitList", secondaryUnitList);
		
		return "management/secondaryUnitList";
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
		
		brand = managementService.getBrandNumByBrandName(brand);
		logger.info("#{}. brand : {}", idx++, brand);
		
		return brand;
	}
	
}
