package com.github.linyuzai.arkevent.transaction.handler.exception;

import com.github.linyuzai.arkevent.basic.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.basic.ArkEventSubscriber;
import com.github.linyuzai.arkevent.transaction.ArkEventTransaction;
import com.github.linyuzai.arkevent.transaction.ArkEventTransactionManager;
import com.github.linyuzai.arkevent.transaction.ArkEventTransactionSubscriber;

public class TransactionArkEventExceptionHandlerAdapter implements ArkEventExceptionHandler.Adapter {

    private TransactionArkEventExceptionHandler handler;

    public TransactionArkEventExceptionHandlerAdapter(ArkEventTransactionManager transactionManager) {
        this.handler = new TransactionArkEventExceptionHandler(transactionManager);
    }

    public TransactionArkEventExceptionHandlerAdapter(TransactionArkEventExceptionHandler handler) {
        this.handler = handler;
    }

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
