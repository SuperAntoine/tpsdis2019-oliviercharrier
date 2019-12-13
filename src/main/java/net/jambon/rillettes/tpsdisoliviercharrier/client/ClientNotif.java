package net.jambon.rillettes.tpsdisoliviercharrier.client;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

@RabbitListener(queues = "fanout-pubsub-notif-olivier-charrier", ackMode = "AUTO")
public class ClientNotif {

    @Autowired
    private Queue queue;

    public ClientNotif() { }

    @RabbitHandler
    public void receive(String msg) {
        System.out.println("Message reçu : " + msg);
    }
}
