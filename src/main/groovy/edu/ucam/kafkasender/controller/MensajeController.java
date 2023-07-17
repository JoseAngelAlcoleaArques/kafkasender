package edu.ucam.kafkasender.controller;

import edu.ucam.kafkasender.entity.Mensaje;
import edu.ucam.kafkasender.services.MensajeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/mensajes")
public class MensajeController {

    private final MensajeService mensajeService;

    public MensajeController(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    @PostMapping("/enviar")
    public Mensaje save(){
        Mensaje mensaje = new Mensaje();
        mensaje.setId("1");
        mensaje.setDate(new Date());
        mensaje.setMensaje("Primer mensaje transmitido en kafka");

        mensajeService.publish(mensaje);

        return mensaje;
    }
}
