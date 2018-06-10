package com.javainuse.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class TestController {

	@RequestMapping("/welcome")
	/*public ModelAndView firstPage() {
		return new ModelAndView("welcome");
	}*/
	public Map<String, Object> read() {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("message", "Hello World of Microservice with K8S and CI/CD and webhook test and deployment stage check");
		//dataMap.put("status", "1");
	    return dataMap;
	}

}
