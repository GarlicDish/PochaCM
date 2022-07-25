package pochacm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pochacm.controller.MainController;
import pochacm.dao.face.UserDao;
import pochacm.dto.User;
import pochacm.service.face.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired private UserDao userDao;

	@Override
	public boolean join(User user) {
		
		//save in DB
		userDao.InsertUserInfo(user);
		
		//Check Result of saving in DB
		if(userDao.selectCntById(user)>0) {
			return true;
		}
		
		//if failed, false
		return false;
	}

}
