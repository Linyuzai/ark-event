package com.github.linyuzai.arkevent.basic.impl.handler.exception.rethrow;

import com.github.linyuzai.arkevent.basic.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.basic.ArkEventSubscriber;

public class RethrowArkEventExceptionHandlerAdapter implements ArkEventExceptionHandler.Adapter {

    private RethrowArkEventExceptionHandler handler = new RethrowArkEventExceptionHandler();

    @Override
    public ArkEventExceptionHandler adapt(ArkEventSubscriber subscriber) {
        if (subscriber instanceof RethrowEventExceptionHandler ||
                subscriber.getClass().isAnnotationPresent(RethrowEventException.class)) {
            return handler;
        }
        return null;
    }

    @Override
    public int order() {
        return EXCEPTION_HANDLER_ADAPTER_RETHROW;
    }
}