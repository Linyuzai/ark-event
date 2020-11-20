package com.github.linyuzai.arkevent.support.strategy.async;

import com.github.linyuzai.arkevent.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.ArkEventSubscriber;

public class AsyncArkEventPublishStrategyAdapter implements ArkEventPublishStrategy.Adapter {

    private AsyncArkEventPublishStrategy asyncArkEventPublishStrategy;

    public AsyncArkEventPublishStrategyAdapter() {
        this(new AsyncArkEventPublishStrategy());
    }

    public AsyncArkEventPublishStrategyAdapter(AsyncArkEventPublishStrategy asyncArkEventPublishStrategy) {
        this.asyncArkEventPublishStrategy = asyncArkEventPublishStrategy;
    }

    @Override
    public ArkEventPublishStrategy adapt(ArkEventSubscriber subscriber) {
        if (subscriber instanceof ArkEventPublishAsync ||
                subscriber.getClass().isAnnotationPresent(OnArkEventPublishAsync.class)) {
            return asyncArkEventPublishStrategy;
        }
        return null;
    }
}
