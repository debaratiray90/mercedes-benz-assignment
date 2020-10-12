package com.userserviceservice.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.userserviceservice.utils.AESSerializer;

@Configuration
@ConfigurationProperties(prefix = "kafka.config")
public class KafkaProducerConfig {
	
	@Value("${aes-secret-key}")
	private String aesSecretKey;
	
	@Value("${aes-salt-key}")
	private String aesSaltKey;

	private String bootstrapAddress;
	private String topicName;
	private int numPartitions;
	private short replicationFactor;

	public void setBootstrapAddress(final String bootstrapAddress) {
		this.bootstrapAddress = bootstrapAddress;
	}

	public String getBootstrapAddress() {
		return this.bootstrapAddress;
	}

	public int getNumPartitions() {
		return this.numPartitions;
	}

	public String getTopicName() {
		return this.topicName;
	}

	public void setTopicName(final String topicName) {
		this.topicName = topicName;
	}

	public void setNumPartitions(final int numPartitions) {
		this.numPartitions = numPartitions;
	}

	public short getReplicationFactor() {
		return this.replicationFactor;
	}

	public void setReplicationFactor(final short replicationFactor) {
		this.replicationFactor = replicationFactor;
	}

	@Bean
	public NewTopic topic1() {
		return new NewTopic(topicName, numPartitions, replicationFactor);
	}

	@Bean
	public ProducerFactory<String, Object> producerFactory() {
		final Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AESSerializer.class);
		configProps.put(AESSerializer.AES_SECRET_KEY, aesSecretKey);
		configProps.put(AESSerializer.AES_SALT_KEY, aesSaltKey);
		return new DefaultKafkaProducerFactory<>(configProps);
	}

	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate() {
		return new KafkaTemplate<>(this.producerFactory());
	}

}
