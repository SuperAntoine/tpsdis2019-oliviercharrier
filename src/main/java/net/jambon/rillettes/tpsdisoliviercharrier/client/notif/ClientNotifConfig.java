package net.jambon.rillettes.tpsdisoliviercharrier.client.notif;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientNotifConfig {

    @Bean
    public Queue pubsubNotif() {
        return new Queue("fanout-pubsub-notif-olivier-charrier");
    }

    @Bean
    public ClientNotif clientNotif() {
        return new ClientNotif();
    }
}