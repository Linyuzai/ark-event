package com.github.linyuzai.arkevent.autoconfigure.mq;

import com.github.linyuzai.arkevent.autoconfigure.mq.decoder.JacksonMqEventDecoder;
import com.github.linyuzai.arkevent.autoconfigure.mq.encoder.JacksonMqEventEncoder;
import com.github.linyuzai.arkevent.mq.ArkMqEventDecoder;
import com.github.linyuzai.arkevent.mq.ArkMqEventEncoder;
import com.github.linyuzai.arkevent.mq.ArkMqEventReceiver;
import com.github.linyuzai.arkevent.mq.ArkMqEventSender;
import com.github.linyuzai.arkevent.mq.condition.exclude.ArkMqEventExcludeSelfConditionFilterFactory;
import com.github.linyuzai.arkevent.mq.condition.type.ArkMqEventConditionFilterFactory;
import com.github.linyuzai.arkevent.mq.impl.ArkMqEventReceiverImpl;
import com.github.linyuzai.arkevent.mq.impl.ArkMqEventSubscriberImpl;
import com.github.linyuzai.arkevent.mq.rabbit.RabbitArkMqEventMessageListenerContainer;
import com.github.linyuzai.arkevent.mq.rabbit.RabbitArkMqEventSender;
import com.github.linyuzai.arkevent.mq.rabbit.RabbitArkMqEventMessageListener;
import com.github.linyuzai.arkevent.mq.rabbit.transaction.ArkMqEventTransaction;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

public class ArkMqEventAutoConfiguration {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${ark-event.mq.queue-prefix:Queue@ArkEvent.}")
    private String queuePrefix;

    @Bean
    @ConditionalOnMissingBean(JacksonMqEventEncoder.class)
    @ConditionalOnClass(name = "com.github.linyuzai.arkevent.mq.condition.ArkMqEvent")
    public JacksonMqEventEncoder jacksonMqEventEncoder() {
        return new JacksonMqEventEncoder();
    }

    @Bean
    @ConditionalOnMissingBean(JacksonMqEventDecoder.class)
    @ConditionalOnClass(name = "com.github.linyuzai.arkevent.mq.condition.ArkMqEvent")
    public JacksonMqEventDecoder jacksonMqEventDecoder() {
        return new JacksonMqEventDecoder();
    }

    @Bean
    @ConditionalOnClass(name = "com.github.linyuzai.arkevent.mq.condition.ArkMqEvent")
    public ArkMqEventConditionFilterFactory arkMqEventConditionFilterFactory() {
        return new ArkMqEventConditionFilterFactory();
    }

    @Bean
    @ConditionalOnClass(name = "com.github.linyuzai.arkevent.mq.condition.ArkMqEvent")
    public ArkMqEventExcludeSelfConditionFilterFactory arkMqEventExcludeSelfConditionFilterFactory() {
        return new ArkMqEventExcludeSelfConditionFilterFactory();
    }

    @Bean
    @ConditionalOnClass(name = "com.github.linyuzai.arkevent.mq.condition.ArkMqEvent")
    public ArkMqEventSubscriberImpl arkMqEventSubscriber(ArkMqEventEncoder encoder, ArkMqEventSender sender) {
        return new ArkMqEventSubscriberImpl(encoder, sender);
    }

    @Bean
    @ConditionalOnClass(name = "com.github.linyuzai.arkevent.mq.condition.ArkMqEvent")
    public ArkMqEventReceiverImpl arkMqEventReceiver(ArkMqEventDecoder decoder) {
        return new ArkMqEventReceiverImpl(decoder);
    }

    @Bean
    @ConditionalOnMissingBean(ArkMqEventSender.class)
    @ConditionalOnClass(name = "com.github.linyuzai.arkevent.mq.rabbit.RabbitArkMqEventSender")
    public RabbitArkMqEventSender rabbitArkMqEventSender(RabbitTemplate rabbitTemplate) {
        return new RabbitArkMqEventSender(rabbitTemplate);
    }

    @Bean
    @ConditionalOnMissingBean(RabbitArkMqEventMessageListener.class)
    @ConditionalOnClass(name = "com.github.linyuzai.arkevent.mq.rabbit.RabbitArkMqEventMessageListener")
    public RabbitArkMqEventMessageListener rabbitArkEventMessageListener(ArkMqEventReceiver receiver) {
        return new RabbitArkMqEventMessageListener(receiver);
    }

    @Bean
    @ConditionalOnMissingBean(ArkMqEventTransaction.class)
    @ConditionalOnClass(name = "com.github.linyuzai.arkevent.mq.rabbit.RabbitArkMqEventMessageListener")
    public RabbitArkMqEventMessageListenerContainer rabbitArkEventMessageListenerContainer(
            RabbitArkMqEventMessageListener listener,
            ConnectionFactory connectionFactory) {
        /*Queue queue = enerMQ.admin().declareQueue();
        if (queue == null) {
            throw new RuntimeException("Declare queue failure");
        }*/

        /*Exchange exchange = eventBusExchange();
        enerMQ.admin().declareExchange(exchange);

        Binding binding = BindingBuilder.bind(eventBusQueue).to(exchange).with("event-bus.#").noargs();
        enerMQ.admin().declareBinding(binding);*/

        RabbitArkMqEventMessageListenerContainer container = new RabbitArkMqEventMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(getQueueName());
        container.setMessageListener(listener);
        return container;
    }

    @Bean
    @ConditionalOnBean(ArkMqEventTransaction.class)
    @ConditionalOnClass(name = "com.github.linyuzai.arkevent.mq.rabbit.RabbitArkMqEventMessageListener")
    public RabbitTransactionManager rabbitArkMqEventTransactionManager(ConnectionFactory connectionFactory) {
        return new RabbitTransactionManager(connectionFactory);
    }

    @Bean
    @ConditionalOnBean(ArkMqEventTransaction.class)
    @ConditionalOnClass(name = "com.github.linyuzai.arkevent.mq.rabbit.RabbitArkMqEventMessageListener")
    public RabbitArkMqEventMessageListenerContainer rabbitArkMqEventTransactionMessageListenerContainer(
            RabbitArkMqEventMessageListener listener,
            ConnectionFactory connectionFactory,
            RabbitTransactionManager transactionManager) {
        /*Queue queue = enerMQ.admin().declareQueue();
        if (queue == null) {
            throw new RuntimeException("Declare queue failure");
        }*/

        /*Exchange exchange = eventBusExchange();
        enerMQ.admin().declareExchange(exchange);

        Binding binding = BindingBuilder.bind(eventBusQueue).to(exchange).with("event-bus.#").noargs();
        enerMQ.admin().declareBinding(binding);*/

        RabbitArkMqEventMessageListenerContainer container = new RabbitArkMqEventMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(getQueueName());
        container.setMessageListener(listener);
        container.setChannelTransacted(true);
        container.setTransactionManager(transactionManager);
        return container;
    }

    private String getQueueName() {
        return queuePrefix + applicationName.toUpperCase();
    }
}
