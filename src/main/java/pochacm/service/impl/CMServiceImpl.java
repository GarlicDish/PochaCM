package pochacm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pochacm.dao.face.CMDao;
import pochacm.dto.Invoice;
import pochacm.dto.Paging;
import pochacm.service.face.CMService;

@Service
public class CMServiceImpl implements CMService {
	private static final Logger logger = LoggerFactory.getLogger(CMServiceImpl.class);

	@Autowired CMDao cmDao;
	
	@Override
	public Paging getInvoicePaging(String curPage, String search) {
		//logger index
		int idx = 0;
		
		logger.info("#{}. getInvoicePaging [GET]", idx++);
		
		int page = 0;
		
		if( curPage != null && !"".equals(curPage) ) {
			
			page = Integer.parseInt(curPage);
			logger.info("#{}. curPage : {}", idx++, curPage);
			
		} else {
			
			logger.warn("there is no curPage or null value");
			
		}
		
		//select total count of invoice
		int totalCount = cmDao.selectCntAllInvoice(search);
		
		logger.info("#{}. totalCount : {}", idx++, totalCount);
		
		//create Paging dto - calculate paging
		Paging paging = new Paging(totalCount, page);
		
		return paging;
	}

	@Override
	public List<Invoice> getInvoiceList(Paging paging) {
		return cmDao.selectAllInvoice(paging);
	}




}
