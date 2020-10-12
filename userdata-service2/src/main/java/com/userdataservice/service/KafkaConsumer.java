package com.userdataservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.userserviceservice.model.User;

@Component
public class KafkaConsumer {

	@Autowired
	private UserDataIF userDataService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String UPDATE = "Update";

	@KafkaListener(topics = "user-topic")
	public void listen(@Payload final User userDto, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) final int partition,
			@Header(KafkaHeaders.OFFSET) final long offset) {
		logger.info("receive message with partition {} and offset {}", partition, offset);
		logger.info("testId: {} ", userDto.getName());

		if (userDto.getEventType().equals(UPDATE)) {
			userDataService.updateUser(userDto);
		} else {
			userDataService.saveUser(userDto);
		}

	}
}
