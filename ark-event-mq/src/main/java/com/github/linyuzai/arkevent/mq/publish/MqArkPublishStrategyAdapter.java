package com.github.linyuzai.arkevent.mq.publish;

import com.github.linyuzai.arkevent.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.ArkEventSubscriber;
import com.github.linyuzai.arkevent.mq.condition.type.ArkMqMask;
import com.github.linyuzai.arkevent.support.filter.condition.remote.RemoteArkEventSubscriberMask;
import com.github.linyuzai.arkevent.support.strategy.publish.async.ArkEventPublishAsync;
import com.github.linyuzai.arkevent.support.strategy.publish.async.AsyncArkEventPublishStrategy;
import com.github.linyuzai.arkevent.support.strategy.publish.async.OnArkEventPublishAsync;

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
        if (subscriber instanceof ArkMqMask) {
            return mqArkEventPublishStrategy;
        }
        return null;
    }

    @Override
    public int order() {
        return PUBLISH_STRATEGY_ADAPTER_REMOTE;
    }
}
