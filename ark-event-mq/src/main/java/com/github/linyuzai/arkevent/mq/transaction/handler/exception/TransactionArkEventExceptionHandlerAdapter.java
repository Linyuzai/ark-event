package com.github.linyuzai.arkevent.mq.transaction.handler.exception;

import com.github.linyuzai.arkevent.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.transaction.ArkEventTransaction;
import com.github.linyuzai.arkevent.mq.transaction.ArkEventTransactionSubscriber;

public class TransactionArkEventExceptionHandlerAdapter implements ArkEventExceptionHandler.Adapter {

    private TransactionArkEventExceptionHandler handler = new TransactionArkEventExceptionHandler();

    @Override
    public ArkEventExceptionHandler adapt(ArkEventSubscriber subscriber) {
        if (subscriber instanceof ArkEventTransactionSubscriber ||
                subscriber.getClass().isAnnotationPresent(ArkEventTransaction.class)) {
            return handler;
        }
        return null;
    }

    @Override
    public int order() {
        return EXCEPTION_HANDLER_ADAPTER_TRANSACTION;
    }
}
