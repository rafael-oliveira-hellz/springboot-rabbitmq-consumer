package br.com.estudos.springboot.rabbitmq.consumer.amqp;

public interface AmqpConsumer<T> {
    void consume(T t);
}
