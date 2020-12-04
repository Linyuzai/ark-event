package com.github.linyuzai.arkevent.transaction.impl.sorter.publish;

import com.github.linyuzai.arkevent.core.ArkEvent;
import com.github.linyuzai.arkevent.core.ArkEventPublishSorter;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;
import com.github.linyuzai.arkevent.transaction.EventTransaction;

import java.util.Map;

public class TransactionArkEventPublishSorter implements ArkEventPublishSorter {

    @Override
    public boolean highOrder(ArkEventSubscriber subscriber, ArkEvent event, Map args) {
        return subscriber.getClass().isAnnotationPresent(EventTransaction.class);
    }

    @Override
    public int order() {
        return PUBLISH_SORTER_TRANSACTION;
    }
}
