package com.github.linyuzai.arkevent.mq.impl;

import com.github.linyuzai.arkevent.mq.ArkMqEventDecoder;
import com.github.linyuzai.arkevent.mq.ArkMqEventReceiver;
import com.github.linyuzai.arkevent.mq.condition.ArkEventFromMq;

public class DefaultArkMqEventReceiver implements ArkMqEventReceiver {

    private ArkMqEventDecoder decoder;

    public DefaultArkMqEventReceiver(ArkMqEventDecoder decoder) {
        this.decoder = decoder;
    }

    @Override
    public void receive(String event) {
        decoder.decode(event).publish(ArkEventFromMq.mask());
    }
}
