package org.josua.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.http.client.HttpResponseException;
import org.josua.web.common.CommonController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends CommonController{
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	/**
	 * http://localhost:8080/notice/lst?pg_no=1&pg_rw=1
	 * @param pageNo
	 * @param pageRow
	 * @param searchType
	 * @param searchWord
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/notice/lst", method = RequestMethod.GET)
	public ResponseEntity<String> getNoticeList (
			@RequestParam(  value = "pg_no"	  , required = true) Integer pageNo
			, @RequestParam(value = "pg_rw"	  , required = true) Integer pageRow
			, @RequestParam(value = "sch_ty"  , required = false , defaultValue = "") String searchType
			, @RequestParam(value = "sch_kwd" , required = false , defaultValue = "") String searchWord
			) throws Exception {
		try {
			String jsonString = "{\"title\": \"how to get stroage size\","
		            + "\"url\": \"https://codechacha.com/ko/get-free-and-total-size-of-volumes-in-android/\","
		            + "\"draft\": false,"
		            + "\"star\": 10"
		            + "}";
			
			return this.getResponseEntity(HttpStatus.OK, this.getHttpHeaders(), jsonString);
			//return this.getResponseEntity(HttpStatus.OK, this.getHttpHeaders(), noticeService.getNoticeList(pageNo, pageRow, searchType, searchWord, "PORTAL"));
		} catch(HttpResponseException e) {
			logger.error("getNoticeList -> HttpResponseException", e.getCause());
			return this.getResponseEntity(HttpStatus.EXPECTATION_FAILED, 	this.getHttpHeaders(), e);
		} catch(Exception e) {
			logger.error("getNoticeList -> Exception", e.getCause());
			return this.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, this.getHttpHeaders(), e);
		} finally {
			// nothing to do ...
		}
	}
	
}
