package pochacm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pochacm.dao.face.StaffDao;
import pochacm.dao.face.UserDao;
import pochacm.dto.User;
import pochacm.service.face.StaffService;

@Service
public class StaffServiceImpl implements StaffService {
	
private static final Logger logger = LoggerFactory.getLogger(StaffServiceImpl.class);
	
	@Autowired private StaffDao staffDao;

	@Override
	public List<User> selectAllBranchStaffByUserNum(User user) {

		//logger index
		int idx = 0;
		logger.info("#{}. selectAllBranchStaffByUserNum", idx++);
		logger.info("#{}. user : {}", idx++,user);
		
		return staffDao.selectAllBranchStaffByUserNum(user);
	}
}
