package com.github.linyuzai.arkevent.mq.impl.strategy.publish;

import com.github.linyuzai.arkevent.core.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.impl.filter.condition.ArkMqEvent;
import com.github.linyuzai.arkevent.mq.impl.filter.condition.MqEvent;
import com.github.linyuzai.arkevent.support.ArkEventPlugin;
import com.github.linyuzai.arkevent.transaction.manager.ArkEventTransactionManager;

import java.util.Map;

public class ArkMqEventPublishStrategy implements ArkEventPublishStrategy {

    private ArkEventTransactionManager transactionManager;

    public ArkMqEventPublishStrategy(ArkEventTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public void implement(ArkEventSubscriber subscriber, Object event, Map<Object, Object> args) throws Throwable {
        boolean transaction = ifTransaction(event) && transactionManager.isInTransaction(event, args);
        if (transaction) {
            args.put(ArkEventPlugin.ARGS_MQ_TRANSACTION_KEY, true);
        }
        subscriber.onSubscribe(event, args);
    }

    private boolean ifTransaction(Object event) {
        if (event instanceof ArkMqEvent) {
            return ((ArkMqEvent) event).transaction();
        } else {
            MqEvent mqEvent = event.getClass().getAnnotation(MqEvent.class);
            return mqEvent != null && mqEvent.transaction();
        }
    }
}
