package net.jambon.rillettes.tpsdisoliviercharrier.rest;

import java.util.ArrayList;
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
        this.template.convertAndSend(queue.getName(), message.toString());
        System.out.println("Message envoyé sur la queue : " + queue.getName() + " avec comme message : " + message.toString());
        this.messages.add(message);
        return message.getId();
    }
}