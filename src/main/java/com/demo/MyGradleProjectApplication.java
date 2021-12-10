package com.demo;

import java.io.File;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import com.demo.entity.Inventory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
//@EnableEurekaServer
public class MyGradleProjectApplication {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		SpringApplication.run(MyGradleProjectApplication.class, args);
		
		ObjectMapper objectMapper = new ObjectMapper();
		Inventory inventory = new Inventory();
		
		objectMapper.writeValue(new File("src/main/java/Inventory.json"), inventory);
		String jsonString = objectMapper.writeValueAsString(inventory);
		System.out.println(jsonString);
	}

}
