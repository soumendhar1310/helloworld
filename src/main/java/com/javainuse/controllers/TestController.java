package com.javainuse.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
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

	/**trying out POST***/
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public Map<String, Object> read(@RequestParam String name, @RequestParam String surnamename) {
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl
		  = "http://35.232.233.147:31461/User/create?name="+name+"&surname="+surnamename;
		ResponseEntity<String> response
		  = restTemplate.getForEntity(fooResourceUrl + "/1", String.class);
		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("Response",response.getBody().toString());
		LOGGER.info(response.getBody());
	    return dataMap;
	}
	
}
