package com.github.linyuzai.arkevent.mq.impl;

import com.github.linyuzai.arkevent.ArkEvent;
import com.github.linyuzai.arkevent.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.ArkMqEventEncoder;
import com.github.linyuzai.arkevent.mq.ArkMqEventSender;
import com.github.linyuzai.arkevent.mq.ArkMqEventSubscriber;

public class DefaultArkMqEventSubscriber implements ArkEventSubscriber, ArkMqEventSubscriber {

    private ArkMqEventEncoder encoder;

    private ArkMqEventSender sender;

    public DefaultArkMqEventSubscriber(ArkMqEventEncoder encoder, ArkMqEventSender sender) {
        this.encoder = encoder;
        this.sender = sender;
    }

    @Override
    public void onSubscribe(ArkEvent arkEvent, Object... args) throws Throwable {
        sender.send(encoder.encode(arkEvent));
    }
}
