package com.github.linyuzai.arkevent.mq.transaction.handler.exception;

import com.github.linyuzai.arkevent.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.exception.ArkEventException;

public class TransactionArkEventExceptionHandler implements ArkEventExceptionHandler {

    @Override
    public void handle(ArkEventException ex) {
        throw ex;
    }
}
