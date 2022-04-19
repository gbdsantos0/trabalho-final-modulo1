package com.dbc.emailhandler.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.dbc.emailhandler.dto.EmailUserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailUserConsumerService {

	private final ValidTokenService validTokenService;
	private final ObjectMapper mapper;
	
	@KafkaListener(
			topics = "${kafka.email-user.topic}",
			groupId = "${kafka.client-id}",
            containerFactory = "listenerContainerFactory"
	)
	public void consume(@Payload String message, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key)
			throws JsonMappingException, JsonProcessingException {
		EmailUserDTO emailUserDTO = this.mapper.readValue(message, EmailUserDTO.class);
		log.info("#### received operation -> '{}' ",emailUserDTO.getOperation());
		
		this.validTokenService.sendVerificationEmail(emailUserDTO);
	}
}
