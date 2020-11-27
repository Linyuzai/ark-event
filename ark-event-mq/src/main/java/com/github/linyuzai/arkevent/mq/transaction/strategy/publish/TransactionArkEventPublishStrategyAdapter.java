package com.github.linyuzai.arkevent.mq.transaction.strategy.publish;

import com.github.linyuzai.arkevent.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.transaction.ArkEventTransaction;
import com.github.linyuzai.arkevent.mq.transaction.ArkEventTransactionSubscriber;

public class TransactionArkEventPublishStrategyAdapter implements ArkEventPublishStrategy.Adapter {

    private TransactionArkEventPublishStrategy transactionArkEventPublishStrategy;

    public TransactionArkEventPublishStrategyAdapter() {
        this(new TransactionArkEventPublishStrategy());
    }

    public TransactionArkEventPublishStrategyAdapter(TransactionArkEventPublishStrategy transactionArkEventPublishStrategy) {
        this.transactionArkEventPublishStrategy = transactionArkEventPublishStrategy;
    }

    @Override
    public ArkEventPublishStrategy adapt(ArkEventSubscriber subscriber) {
        if (subscriber instanceof ArkEventTransactionSubscriber ||
                subscriber.getClass().isAnnotationPresent(ArkEventTransaction.class)) {
            return transactionArkEventPublishStrategy;
        }
        return null;
    }

    @Override
    public int order() {
        return PUBLISH_STRATEGY_ADAPTER_TRANSACTION;
    }
}
