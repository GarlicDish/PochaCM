package pochacm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pochacm.dto.User;
import pochacm.service.face.LoginService;
import pochacm.service.face.UserService;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private LoginService loginService;
	@Autowired
	private UserService userService;

	User user = new User();

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(HttpServletRequest request) {

		// logger index
		int idx = 0;

		logger.info("#{}. Entering login page [GET]", idx++);

		// path check
		String referer = request.getHeader("Referer");

		logger.info("#{}. URL visited immediately prior to visiting this page = {}", idx++, referer);

		request.getSession().setAttribute("redirectURL", referer);

		logger.info("#{}. Saving the referer = {}", idx++, request.getSession().getAttribute("redirectURL"));

		return "user/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginProcess(User user, HttpSession session) {

		// logger index
		int idx = 0;

		logger.info("#{}. Entering login page [POST]", idx++);

		boolean loginResult = loginService.loginResult(user);

		if (loginResult) {

			logger.info("#{}. loginResult = {}", idx++, loginResult);

			session.setAttribute("login", loginResult);
			session.setAttribute("userNum", loginService.getUserNoByEmail(user));
			session.setAttribute("userEmail", user.getUserEmail());

			logger.info("#{}. session login = {}", idx++, session.getAttribute("login"));
			logger.info("#{}. session userNum = {}", idx++, session.getAttribute("userNum"));
			logger.info("#{}. session userEmail = {}", idx++, session.getAttribute("userEmail"));

			// referer check
			logger.info("#{}. session referer: " + session.getAttribute("redirectURL"));
			String ss = (String) session.getAttribute("redirectURL");

			// checking the previous page was login page or not.
			if (ss != null) {
				// login success
				if (!ss.contains("/login")) {
					return "redirect:" + session.getAttribute("redirectURL");
				}
			}
			return "redirect:/main";

		} else {
			// login fail
			logger.info("#{}. loginResult = {}", idx++, loginResult);

			logger.info("login failure");
			session.invalidate();

			return "redirect: /user/login";
		}
	}

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
			session.setAttribute("userNum", loginService.getUserNoByEmail(user));
			session.setAttribute("userEmail", user.getUserEmail());

			return "redirect:/joinResult";
		} else {
			logger.info("#{}. join FAIL", idx++);

			// delete the session just in case
			session.invalidate();

			return "redirect:/join";
		}
	}
}
