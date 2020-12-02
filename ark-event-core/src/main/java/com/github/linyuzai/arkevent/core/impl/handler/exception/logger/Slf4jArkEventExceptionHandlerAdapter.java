package com.github.linyuzai.arkevent.core.impl.handler.exception.logger;

import com.github.linyuzai.arkevent.core.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;

public class Slf4jArkEventExceptionHandlerAdapter implements ArkEventExceptionHandler.Adapter {

    private Slf4jArkEventExceptionHandler exceptionHandler;

    public Slf4jArkEventExceptionHandlerAdapter() {
        this(new Slf4jArkEventExceptionHandler());
    }

    public Slf4jArkEventExceptionHandlerAdapter(Slf4jArkEventExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    public Slf4jArkEventExceptionHandler getExceptionHandler() {
        return exceptionHandler;
    }

    public void setExceptionHandler(Slf4jArkEventExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public ArkEventExceptionHandler adapt(ArkEventSubscriber subscriber) {
        return exceptionHandler;
    }

    @Override
    public int order() {
        return EXCEPTION_HANDLER_ADAPTER_LOGGER;
    }
}
