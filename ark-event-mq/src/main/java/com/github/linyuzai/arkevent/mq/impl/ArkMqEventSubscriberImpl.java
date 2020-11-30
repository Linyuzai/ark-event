package com.github.linyuzai.arkevent.mq.impl;

import com.github.linyuzai.arkevent.basic.ArkEvent;
import com.github.linyuzai.arkevent.basic.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.ArkMqEventEncoder;
import com.github.linyuzai.arkevent.mq.ArkMqEventSender;
import com.github.linyuzai.arkevent.mq.ArkMqEventRemoter;
import com.github.linyuzai.arkevent.mq.ArkMqEventTransactionSender;
import com.github.linyuzai.arkevent.transaction.ArkEventTransactionManager;

public class ArkMqEventSubscriberImpl implements ArkEventSubscriber, ArkMqEventRemoter {

    private ArkEventTransactionManager transactionManager;

    private ArkMqEventEncoder encoder;

    private ArkMqEventSender sender;

    private ArkMqEventTransactionSender transactionSender;

    public ArkMqEventSubscriberImpl(ArkEventTransactionManager transactionManager, ArkMqEventEncoder encoder,
                                    ArkMqEventSender sender, ArkMqEventTransactionSender transactionSender) {
        this.transactionManager = transactionManager;
        this.encoder = encoder;
        this.sender = sender;
        this.transactionSender = transactionSender;
    }

    @Override
    public void onSubscribe(ArkEvent arkEvent, Object... args) throws Throwable {
        String encode = encoder.encode(arkEvent);
        if (transactionManager.isInTransaction()) {
            transactionSender.send(encode);
        } else {
            sender.send(encode);
        }
    }
}
