package com.github.linyuzai.arkevent.mq.support.strategy.publish;

import com.github.linyuzai.arkevent.basic.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.basic.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.ArkMqEventRemoter;

public class MqArkPublishStrategyAdapter implements ArkEventPublishStrategy.Adapter {

    private MqArkEventPublishStrategy mqArkEventPublishStrategy;

    public MqArkPublishStrategyAdapter() {
        this(new MqArkEventPublishStrategy());
    }

    public MqArkPublishStrategyAdapter(MqArkEventPublishStrategy mqArkEventPublishStrategy) {
        this.mqArkEventPublishStrategy = mqArkEventPublishStrategy;
    }

    @Override
    public ArkEventPublishStrategy adapt(ArkEventSubscriber subscriber) {
        if (subscriber instanceof ArkMqEventRemoter) {
            return mqArkEventPublishStrategy;
        }
        return null;
    }

    @Override
    public int order() {
        return PUBLISH_STRATEGY_ADAPTER_REMOTE;
    }
}
