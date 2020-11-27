package com.github.linyuzai.arkevent.mq.transaction.sorter.publish;

import com.github.linyuzai.arkevent.ArkEvent;
import com.github.linyuzai.arkevent.ArkEventPublishSorter;
import com.github.linyuzai.arkevent.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.transaction.ArkEventTransaction;
import com.github.linyuzai.arkevent.mq.transaction.ArkEventTransactionSubscriber;
import com.github.linyuzai.arkevent.support.strategy.publish.async.AsyncArkEventPublishStrategy;

public class TransactionArkEventPublishSorter implements ArkEventPublishSorter {

    @Override
    public boolean highOrder(ArkEventPublishStrategy strategy, ArkEventSubscriber subscriber, ArkEvent event, Object... args) {
        return subscriber instanceof ArkEventTransactionSubscriber ||
                subscriber.getClass().isAnnotationPresent(ArkEventTransaction.class);
    }

    @Override
    public int order() {
        return PUBLISH_SORTER_TRANSACTION;
    }
}
