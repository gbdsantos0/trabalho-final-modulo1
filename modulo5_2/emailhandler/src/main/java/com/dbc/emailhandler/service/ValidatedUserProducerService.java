package com.dbc.emailhandler.service;

import java.util.List;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.dbc.emailhandler.dto.ValidatedUserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
@RequiredArgsConstructor
public class ValidatedUserProducerService {
	
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper mapper;

	@Value("${kafka.validated-user.topic}")
	private String topic;
	
	public void sendMessage(List<ValidatedUserDTO> validatedUserDTO) throws JsonProcessingException {
		validatedUserDTO.forEach(validatedUser -> {
			try {
				this.sendMessage(this.mapper.writeValueAsString(validatedUser));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		});	
	}
	
	private void sendMessage(String msg) {
		Message<String> message = MessageBuilder.withPayload(msg)
				.setHeader(KafkaHeaders.TOPIC, topic)
				.setHeader(KafkaHeaders.MESSAGE_KEY, UUID.randomUUID().toString())
				.build();
		
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(message);

		future.addCallback(new ListenableFutureCallback<>() {
			@Override
			public void onFailure(Throwable ex) {
				log.error("Erro ao enviar mensagem para o kafka: {}", message, ex);
			}

			@Override
			public void onSuccess(SendResult<String, String> result) {
				log.info("Log enviado para o kafka com a mensagem: {}", message);
			}
		});
	}
}
