package com.pabloagustin.kafkaexample;

import com.pabloagustin.kafkaexample.payloads.Message;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;

@SpringBootApplication
public class KafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String, Message> kafkaTemplate){
		return args -> {
			// Producing 10000 events
			for (int i = 0; i < 10 ; i++) {
				kafkaTemplate.send("pabloagustin", new Message(
						"Hello from kafka :D" + i,
						LocalDateTime.now()));
			}
		};
	}

}
