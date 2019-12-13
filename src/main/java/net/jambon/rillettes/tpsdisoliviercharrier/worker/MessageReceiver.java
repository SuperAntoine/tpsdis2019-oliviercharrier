package net.jambon.rillettes.tpsdisoliviercharrier.worker;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RabbitListener(queues = "message-olivier-charrier", ackMode = "AUTO")
public class MessageReceiver {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private FanoutExchange fanout;

    @Autowired
    private RabbitTemplate template;

    //Sauvegarde ou met à jour dans la BDD
    private void saveOrUpdate(Message msg) {
        this.messageRepository.save(msg);
    }

    //Suppression
    private void delete(Message msg) {
        this.messageRepository.delete(msg);
    }

    //Réception du message depuis l'API et envoi aux clients
    @RabbitHandler
    public void receive(String msg) {
        System.out.println("Message reçu : " + msg);
        Message message = new Message();
        //On regarde le type de message et on fait l'action correspondante
        String type = message.fromString(msg);
        if (type.equals("POST") || type.equals("UPDATE")) {
            this.saveOrUpdate(message);
        } else if (type.equals("DELETE")) {
            this.delete(message);
        }
        //Envoi aux clients
        System.out.println("Message envoyé sur la queue : " + fanout.getName() + " avec comme message : " + msg);
        this.template.convertAndSend(fanout.getName(), "", msg);
    }
}
