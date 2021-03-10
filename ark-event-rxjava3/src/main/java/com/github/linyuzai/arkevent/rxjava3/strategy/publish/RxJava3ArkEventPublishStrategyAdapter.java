package com.github.linyuzai.arkevent.rxjava3.strategy.publish;

import com.github.linyuzai.arkevent.core.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;
import com.github.linyuzai.arkevent.rxjava3.RxJava3FlowableArkEventSubscriber;
import com.github.linyuzai.arkevent.rxjava3.RxJava3ObservableArkEventSubscriber;

public class RxJava3ArkEventPublishStrategyAdapter implements ArkEventPublishStrategy.Adapter {

    @Override
    public ArkEventPublishStrategy adapt(ArkEventSubscriber subscriber) {
        if (subscriber instanceof RxJava3FlowableArkEventSubscriber) {
            return new RxJava3FlowableArkEventPublishStrategy((RxJava3FlowableArkEventSubscriber) subscriber);
        } else if (subscriber instanceof RxJava3ObservableArkEventSubscriber) {
            return new RxJava3ObservableArkEventPublishStrategy((RxJava3ObservableArkEventSubscriber) subscriber);
        }
        return null;
    }

    @Override
    public int order() {
        return PUBLISH_STRATEGY_ADAPTER_RXJAVA3;
    }
}
