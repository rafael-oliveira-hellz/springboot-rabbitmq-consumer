package br.com.estudos.springboot.rabbitmq.consumer.service.implementation;

import br.com.estudos.springboot.rabbitmq.consumer.amqp.AmqpRePublish;
import br.com.estudos.springboot.rabbitmq.consumer.service.RePublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RePublishServiceImpl implements RePublishService {

    @Autowired
    private AmqpRePublish rePublish;

    @Override
    public void repost() {
        rePublish.rePublish();
    }
}
