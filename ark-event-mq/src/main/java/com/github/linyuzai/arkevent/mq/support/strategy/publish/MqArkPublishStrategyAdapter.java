package com.github.linyuzai.arkevent.mq.support.strategy.publish;

import com.github.linyuzai.arkevent.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.support.filter.condition.type.MqArkEventSubscriberMask;

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
        if (subscriber instanceof MqArkEventSubscriberMask) {
            return mqArkEventPublishStrategy;
        }
        return null;
    }

    @Override
    public int order() {
        return PUBLISH_STRATEGY_ADAPTER_REMOTE;
    }
}
