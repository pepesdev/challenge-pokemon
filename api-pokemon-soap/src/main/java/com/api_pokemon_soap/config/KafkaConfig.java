package com.api_pokemon_soap.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public String requestLogTopic(@Value("${app.kafka.request-log-topic}") String topic) {
        return topic;
    }
}
