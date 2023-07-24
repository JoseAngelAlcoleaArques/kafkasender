package edu.ucam.kafkasender.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqSenderConfig {

    @Bean
    public Queue queue() {
        return new Queue("MQ_DEMO");
    }
}
