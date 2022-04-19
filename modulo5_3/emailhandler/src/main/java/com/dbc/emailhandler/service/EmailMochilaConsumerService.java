package com.dbc.emailhandler.service;

import com.dbc.emailhandler.dto.MochilaUserDto;
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
public class EmailMochilaConsumerService {

	private final EmailService emailService;
	private final ObjectMapper mapper;
	
	@KafkaListener(
			topics = "${kafka.email-mochila-user.topic}",
			groupId = "${kafka.client-id}",
            containerFactory = "listenerContainerFactory",
			clientIdPrefix = "email-mochila"

	)
	public void consume(@Payload String message, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key)
			throws JsonMappingException, JsonProcessingException {
		MochilaUserDto mochilaUserDto = this.mapper.readValue(message, MochilaUserDto.class);
		log.info("#### mochila mail to -> '{}' ",mochilaUserDto.getEmail());
		
		this.emailService.sendEmail(mochilaUserDto);
	}
}
