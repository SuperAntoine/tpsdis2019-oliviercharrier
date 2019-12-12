package net.jambon.rillettes.tpsdisoliviercharrier.client;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    public Queue pubsubNotif() {
        return new Queue("pubsub-notif-olivier-charrier");
    }

    @Bean
    public Queue pubsubMessage() {
        return new Queue("pubsub-message-olivier-charrier");
    }
}