package com.github.linyuzai.arkevent.transaction.impl.handler.exception;

import com.github.linyuzai.arkevent.core.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.core.ArkEventException;
import com.github.linyuzai.arkevent.core.impl.handler.exception.logger.Slf4jArkEventExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TransactionArkEventExceptionHandler implements ArkEventExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(TransactionArkEventExceptionHandler.class);

    private ArkEventExceptionHandler loggerHandler;

    public TransactionArkEventExceptionHandler() {
        this(new Slf4jArkEventExceptionHandler(log));
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
