package com.github.linyuzai.arkevent.support.strategy.direct;

import com.github.linyuzai.arkevent.ArkEvent;
import com.github.linyuzai.arkevent.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.ArkEventSubscriber;
import com.github.linyuzai.arkevent.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.exception.ArkEventException;

public class DirectArkEventPublishStrategy implements ArkEventPublishStrategy {

    @Override
    public void publish(ArkEventSubscriber subscriber, ArkEvent event, ArkEventExceptionHandler handler) {
        try {
            subscriber.onSubscribe(event);
        } catch (Throwable e) {
            handler.handle(new ArkEventException(e, event, subscriber, this));
        }
    }
}
