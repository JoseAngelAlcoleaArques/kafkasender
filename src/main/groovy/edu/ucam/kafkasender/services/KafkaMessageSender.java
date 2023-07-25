package edu.ucam.kafkasender.services;

import edu.ucam.kafkasender.interfaces.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageSender implements MessageSender {

    private final KafkaTemplate<String, String> producer;

    @Autowired
    public KafkaMessageSender(KafkaTemplate<String, String> producer) {
        this.producer = producer;
    }

    @Override
    public void sendMessage(String message) {
        String topicMensaje = "test_topic";
        producer.send(topicMensaje, message);
    }
}
