package net.jambon.rillettes.tpsdisoliviercharrier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAllMessages() {
        List<Message> msgs = new ArrayList<Message>();
        this.messageRepository.findAll().forEach(msg -> msgs.add(msg));
        return msgs;
    }

    public void saveOrUpdate(Message msg) {
        this.messageRepository.save(msg);
    }
}
