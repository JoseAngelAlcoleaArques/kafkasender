package edu.ucam.kafkasender.services;

import edu.ucam.kafkasender.config.RabbitMqSenderConfig;
import edu.ucam.kafkasender.interfaces.MessageSender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderFactory {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMqSenderConfig rabbitMqConfig;
    private final KafkaTemplate<String, String> producer;

    public MessageSenderFactory(RabbitTemplate rabbitTemplate, RabbitMqSenderConfig rabbitMqConfig, KafkaTemplate<String, String> producer) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMqConfig = rabbitMqConfig;
        this.producer = producer;
    }


    public MessageSender getMessageSender(String type){
        switch (type){
            case "KAFKA":
                return new KafkaMessageSender(producer);
            case "RABBITMQ":
                return new RabbitMqMessageSender(rabbitTemplate, rabbitMqConfig);
            default:
                throw new IllegalArgumentException("Tipo de MessageSender no válido: ${type}");
        }
    }
}
