package com.github.linyuzai.arkevent.mq.rabbit.impl;

import com.github.linyuzai.arkevent.core.ArkEvent;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.*;
import com.github.linyuzai.arkevent.mq.rabbit.RabbitArkMqEventRoutingKeyProvider;
import com.github.linyuzai.arkevent.mq.rabbit.RabbitArkMqEventTopicExchange;
import com.github.linyuzai.arkevent.transaction.manager.ArkEventTransactionManager;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitArkMqEventSubscriber implements ArkEventSubscriber, ArkMqEventSubscriber {

    private RabbitTemplate template;

    private RabbitArkMqEventTopicExchange exchange;

    private RabbitArkMqEventRoutingKeyProvider routingKeyProvider;

    private ArkEventTransactionManager transactionManager;

    private ArkMqEventEncoder encoder;

    public RabbitTemplate getTemplate() {
        return template;
    }

    public void setTemplate(RabbitTemplate template) {
        this.template = template;
    }

    public RabbitArkMqEventTopicExchange getExchange() {
        return exchange;
    }

    public void setExchange(RabbitArkMqEventTopicExchange exchange) {
        this.exchange = exchange;
    }

    public RabbitArkMqEventRoutingKeyProvider getRoutingKeyProvider() {
        return routingKeyProvider;
    }

    public void setRoutingKeyProvider(RabbitArkMqEventRoutingKeyProvider routingKeyProvider) {
        this.routingKeyProvider = routingKeyProvider;
    }

    public ArkEventTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(ArkEventTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public ArkMqEventEncoder getEncoder() {
        return encoder;
    }

    public void setEncoder(ArkMqEventEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public void onSubscribe(ArkEvent arkEvent, Object... args) throws Throwable {
        Object encode = encoder.encode(arkEvent);
        if (transactionManager.isInTransaction()) {
            template.convertSendAndReceive(exchange.getName(), routingKeyProvider.getRoutingKey(), encode);
        } else {
            template.convertAndSend(exchange.getName(), routingKeyProvider.getRoutingKey(), encode);
        }
    }
}
