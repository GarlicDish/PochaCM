package pochacm.service.face;

import pochacm.dto.User;

public interface LoginService {

	/**
	 * Based on the ID & PW which are entered, return the login result.
	 * 
	 * @param user - An object which has the ID & PW are entered.
	 * @return true / false
	 */
	boolean loginResult(User user);

	/**
	 * Return the user number based on the user ID which entered.
	 * @param user - 
	 * @return
	 */
	int getUserNoByEmail(User user);


}
