package com.github.linyuzai.arkevent.support.handler.exception.logger;

import com.github.linyuzai.arkevent.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.ArkEventSubscriber;

public class Slf4jArkEventExceptionHandlerAdapter implements ArkEventExceptionHandler.Adapter {

    private Slf4jArkEventExceptionHandler handler = new Slf4jArkEventExceptionHandler();

    @Override
    public ArkEventExceptionHandler adapt(ArkEventSubscriber subscriber) {
        return handler;
        //TransactionSynchronizationManager.isActualTransactionActive()
    }

    @Override
    public int order() {
        return EXCEPTION_HANDLER_ADAPTER_LOGGER;
    }
}
