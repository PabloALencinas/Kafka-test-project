package com.pabloagustin.kafkaexample.listener;

import com.pabloagustin.kafkaexample.payloads.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

	@KafkaListener(
			topics = "pabloagustin",
			groupId = "groupId",
			containerGroup = "messageFactory"
	)
	// Listeners for ours topic
	void listener(Message data){
		// Whatever we want to do with the data
		System.out.println("Listener received: " + data.toString() + " :D" );
	}

}
