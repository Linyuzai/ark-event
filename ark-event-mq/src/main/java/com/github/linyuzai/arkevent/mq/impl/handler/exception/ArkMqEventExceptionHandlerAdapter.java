package com.github.linyuzai.arkevent.mq.impl.handler.exception;

import com.github.linyuzai.arkevent.basic.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.basic.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.ArkMqEventRemoter;

public class ArkMqEventExceptionHandlerAdapter implements ArkEventExceptionHandler.Adapter {

    private ArkMqEventExceptionHandler handler;

    public ArkMqEventExceptionHandlerAdapter() {
        this(new ArkMqEventExceptionHandler());
    }

    public ArkMqEventExceptionHandlerAdapter(ArkEventExceptionHandler handler) {
        this(new ArkMqEventExceptionHandler(handler));
    }

    public ArkMqEventExceptionHandlerAdapter(ArkMqEventExceptionHandler handler) {
        this.handler = handler;
    }

    @Override
    public ArkEventExceptionHandler adapt(ArkEventSubscriber subscriber) {
        if (subscriber instanceof ArkMqEventRemoter) {
            return handler;
        }
        return null;
    }

    @Override
    public int order() {
        return EXCEPTION_HANDLER_ADAPTER_REMOTE;
    }
}
