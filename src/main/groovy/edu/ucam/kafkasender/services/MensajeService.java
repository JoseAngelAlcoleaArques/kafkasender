package edu.ucam.kafkasender.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ucam.kafkasender.entity.Mensaje;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MensajeService {

    private String topicMensaje = "test_topic";
    private final KafkaTemplate<String, String> producer;

    public MensajeService(KafkaTemplate<String, String> producer) {
        this.producer = producer;
    }

    public void publish(Mensaje mensaje){
        String jsonString = convertToJsonString(mensaje);
        this.producer.send(topicMensaje, jsonString);
    }

    private String convertToJsonString(Mensaje mensaje) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(mensaje);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
