package com.github.linyuzai.arkevent.mq.impl;

import com.github.linyuzai.arkevent.mq.ArkMqEventDecoder;
import com.github.linyuzai.arkevent.mq.ArkMqEventReceiver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArkMqEventReceiverImpl implements ArkMqEventReceiver {

    private ArkMqEventDecoder decoder;

    public ArkMqEventReceiverImpl(ArkMqEventDecoder decoder) {
        this.decoder = decoder;
    }

    @Override
    public void receive(String event, Object... args) throws Throwable {
        decoder.decode(event).publish(getArgs(args));
    }

    private Object[] getArgs(Object... args) {
        List<Object> argList = new ArrayList<>(Arrays.asList(args));
        argList.add(ArkEventFromMq.args());
        return argList.toArray();
    }
}
