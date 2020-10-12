package com.userserviceservice.utils;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userserviceservice.model.User;

public class CustomUserSerializerKafka implements Serializer<User> {


	
	@Override
	public byte[] serialize(String topic, User data) {
		byte[] retVal = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			retVal = objectMapper.writeValueAsString(data).getBytes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}
}
