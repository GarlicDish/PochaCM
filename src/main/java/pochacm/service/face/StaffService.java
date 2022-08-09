package pochacm.service.face;

import java.util.List;

import pochacm.dto.User;

public interface StaffService {

	List<User> selectAllBranchStaffByUserNum(User user);

}
