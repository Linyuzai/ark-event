package com.github.linyuzai.arkevent.mq.rabbit.impl;

import com.github.linyuzai.arkevent.core.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.*;
import com.github.linyuzai.arkevent.mq.rabbit.*;
import com.github.linyuzai.arkevent.support.ArkEventPlugin;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;
import java.util.Map;

public class RabbitArkMqEventSubscriber implements ArkEventSubscriber, ArkMqEventSubscriber {

    private RabbitTemplate template;

    private RabbitTemplate publisherConfirmsTemplate;

    private RabbitArkMqEventTopicExchange exchange;

    private RabbitArkMqEventRoutingKeyProvider routingKeyProvider;

    private ArkMqEventEncoder encoder;

    private List<RabbitArkMqEventMessagePostProcessor> messagePostProcessors;

    private long waitForConfirmsTimeout = 5000;

    public RabbitTemplate getTemplate() {
        return template;
    }

    public void setTemplate(RabbitTemplate template) {
        this.template = template;
    }

    public RabbitTemplate getPublisherConfirmsTemplate() {
        return publisherConfirmsTemplate;
    }

    public void setPublisherConfirmsTemplate(RabbitTemplate publisherConfirmsTemplate) {
        this.publisherConfirmsTemplate = publisherConfirmsTemplate;
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

    public long getWaitForConfirmsTimeout() {
        return waitForConfirmsTimeout;
    }

    public void setWaitForConfirmsTimeout(long waitForConfirmsTimeout) {
        this.waitForConfirmsTimeout = waitForConfirmsTimeout;
    }

    @Override
    public void onSubscribe(Object event, Map<Object, Object> args) throws Throwable {
        final MessagePostProcessor mpp = message -> {
            if (messagePostProcessors != null) {
                for (RabbitArkMqEventMessagePostProcessor p : messagePostProcessors) {
                    p.postMessage(message, event, args);
                }
            }
            return message;
        };
        if (ArkEventPlugin.isMqTransaction(args)) {
            sendInTransaction(exchange.getName(), routingKeyProvider.getRoutingKey(),
                    encoder.encode(event), mpp);
        } else {
            if (args.containsKey(RabbitPublisherConfirms.class.getName())) {
                sendInPublisherConfirms(exchange.getName(), routingKeyProvider.getRoutingKey(),
                        encoder.encode(event), mpp, waitForConfirmsTimeout);
            }
            if (args.containsKey(RabbitRPC.class.getName())) {
                sendInRPC(exchange.getName(), routingKeyProvider.getRoutingKey(),
                        encoder.encode(event), mpp);
            } else {
                template.convertAndSend(exchange.getName(), routingKeyProvider.getRoutingKey(),
                        encoder.encode(event), mpp);
            }
        }
    }

    public void sendInTransaction(String exchange, String routingKey, final Object message,
                                  final MessagePostProcessor messagePostProcessor) {
        sendInPublisherConfirms(exchange, routingKey, message, messagePostProcessor,
                waitForConfirmsTimeout);
    }

    public void sendInPublisherConfirms(String exchange, String routingKey, final Object message,
                                        final MessagePostProcessor messagePostProcessor,
                                        long waitForConfirmsTimeout) {
        Boolean aux = publisherConfirmsTemplate.invoke(operations -> {
            operations.convertAndSend(exchange, routingKey, message, messagePostProcessor);
            return operations.waitForConfirms(waitForConfirmsTimeout);
        });
        if (!Boolean.TRUE.equals(aux)) {
            throw new AmqpException("nacks received");
        }
    }

    public void sendInRPC(String exchange, String routingKey, final Object message,
                          final MessagePostProcessor messagePostProcessor) {
        template.convertSendAndReceive(exchange, routingKey, message, messagePostProcessor);
    }
}
