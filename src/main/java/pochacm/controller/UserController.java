package pochacm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pochacm.dto.User;
import pochacm.service.face.UserService;

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

			session.setAttribute("login", loginResult);
			session.setAttribute("userNum", userService.getUserNoByEmail(user));
			session.setAttribute("userEmail", user.getUserEmail());
			session.setAttribute("positionNum", userService.getUserPositionByEmail(user));

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
	public String join() {
		
		// logger index
		int idx = 0;

		logger.info("#{}. join", idx++);
		
		return "user/join";
	}

	// join process
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinProc(User user, HttpSession session) {
		
		// logger index
		int idx = 0;
		logger.info("#{}. joinProc", idx++);

		// join result
		boolean joinResult = userService.join(user);

		if (joinResult) {

			logger.info("#{}. join SUCCESS", idx++);

			// immediately save the session and get pop-up message for welcome
			session.setAttribute("login", joinResult);
			session.setAttribute("userNum", userService.getUserNoByEmail(user));
			session.setAttribute("userEmail", user.getUserEmail());

			return "redirect:/joinResult";
		} else {
			logger.info("#{}. join FAIL", idx++);

			// delete the session just in case
			session.invalidate();

			return "redirect:/join";
		}
	}
	
	
	
	
	
	//++++++++++++++++++++++++++ AJAX AREA +++++++++++++++++++++++++++++++++++
	
	//Check email duplication
	@RequestMapping(value="/join/emailCheck", method= RequestMethod.GET)
	@ResponseBody
	public int emailDuplCheck (@RequestParam(value = "userEmail") String userEmail) throws HttpMediaTypeNotAcceptableException {
		// logger index
		int idx = 0;
		logger.info("#{}. emailDuplCheck", idx++);
		logger.info("#{}. email transferred = {}", idx++, userEmail);
		
		//dto for carry the email info
		User user = new User();
		
		user = userService.getUserEmailFromParmater(userEmail);
		logger.info("#{}. user = {}", idx++, user);
		
		return userService.checkEmailDuplByEmail(user);
	}
	
	//Check Phone duplication
	@RequestMapping(value="/join/phoneCheck", method= RequestMethod.GET)
	@ResponseBody
	public int phoneDuplCheck(String userPhone) {
		// logger index
		int idx = 0;
		logger.info("#{}. phoneDuplCheck", idx++);
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
