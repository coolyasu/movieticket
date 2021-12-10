package com.demo.validator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import com.demo.exception.BadRequestException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;


public class JSONValidator {
	
	public void ValidateData(String data) throws IOException{
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		InputStream inputStream = JSONValidator.class.getClassLoader().getResourceAsStream("JSONSchemaValidator.json");
		JsonSchema schema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7).getSchema(inputStream);
		
		JsonNode jsonNode = objectMapper.readTree(data);
		
		
		Set<ValidationMessage> result = schema.validate(jsonNode);
		
		if(result.size()>0){
			result.forEach(res -> System.out.println(res.getMessage()));
			throw new BadRequestException(result.toString());
		}
	}
}
