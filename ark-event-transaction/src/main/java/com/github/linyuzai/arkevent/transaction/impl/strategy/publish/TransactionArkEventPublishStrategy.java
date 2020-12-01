package com.github.linyuzai.arkevent.transaction.impl.strategy.publish;

import com.github.linyuzai.arkevent.support.ArkEventPlugin;
import com.github.linyuzai.arkevent.core.ArkEvent;
import com.github.linyuzai.arkevent.core.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.core.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;
import com.github.linyuzai.arkevent.core.ArkEventException;
import com.github.linyuzai.arkevent.transaction.manager.ArkEventTransactionManager;

public class TransactionArkEventPublishStrategy implements ArkEventPublishStrategy {

    private ArkEventTransactionManager transactionManager;

    public TransactionArkEventPublishStrategy(ArkEventTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public void execute(ArkEventSubscriber subscriber, ArkEventExceptionHandler handler, ArkEvent event, Object... args) {
        try {
            subscriber.onSubscribe(event, args);
        } catch (Throwable e) {
            ArkEventException aee = new ArkEventException(e, subscriber, this, event, args);
            if (ArkEventPlugin.isFromRemote(args)) {
                handler.handle(aee);
            } else {
                if (transactionManager.isInTransaction(event, args)) {
                    throw aee;
                } else {
                    handler.handle(aee);
                }
            }
        }
    }
}
