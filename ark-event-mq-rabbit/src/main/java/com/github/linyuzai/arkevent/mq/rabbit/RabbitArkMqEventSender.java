package com.github.linyuzai.arkevent.mq.rabbit;

import com.github.linyuzai.arkevent.ArkEventException;
import com.github.linyuzai.arkevent.mq.ArkMqEventSender;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitArkMqEventSender implements ArkMqEventSender {

    private RabbitTemplate template;

    private RabbitArkMqEventTopicExchange exchange;

    private RabbitArkMqEventRoutingKeyProvider routingKeyProvider;

    public RabbitArkMqEventSender(RabbitTemplate template, RabbitArkMqEventTopicExchange exchange,
                                  RabbitArkMqEventRoutingKeyProvider routingKeyProvider) {
        if (template == null) {
            throw new ArkEventException("RabbitTemplate is null");
        }
        this.template = template;
        this.exchange = exchange;
        this.routingKeyProvider = routingKeyProvider;
    }

    @Override
    public void send(String event, Object... args) throws Throwable {
        template.convertSendAndReceive(exchange.getName(), routingKeyProvider.getRoutingKey(), event);
    }
}
