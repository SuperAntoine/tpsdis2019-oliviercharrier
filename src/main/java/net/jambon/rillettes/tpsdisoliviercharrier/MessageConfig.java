package net.jambon.rillettes.tpsdisoliviercharrier;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    @Bean
    public Queue message() {
        return new Queue("message");
    }

    @Bean
    public MessageService service() {
        return new MessageService();
    }
}