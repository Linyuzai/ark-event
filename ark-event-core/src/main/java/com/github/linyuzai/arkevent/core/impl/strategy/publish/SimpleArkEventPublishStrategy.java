package com.github.linyuzai.arkevent.core.impl.strategy.publish;

import com.github.linyuzai.arkevent.core.ArkEvent;
import com.github.linyuzai.arkevent.core.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;
import com.github.linyuzai.arkevent.core.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.core.ArkEventException;

public class SimpleArkEventPublishStrategy implements ArkEventPublishStrategy {

    @Override
    public void execute(ArkEventSubscriber subscriber, ArkEventExceptionHandler handler, ArkEvent event, Object... args) {
        try {
            subscriber.onSubscribe(event, args);
        } catch (Throwable e) {
            handler.handle(new ArkEventException(e, subscriber, this, event, args));
        }
    }
}
