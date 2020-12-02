package com.github.linyuzai.arkevent.transaction.impl.handler.exception;

import com.github.linyuzai.arkevent.core.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;
import com.github.linyuzai.arkevent.transaction.EventTransaction;

public class TransactionArkEventExceptionHandlerAdapter implements ArkEventExceptionHandler.Adapter {

    private TransactionArkEventExceptionHandler exceptionHandler;

    public TransactionArkEventExceptionHandlerAdapter(TransactionArkEventExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    public TransactionArkEventExceptionHandler getExceptionHandler() {
        return exceptionHandler;
    }

    public void setExceptionHandler(TransactionArkEventExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public ArkEventExceptionHandler adapt(ArkEventSubscriber subscriber) {
        if (subscriber.getClass().isAnnotationPresent(EventTransaction.class)) {
            return exceptionHandler;
        }
        return null;
    }

    @Override
    public int order() {
        return EXCEPTION_HANDLER_ADAPTER_TRANSACTION;
    }
}
