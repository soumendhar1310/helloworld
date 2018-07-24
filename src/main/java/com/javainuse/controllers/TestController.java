package com.javainuse.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.context.annotation.Bean;

@RestController
public class TestController {
	
	@Autowired
	private Environment env;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	@Bean
	RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
	
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

	/**Call Business Microservice***/
	//@NewSpan
	//@RequestMapping(value="/create"/*, method = RequestMethod.POST*/)
	/*@GetMapping("/create")
	public Map<String, Object> create(@RequestParam String name, @RequestParam String surname) {
		//RestTemplate restTemplate = new RestTemplate();
	    JsonNode rootNode=null;
	    ObjectMapper objectMapper=null;
	    String recordID="";
	    String userJSONText="";
		//String fooResourceUrl
		  //= "http://35.232.233.147:31461/User/create?name="+name+"&surname="+surname;
		String fooResourceUrl
		  //= env.getProperty("business.service.URL")+"/User/create?name="+name+"&surname="+surname;
			//="http://"+System.getenv("SPRING_BOOT_BUSINESS_SERVICE_PORT_8080_TCP_ADDR")+":"
			//	+System.getenv("SPRING_BOOT_BUSINESS_SERVICE_PORT_8080_TCP_PORT")+"/User/create?name="+name+"&surname="+surname;
			="http://"+System.getenv(env.getProperty("business.service.URI"))+":"
					+System.getenv(env.getProperty("business.service.PORT"))+"/User/create?name="+name+"&surname="+surname;
		
		LOGGER.info("BUSINESS MS URL ******"+fooResourceUrl);
	
		ResponseEntity<String> response
		  = this.restTemplate().getForEntity(fooResourceUrl, String.class);//restTemplate.getForEntity(fooResourceUrl, String.class);
		Map<String, String> responseMap = new HashMap<String, String>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			objectMapper = new ObjectMapper();
			rootNode = objectMapper.readTree(response.getBody());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try {
			
	           JsonNode userInformationNode = rootNode.get("User");
	           //userJSONText=userInformationNode.textValue();
	           Map<String, String> userInformationMap = objectMapper.convertValue(userInformationNode, Map.class);
	           for (Map.Entry<String, String> entry : userInformationMap.entrySet())
	           {
	        	   		LOGGER.info("\n----------------------------\n"+entry.getKey() + "=" + entry.getValue()+"\n");
	        	   		if(entry.getKey().equals("id"))
	        	   			recordID=entry.getValue();
	           }

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		responseMap.put("BMS", "User created successfully with Identifier "+recordID);
		
		//String responseMsg="";
		//responseMsg = "User created successfully with Identifier "+recordID;
		//dataMap.put("message","User created successfully with Identifier "+recordID);
		LOGGER.info(response.getBody());
		*/
	
		/*/**Call Adapter Microservice***/
		
		/*//userJSONText = "{\"name\":\""+name+"\", \"surname\":\""+surname+"\"}";
		userJSONText =name+","+surname;
		fooResourceUrl
			="http://"+System.getenv(env.getProperty("adapter.service.URI"))+":"
					+System.getenv(env.getProperty("adapter.service.PORT"))+"/send?msg="+userJSONText;
		
		LOGGER.info("ADAPTER MS URL ******"+fooResourceUrl);
		response
		  = this.restTemplate().getForEntity(fooResourceUrl, String.class);
		
		responseMap.put("AMS","Message passed to Queue for downline consumption");
		//responseMsg = responseMsg+"\n Message passed to Queue for downline consumption";
		//dataMap.put("message",responseMsg);
		*/
	/*	dataMap.put("message",responseMap);
		
	    return dataMap;
	}*/
	
}
