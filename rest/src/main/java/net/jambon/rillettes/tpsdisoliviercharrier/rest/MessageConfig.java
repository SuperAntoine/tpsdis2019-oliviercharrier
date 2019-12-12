package net.jambon.rillettes.tpsdisoliviercharrier.rest;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    @Bean
    public Queue message() {
        return new Queue("message-olivier-charrier");
    }
}