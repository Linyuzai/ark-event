package com.github.linyuzai.arkevent.support.strategy.publish.async;

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
        if (subscriber instanceof AsyncEventPublishStrategy ||
                subscriber.getClass().isAnnotationPresent(AsyncEventPublish.class)) {
            return asyncArkEventPublishStrategy;
        }
        return null;
    }

    @Override
    public int order() {
        return PUBLISH_STRATEGY_ADAPTER_ASYNC;
    }
}
