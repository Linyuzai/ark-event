package com.github.linyuzai.arkevent.basic.impl.strategy.publish;

import com.github.linyuzai.arkevent.basic.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.basic.ArkEventSubscriber;

public class DirectArkEventPublishStrategyAdapter implements ArkEventPublishStrategy.Adapter {

    private DirectArkEventPublishStrategy directArkEventPublishStrategy;

    public DirectArkEventPublishStrategyAdapter() {
        this(new DirectArkEventPublishStrategy());
    }

    public DirectArkEventPublishStrategyAdapter(DirectArkEventPublishStrategy directArkEventPublishStrategy) {
        this.directArkEventPublishStrategy = directArkEventPublishStrategy;
    }

    @Override
    public ArkEventPublishStrategy adapt(ArkEventSubscriber subscriber) {
        return directArkEventPublishStrategy;
    }

    @Override
    public int order() {
        return PUBLISH_STRATEGY_ADAPTER_DIRECT;
    }
}
