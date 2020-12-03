package com.github.linyuzai.arkevent.mq.impl.strategy.publish;

import com.github.linyuzai.arkevent.core.ArkEventException;
import com.github.linyuzai.arkevent.core.ArkEvent;
import com.github.linyuzai.arkevent.core.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.core.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.impl.filter.condition.ArkMqEvent;
import com.github.linyuzai.arkevent.mq.impl.filter.condition.MqEvent;
import com.github.linyuzai.arkevent.support.ArkEventPlugin;
import com.github.linyuzai.arkevent.transaction.manager.ArkEventTransactionManager;

public class ArkMqEventPublishStrategy implements ArkEventPublishStrategy {

    private ArkEventTransactionManager transactionManager;

    public ArkMqEventPublishStrategy(ArkEventTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public void execute(ArkEventSubscriber subscriber, ArkEventExceptionHandler handler, ArkEvent event, Object... args) {
        boolean transaction = ifTransaction(event) && transactionManager.isInTransaction(event, args);
        try {
            Object[] newArgs = transaction ? ArkEventPlugin.addArgs(args, ArkEventPlugin.mqTransactionArgs()) : args;
            subscriber.onSubscribe(event, newArgs);
        } catch (Throwable e) {
            ArkEventException aee = new ArkEventException(e, subscriber, this, event, args);
            if (transaction) {
                throw aee;
            } else {
                handler.handle(aee);
            }
        }
    }

    private boolean ifTransaction(ArkEvent event) {
        if (event instanceof ArkMqEvent) {
            return ((ArkMqEvent) event).transaction();
        } else {
            MqEvent mqEvent = event.getClass().getAnnotation(MqEvent.class);
            return mqEvent.transaction();
        }
    }
}
