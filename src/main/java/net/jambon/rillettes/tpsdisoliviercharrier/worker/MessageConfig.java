package net.jambon.rillettes.tpsdisoliviercharrier.worker;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    @Bean Queue pubsub() {
        return new Queue("pubsub-olivier-charrier");
    }
}