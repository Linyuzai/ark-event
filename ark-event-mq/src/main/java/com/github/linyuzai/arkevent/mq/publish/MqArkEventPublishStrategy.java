package com.github.linyuzai.arkevent.mq.publish;

import com.github.linyuzai.arkevent.ArkEvent;
import com.github.linyuzai.arkevent.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.ArkEventSubscriber;
import com.github.linyuzai.arkevent.exception.ArkEventException;

public class MqArkEventPublishStrategy implements ArkEventPublishStrategy {

    @Override
    public void execute(ArkEventSubscriber subscriber, ArkEventExceptionHandler handler, ArkEvent event, Object... args) {
        try {
            subscriber.onSubscribe(event, args);
        } catch (Throwable e) {
            handler.handle(new ArkEventException(e, subscriber, this, event, args));
        }
    }
}
