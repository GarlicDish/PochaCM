package pochacm.dao.face;

import java.util.List;
import java.util.Map;

import pochacm.dto.Branch;
import pochacm.dto.Position;
import pochacm.dto.User;

public interface UserDao {

	void InsertUserInfo(User user);

	int selectCntByEmail(User user);

	int selectCntByPhone(User user);

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

	List<Position> getAllPosition();

	List<Branch> getAllBranch();

	int selectUserValidationByEmail(User user);

	Map<String, String> selectUserByUserNum(User user);

	int selectUserByUserNumAndPW(User user);

	void updateUserInfo(User user);

	void withdrawUserByUserNum(User user);

//	Object getAllState();
//
//	Object getAllSuburb();
//
//	Object getAllPostcode();
}
