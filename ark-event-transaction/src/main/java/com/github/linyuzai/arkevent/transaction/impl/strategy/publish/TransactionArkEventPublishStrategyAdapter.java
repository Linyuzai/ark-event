package com.github.linyuzai.arkevent.transaction.impl.strategy.publish;

import com.github.linyuzai.arkevent.core.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;
import com.github.linyuzai.arkevent.transaction.manager.ArkEventTransactionManager;
import com.github.linyuzai.arkevent.transaction.EventTransaction;

public class TransactionArkEventPublishStrategyAdapter implements ArkEventPublishStrategy.Adapter {

    private TransactionArkEventPublishStrategy transactionArkEventPublishStrategy;

    public TransactionArkEventPublishStrategyAdapter(ArkEventTransactionManager transactionManager) {
        this(new TransactionArkEventPublishStrategy(transactionManager));
    }

    public TransactionArkEventPublishStrategyAdapter(TransactionArkEventPublishStrategy transactionArkEventPublishStrategy) {
        this.transactionArkEventPublishStrategy = transactionArkEventPublishStrategy;
    }

    @Override
    public ArkEventPublishStrategy adapt(ArkEventSubscriber subscriber) {
        if (subscriber.getClass().isAnnotationPresent(EventTransaction.class)) {
            return transactionArkEventPublishStrategy;
        }
        return null;
    }

    @Override
    public int order() {
        return PUBLISH_STRATEGY_ADAPTER_TRANSACTION;
    }
}
