package com.userdataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class UserdataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserdataServiceApplication.class, args);
	}

}
