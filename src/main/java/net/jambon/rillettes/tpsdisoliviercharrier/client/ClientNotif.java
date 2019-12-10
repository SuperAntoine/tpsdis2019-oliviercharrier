package net.jambon.rillettes.tpsdisoliviercharrier.client;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "pubsub-notif-olivier-charrier", ackMode = "AUTO")
public class ClientNotif {

    private Queue pubsubNotif;

    @RabbitHandler
    public void receive(String msg) {
        System.out.println("Client 1");
    }
}
