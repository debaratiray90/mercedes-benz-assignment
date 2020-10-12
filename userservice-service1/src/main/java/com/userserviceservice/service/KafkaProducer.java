package com.userserviceservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.userserviceservice.config.KafkaProducerConfig;
import com.userserviceservice.model.User;

@Component
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Autowired
	private KafkaProducerConfig config;
	

    public void sendMessage(final User dto) {
        kafkaTemplate.send(config.getTopicName(), dto.getName(), dto);
    }

}
