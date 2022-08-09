package pochacm.service.face;

import java.util.List;
import java.util.Map;

import pochacm.dto.AUPostcode;
import pochacm.dto.Branch;
import pochacm.dto.Position;
import pochacm.dto.User;

public interface UserService {

	boolean join(User user);

	int checkEmailDuplByEmail(User user);

	int checkPhoneDuplByEmail(User user);
	
	/**
	 * Based on the ID & PW which are entered, return the login result.
	 * 
	 * @param user - An object which has the ID & PW are entered.
	 * @return true / false
	 */
	boolean loginResult(User user);

	/**
	 * Return the user number based on the user ID which entered.
	 * @param user - with ID & password
	 * @return
	 */
	int getUserNoByEmail(User user);

	/**
	 * get user position based on email
	 * @param user - with ID & password
	 * @return user dto with position number
	 */
	int getUserPositionByEmail(User user);

	User getUserEmailFromParmater(String userEmail);

	List<Position> getAllPosition();

	List<Branch> getAllBranch();

	boolean getUserValidationByEmail(User user);

	Map<String, String> getUserByUserNum(User user);

	boolean checkValidation(User user);

	void updateUserInfo(User user);


}
