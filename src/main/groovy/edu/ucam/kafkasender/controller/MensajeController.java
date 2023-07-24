package edu.ucam.kafkasender.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ucam.kafkasender.entity.EventoDTO;
import edu.ucam.kafkasender.entity.Mensaje;
import edu.ucam.kafkasender.services.MensajeService;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Timestamp;
import java.util.Date;

@EnableRabbit
@RestController
@RequestMapping("/mensajes")
public class MensajeController {

    private final MensajeService mensajeService;

    public MensajeController(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/enviar")
    public EventoDTO save(){
        EventoDTO enventoDTO = new EventoDTO();
        enventoDTO.setId("001");
        enventoDTO.setVersion(1L);
//        enventoDTO.setFecha(new Timestamp());
//        enventoDTO.setData();

        mensajeService.publish(enventoDTO);
        return enventoDTO;
    }

    @PostMapping("/sendMapping")
    String sendMessage(@RequestBody String json) throws JsonProcessingException {

        String jsonString = objectMapper.writeValueAsString(objectMapper.readValue(json, Object.class));

        mensajeService.sendMessage(jsonString);
        return "Message published successfully";
    }
}
