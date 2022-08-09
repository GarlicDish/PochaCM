package pochacm.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
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

import pochacm.dto.User;
import pochacm.service.face.UserService;
import pochacm.util.CSVReader;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	//Login Page
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(HttpServletRequest request) {

		//logger index
		int idx = 0;
		logger.info("#{}. Entering login page [GET]", idx++);

		//URL path check
		String referer = request.getHeader("Referer");

		logger.info("#{}. URL visited immediately prior to visiting this page = {}", idx++, referer);

		//Save the URL path in session
		request.getSession().setAttribute("redirectURL", referer);

		logger.info("#{}. Saving the referer = {}", idx++, request.getSession().getAttribute("redirectURL"));

		return "user/login";
	}

	//Login check and move the page
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginProcess(User user, HttpSession session) {

		// logger index
		int idx = 0;

		logger.info("#{}. Entering login page [POST]", idx++);

		boolean loginResult = userService.loginResult(user);
		
		if (loginResult) {
			// login success

			logger.info("#{}. loginResult = {}", idx++, loginResult);
			
			boolean isValid = userService.getUserValidationByEmail(user);
			
			if( !isValid ) {
				session.invalidate();
				return "redirect:/login";
			}
			
			session.setAttribute("login", loginResult);
			session.setAttribute("userNum", userService.getUserNoByEmail(user));
			session.setAttribute("userEmail", user.getUserEmail());
			session.setAttribute("positionNum", userService.getUserPositionByEmail(user));
			session.setAttribute("invalidation", isValid);

			logger.info("#{}. session login = {}", idx++, session.getAttribute("login"));
			logger.info("#{}. session userNum = {}", idx++, session.getAttribute("userNum"));
			logger.info("#{}. session userEmail = {}", idx++, session.getAttribute("userEmail"));
			logger.info("#{}. session positionNum = {}", idx++, session.getAttribute("positionNum"));

			// referer check
			logger.info("#{}. session referer: " + session.getAttribute("redirectURL"));
			
//			String ss = (String) session.getAttribute("redirectURL");

			// checking the previous page was login page or not.
//			if (ss != null) {
//				if (!ss.contains("/login")) {
//					return "redirect:" + session.getAttribute("redirectURL");
//				}
//			}
			return "redirect:/main";

		} else {
			// login fail
			logger.info("#{}. loginResult = {}", idx++, loginResult);
			logger.info("login failure");
			
			session.invalidate();

			return "redirect:/login";
		}
	}

	//logout
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		// logger index
		int idx = 0;

		logger.info("#{}. logout & session.invalidate", idx++);

		// session reset
		session.invalidate();

		// after logout, move to main page
		return "redirect: /main";
	}

	 
	 
	// move to join page
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Model model) throws FileNotFoundException {
		
		// logger index
		int idx = 0;
		logger.info("#{}. join", idx++);
		
		CSVReader csvReader = new CSVReader();
		
		List<List<String>> addressList = csvReader.readCSV();
//		logger.info("#{}. csvReader.readCSV() : {}", idx++, addressList);
		
		
		//GET STATE LIST FROM CSV FILE
		List<String> stateList1 = new ArrayList<>();
		for(int i=0; i < addressList.size() ;i++) {
			String state = addressList.get(i).get(3).toString();
//			logger.info("#{}. state : {}", idx++, state);
			stateList1.add(state);
		}
		Set<String> set = new HashSet<String>(stateList1);
		List<String> stateList = new ArrayList<String>(set);
		stateList.remove("state_code");
		stateList.sort(null);
		logger.info("#{}. stateList : {}", idx++, stateList);
		
		model.addAttribute("stateList", stateList);
		model.addAttribute("branchList", userService.getAllBranch());
		logger.info("#{}. model.getAttribute(\"branchList\") : {}", idx++, model.getAttribute("branchList"));
		model.addAttribute("positionList", userService.getAllPosition());
		logger.info("#{}. model.getAttribute(\"positionList\") : {}", idx++, model.getAttribute("positionList"));
		
		return "user/join";
	}

	// join process
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinProc(User user, String workStartDate, HttpSession session) {
		
		// logger index
		int idx = 0;
		logger.info("#{}. /join [POST]", idx++);

		// join result
		boolean joinResult = userService.join(user);

		if (joinResult) {

			logger.info("#{}. join SUCCESS", idx++);

			// immediately save the session and get pop-up message for welcome
			session.setAttribute("login", joinResult);
			session.setAttribute("userNum", userService.getUserNoByEmail(user));
			session.setAttribute("userEmail", user.getUserEmail());

			return "main";
		} else {
			logger.info("#{}. join FAIL", idx++);

			// delete the session just in case
			session.invalidate();

			return "redirect:/join";
		}
	}
	
	@GetMapping("/myProfile")
	public String viewMyProfile(HttpSession session, Model model) {
		// logger index
		int idx = 0;
		logger.info("#{}. /myProfile [GET]", idx++);
		
		User user = new User();
		user.setUserNum((int) session.getAttribute("userNum"));
		logger.info("#{}. user : {}", idx++, user);
		
		Map<String,String> map = userService.getUserByUserNum(user);
		logger.info("#{}. map : {}", idx++, map);

		model.addAttribute("userName",map.get("USER_NAME"));
		model.addAttribute("userPhone",map.get("USER_PHONE"));
		model.addAttribute("userEmail",map.get("USER_EMAIL"));
		model.addAttribute("branchName",map.get("BRANCH_NAME"));
		model.addAttribute("positionName",map.get("POSITION_NAME"));
		model.addAttribute("branchAddress",map.get("BRANCH_ADDRESS"));
		model.addAttribute("branchPhone",map.get("BRANCH_PHONE"));
		
		return "user/myProfile";
	}
	
	@GetMapping("/myprofile/validation")
	public String validationForUpdateMyProfile() {
		
		return "user/validationPopUp";
	}
	
	
	@GetMapping("/myProfile/update")
	public String updateMyProfile(HttpSession session, Model model) {
		// logger index
		int idx = 0;
		logger.info("#{}. /myProfile/update [GET]", idx++);
		
		return "user/myProfileUpdate";
	}

	@PostMapping("/myProfile/update")
	public String validationSubmission(HttpSession session, Model model, String userPassword) {
		// logger index
		int idx = 0;
		logger.info("#{}. /myProfile/update [POST]", idx++);
		
		User user = new User();
		user.setUserNum((int)session.getAttribute("userNum"));
		user.setUserPassword(userPassword);
		logger.info("#{}. user : {}", idx++, user);
		
		//check userNum and password is matched or not
		boolean isValidated = userService.checkValidation(user);
		
		logger.info("#{}. isValidated : {}", idx++, isValidated);
		
		if (isValidated) {
			Map<String,String> map = userService.getUserByUserNum(user);
			logger.info("#{}. map : {}", idx++, map);
			

			CSVReader csvReader = new CSVReader();
			
			List<List<String>> addressList = csvReader.readCSV();
//			logger.info("#{}. csvReader.readCSV() : {}", idx++, addressList);
			
			//GET STATE LIST FROM CSV FILE
			List<String> stateList1 = new ArrayList<>();
			for(int i=0; i < addressList.size() ;i++) {
				String state = addressList.get(i).get(3).toString();
//						logger.info("#{}. state : {}", idx++, state);
				stateList1.add(state);
			}
			Set<String> set = new HashSet<String>(stateList1);
			List<String> stateList = new ArrayList<String>(set);
			stateList.remove("state_code");
			stateList.sort(null);
			logger.info("#{}. stateList : {}", idx++, stateList);
			
			model.addAttribute("stateList", stateList);
			model.addAttribute("branchList", userService.getAllBranch());
			logger.info("#{}. model.getAttribute(\"branchList\") : {}", idx++, model.getAttribute("branchList"));
			model.addAttribute("positionList", userService.getAllPosition());
			logger.info("#{}. model.getAttribute(\"positionList\") : {}", idx++, model.getAttribute("positionList"));
			
			model.addAttribute("map", map);
			
			return "user/myProfileUpdate";
		} else {
			
			return "redirect: /myProfile";
		}
	}
	
	@PostMapping("/myProfile/updateSubmit")
	public String moveToUpdateProfile(HttpSession session, Model model, User user) {
		// logger index
		int idx = 0;
		logger.info("#{}. /myProfile/updateSubmit [POST]", idx++);
		logger.info("#{}. user : {}", idx++, user);
		
		userService.updateUserInfo(user);
		
		
		return "redirect: /myProfile";
	}
	
	//++++++++++++++++++++++++++ AJAX AREA +++++++++++++++++++++++++++++++++++
	
//	class userEmail {
//	    private final String email;
//
//	    public userEmail(String email) {
//	        this.email = email;
//	    }
//
//	    public String getUserEmail() {
//	        return email;
//	    }
//	}
	
	//Check email duplication
	@GetMapping("/join/emailCheck")
	@ResponseBody
	public int emailDuplCheck ( String userEmail){
		// logger index
		int idx = 0;
		logger.info("#{}. /join/emailCheck [AJAX]", idx++);
		logger.info("#{}. email transferred = {}", idx++, userEmail);
		
		//dto for carry the email info
		User user = new User();
		
		user = userService.getUserEmailFromParmater(userEmail);
		logger.info("#{}. user = {}", idx++, user);
		
		int cnt = userService.checkEmailDuplByEmail(user);
		logger.info("#{}. cnt = {}", idx++, cnt);
		
		return cnt;
	}
	
	//Check Phone duplication
	@RequestMapping(value="/join/phoneCheck", method= RequestMethod.GET)
	@ResponseBody
	public int phoneDuplCheck(String userPhone) {
		// logger index
		int idx = 0;
		logger.info("#{}. /join/phoneCheck [AJAX]", idx++);
		logger.info("#{}. Phone transferred = {}", idx++, userPhone);
		
		//dto for carry the email info
		User user = new User();
		
		user.setUserPhone(userPhone);
		logger.info("#{}. Phone user = {}", idx++, user);
		logger.info("#{}. userService.checkPhoneDuplByEmail(user) = {}", idx++, userService.checkPhoneDuplByEmail(user));
		
		if (userService.checkPhoneDuplByEmail(user) == 0) {
			return 0;
		} else {
			return 1;
		}
	}
	
	
}