package br.com.estudos.springboot.rabbitmq.consumer.service.implementation;

import br.com.estudos.springboot.rabbitmq.consumer.dto.MessageQueue;
import br.com.estudos.springboot.rabbitmq.consumer.service.ConsumerService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Override
    public void action(MessageQueue message) throws Exception {
        //throw new Exception("Erro ao consumir a mensagem");
        System.out.println("Received <" + message.getMessage() + ">");

        if("teste".equalsIgnoreCase(message.getMessage())) {
            throw new AmqpRejectAndDontRequeueException("Erro ao consumir a mensagem");
        }

        System.out.println(message.getMessage());
    }
}