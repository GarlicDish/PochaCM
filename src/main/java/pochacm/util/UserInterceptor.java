package pochacm.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import pochacm.controller.UserController;

public class UserInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//logger index
		int idx = 0;
		logger.info("#{}. Entering login page [GET]", idx++);
				
		HttpSession session = request.getSession();
		if( session.getAttribute("login") == null || session.getAttribute("login") == "false" ) { //비로그인 상태
			response.sendRedirect("/login");
			
			return false;
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		logger.info(" + + + 인터셉터 끝 + + +");
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
}
