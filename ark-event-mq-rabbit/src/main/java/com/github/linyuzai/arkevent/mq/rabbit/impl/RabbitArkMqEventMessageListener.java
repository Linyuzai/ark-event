package com.github.linyuzai.arkevent.mq.rabbit.impl;

import com.github.linyuzai.arkevent.mq.ArkMqEventDecoder;
import com.github.linyuzai.arkevent.support.ArkEventPlugin;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RabbitArkMqEventMessageListener implements MessageListener {

    private ArkMqEventDecoder decoder;

    public RabbitArkMqEventMessageListener(ArkMqEventDecoder decoder) {
        this.decoder = decoder;
    }

    @Override
    public void onMessage(Message message) {
        try {
            String txId = message.getMessageProperties().getCorrelationId();
            decoder.decode(new String(message.getBody())).publish(getArgs());
        } catch (Throwable e) {
            //throw new ArkEventException(e);
            e.printStackTrace();
        }
    }

    private Object[] getArgs(Object... args) {
        List<Object> argList = new ArrayList<>(Arrays.asList(args));
        argList.add(ArkEventPlugin.remoteArgs());
        return argList.toArray();
    }
}
