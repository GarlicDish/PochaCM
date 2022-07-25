package pochacm.service.face;

import pochacm.dto.User;

public interface UserService {

	boolean join(User user);

	int checkEmailDuplByEmail(User user);

	int checkPhoneDuplByEmail(User user);

}
