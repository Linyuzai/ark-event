package com.github.linyuzai.arkevent.mq.impl;

import com.github.linyuzai.arkevent.ArkEvent;
import com.github.linyuzai.arkevent.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.ArkMqEventEncoder;
import com.github.linyuzai.arkevent.mq.ArkMqEventSender;
import com.github.linyuzai.arkevent.mq.ArkMqEventSubscriberMask;

public class ArkMqEventSubscriberImpl implements ArkEventSubscriber, ArkMqEventSubscriberMask {

    private ArkMqEventEncoder encoder;

    private ArkMqEventSender sender;

    public ArkMqEventSubscriberImpl(ArkMqEventEncoder encoder, ArkMqEventSender sender) {
        this.encoder = encoder;
        this.sender = sender;
    }

    @Override
    public void onSubscribe(ArkEvent arkEvent, Object... args) throws Throwable {
        sender.send(encoder.encode(arkEvent));
    }
}
