package pochacm.dao.face;

import pochacm.dto.User;

public interface UserDao {

	int selectCntById(User user);

	void InsertUserInfo(User user);

}
