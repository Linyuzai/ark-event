package com.github.linyuzai.arkevent.support.strategy.publish.async;

import com.github.linyuzai.arkevent.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.ArkEventSubscriber;

public class AsyncArkPublishStrategyAdapter implements ArkEventPublishStrategy.Adapter {

    private AsyncArkEventPublishStrategy asyncArkEventPublishStrategy;

    public AsyncArkPublishStrategyAdapter() {
        this(new AsyncArkEventPublishStrategy());
    }

    public AsyncArkPublishStrategyAdapter(AsyncArkEventPublishStrategy asyncArkEventPublishStrategy) {
        this.asyncArkEventPublishStrategy = asyncArkEventPublishStrategy;
    }

    @Override
    public ArkEventPublishStrategy adapt(ArkEventSubscriber subscriber) {
        if (subscriber.getClass().isAnnotationPresent(AsyncEventPublish.class)) {
            return asyncArkEventPublishStrategy;
        }
        return null;
    }

    @Override
    public int order() {
        return PUBLISH_STRATEGY_ADAPTER_ASYNC;
    }
}
