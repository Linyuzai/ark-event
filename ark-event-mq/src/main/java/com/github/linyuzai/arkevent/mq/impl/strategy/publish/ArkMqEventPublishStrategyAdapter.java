package com.github.linyuzai.arkevent.mq.impl.strategy.publish;

import com.github.linyuzai.arkevent.basic.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.basic.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.ArkMqEventRemoter;
import com.github.linyuzai.arkevent.transaction.ArkEventTransactionManager;

public class ArkMqEventPublishStrategyAdapter implements ArkEventPublishStrategy.Adapter {

    private ArkMqEventPublishStrategy arkMqEventPublishStrategy;

    public ArkMqEventPublishStrategyAdapter(ArkEventTransactionManager transactionManager) {
        this(new ArkMqEventPublishStrategy(transactionManager));
    }

    public ArkMqEventPublishStrategyAdapter(ArkMqEventPublishStrategy arkMqEventPublishStrategy) {
        this.arkMqEventPublishStrategy = arkMqEventPublishStrategy;
    }

    @Override
    public ArkEventPublishStrategy adapt(ArkEventSubscriber subscriber) {
        if (subscriber instanceof ArkMqEventRemoter) {
            return arkMqEventPublishStrategy;
        }
        return null;
    }

    @Override
    public int order() {
        return PUBLISH_STRATEGY_ADAPTER_REMOTE;
    }
}
