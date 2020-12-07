package com.github.linyuzai.arkevent.mq.rabbit;

import com.github.linyuzai.arkevent.core.ArkEvent;
import org.springframework.amqp.core.Message;

import java.util.Map;

public interface RabbitArkMqEventMessagePostProcessor {

    void postMessage(Message message, ArkEvent event, Map<Object, Object> args);
}
