package pochacm.dao.face;

import pochacm.dto.User;

public interface LoginDao {

	/**
	 * find the entered ID & PW are matched with DB
	 * @param user - ID & PW
	 * @return 1-true/ 0-false
	 */
	int selectIdAndPw(User user);

	/**
	 * find the UserNo by Email information
	 * @param user - with email and password
	 * @return userNo
	 */
	int getUserNoByEmail(User user);

	int selectPositionByEmail(User user);

}
