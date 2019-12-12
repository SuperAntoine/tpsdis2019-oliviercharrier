package net.jambon.rillettes.tpsdisoliviercharrier.client;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "pubsub-message-olivier-charrier", ackMode = "AUTO")
public class ClientMessage {

    private Queue pubsubMessage;

    @RabbitHandler
    public void receive(String msg) {
        System.out.println("Client 2");
    }
}
