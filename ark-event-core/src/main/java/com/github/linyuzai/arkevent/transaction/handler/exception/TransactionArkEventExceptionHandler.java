package com.github.linyuzai.arkevent.transaction.handler.exception;

import com.github.linyuzai.arkevent.basic.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.ArkEventException;
import com.github.linyuzai.arkevent.transaction.ArkEventTransactionManager;
import com.github.linyuzai.arkevent.basic.impl.handler.exception.logger.Slf4jArkEventExceptionHandler;

public abstract class TransactionArkEventExceptionHandler implements ArkEventExceptionHandler {

    private ArkEventExceptionHandler loggerHandler;

    public TransactionArkEventExceptionHandler() {
        this(new Slf4jArkEventExceptionHandler());
    }

    public TransactionArkEventExceptionHandler(ArkEventExceptionHandler loggerHandler) {
        this.loggerHandler = loggerHandler;
    }

    @Override
    public void handle(ArkEventException ex) {
        try {
            handleTransactionException(ex);
        } catch (Throwable e) {
            loggerHandler.handle(ex);
        }
    }

    public abstract void handleTransactionException(ArkEventException ex) throws Throwable;
}
