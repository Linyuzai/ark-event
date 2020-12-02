package com.github.linyuzai.arkevent.core.impl.handler.exception.rethrow;

import com.github.linyuzai.arkevent.core.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;

public class RethrowArkEventExceptionHandlerAdapter implements ArkEventExceptionHandler.Adapter {

    private RethrowArkEventExceptionHandler exceptionHandler;

    public RethrowArkEventExceptionHandlerAdapter() {
        this(new RethrowArkEventExceptionHandler());
    }

    public RethrowArkEventExceptionHandlerAdapter(RethrowArkEventExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    public RethrowArkEventExceptionHandler getExceptionHandler() {
        return exceptionHandler;
    }

    public void setExceptionHandler(RethrowArkEventExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public ArkEventExceptionHandler adapt(ArkEventSubscriber subscriber) {
        if (subscriber instanceof RethrowEventExceptionHandler ||
                subscriber.getClass().isAnnotationPresent(RethrowEventException.class)) {
            return exceptionHandler;
        }
        return null;
    }

    @Override
    public int order() {
        return EXCEPTION_HANDLER_ADAPTER_RETHROW;
    }
}
