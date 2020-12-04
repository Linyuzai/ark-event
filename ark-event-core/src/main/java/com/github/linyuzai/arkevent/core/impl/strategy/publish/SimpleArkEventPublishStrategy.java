package com.github.linyuzai.arkevent.core.impl.strategy.publish;

import com.github.linyuzai.arkevent.core.ArkEvent;
import com.github.linyuzai.arkevent.core.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;
import com.github.linyuzai.arkevent.core.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.core.ArkEventException;

import java.util.Map;

public class SimpleArkEventPublishStrategy implements ArkEventPublishStrategy {

    @Override
    public void implement(ArkEventSubscriber subscriber, ArkEvent event, Map<Object, Object> args) throws Throwable {
        subscriber.onSubscribe(event, args);
    }
}
