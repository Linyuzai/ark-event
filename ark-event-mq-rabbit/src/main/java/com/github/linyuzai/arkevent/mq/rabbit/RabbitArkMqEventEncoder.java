package com.github.linyuzai.arkevent.mq.rabbit;

import com.github.linyuzai.arkevent.mq.ArkMqEventEncoder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

public abstract class RabbitArkMqEventEncoder implements ArkMqEventEncoder {

    @Override
    public Object encode(Object event) throws Throwable {
        MessageProperties properties = new MessageProperties();
        properties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        configureProperties(properties);
        return new Message(encodeEvent(event), properties);
    }

    public abstract byte[] encodeEvent(Object event) throws Throwable;

    public void configureProperties(MessageProperties properties) {

    }
}
