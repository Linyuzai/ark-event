package com.github.linyuzai.arkevent.mq.rabbit;

import com.github.linyuzai.arkevent.mq.ArkMqEventDecoder;
import org.springframework.amqp.core.Message;

public abstract class RabbitArkMqEventDecoder implements ArkMqEventDecoder {

    @Override
    public Object decode(Object event) throws Throwable {
        return decodeEvent(((Message) event).getBody());
    }

    public abstract Object decodeEvent(byte[] bytes) throws Throwable;
}
