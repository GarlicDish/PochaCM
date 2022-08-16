package pochacm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SummaryController {
	
	private static final Logger logger = LoggerFactory.getLogger(SummaryController.class);

	@GetMapping("/summary")
	public String viewSummary(HttpServletRequest req, HttpSession session) {
		//logger index
		int idx = 0;
		logger.info("#{}. Entering summary page [GET]", idx++);
		
		//if he/she does not login, back to login page
		if(session.getAttribute("userNum") == null) {
			logger.info("#{}. Not Logined", idx++);
			return "redirect:/login";
		}
		
		return "cm/summary";
	}
}
