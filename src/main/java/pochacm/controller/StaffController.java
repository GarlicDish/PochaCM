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

import pochacm.dto.User;
import pochacm.service.face.StaffService;
import pochacm.service.face.UserService;

@Controller
public class StaffController {

	private static final Logger logger = LoggerFactory.getLogger(StaffController.class);

	@Autowired
	private StaffService staffService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/staff")
	public String getStaffList(HttpSession session, Model model) {
		
		//logger index
		int idx = 0;
		logger.info("#{}. /staff [GET]", idx++);
		
		User user = new User();
		user.setUserNum((int)session.getAttribute("userNum"));
		user.setPositionNum((int)session.getAttribute("positionNum"));
		
		logger.info("#{}. user : {}", idx++, user);
		
		List<User> staffList = staffService.selectAllBranchStaffByUserNum(user);
		logger.info("#{}. staffList : {}", idx++, staffList);
		
		model.addAttribute("staffList", staffList);
		
		
		return "staff/list";
	}
	
	@GetMapping("/staff/detail")
	public String viewStaffDetail(User user, Model model) {
		//logger index
		int idx = 0;
		logger.info("#{}. /staff/detail [GET]", idx++);
		
		logger.info("#{}. user : {}", idx++, user);
		Map<String,String> map = userService.getUserByUserNum(user);
		logger.info("#{}. map : {}", idx++, map);
		model.addAttribute("map", map);
		
		return "staff/view";
	}
	
	@PostMapping("/staff/valid")
	public String validateStaff(User user) {
		//logger index
		int idx = 0;
		logger.info("#{}. /staff/valid [POST]", idx++);
		
		staffService.validateStaffByUser(user);
		
		return "redirect: /staff";
	}
	
}
