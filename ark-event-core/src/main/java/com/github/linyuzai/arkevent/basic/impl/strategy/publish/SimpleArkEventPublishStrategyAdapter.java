package com.github.linyuzai.arkevent.basic.impl.strategy.publish;

import com.github.linyuzai.arkevent.basic.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.basic.ArkEventSubscriber;

public class SimpleArkEventPublishStrategyAdapter implements ArkEventPublishStrategy.Adapter {

    private SimpleArkEventPublishStrategy simpleArkEventPublishStrategy;

    public SimpleArkEventPublishStrategyAdapter() {
        this(new SimpleArkEventPublishStrategy());
    }

    public SimpleArkEventPublishStrategyAdapter(SimpleArkEventPublishStrategy simpleArkEventPublishStrategy) {
        this.simpleArkEventPublishStrategy = simpleArkEventPublishStrategy;
    }

    @Override
    public ArkEventPublishStrategy adapt(ArkEventSubscriber subscriber) {
        return simpleArkEventPublishStrategy;
    }

    @Override
    public int order() {
        return PUBLISH_STRATEGY_ADAPTER_SIMPLE;
    }
}
