package net.jambon.rillettes.tpsdisoliviercharrier.client.message;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientMessageConfig {

    @Bean
    public Queue pubsubNotif() {
        return new Queue("fanout-pubsub-message-olivier-charrier");
    }

    @Bean
    public ClientMessage clientNotif() {
        return new ClientMessage();
    }
}