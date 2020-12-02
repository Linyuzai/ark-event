package com.github.linyuzai.arkevent.mq.rabbit;

import com.github.linyuzai.arkevent.core.ArkEvent;
import com.github.linyuzai.arkevent.mq.ArkMqEventDecoder;
import com.github.linyuzai.arkevent.mq.ArkMqEventEncoder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

public abstract class RabbitArkMqEventDecoder implements ArkMqEventDecoder {

    @Override
    public ArkEvent decode(Object event) throws Throwable {
        return decodeEvent(((Message) event).getBody());
    }

    public abstract ArkEvent decodeEvent(byte[] bytes) throws Throwable;
}
