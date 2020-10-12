package com.userserviceservice.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.userserviceservice.model.User;

public class CustomUserDeserializer extends StdDeserializer<User> {

	protected CustomUserDeserializer(Class<?> vc) {
		super(vc);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public User deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		Integer age = (Integer) ((IntNode) node.get("age")).numberValue();
		Integer userId = (Integer) ((IntNode) node.get("userId")).numberValue();
		String name = node.get("name").asText();
		String dob = node.get("dateOfBirth").asText();
		String sDate1 = "31/12/1998";
		Date dateOfBirth = null;
		try {
			dateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
			System.out.println(sDate1 + "\t" + dateOfBirth);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String salary = node.get("salary").asText();
	
		return new User(Double.parseDouble(salary), userId, name,age,dateOfBirth);
	}

}
