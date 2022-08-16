package pochacm.dao.face;

import java.util.List;

import pochacm.dto.User;

public interface StaffDao {

	List<User> selectAllBranchStaffByUserNum(User user);

	void validateStaffByUser(User user);

}
