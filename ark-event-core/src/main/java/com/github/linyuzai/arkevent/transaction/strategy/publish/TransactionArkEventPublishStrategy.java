package com.github.linyuzai.arkevent.transaction.strategy.publish;

import com.github.linyuzai.arkevent.basic.ArkEvent;
import com.github.linyuzai.arkevent.basic.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.basic.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.basic.ArkEventSubscriber;
import com.github.linyuzai.arkevent.ArkEventException;

public class TransactionArkEventPublishStrategy implements ArkEventPublishStrategy {

    @Override
    public void execute(ArkEventSubscriber subscriber, ArkEventExceptionHandler handler, ArkEvent event, Object... args) {
        try {
            subscriber.onSubscribe(event, args);
        } catch (Throwable e) {
            handler.handle(new ArkEventException(e, subscriber, this, event, args));
        }
    }
}
