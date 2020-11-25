package com.github.linyuzai.arkevent.mq.rabbit.transaction;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

public class RabbitArkMqEventTransactionMessageListenerContainer extends SimpleMessageListenerContainer {

    public RabbitArkMqEventTransactionMessageListenerContainer() {
    }

    public RabbitArkMqEventTransactionMessageListenerContainer(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }
}
