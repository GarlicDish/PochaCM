package pochacm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pochacm.dao.face.UserDao;
import pochacm.dto.User;
import pochacm.service.face.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired private UserDao userDao;

	@Override
	public boolean join(User user) {
		// logger index
		int idx = 0;
		logger.info("#{}. join", idx++);
		
		//save in DB
		userDao.InsertUserInfo(user);
		
		//Check Result of saving in DB
		if(userDao.selectCntByEmail(user) > 0) {
			logger.info("#{}. information insert Result : {}", idx++, userDao.selectCntByEmail(user));
			return true;
		}
		
		logger.info("#{}. information insert Result : {}", idx++, userDao.selectCntByEmail(user));
		//if failed, false
		return false;
	}

	@Override
	public int checkEmailDuplByEmail(User user) {
		// logger index
				int idx = 0;
				logger.info("#{}. checkEmailDuplByEmail", idx++);
		return userDao.selectCntByEmail(user);
	}

	@Override
	public int checkPhoneDuplByEmail(User user) {
		return userDao.selectCntByPhone(user);
	}

}
