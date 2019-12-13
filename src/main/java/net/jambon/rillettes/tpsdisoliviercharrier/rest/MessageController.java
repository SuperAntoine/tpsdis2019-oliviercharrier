package net.jambon.rillettes.tpsdisoliviercharrier.rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    private List<Message> messages;

    @Autowired
    public MessageController() {
        this.messages = new ArrayList<>();
    }

    @GetMapping("/messages")
    private List<Message> getAllMessages() {
        return this.messages;
    }

    @PostMapping("/messages")
    private int saveMessage(@RequestParam(value = "content", defaultValue = "this is a message") String msg) {
        Message message = new Message(msg);
        System.out.println("Message envoyé sur la queue : " + queue.getName() + " avec comme message : " + message.toString("POST"));
        this.template.convertAndSend(queue.getName(), message.toString("POST"));
        this.messages.add(message);
        return message.getId();
    }

    private Message getById(int id) {
        for (Message msg: this.messages) {
            if (msg.getId() == id) {
                return msg;
            }
        }
        return null;
    }

    @PostMapping("/messages/delete")
    private void deleteMessage(@RequestParam(value = "id") int id) {
        if (this.messages.size() == 0) {
            this.messages = new ArrayList<>();
            this.messageRepository.findAll().forEach(msg -> this.messages.add(msg));
        }
        Message message = this.getById(id);
        if (message != null) {
            System.out.println("Message envoyé sur la queue : " + queue.getName() + " avec comme message : " + message.toString("DELETE"));
            this.template.convertAndSend(queue.getName(), message.toString("DELETE"));
            this.messages.removeIf(msg -> msg.getId() == id);
        }
    }
}