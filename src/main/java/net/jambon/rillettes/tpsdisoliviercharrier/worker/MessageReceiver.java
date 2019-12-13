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

    public List<Message> getAllMessages() {
        List<Message> msgs = new ArrayList<Message>();
        this.messageRepository.findAll().forEach(msg -> msgs.add(msg));
        return msgs;
    }

    public void saveOrUpdate(Message msg) {
        this.messageRepository.save(msg);
    }

    @RabbitHandler
    public void receive(String msg) {
        System.out.println("Message reçu : " + msg);
        Message message = new Message();
        String type = message.fromString(msg);
        if (type.equals("POST")) {
            this.saveOrUpdate(message);
        } else if (type.equals("DELETE")) {
            //Suppression
        }
        System.out.println("Message envoyé sur la queue : " + fanout.getName() + " avec comme message : " + msg);
        this.template.convertAndSend(fanout.getName(), "", msg);
    }
}
