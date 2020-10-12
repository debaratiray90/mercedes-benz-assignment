package com.userserviceservice.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;



import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.userserviceservice.model.User;	

public class CustomUserSerializer extends StdSerializer<User>  {

	protected CustomUserSerializer(Class<User> t) {
		super(t);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void serialize(User value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
		jgen.writeStartObject();
        jgen.writeStringField("name", value.getName());
       // jgen.writeStr
        jgen.writeStringField("salary", value.getSalary().toString());
        jgen.writeStringField("dateOfBirth", dateFormat.format(value.getDateOfBirth()));
        jgen.writeNumberField("age", value.getAge());
        jgen.writeEndObject();
		
	}

}
