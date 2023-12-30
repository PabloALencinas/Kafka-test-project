package com.pabloagustin.kafkaexample.payloads;

import java.time.LocalDateTime;

public record Message(
		String message,
		LocalDateTime createdAt
) {
}
