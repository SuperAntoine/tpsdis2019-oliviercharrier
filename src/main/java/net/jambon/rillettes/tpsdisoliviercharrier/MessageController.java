package net.jambon.rillettes.tpsdisoliviercharrier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/messages")
    private List<Message> getAllMessages() {
        return this.messageService.getAllMessages();
    }

    @PostMapping("/messages")
    private int saveMessage(@RequestParam(value = "content", defaultValue = "this is a message") String msg) {
        Message message = new Message(msg);
        this.messageService.saveOrUpdate(message);
        return message.getId();
    }
}