package com.github.linyuzai.arkevent.autoconfigure.mq;

import com.github.linyuzai.arkevent.ArkEventModuleIdProvider;
import com.github.linyuzai.arkevent.ArkEventModulesProvider;
import com.github.linyuzai.arkevent.autoconfigure.mq.decoder.JacksonMqEventDecoder;
import com.github.linyuzai.arkevent.autoconfigure.mq.encoder.JacksonMqEventEncoder;
import com.github.linyuzai.arkevent.mq.ArkMqEventDecoder;
import com.github.linyuzai.arkevent.mq.ArkMqEventEncoder;
import com.github.linyuzai.arkevent.mq.ArkMqEventReceiver;
import com.github.linyuzai.arkevent.mq.ArkMqEventSender;
import com.github.linyuzai.arkevent.mq.support.filter.condition.type.ArkMqEventConditionFilterFactory;
import com.github.linyuzai.arkevent.mq.impl.ArkMqEventReceiverImpl;
import com.github.linyuzai.arkevent.mq.impl.ArkMqEventSubscriberImpl;
import com.github.linyuzai.arkevent.mq.support.strategy.publish.MqArkPublishStrategyAdapter;
import com.github.linyuzai.arkevent.mq.rabbit.*;
import com.github.linyuzai.arkevent.mq.rabbit.impl.ApplicationNameArkEventModuleIdProvider;
import com.github.linyuzai.arkevent.mq.rabbit.impl.DefaultRabbitArkMqEventRoutingKeyProvider;
import com.github.linyuzai.arkevent.mq.transaction.ArkMqEventTransaction;
import com.github.linyuzai.arkevent.mq.rabbit.transaction.RabbitArkMqEventTransactionMessageListenerContainer;
import com.github.linyuzai.arkevent.support.sorter.publish.remote.RemoteArkEventPublishSorter;
import com.github.linyuzai.arkevent.support.filter.condition.remote.RemoteArkEventConditionFilterFactory;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnClass(name = "com.github.linyuzai.arkevent.mq.ArkMqEventMask")
public class ArkMqEventAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(JacksonMqEventEncoder.class)
    public JacksonMqEventEncoder jacksonMqEventEncoder() {
        return new JacksonMqEventEncoder();
    }

    @Bean
    @ConditionalOnMissingBean(JacksonMqEventDecoder.class)
    public JacksonMqEventDecoder jacksonMqEventDecoder() {
        return new JacksonMqEventDecoder();
    }

    @Bean
    public ArkMqEventConditionFilterFactory arkMqEventConditionFilterFactory() {
        return new ArkMqEventConditionFilterFactory();
    }

    @Bean
    public RemoteArkEventConditionFilterFactory remoteArkEventConditionFilterFactory() {
        return new RemoteArkEventConditionFilterFactory();
    }

    @Bean
    public MqArkPublishStrategyAdapter mqArkPublishStrategyAdapter() {
        return new MqArkPublishStrategyAdapter();
    }

    @Bean
    public RemoteArkEventPublishSorter mqArkEventPublishSorter() {
        return new RemoteArkEventPublishSorter();
    }


    @Bean
    public ArkMqEventReceiverImpl arkMqEventReceiver(ArkMqEventDecoder decoder) {
        return new ArkMqEventReceiverImpl(decoder);
    }

    @Configuration
    @AutoConfigureAfter(RabbitArkMqEventAutoConfiguration.class)
    public static class FinalAutoConfiguration {

        @Bean
        public ArkMqEventSubscriberImpl arkMqEventSubscriber(ArkMqEventEncoder encoder, ArkMqEventSender sender) {
            return new ArkMqEventSubscriberImpl(encoder, sender);
        }
    }

    @Configuration
    @AutoConfigureAfter(ArkMqEventAutoConfiguration.class)
    @ConditionalOnClass(name = "com.github.linyuzai.arkevent.mq.rabbit.RabbitArkMqEventMask")
    public static class RabbitArkMqEventAutoConfiguration {

        @Value("${ark-event.mq.queue-prefix:Queue@ArkEvent.}")
        private String queuePrefix;

        /*@Bean
        @ConditionalOnMissingBean(ConnectionFactory.class)
        @ConfigurationProperties(prefix = "ark-event.mq")
        public ConnectionFactory rabbitArkMqEventConnectionFactory() {
            return new CachingConnectionFactory();
        }*/

        @Bean
        @ConditionalOnMissingBean(RabbitArkMqEventTopicExchange.class)
        public RabbitArkMqEventTopicExchange rabbitArkMqEventTopicExchange() {
            return new RabbitArkMqEventTopicExchange("Exchange@ArkEvent.Topic");
        }

        @Bean
        @ConditionalOnMissingBean(ArkEventModuleIdProvider.class)
        public ApplicationNameArkEventModuleIdProvider applicationNameArkEventModuleIdProvider() {
            return new ApplicationNameArkEventModuleIdProvider();
        }

        @Bean
        @ConditionalOnMissingBean(RabbitArkMqEventQueue.class)
        public RabbitArkMqEventQueue rabbitArkMqEventQueue(ArkEventModuleIdProvider idProvider) {
            return new RabbitArkMqEventQueue(queuePrefix + idProvider.getModuleId().toUpperCase());
        }

        /*@Bean
        @ConditionalOnMissingBean(RabbitTemplate.class)
        public RabbitTemplate arkMqEventRabbitTemplate(ConnectionFactory connectionFactory) {
            return new RabbitTemplate(connectionFactory);
        }*/

        @Bean
        @ConditionalOnMissingBean(ArkMqEventSender.class)
        public RabbitArkMqEventSender rabbitArkMqEventSender(RabbitTemplate template, RabbitArkMqEventTopicExchange exchange,
                                                             RabbitArkMqEventRoutingKeyProvider routingKeyProvider) {
            return new RabbitArkMqEventSender(template, exchange, routingKeyProvider);
        }

        @Bean
        @ConditionalOnMissingBean(RabbitArkMqEventMessageListener.class)
        public RabbitArkMqEventMessageListener rabbitArkMqEventMessageListener(ArkMqEventReceiver receiver) {
            return new RabbitArkMqEventMessageListener(receiver);
        }

        @Bean
        @ConditionalOnMissingBean(ArkMqEventTransaction.class)
        public RabbitArkMqEventMessageListenerContainer rabbitArkEventMessageListenerContainer(
                RabbitArkMqEventQueue rabbitArkMqEventQueue,
                RabbitArkMqEventMessageListener listener,
                ConnectionFactory connectionFactory) {

            RabbitArkMqEventMessageListenerContainer container = new RabbitArkMqEventMessageListenerContainer();
            initRabbitMessageListenerContainer(rabbitArkMqEventQueue,
                    listener, connectionFactory, container);
            return container;
        }

        @Bean
        @ConditionalOnBean(ArkMqEventTransaction.class)
        public RabbitTransactionManager arkMqEventRabbitTransactionManager(ConnectionFactory connectionFactory) {
            return new RabbitTransactionManager(connectionFactory);
        }

        @Bean
        @ConditionalOnBean(ArkMqEventTransaction.class)
        public RabbitArkMqEventTransactionMessageListenerContainer rabbitArkMqEventTransactionMessageListenerContainer(
                RabbitArkMqEventQueue rabbitArkMqEventQueue,
                RabbitArkMqEventMessageListener listener,
                ConnectionFactory connectionFactory,
                RabbitTransactionManager transactionManager) {
            RabbitArkMqEventTransactionMessageListenerContainer container = new RabbitArkMqEventTransactionMessageListenerContainer();
            initRabbitMessageListenerContainer(rabbitArkMqEventQueue,
                    listener, connectionFactory, container);
            container.setChannelTransacted(true);
            container.setTransactionManager(transactionManager);
            return container;
        }

        @Bean
        @ConditionalOnMissingBean(RabbitAdmin.class)
        public RabbitAdmin arkEventRabbitAdmin(ConnectionFactory connectionFactory) {
            return new RabbitAdmin(connectionFactory);
        }

        @Bean
        @ConditionalOnMissingBean(RabbitArkMqEventRoutingKeyProvider.class)
        public RabbitArkMqEventRoutingKeyProvider rabbitArkMqEventRoutingKeyProvider(
                ArkEventModuleIdProvider idProvider, ArkEventModulesProvider moduleProvider) {
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

        private void initRabbitMessageListenerContainer(RabbitArkMqEventQueue rabbitArkMqEventQueue,
                                                        RabbitArkMqEventMessageListener listener,
                                                        ConnectionFactory connectionFactory,
                                                        SimpleMessageListenerContainer container) {
            container.setConnectionFactory(connectionFactory);
            container.setQueueNames(rabbitArkMqEventQueue.getName());
            container.setMessageListener(listener);
        }
    }
}
