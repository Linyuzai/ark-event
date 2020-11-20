package com.github.linyuzai.arkevent.support.strategy.async;

import com.github.linyuzai.arkevent.ArkEvent;
import com.github.linyuzai.arkevent.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.ArkEventSubscriber;
import com.github.linyuzai.arkevent.exception.ArkEventException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncArkEventPublishStrategy implements ArkEventPublishStrategy {

    private ExecutorService executorService;

    public AsyncArkEventPublishStrategy() {
        this(4);
    }

    public AsyncArkEventPublishStrategy(int nThreads) {
        this(Executors.newFixedThreadPool(nThreads));
    }

    public AsyncArkEventPublishStrategy(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void publish(ArkEventSubscriber subscriber, ArkEvent event, ArkEventExceptionHandler handler) {
        executorService.execute(() -> {
            try {
                subscriber.onSubscribe(event);
            } catch (Throwable e) {
                handler.handle(new ArkEventException(e, event, subscriber, this));
            }
        });
    }
}
