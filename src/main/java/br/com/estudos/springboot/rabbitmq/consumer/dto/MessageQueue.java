package br.com.estudos.springboot.rabbitmq.consumer.dto;

public class MessageQueue {
    private String message;

    public MessageQueue() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
