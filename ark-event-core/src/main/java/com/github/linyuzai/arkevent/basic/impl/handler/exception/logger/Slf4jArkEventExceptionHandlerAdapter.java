package com.github.linyuzai.arkevent.basic.impl.handler.exception.logger;

import com.github.linyuzai.arkevent.basic.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.basic.ArkEventSubscriber;

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
