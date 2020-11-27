package com.github.linyuzai.arkevent.transaction.strategy.publish;

import com.github.linyuzai.arkevent.basic.ArkEvent;
import com.github.linyuzai.arkevent.basic.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.basic.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.basic.ArkEventSubscriber;
import com.github.linyuzai.arkevent.ArkEventException;
import com.github.linyuzai.arkevent.remote.ArkEventFromRemote;
import com.github.linyuzai.arkevent.transaction.ArkEventTransactionManager;

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
            if (ArkEventFromRemote.isRemote(args)) {
                handler.handle(aee);
            } else {
                if (transactionManager.isInTransaction()) {
                    throw aee;
                } else {
                    handler.handle(aee);
                }
            }
        }
    }
}
