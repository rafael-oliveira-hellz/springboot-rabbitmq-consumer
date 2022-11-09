package br.com.estudos.springboot.rabbitmq.consumer.amqp.implementation;

import br.com.estudos.springboot.rabbitmq.consumer.amqp.AmqpRePublish;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RabbitMQRePublish implements AmqpRePublish {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.routing-key.subscriber}")
    private String queue;

    @Value("${spring.rabbitmq.exchange.subscriber}")
    private String exchange;

    @Value("${spring.rabbitmq.dead-letter.subscriber}")
    private String deadLetter;

    @Value("${spring.rabbitmq.parking-lot.subscriber}")
    private String parkingLot;

    private static final String X_RETRIES_HEADER = "x-retries";

    @Override
    @Scheduled(cron = "${spring.rabbitmq.listener.time-retry}")
    public void rePublish() {
        List<Message> messages = getQueueMessages();

        messages.forEach(message -> {
            Map<String, Object> headers = message.getMessageProperties().getHeaders();
            Integer retriesHeader = (Integer) headers.get(X_RETRIES_HEADER);

            if(retriesHeader == null) {
                retriesHeader = 0;
            }

            if (retriesHeader < 3) {
                headers.put(X_RETRIES_HEADER, retriesHeader + 1);
                rabbitTemplate.send(exchange, queue, message);
            } else {
                rabbitTemplate.send(parkingLot, message);
            }
        });
    }

    private List<Message> getQueueMessages() {
        List<Message> messages = new ArrayList<>();
        Boolean isNotNull;
        Message message;

        do {
            message = rabbitTemplate.receive(deadLetter);
            isNotNull = message != null;

            if(Boolean.TRUE.equals(isNotNull)) {
                messages.add(message);
            }
        } while (Boolean.TRUE.equals(isNotNull));

        return messages;
    }

}
