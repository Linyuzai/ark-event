package com.github.linyuzai.arkevent.autoconfigure.handler.exception.slf4j;

import com.github.linyuzai.arkevent.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.ArkEventSubscriber;
import com.github.linyuzai.arkevent.exception.ArkEventException;
import com.github.linyuzai.arkevent.support.strategy.publish.async.ArkEventPublishAsync;
import com.github.linyuzai.arkevent.support.strategy.publish.async.OnArkEventPublishAsync;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Slf4jArkEventExceptionHandlerAdapter implements ArkEventExceptionHandler.Adapter {

    private Slf4jArkEventExceptionHandler handler = new Slf4jArkEventExceptionHandler();

    @Override
    public ArkEventExceptionHandler adapt(ArkEventSubscriber subscriber) {
        if (subscriber instanceof ArkEventPublishAsync ||
                subscriber.getClass().isAnnotationPresent(OnArkEventPublishAsync.class)) {
            return handler;
        }
        return null;
    }

    @Override
    public int order() {
        return EXCEPTION_HANDLER_ADAPTER_LOGGER;
    }
}
