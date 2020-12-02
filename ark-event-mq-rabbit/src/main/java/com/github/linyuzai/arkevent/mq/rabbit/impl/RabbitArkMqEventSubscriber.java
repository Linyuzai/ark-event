package com.github.linyuzai.arkevent.mq.rabbit.impl;

import com.github.linyuzai.arkevent.core.ArkEvent;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.*;
import com.github.linyuzai.arkevent.mq.rabbit.RabbitArkMqEventRoutingKeyProvider;
import com.github.linyuzai.arkevent.mq.rabbit.RabbitArkMqEventTopicExchange;
import com.github.linyuzai.arkevent.transaction.manager.ArkEventTransactionManager;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitArkMqEventSubscriber implements ArkEventSubscriber, ArkMqEventSubscriber {

    private RabbitTemplate template;

    private RabbitArkMqEventTopicExchange exchange;

    private RabbitArkMqEventRoutingKeyProvider routingKeyProvider;

    private ArkEventTransactionManager transactionManager;

    private ArkMqEventIdempotentManager idempotentManager;

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

    public ArkMqEventIdempotentManager getIdempotentManager() {
        return idempotentManager;
    }

    public void setIdempotentManager(ArkMqEventIdempotentManager idempotentManager) {
        this.idempotentManager = idempotentManager;
    }

    public ArkMqEventEncoder getEncoder() {
        return encoder;
    }

    public void setEncoder(ArkMqEventEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public void onSubscribe(ArkEvent event, Object... args) throws Throwable {
        if (transactionManager.isInTransaction(event, args)) {
            CorrelationData correlationData = getCorrelationData(event, args);
            template.convertSendAndReceive(exchange.getName(), routingKeyProvider.getRoutingKey(),
                    encoder.encode(event), correlationData);
        } else {
            CorrelationData correlationData = getCorrelationData(event, args);
            template.convertAndSend(exchange.getName(), routingKeyProvider.getRoutingKey(),
                    encoder.encode(event), correlationData);
        }
    }

    private CorrelationData getCorrelationData(ArkEvent event, Object... args) {
        String eventId = idempotentManager.getEventId(event, args);
        return new CorrelationData(eventId);
    }
}
