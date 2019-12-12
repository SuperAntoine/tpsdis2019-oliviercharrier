package net.jambon.rillettes.tpsdisoliviercharrier.worker;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("pubsub-olivier-charrier");
    }

    @Bean
    public Binding bindingNotif(FanoutExchange fanout, Queue pubsubNotif) {
        return BindingBuilder.bind(pubsubNotif).to(fanout);
    }

    @Bean
    public Queue pubsubNotif() {
        return new Queue("pubsub-notif-olivier-charrier");
    }

    @Bean
    public Binding bindingMessage(FanoutExchange fanout, Queue pubsubMessage) {
        return BindingBuilder.bind(pubsubMessage).to(fanout);
    }

    @Bean
    public Queue pubsubMessage() {
        return new Queue("pubsub-message-olivier-charrier");
    }

}