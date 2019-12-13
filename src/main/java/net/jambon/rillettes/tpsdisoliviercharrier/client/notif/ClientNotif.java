package net.jambon.rillettes.tpsdisoliviercharrier.client.notif;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

@RabbitListener(queues = "fanout-pubsub-notif-olivier-charrier")
public class ClientNotif {

    @Autowired
    private Queue queue;

    public ClientNotif() { }

    @RabbitHandler
    public void receive(String msg) {
        String timestamp = msg.split("\t")[2];
        System.out.println(String.format("[%s] Nouveau message",timestamp ));
    }
}
