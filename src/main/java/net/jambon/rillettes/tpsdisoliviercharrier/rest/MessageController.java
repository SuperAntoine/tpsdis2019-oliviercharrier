package net.jambon.rillettes.tpsdisoliviercharrier.rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    @Autowired
    private MessageRepository messageRepository;

    //Contient l'ensemble des messages sans se synchroniser avec la BDD
    private List<Message> messages;

    @Autowired
    public MessageController() {
        this.messages = new ArrayList<>();
    }

    private void initializeMessages() {
        //Le repository n'est pas initialisé dans le constructeur donc on charge la base ici
        //L'accès à la BDD ne se fait qu'une fois et sert à initilialiser la liste locale de messages
        if (this.messages.size() == 0) {
            this.messages = new ArrayList<>();
            this.messageRepository.findAll().forEach(msg -> this.messages.add(msg));
        }
    }

    //Retourne un message selon l'id
    private Message getById(int id) {
        for (Message msg: this.messages) {
            if (msg.getId() == id) {
                return msg;
            }
        }
        return null;
    }

    //Retourne tous les messages
    @GetMapping("/messages")
    private List<Message> getAllMessages() {
        return this.messages;
    }

    //Création d'un nouveau message
    @PostMapping("/messages")
    private int saveMessage(@RequestParam(value = "content", defaultValue = "this is a message") String msg) {
        this.initializeMessages();
        Message message = new Message(msg, this.messages.size());
        //Envoi aux workers
        System.out.println("Message envoyé sur la queue : " + queue.getName() + " avec comme message : " + message.toString("POST"));
        this.template.convertAndSend(queue.getName(), message.toString("POST"));
        //Sauvegarde locale
        this.messages.add(message);
        return message.getId();
    }

    //Suppression d'un message
    @PostMapping("/messages/delete")
    private void deleteMessage(@RequestParam(value = "id") int id) {
        this.initializeMessages();
        //On récupère le message à supprimer
        Message message = this.getById(id);
        if (message != null) {
            //Envoi aux workers
            System.out.println("Message envoyé sur la queue : " + queue.getName() + " avec comme message : " + message.toString("DELETE"));
            this.template.convertAndSend(queue.getName(), message.toString("DELETE"));
            //Suppression locale
            this.messages.removeIf(msg -> msg.getId() == id);
        }
    }

    //Modification d'un message
    @PostMapping("/messages/update")
    private void updateMessage(@RequestParam Map<String, String> params) {
        int id = Integer.parseInt(params.get("id"));
        String content = params.get("content");
        this.initializeMessages();
        //On récupère le message à modifier
        Message message = this.getById(id);
        if (message != null) {
            //Modification du message
            message.setContenu(content);
            //Envoi aux workers
            System.out.println("Message envoyé sur la queue : " + queue.getName() + " avec comme message : " + message.toString("UPDATE"));
            this.template.convertAndSend(queue.getName(), message.toString("UPDATE"));
            //Modification locale
            this.messages.removeIf(msg -> msg.getId() == id);
            this.messages.add(message);
        }
    }
}