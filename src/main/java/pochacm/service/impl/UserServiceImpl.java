package pochacm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pochacm.dao.face.UserDao;
import pochacm.dto.Branch;
import pochacm.dto.Position;
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
		
		int cnt = userDao.selectCntByEmail(user);
		logger.info("#{}. cnt : {}", idx++, cnt);
		
		return cnt;
	}

	@Override
	public int checkPhoneDuplByEmail(User user) {
		return userDao.selectCntByPhone(user);
	}
	

	@Override
	public boolean loginResult(User user) {
		int idx = 0;
		logger.info("#{}. Entering loginService - loginResult", idx++);
		
		logger.info("#{}. user info : {} ", idx++, user);
		
		if( userDao.selectIdAndPw(user)>0 ) {
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
		
		return userDao.getUserNoByEmail(user);
	}

	@Override
	public int getUserPositionByEmail(User user) {
		int idx = 0;
		
		logger.info("#{}. Entering loginService - getUserPositionByEmail", idx++);
		
		return userDao.selectPositionByEmail(user);
	}

	@Override
	public User getUserEmailFromParmater(String userEmail) {
		int idx = 0;
		
		logger.info("#{}. Entering loginService - getUserEmailFromParmater", idx++);
			User user = new User();
			user.setUserEmail(userEmail);
		return user;
	}

	@Override
	public List<Position> getAllPosition() {
		return userDao.getAllPosition();
	}

	@Override
	public List<Branch> getAllBranch() {
		return userDao.getAllBranch();
	}

//	@Override
//	public Object getAllState() {
//		return userDao.getAllState();
//	}
//
//	@Override
//	public Object getAllSuburb() {
//		return userDao.getAllSuburb();
//	}
//
//	@Override
//	public Object getAllPostcode() {
//		return userDao.getAllPostcode();
//	}

}
