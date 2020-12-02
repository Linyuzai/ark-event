package com.github.linyuzai.arkevent.mq.rabbit;

import com.github.linyuzai.arkevent.core.ArkEvent;
import com.github.linyuzai.arkevent.mq.ArkMqEventEncoder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

public abstract class RabbitArkMqEventEncoder implements ArkMqEventEncoder {

    @Override
    public Object encode(ArkEvent event) throws Throwable {
        MessageProperties properties = new MessageProperties();
        properties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        configureProperties(properties);
        return new Message(encodeEvent(event), properties);
    }

    public abstract byte[] encodeEvent(ArkEvent event) throws Throwable;

    public void configureProperties(MessageProperties properties) {

    }
}
