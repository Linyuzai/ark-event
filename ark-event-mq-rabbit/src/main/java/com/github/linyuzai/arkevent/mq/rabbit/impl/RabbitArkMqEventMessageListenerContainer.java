package com.github.linyuzai.arkevent.mq.rabbit.impl;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

public class RabbitArkMqEventMessageListenerContainer extends SimpleMessageListenerContainer {

    public RabbitArkMqEventMessageListenerContainer() {
    }

    public RabbitArkMqEventMessageListenerContainer(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }
}
