package com.github.linyuzai.arkevent.support.strategy.publish.direct;

import com.github.linyuzai.arkevent.*;

public class DirectArkPublishStrategyAdapter implements ArkEventPublishStrategy.Adapter {

    private DirectArkEventPublishStrategy directArkEventPublishStrategy;

    public DirectArkPublishStrategyAdapter() {
        this(new DirectArkEventPublishStrategy());
    }

    public DirectArkPublishStrategyAdapter(DirectArkEventPublishStrategy directArkEventPublishStrategy) {
        this.directArkEventPublishStrategy = directArkEventPublishStrategy;
    }

    @Override
    public ArkEventPublishStrategy adapt(ArkEventSubscriber subscriber) {
        return directArkEventPublishStrategy;
    }
}
