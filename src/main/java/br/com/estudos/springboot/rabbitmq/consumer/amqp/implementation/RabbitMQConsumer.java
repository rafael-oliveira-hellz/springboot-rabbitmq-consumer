package br.com.estudos.springboot.rabbitmq.consumer.amqp.implementation;

import br.com.estudos.springboot.rabbitmq.consumer.amqp.AmqpConsumer;
import br.com.estudos.springboot.rabbitmq.consumer.dto.MessageQueue;
import br.com.estudos.springboot.rabbitmq.consumer.service.ConsumerService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer implements AmqpConsumer<MessageQueue> {

    @Autowired
    private ConsumerService consumerService;

    @Override
    @RabbitListener(queues = "${spring.rabbitmq.routing-key.subscriber}")
    public void consume(MessageQueue message) {
        try {
            consumerService.action(message);
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}

