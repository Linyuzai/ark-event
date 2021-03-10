package com.github.linyuzai.arkevent.core.impl.strategy.publish;

import com.github.linyuzai.arkevent.core.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;

import java.util.Map;

public class SimpleArkEventPublishStrategy implements ArkEventPublishStrategy {

    @Override
    public void apply(ArkEventSubscriber subscriber, Object event, Map<Object, Object> args) throws Throwable {
        subscriber.onSubscribe(event, args);
    }
}
