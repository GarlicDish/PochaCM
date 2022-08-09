package pochacm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pochacm.dto.User;
import pochacm.service.face.StaffService;
import pochacm.service.face.UserService;

@Controller
public class StaffController {

	private static final Logger logger = LoggerFactory.getLogger(StaffController.class);

	@Autowired
	private StaffService staffService;
	
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
		
		model.addAttribute("staffList", staffList);
		logger.info("#{}. staffList : {}", idx++, staffList);
		
		
		return "staff/list";
	}
	
}
