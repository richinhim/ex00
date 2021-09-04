package org.josua.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {

	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	//return type이 void인 경우에는 WEB-INF/views/doA.jsp 경로에서 파일을 실행하게 된다.
	
	
	@RequestMapping("doA")
	public void doA() {
		
		logger.info("doA called...............");
	}
	

	@RequestMapping("doB")
	public void doB() {
		
		logger.info("doB called...............");
	}
	
	
	/**
	 * http://localhost:8080/doC?msg=%27joshua%27
	 * @param msg
	 * @return
	 */
	@RequestMapping("doC")
	public String doC(@ModelAttribute("msg") String msg) {
		
		
		logger.info("doC called......................");
		return "result";
	}
}
