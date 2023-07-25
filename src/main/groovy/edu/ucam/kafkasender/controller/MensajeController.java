package edu.ucam.kafkasender.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ucam.kafkasender.entity.EventoDTO;
import edu.ucam.kafkasender.interfaces.MessageSender;
import edu.ucam.kafkasender.services.MessageSenderFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@EnableRabbit
@RestController
@RequestMapping("/mensajes")
public class MensajeController {

    @Autowired
    private MessageSenderFactory messageSenderFactory;

    @PostMapping("/enviar")
    String sendMessage(@RequestParam String type, @RequestBody EventoDTO eventoDTO) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String message = objectMapper.writeValueAsString(eventoDTO);

        MessageSender messageSender = messageSenderFactory.getMessageSender(type);
        messageSender.sendMessage(message);

        return "Message successfully sent";
    }
}
