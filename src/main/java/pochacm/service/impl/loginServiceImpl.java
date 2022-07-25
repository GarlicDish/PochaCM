package pochacm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pochacm.controller.MainController;
import pochacm.dao.face.LoginDao;
import pochacm.dto.User;
import pochacm.service.face.LoginService;

@Service
public class loginServiceImpl implements LoginService{

	@Autowired private LoginDao loginDao;
	
	private static final Logger logger = LoggerFactory.getLogger(loginServiceImpl.class);

	@Override
	public boolean loginResult(User user) {
		int idx = 0;
		logger.info("#{}. Entering loginService - loginResult", idx++);
		
		logger.info("#{}. user info : {} ", idx++, user);
		
		if( loginDao.selectIdAndPw(user)>0 ) {
			logger.info("#{}. login info matched ", idx++);
			return true;
		}else {
			logger.info("#{}. login info not matched ", idx++);
			return false;
		}
	}

	@Override
	public int getUserNoByEmail(User user) {
		int idx = 0;
		logger.info("#{}. Entering loginService - getUserNoByEmail", idx++);
		
		return loginDao.getUserNoByEmail(user);
	}

	
}
