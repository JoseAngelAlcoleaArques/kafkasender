package edu.ucam.kafkasender.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqSenderConfig {

    @Value("${rabbitmq.cola.nombre}")
    private String rabbitMqQueue;

    @Bean
    public Queue queue() {
        return new Queue(rabbitMqQueue);
    }
}
