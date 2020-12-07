package com.github.linyuzai.arkevent.mq.rabbit.impl;

import com.github.linyuzai.arkevent.core.ArkEvent;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.*;
import com.github.linyuzai.arkevent.mq.rabbit.RabbitArkMqEventMessagePostProcessor;
import com.github.linyuzai.arkevent.mq.rabbit.RabbitArkMqEventRoutingKeyProvider;
import com.github.linyuzai.arkevent.mq.rabbit.RabbitArkMqEventTopicExchange;
import com.github.linyuzai.arkevent.support.ArkEventPlugin;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;
import java.util.Map;

public class RabbitArkMqEventSubscriber implements ArkEventSubscriber, ArkMqEventSubscriber {

    private RabbitTemplate template;

    private RabbitArkMqEventTopicExchange exchange;

    private RabbitArkMqEventRoutingKeyProvider routingKeyProvider;

    private ArkMqEventEncoder encoder;

    private List<RabbitArkMqEventMessagePostProcessor> messagePostProcessors;

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

    public ArkMqEventEncoder getEncoder() {
        return encoder;
    }

    public void setEncoder(ArkMqEventEncoder encoder) {
        this.encoder = encoder;
    }

    public List<RabbitArkMqEventMessagePostProcessor> getMessagePostProcessors() {
        return messagePostProcessors;
    }

    public void setMessagePostProcessors(List<RabbitArkMqEventMessagePostProcessor> messagePostProcessors) {
        this.messagePostProcessors = messagePostProcessors;
    }

    @Override
    public void onSubscribe(ArkEvent event, Map<Object, Object> args) throws Throwable {
        final MessagePostProcessor mpp = message -> {
            if (messagePostProcessors != null) {
                for (RabbitArkMqEventMessagePostProcessor p : messagePostProcessors) {
                    p.postMessage(message, event, args);
                }
            }
            return message;
        };
        if (ArkEventPlugin.isMqTransaction(args)) {
            template.convertSendAndReceive(exchange.getName(), routingKeyProvider.getRoutingKey(),
                    encoder.encode(event), mpp);
        } else {
            template.convertAndSend(exchange.getName(), routingKeyProvider.getRoutingKey(),
                    encoder.encode(event), mpp);
        }
    }
}
