package com.github.linyuzai.arkevent.transaction.handler.exception;

import com.github.linyuzai.arkevent.basic.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.ArkEventException;
import com.github.linyuzai.arkevent.transaction.ArkEventTransactionManager;
import com.github.linyuzai.arkevent.basic.impl.handler.exception.logger.Slf4jArkEventExceptionHandler;

public class TransactionArkEventExceptionHandler implements ArkEventExceptionHandler {

    private ArkEventTransactionManager transactionManager;

    private ArkEventExceptionHandler loggerHandler;

    public TransactionArkEventExceptionHandler(ArkEventTransactionManager transactionManager) {
        this(transactionManager, new Slf4jArkEventExceptionHandler());
    }

    public TransactionArkEventExceptionHandler(ArkEventTransactionManager transactionManager, ArkEventExceptionHandler loggerHandler) {
        this.transactionManager = transactionManager;
        this.loggerHandler = loggerHandler;
    }

    @Override
    public void handle(ArkEventException ex) {
        if (transactionManager.isInTransaction()) {
            throw ex;
        } else {
            loggerHandler.handle(ex);
        }
    }
}
