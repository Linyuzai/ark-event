package com.github.linyuzai.arkevent.mq.impl.strategy.publish;

import com.github.linyuzai.arkevent.core.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.ArkMqEventSubscriber;
import com.github.linyuzai.arkevent.transaction.manager.ArkEventTransactionManager;

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
        if (subscriber instanceof ArkMqEventSubscriber) {
            return arkMqEventPublishStrategy;
        }
        return null;
    }

    @Override
    public int order() {
        return PUBLISH_STRATEGY_ADAPTER_MQ;
    }
}
