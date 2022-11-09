package br.com.estudos.springboot.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableRabbit
@EnableScheduling
@SpringBootApplication
@ComponentScan("br.com.estudos.springboot.rabbitmq")
public class SpringbootRabbitmqConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRabbitmqConsumerApplication.class, args);
    }

}
