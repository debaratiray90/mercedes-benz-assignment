package com.userdataservice.service;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.stereotype.Component;

import io.confluent.kafka.serializers.protobuf.KafkaProtobufDeserializer;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufDeserializerConfig;

@Component
public class ProtobufConsumer {

	public void readMessages() {
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "protobuf-consumer-group");
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaProtobufDeserializer.class);

		properties.put(KafkaProtobufDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
//        properties.put(KafkaProtobufDeserializerConfig.SPECIFIC_PROTOBUF_VALUE_TYPE, UserMessageProtos.class.getName());

		KafkaConsumer<String, SimpleMessage> consumer = new KafkaConsumer<>(properties);

		consumer.subscribe(Collections.singleton("protobuf-topic"));

		// poll the record from the topic
		while (true) {
			ConsumerRecords<String, SimpleMessage> records = consumer.poll(Duration.ofMillis(100));

			for (ConsumerRecord<String, SimpleMessage> record : records) {
				// System.out.println("Message Id : " + record.value().getUserId));
				// System.out.println("Message Name : " + record.value().getUserName());
			}
			consumer.commitAsync();
		}

	}
}
