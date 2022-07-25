package pochacm.dao.face;

import pochacm.dto.User;

public interface UserDao {

	void InsertUserInfo(User user);

	int selectCntByEmail(User user);

	int selectCntByPhone(User user);

}
