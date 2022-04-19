package com.dbc.pokesuits.config.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Value(value = "${kafka.bootstrap-servers}")//puxa o endereço do servidor do kafka
    private String bootstrapAddress;//localhost:9092

    @Bean
    public KafkaTemplate<String,String> configKafkaTemplate(){//configurando o template para o kafka
        Map<String, Object> configProps = new HashMap<>();//
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);//servidor do kafka na config
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);//serializer da chave
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);//serializer do valor(mensagem)
        DefaultKafkaProducerFactory<String, String> kafkaProducerFactory = new DefaultKafkaProducerFactory<>(configProps);//inicializa fabrica do kafka
        return new KafkaTemplate<>(kafkaProducerFactory);
    }
}
