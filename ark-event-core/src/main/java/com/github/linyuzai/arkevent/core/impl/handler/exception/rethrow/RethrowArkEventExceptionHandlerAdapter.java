package com.github.linyuzai.arkevent.core.impl.handler.exception.rethrow;

import com.github.linyuzai.arkevent.core.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;

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
