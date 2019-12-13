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
        String[] split = msg.split("\t");
        String timestamp = split[3];
        String type = split[0];
        System.out.print(String.format("[%s] ", timestamp));
        if (type.equals("POST")) {
            System.out.println("Nouveau message");
        } else if (type.equals("DELETE")) {
            System.out.println("Suppression d'un message");
        }
    }
}
