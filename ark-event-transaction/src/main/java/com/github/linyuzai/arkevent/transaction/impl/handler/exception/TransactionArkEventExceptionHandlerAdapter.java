package com.github.linyuzai.arkevent.transaction.impl.handler.exception;

import com.github.linyuzai.arkevent.core.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;
import com.github.linyuzai.arkevent.transaction.EventTransaction;

public class TransactionArkEventExceptionHandlerAdapter implements ArkEventExceptionHandler.Adapter {

    private TransactionArkEventExceptionHandler handler;

    public TransactionArkEventExceptionHandlerAdapter(TransactionArkEventExceptionHandler handler) {
        this.handler = handler;
    }

    @Override
    public ArkEventExceptionHandler adapt(ArkEventSubscriber subscriber) {
        if (subscriber.getClass().isAnnotationPresent(EventTransaction.class)) {
            return handler;
        }
        return null;
    }

    @Override
    public int order() {
        return EXCEPTION_HANDLER_ADAPTER_TRANSACTION;
    }
}
