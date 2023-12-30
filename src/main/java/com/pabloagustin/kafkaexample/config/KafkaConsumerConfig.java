package com.pabloagustin.kafkaexample.config;

import com.pabloagustin.kafkaexample.payloads.Message;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

	// Broker url
	@Value("${spring.kafka.bootstrap-servers}")
	private String boostrapServers;

	// Configuration that we can pass to the producer factory
	public Map<String, Object> consumerConfig(){
		HashMap<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapServers);
		return props;
	}

	// Instead of ProducerFactory -> ConsumerFactory
	@Bean
	public ConsumerFactory<String, Message> consumerFactory(){
		JsonDeserializer<Message> jsonDeserializer = new JsonDeserializer<>();
		jsonDeserializer.addTrustedPackages("*");
		return new DefaultKafkaConsumerFactory<>(
				consumerConfig(),
				new StringDeserializer(),
				jsonDeserializer
		);
	}

	// Listener container factory
	// This listener receives all messages from topics or partitions of a single thread
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Message>> factory(
			ConsumerFactory<String, Message> consumerFactory
	){
		ConcurrentKafkaListenerContainerFactory<String, Message> factory =
				new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}

	@Bean
	public RecordMessageConverter converter() {
		return new JsonMessageConverter();
	}

}
