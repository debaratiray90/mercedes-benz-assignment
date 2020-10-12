package com.userdataservice.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.userdataservice.utils.AESDeserializer;

@Configuration
@ConfigurationProperties(prefix = "kafka.config")
public class KafkaConsumerConfig {

	@Value("${aes-secret-key}")
	private String aesSecretKey;
	
	@Value("${aes-salt-key}")
	private String aesSaltKey;
	
	private static final String TRUSTED_PACKAGE = "com.userserviceservice.model";
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
	public ConsumerFactory<String, String> consumerFactory() {
		final Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "my-consumer-group");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, AESDeserializer.class);
		props.put(AESDeserializer.AES_SECRET_KEY, aesSecretKey);
		props.put(AESDeserializer.AES_SALT_KEY, aesSaltKey);

		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put(JsonDeserializer.TRUSTED_PACKAGES, TRUSTED_PACKAGE);


		return new DefaultKafkaConsumerFactory<>(props);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		final ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(this.consumerFactory());
		return factory;
	}
	
}
