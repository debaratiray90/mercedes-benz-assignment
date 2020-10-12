package com.userserviceservice.utils;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userserviceservice.model.User;

public class CustomUserDeserializerKafka implements Deserializer<User>{

	@Override
	public User deserialize(String topic, byte[] data) {
		 ObjectMapper mapper = new ObjectMapper();
		   User user = null;
		   try {
		     user = mapper.readValue(data, User.class);
		   } catch (Exception e) {
		     e.printStackTrace();
		   }
		   return user;
	}

}
