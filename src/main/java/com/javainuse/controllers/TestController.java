package com.javainuse.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class TestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping("/welcome")
	/*public ModelAndView firstPage() {
		return new ModelAndView("welcome");
	}*/
	public Map<String, Object> read() {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("message", "Hello World for demo and checking for IoT demo with Sonar on VM");
		//dataMap.put("status", "1");
		LOGGER.info("ELK Logback successfully worked");
		
	    return dataMap;
	}

}
