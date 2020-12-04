package com.github.linyuzai.arkevent.autoconfigure.configuration;

import com.github.linyuzai.arkevent.autoconfigure.decoder.JacksonMqEventDecoder;
import com.github.linyuzai.arkevent.autoconfigure.encoder.JacksonMqEventEncoder;
import com.github.linyuzai.arkevent.core.ArkEvent;
import com.github.linyuzai.arkevent.mq.*;
import com.github.linyuzai.arkevent.mq.impl.filter.condition.ArkMqEventConditionFilterFactory;
import com.github.linyuzai.arkevent.mq.impl.handler.exception.ArkMqEventExceptionHandler;
import com.github.linyuzai.arkevent.mq.impl.handler.exception.ArkMqEventExceptionHandlerAdapter;
import com.github.linyuzai.arkevent.mq.impl.manager.idempotent.AnnotationArkMqEventIdempotentManager;
import com.github.linyuzai.arkevent.mq.impl.sorter.publish.ArkMqEventPublishSorter;
import com.github.linyuzai.arkevent.mq.impl.strategy.publish.ArkMqEventPublishStrategyAdapter;
import com.github.linyuzai.arkevent.mq.properties.ArkMqEventProperties;
import com.github.linyuzai.arkevent.mq.rabbit.RabbitArkMqEventQueue;
import com.github.linyuzai.arkevent.mq.rabbit.RabbitArkMqEventRoutingKeyProvider;
import com.github.linyuzai.arkevent.mq.rabbit.RabbitArkMqEventTopicExchange;
import com.github.linyuzai.arkevent.mq.rabbit.impl.*;
import com.github.linyuzai.arkevent.transaction.manager.ArkEventTransactionManager;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

public class ArkMqEventAutoConfiguration {

    public static class TransactionAutoConfiguration {

        @Bean
        @ConditionalOnMissingBean(ArkEventTransactionManager.class)
        public ArkEventTransactionManager arkEventTransactionManager() {
            return ArkEventTransactionManager.NO_TRANSACTION;
        }
    }

    @Bean
    @ConditionalOnMissingBean(ArkMqEventProperties.class)
    @ConfigurationProperties(prefix = "ark-event.mq")
    public ArkMqEventProperties arkMqEventProperties() {
        return new ArkMqEventProperties();
    }

    @Bean
    @ConditionalOnMissingBean(RabbitArkMqEventTopicExchange.class)
    public RabbitArkMqEventTopicExchange rabbitArkMqEventTopicExchange(ArkMqEventProperties arkMqEventProperties) {
        return new RabbitArkMqEventTopicExchange(arkMqEventProperties.getExchangeName());
    }

    @Bean
    @ConditionalOnMissingBean(RabbitArkMqEventQueue.class)
    public RabbitArkMqEventQueue rabbitArkMqEventQueue(ArkMqEventProperties arkMqEventProperties,
                                                       ArkMqEventModuleIdProvider idProvider) {
        Map<String, Object> args = new HashMap<>();
        args.put("x-queue-type", "classic");
        return new RabbitArkMqEventQueue(arkMqEventProperties.getQueuePrefix() + idProvider.getModuleId().toUpperCase(),
                true, false, false, args);
    }

    @Bean
    @ConditionalOnMissingBean(RabbitArkMqEventRoutingKeyProvider.class)
    public RabbitArkMqEventRoutingKeyProvider rabbitArkMqEventRoutingKeyProvider(
            ArkMqEventModuleIdProvider idProvider, ArkMqEventModulesProvider moduleProvider) {
        return new DefaultRabbitArkMqEventRoutingKeyProvider(idProvider, moduleProvider);
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
    @ConditionalOnMissingBean(ArkMqEventPublishSorter.class)
    public ArkMqEventPublishSorter arkMqEventPublishSorter() {
        return new ArkMqEventPublishSorter();
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
    @ConditionalOnMissingBean(ArkMqEventExceptionHandler.class)
    public ArkMqEventExceptionHandler arkMqEventExceptionHandler() {
        return new ArkMqEventExceptionHandler();
    }

    @Bean
    @ConditionalOnMissingBean(ArkMqEventExceptionHandlerAdapter.class)
    public ArkMqEventExceptionHandlerAdapter arkMqEventExceptionHandlerAdapter(ArkMqEventExceptionHandler exceptionHandler) {
        return new ArkMqEventExceptionHandlerAdapter(exceptionHandler);
    }

    @Bean
    @ConditionalOnMissingBean(ArkMqEventModuleIdProvider.class)
    public ApplicationNameArkMqEventModuleIdProvider applicationNameArkMqEventModuleIdProvider() {
        return new ApplicationNameArkMqEventModuleIdProvider();
    }

    @Bean
    @ConditionalOnMissingBean(ArkMqEventSubscriber.class)
    public RabbitArkMqEventSubscriber arkMqEventSubscriber(
            RabbitTemplate template,
            RabbitArkMqEventTopicExchange exchange,
            RabbitArkMqEventRoutingKeyProvider routingKeyProvider,
            ArkMqEventIdempotentManager idempotentManager,
            ArkMqEventEncoder encoder) {
        RabbitArkMqEventSubscriber subscriber = new RabbitArkMqEventSubscriber();
        subscriber.setTemplate(template);
        subscriber.setExchange(exchange);
        subscriber.setRoutingKeyProvider(routingKeyProvider);
        subscriber.setIdempotentManager(idempotentManager);
        subscriber.setEncoder(encoder);
        return subscriber;
    }

    @Bean
    @ConditionalOnMissingBean(ArkMqEventIdempotentManager.class)
    public ArkMqEventIdempotentManager arkMqEventIdempotentManager() {
        return new AnnotationArkMqEventIdempotentManager() {

            @Override
            public String getIdempotentEventId(ArkEvent event, Map<Object, Object> args) {
                return EVENT_IDEMPOTENT_IGNORED;
            }

            @Override
            public boolean isIdempotentEventHandled(String eventId, ArkMqEventDecoder decoder, Object o) {
                return false;
            }

            @Override
            public void onEventRepeated(String eventId, ArkMqEventDecoder decoder, Object o) {

            }
        };
    }

    @Bean
    @ConditionalOnMissingBean(RabbitArkMqEventMessageListener.class)
    public RabbitArkMqEventMessageListener rabbitArkMqEventMessageListener(ArkMqEventIdempotentManager idempotentManager,
                                                                           ArkEventTransactionManager transactionManager,
                                                                           ArkMqEventDecoder decoder,
                                                                           ArkMqEventExceptionHandler exceptionHandler) {
        RabbitArkMqEventMessageListener listener = new RabbitArkMqEventMessageListener();
        listener.setTransactionManager(transactionManager);
        listener.setIdempotentManager(idempotentManager);
        listener.setDecoder(decoder);
        listener.setExceptionHandler(exceptionHandler);
        return listener;
    }

    @Bean
    @ConditionalOnMissingBean(RabbitArkMqEventMessageListenerContainer.class)
    public RabbitArkMqEventMessageListenerContainer rabbitArkMqEventMessageListenerContainer(
            RabbitArkMqEventQueue rabbitArkMqEventQueue,
            RabbitArkMqEventMessageListener listener,
            ConnectionFactory connectionFactory) {
        RabbitArkMqEventMessageListenerContainer container = new RabbitArkMqEventMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(rabbitArkMqEventQueue.getName());
        container.setMessageListener(listener);
        return container;
    }
}
