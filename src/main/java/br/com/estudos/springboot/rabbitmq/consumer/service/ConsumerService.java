package br.com.estudos.springboot.rabbitmq.consumer.service;

import br.com.estudos.springboot.rabbitmq.consumer.dto.MessageQueue;

public interface ConsumerService {
    void action(MessageQueue message) throws Exception;
}
