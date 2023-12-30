package com.pabloagustin.kafkaexample.controllers;

import com.pabloagustin.kafkaexample.payloads.Message;
import com.pabloagustin.kafkaexample.payloads.MessageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {

	// Inject the kafka template
	private KafkaTemplate<String, Message> kafkaTemplate;

	// Constructor
	public MessageController(KafkaTemplate<String, Message> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@PostMapping
	public void publish(@RequestBody MessageRequest messageRequest){
		Message message = new Message(messageRequest.message(), LocalDateTime.now());
		kafkaTemplate.send("pabloagustin", message);
	}

}
