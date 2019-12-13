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
        //On récupère le timestamp et le type d'action
        String[] split = msg.split("\t");
        String timestamp = split[3];
        String type = split[0];
        //On affiche le timestamp de l'action
        System.out.print(String.format("[%s] ", timestamp));
        //On affiche la bonne notification
        if (type.equals("POST")) {
            System.out.println("Nouveau message");
        } else if (type.equals("DELETE")) {
            System.out.println("Suppression d'un message");
        } else if (type.equals("UPDATE")) {
            System.out.println("Mise à jour d'un message");
        }
    }
}
