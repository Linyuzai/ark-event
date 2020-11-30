package com.github.linyuzai.arkevent.transaction.sorter.publish;

import com.github.linyuzai.arkevent.basic.ArkEvent;
import com.github.linyuzai.arkevent.basic.ArkEventPublishSorter;
import com.github.linyuzai.arkevent.basic.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.basic.ArkEventSubscriber;
import com.github.linyuzai.arkevent.transaction.ArkEventTransaction;

public class TransactionArkEventPublishSorter implements ArkEventPublishSorter {

    @Override
    public boolean highOrder(ArkEventPublishStrategy strategy, ArkEventSubscriber subscriber, ArkEvent event, Object... args) {
        return subscriber.getClass().isAnnotationPresent(ArkEventTransaction.class);
    }

    @Override
    public int order() {
        return PUBLISH_SORTER_TRANSACTION;
    }
}
