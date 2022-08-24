package pochacm.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pochacm.service.face.APIService;

@Controller
public class APIController {

private static final Logger logger = LoggerFactory.getLogger(APIController.class);

@Autowired APIService apiService;
	
	@RequestMapping(value = {"/api"}, method = RequestMethod.GET)
	public String api() {

		int idx = 0;
		logger.info("#{}. /api [GET]", idx++);
		
		int totalNumber = apiService.getTotalPage();
		logger.info("#{}. totalNumber : {}", idx++, totalNumber);
		
		apiService.insertSalesAPI(totalNumber);
		
		return "main";
	}
}
