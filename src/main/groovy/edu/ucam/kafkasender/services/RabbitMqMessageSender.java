package edu.ucam.kafkasender.services;

import edu.ucam.kafkasender.config.RabbitMqSenderConfig;
import edu.ucam.kafkasender.interfaces.MessageSender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqMessageSender implements MessageSender {

    private final RabbitTemplate rabbitTemplate;

    private final RabbitMqSenderConfig rabbitMqConfig;

    public RabbitMqMessageSender(RabbitTemplate rabbitTemplate, RabbitMqSenderConfig rabbitMqConfig) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMqConfig = rabbitMqConfig;
    }

    @Override
    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(rabbitMqConfig.queue().getName(), message);
    }
}
