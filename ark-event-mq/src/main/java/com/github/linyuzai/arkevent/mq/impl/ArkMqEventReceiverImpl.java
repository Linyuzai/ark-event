package com.github.linyuzai.arkevent.mq.impl;

import com.github.linyuzai.arkevent.mq.ArkMqEventDecoder;
import com.github.linyuzai.arkevent.mq.ArkMqEventReceiver;
import com.github.linyuzai.arkevent.mq.support.filter.condition.ArkEventFromMq;

public class ArkMqEventReceiverImpl implements ArkMqEventReceiver {

    private ArkMqEventDecoder decoder;

    public ArkMqEventReceiverImpl(ArkMqEventDecoder decoder) {
        this.decoder = decoder;
    }

    @Override
    public void receive(String event) throws Throwable {
        decoder.decode(event).publish(ArkEventFromMq.mask());
    }
}
