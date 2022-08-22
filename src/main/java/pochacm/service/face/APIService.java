package pochacm.service.face;

import java.util.Date;

public interface APIService {

	void insertSalesAPI(int totalNumber);

	int getTotalPage(Date searchDate, int i, int j);

}
