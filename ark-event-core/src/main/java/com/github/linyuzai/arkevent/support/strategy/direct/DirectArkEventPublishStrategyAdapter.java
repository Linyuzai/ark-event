package com.github.linyuzai.arkevent.support.strategy.direct;

import com.github.linyuzai.arkevent.*;

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
}
