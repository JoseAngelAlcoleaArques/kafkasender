package edu.ucam.kafkasender

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@EnableRabbit
@SpringBootApplication
class KafkasenderApplication {

    static void main(String[] args) {
        SpringApplication.run(KafkasenderApplication, args)
    }

}
