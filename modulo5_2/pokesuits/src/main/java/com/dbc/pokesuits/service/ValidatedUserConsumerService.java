package com.dbc.pokesuits.service;

import com.dbc.pokesuits.dto.mailconnect.ValidatedUserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class ValidatedUserConsumerService {
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "${kafka.validated-user.topic}",
            groupId = "${kafka.group-id}",
            containerFactory = "listenerContainerFactory")
    public void consumeMailValidation(@Payload String message,
                                  @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                                  @Header(KafkaHeaders.OFFSET) Long offset) throws JsonProcessingException {

        ValidatedUserDTO validatedUserDTO = objectMapper.readValue(message, ValidatedUserDTO.class);

        log.info("validacao de usuario consumida");

        log.info("#### offset -> '" + offset + "' key -> '" + key + "' -> Consumed Object message -> '" + message + "'");
    }
}
