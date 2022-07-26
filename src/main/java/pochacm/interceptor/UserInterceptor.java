package pochacm.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(UserInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		logger.info("= = = = = = = UserInterceptor START = = = = = = = ");
				
		HttpSession session = request.getSession();
		if( session.getAttribute("login") == null || session.getAttribute("login") == "False" ) { //Check Login status
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter printwriter = response.getWriter();
			printwriter.print("<script>alert('login needed.'); location.href='/login';</script>");
			printwriter.flush();
			printwriter.close();
			
			return false;
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		logger.info("= = = = = = =  UserInterceptor END = = = = = = = ");
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
}
