package edu.ucam.kafkasender.services;

import edu.ucam.kafkasender.entity.Mensaje;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MensajeService {

    private final KafkaTemplate<String, Mensaje> producer;

    public MensajeService(KafkaTemplate<String, Mensaje> producer) {
        this.producer = producer;
    }

    private String topicMensaje = "test_topic";

    public void publish(Mensaje mensaje){
        this.producer.send(topicMensaje, mensaje);
    }
}
