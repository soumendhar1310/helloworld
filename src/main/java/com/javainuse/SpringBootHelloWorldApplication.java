package com.javainuse;


import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javainuse.controllers.TestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@RestController
@SpringBootApplication
@EnableSwagger2
public class SpringBootHelloWorldApplication {

	
	@Autowired
	private Environment env;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	@Bean
	RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
	
	
	@Bean
    public Docket api()/* throws IOException, XmlPullParserException*/ {
        //MavenXpp3Reader reader = new MavenXpp3Reader();
        //Model model = (Model) reader.read(new FileReader("pom.xml"));
        return new Docket(DocumentationType.SWAGGER_2)  
          .select() 
          .apis(RequestHandlerSelectors.basePackage("com.javainuse"))
          .paths(PathSelectors.any())                          
          .build().apiInfo(metadata());                                           
	}
	
	
	@SuppressWarnings("deprecation")
	private ApiInfo metadata() {
	      return new ApiInfoBuilder()
	        .title("My awesome API")
	        .description("Some description")
	        .version("1.0")
	        .contact("my-email@domain.org")
	        .build();
	    }
	
	/*
	@GetMapping("/create")
	public Map<String, Object> create(@RequestParam String name, @RequestParam String surname) {
		//RestTemplate restTemplate = new RestTemplate();
	    JsonNode rootNode=null;
	    ObjectMapper objectMapper=null;
	    String recordID="";
	    String userJSONText="";
		String fooResourceUrl
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
		
		//Call Adapter Microservice
		
		//userJSONText = "{\"name\":\""+name+"\", \"surname\":\""+surname+"\"}";
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
		
		
		dataMap.put("message",responseMap);
		
	    return dataMap;
	}
	*/
	/*@Bean
	public AlwaysSampler defaultSampler() {
	  return new AlwaysSampler();
	}*/
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootHelloWorldApplication.class, args);
	}
}
