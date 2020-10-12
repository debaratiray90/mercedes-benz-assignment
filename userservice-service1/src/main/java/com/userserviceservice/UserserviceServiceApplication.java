package com.userserviceservice;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.userserviceservice.model.User;
import com.userserviceservice.service.KafkaProducer;

@SpringBootApplication
public class UserserviceServiceApplication  {

	@Autowired
	private ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(UserserviceServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/*
	 * public void run(final String... arg0) throws Exception { final KafkaProducer
	 * kp = context.getBean(KafkaProducer.class); for (int i = 1; i < 2; i++) { User
	 * user = new User(10.20, 1223, "Rahul", 30, new Date());
	 * user.setFileType("XML"); user.setEventType("UPDATE"); kp.sendMessage(user);
	 * System.out.println("message sent..."); } }
	 */

}
