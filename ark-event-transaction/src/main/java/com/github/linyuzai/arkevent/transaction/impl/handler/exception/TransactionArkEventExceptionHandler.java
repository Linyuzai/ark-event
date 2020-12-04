package com.github.linyuzai.arkevent.transaction.impl.handler.exception;

import com.github.linyuzai.arkevent.core.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.core.ArkEventException;
import com.github.linyuzai.arkevent.core.impl.handler.exception.logger.Slf4jArkEventExceptionHandler;
import com.github.linyuzai.arkevent.support.ArkEventPlugin;
import com.github.linyuzai.arkevent.transaction.manager.ArkEventTransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionArkEventExceptionHandler implements ArkEventExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(TransactionArkEventExceptionHandler.class);

    private ArkEventTransactionManager transactionManager;

    private ArkEventExceptionHandler loggerExceptionHandler;

    public TransactionArkEventExceptionHandler(ArkEventTransactionManager transactionManager) {
        this(transactionManager, new Slf4jArkEventExceptionHandler(log));
    }

    public TransactionArkEventExceptionHandler(ArkEventTransactionManager transactionManager,
                                               ArkEventExceptionHandler loggerExceptionHandler) {
        this.transactionManager = transactionManager;
        this.loggerExceptionHandler = loggerExceptionHandler;
    }

    public ArkEventTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(ArkEventTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public ArkEventExceptionHandler getLoggerExceptionHandler() {
        return loggerExceptionHandler;
    }

    public void setLoggerExceptionHandler(ArkEventExceptionHandler loggerExceptionHandler) {
        this.loggerExceptionHandler = loggerExceptionHandler;
    }

    @Override
    public void handle(ArkEventException ex) {
        if (ArkEventPlugin.isRemote(ex.getArgs())) {
            handleTransactionException(ex);
        } else {
            if (transactionManager.isInTransaction(ex.getEvent(), ex.getArgs())) {
                throw ex;
            } else {
                handleTransactionException(ex);
            }
        }
    }

    public void handleTransactionException(ArkEventException ex) {
        loggerExceptionHandler.handle(ex);
    }
}
