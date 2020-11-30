package com.github.linyuzai.arkevent.autoconfigure.mq;

import com.github.linyuzai.arkevent.mq.*;
import com.github.linyuzai.arkevent.mq.impl.handler.exception.ArkMqEventExceptionHandler;
import com.github.linyuzai.arkevent.mq.impl.handler.exception.ArkMqEventExceptionHandlerAdapter;
import com.github.linyuzai.arkevent.mq.impl.strategy.publish.ArkMqEventPublishStrategyAdapter;
import com.github.linyuzai.arkevent.remote.ArkEventModuleIdProvider;
import com.github.linyuzai.arkevent.remote.ArkEventModulesProvider;
import com.github.linyuzai.arkevent.autoconfigure.mq.decoder.JacksonMqEventDecoder;
import com.github.linyuzai.arkevent.autoconfigure.mq.encoder.JacksonMqEventEncoder;
import com.github.linyuzai.arkevent.mq.impl.filter.condition.ArkMqEventConditionFilterFactory;
import com.github.linyuzai.arkevent.mq.impl.ArkMqEventReceiverImpl;
import com.github.linyuzai.arkevent.mq.impl.ArkMqEventSubscriberImpl;
import com.github.linyuzai.arkevent.mq.rabbit.*;
import com.github.linyuzai.arkevent.mq.rabbit.impl.ApplicationNameArkEventModuleIdProvider;
import com.github.linyuzai.arkevent.mq.rabbit.impl.DefaultRabbitArkMqEventRoutingKeyProvider;
import com.github.linyuzai.arkevent.remote.impl.sorter.publish.RemoteArkEventPublishSorter;
import com.github.linyuzai.arkevent.remote.impl.filter.condition.RemoteArkEventConditionFilterFactory;
import com.github.linyuzai.arkevent.transaction.ArkEventTransactionManager;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@ConditionalOnClass(name = "com.github.linyuzai.arkevent.mq.ArkMqEventMask")
public class ArkMqEventAutoConfiguration {

    @Value("${ark-event.mq.queue-prefix:Queue@ArkEvent.}")
    private String queuePrefix;

    @Bean
    @ConditionalOnMissingBean(ArkMqEventEncoder.class)
    public JacksonMqEventEncoder jacksonMqEventEncoder() {
        return new JacksonMqEventEncoder();
    }

    @Bean
    @ConditionalOnMissingBean(ArkMqEventDecoder.class)
    public JacksonMqEventDecoder jacksonMqEventDecoder() {
        return new JacksonMqEventDecoder();
    }

    @Bean
    @ConditionalOnMissingBean(RemoteArkEventConditionFilterFactory.class)
    public RemoteArkEventConditionFilterFactory remoteArkEventConditionFilterFactory() {
        return new RemoteArkEventConditionFilterFactory();
    }

    @Bean
    @ConditionalOnMissingBean(RemoteArkEventPublishSorter.class)
    public RemoteArkEventPublishSorter remoteArkEventPublishSorter() {
        return new RemoteArkEventPublishSorter();
    }

    @Bean
    @ConditionalOnMissingBean(ArkMqEventConditionFilterFactory.class)
    public ArkMqEventConditionFilterFactory arkMqEventConditionFilterFactory() {
        return new ArkMqEventConditionFilterFactory();
    }

    @Bean
    @ConditionalOnMissingBean(ArkMqEventPublishStrategyAdapter.class)
    public ArkMqEventPublishStrategyAdapter arkMqEventPublishStrategyAdapter(ArkEventTransactionManager transactionManager) {
        return new ArkMqEventPublishStrategyAdapter(transactionManager);
    }

    @Bean
    @ConditionalOnMissingBean(ArkMqEventExceptionHandlerAdapter.class)
    public ArkMqEventExceptionHandlerAdapter arkMqEventExceptionHandlerAdapter() {
        return new ArkMqEventExceptionHandlerAdapter();
    }

    @Bean
    @ConditionalOnMissingBean(ArkMqEventReceiver.class)
    public ArkMqEventReceiverImpl arkMqEventReceiver(ArkMqEventDecoder decoder) {
        return new ArkMqEventReceiverImpl(decoder);
    }

    @Bean
    @ConditionalOnMissingBean(ArkMqEventSender.class)
    public RabbitArkMqEventSender rabbitArkMqEventSender(RabbitTemplate template, RabbitArkMqEventTopicExchange exchange,
                                                         RabbitArkMqEventRoutingKeyProvider routingKeyProvider) {
        return new RabbitArkMqEventSender(template, exchange, routingKeyProvider);
    }

    @Bean
    @ConditionalOnMissingBean(ArkMqEventTransactionSender.class)
    public RabbitArkMqEventTransactionSender rabbitArkMqEventTransactionSender(RabbitTemplate template, RabbitArkMqEventTopicExchange exchange,
                                                                               RabbitArkMqEventRoutingKeyProvider routingKeyProvider) {
        return new RabbitArkMqEventTransactionSender(template, exchange, routingKeyProvider);
    }

    @Bean
    @ConditionalOnMissingBean(ArkEventModuleIdProvider.class)
    public ApplicationNameArkEventModuleIdProvider applicationNameArkEventModuleIdProvider() {
        return new ApplicationNameArkEventModuleIdProvider();
    }

    @Bean
    @ConditionalOnMissingBean(ArkMqEventRemoter.class)
    public ArkMqEventSubscriberImpl arkMqEventSubscriber(ArkEventTransactionManager transactionManager,
                                                         ArkMqEventEncoder encoder,
                                                         ArkMqEventSender sender,
                                                         ArkMqEventTransactionSender transactionSender) {
        return new ArkMqEventSubscriberImpl(transactionManager, encoder, sender, transactionSender);
    }

    @Bean
    @ConditionalOnMissingBean(RabbitArkMqEventTopicExchange.class)
    public RabbitArkMqEventTopicExchange rabbitArkMqEventTopicExchange() {
        return new RabbitArkMqEventTopicExchange("Exchange@ArkEvent.Topic");
    }

    @Bean
    @ConditionalOnMissingBean(RabbitArkMqEventQueue.class)
    public RabbitArkMqEventQueue rabbitArkMqEventQueue(ArkEventModuleIdProvider idProvider) {
        return new RabbitArkMqEventQueue(queuePrefix + idProvider.getModuleId().toUpperCase());
    }

    @Bean
    @ConditionalOnMissingBean(RabbitArkMqEventMessageListener.class)
    public RabbitArkMqEventMessageListener rabbitArkMqEventMessageListener(ArkMqEventReceiver receiver) {
        return new RabbitArkMqEventMessageListener(receiver);
    }

    @Bean
    public SimpleMessageListenerContainer rabbitArkEventMessageListenerContainer(
            RabbitArkMqEventQueue rabbitArkMqEventQueue,
            RabbitArkMqEventMessageListener listener,
            ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(rabbitArkMqEventQueue.getName());
        container.setMessageListener(listener);
        return container;
    }

    @Bean
    @ConditionalOnMissingBean(RabbitArkMqEventRoutingKeyProvider.class)
    public RabbitArkMqEventRoutingKeyProvider rabbitArkMqEventRoutingKeyProvider(
            ArkEventModuleIdProvider idProvider, ArkEventModulesProvider moduleProvider) {
        return new DefaultRabbitArkMqEventRoutingKeyProvider(idProvider, moduleProvider);
    }

    @Bean
    @ConditionalOnMissingBean(RabbitAdmin.class)
    public RabbitAdmin rabbitAdmin(ConnectionFactory factory) {
        return new RabbitAdmin(factory);
    }

    @Bean
    public Object rabbitArkEventBinding(RabbitAdmin admin,
                                        RabbitArkMqEventTopicExchange exchange,
                                        RabbitArkMqEventQueue queue,
                                        RabbitArkMqEventRoutingKeyProvider routingKeyProvider) {
        admin.declareExchange(exchange);
        admin.declareQueue(queue);
        for (String routingKey : routingKeyProvider.getRoutingKeys()) {
            admin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(routingKey));
        }
        return new Object();
    }
}
