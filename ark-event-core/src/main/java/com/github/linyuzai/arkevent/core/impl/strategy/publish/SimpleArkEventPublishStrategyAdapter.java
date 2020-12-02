package com.github.linyuzai.arkevent.core.impl.strategy.publish;

import com.github.linyuzai.arkevent.core.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;

public class SimpleArkEventPublishStrategyAdapter implements ArkEventPublishStrategy.Adapter {

    private SimpleArkEventPublishStrategy publishStrategy;

    public SimpleArkEventPublishStrategyAdapter() {
        this(new SimpleArkEventPublishStrategy());
    }

    public SimpleArkEventPublishStrategyAdapter(SimpleArkEventPublishStrategy publishStrategy) {
        this.publishStrategy = publishStrategy;
    }

    public SimpleArkEventPublishStrategy getPublishStrategy() {
        return publishStrategy;
    }

    public void setPublishStrategy(SimpleArkEventPublishStrategy publishStrategy) {
        this.publishStrategy = publishStrategy;
    }

    @Override
    public ArkEventPublishStrategy adapt(ArkEventSubscriber subscriber) {
        return publishStrategy;
    }

    @Override
    public int order() {
        return PUBLISH_STRATEGY_ADAPTER_SIMPLE;
    }
}
