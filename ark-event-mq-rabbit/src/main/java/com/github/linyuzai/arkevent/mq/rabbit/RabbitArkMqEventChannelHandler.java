package com.github.linyuzai.arkevent.mq.rabbit;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

public interface RabbitArkMqEventChannelHandler {

    default void onMessageReceived(Message message, Channel channel) throws Exception {

    }

    default void onMessageHandled(Message message, Channel channel) throws Exception {

    }

    default void onMessageExceptionThrown(Throwable e, Message message, Channel channel) throws Exception {

    }

    default void onMessageExceptionHandled(Throwable e, Message message, Channel channel) throws Exception {

    }
}
