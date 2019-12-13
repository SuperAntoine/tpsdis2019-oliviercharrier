package net.jambon.rillettes.tpsdisoliviercharrier.client.message;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

@RabbitListener(queues = "fanout-pubsub-message-olivier-charrier")
public class ClientMessage {

    @Autowired
    private Queue pubsubMessage;

    public ClientMessage() { }

    @RabbitHandler
    public void receive(String msg) {
        Message message = new Message();
        message.fromString(msg);
        System.out.println(message.toString());
    }
}
