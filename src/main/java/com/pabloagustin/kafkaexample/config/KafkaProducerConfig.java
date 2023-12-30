package com.pabloagustin.kafkaexample.config;

import com.pabloagustin.kafkaexample.payloads.Message;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

	// Broker url
	@Value("${spring.kafka.bootstrap-servers}")
	private String boostrapServers;

	// Configuration that we can pass to the producer factory
	public Map<String, Object> producerConfig(){
		HashMap<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return props;
	}

	@Bean
	// Producer Factory: for creating producer instances
	public ProducerFactory<String, Message> producerFactory(){
		return new DefaultKafkaProducerFactory<>(producerConfig());
	}

	@Bean
	// Kafka Template
	public KafkaTemplate<String, Message> kafkaTemplate(
			ProducerFactory<String, Message> producerFactory
	){
		return new KafkaTemplate<>(producerFactory);
	}

}
