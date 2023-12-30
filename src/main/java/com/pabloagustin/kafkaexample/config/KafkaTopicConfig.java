package com.pabloagustin.kafkaexample.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
// Class to create Topics
public class KafkaTopicConfig {

	@Bean
	public NewTopic pabloTopic(){
		return TopicBuilder.name("pabloagustin")
				.build();
	}
}
