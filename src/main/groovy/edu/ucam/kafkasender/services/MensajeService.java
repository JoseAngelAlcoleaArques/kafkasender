package edu.ucam.kafkasender.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ucam.kafkasender.config.RabbitMqSenderConfig;
import edu.ucam.kafkasender.entity.EventoDTO;
import edu.ucam.kafkasender.entity.Mensaje;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MensajeService {

    private final RabbitTemplate rabbitTemplate;

    private final KafkaTemplate<String, String> producer;

    private final RabbitMqSenderConfig rabbitMqConfig;

    public MensajeService(RabbitTemplate rabbitTemplate, KafkaTemplate<String, String> producer, RabbitMqSenderConfig rabbitMqConfig) {
        this.rabbitTemplate = rabbitTemplate;
        this.producer = producer;
        this.rabbitMqConfig = rabbitMqConfig;
    }

    public void publish(EventoDTO eventoDTO){
        String jsonString = convertToJsonString(eventoDTO);
        String topicMensaje = "test_topic";
        this.producer.send(topicMensaje, jsonString);
    }

    private String convertToJsonString(EventoDTO eventoDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(eventoDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void sendMessage(String message){
        rabbitTemplate.convertAndSend(rabbitMqConfig.queue().getName(), message);
    }
}
