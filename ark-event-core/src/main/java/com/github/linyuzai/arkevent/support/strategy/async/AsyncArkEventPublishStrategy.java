package com.github.linyuzai.arkevent.support.strategy.async;

import com.github.linyuzai.arkevent.*;
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
    public void execute(ArkEventSubscriber subscriber, ArkEventExceptionHandler handler, ArkEvent event, Object... args) {
        executorService.execute(() -> {
            try {
                subscriber.onSubscribe(event, args);
            } catch (Throwable e) {
                handler.handle(new ArkEventException(e, subscriber, this, event, args));
            }
        });
    }
}
