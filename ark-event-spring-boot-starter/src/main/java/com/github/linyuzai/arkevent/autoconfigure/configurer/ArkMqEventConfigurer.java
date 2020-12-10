package com.github.linyuzai.arkevent.autoconfigure.configurer;

import com.github.linyuzai.arkevent.mq.ArkMqEventMessageListener;
import com.github.linyuzai.arkevent.mq.ArkMqEventSubscriber;
import com.github.linyuzai.arkevent.mq.rabbit.impl.RabbitArkMqEventMessageListenerContainer;

public interface ArkMqEventConfigurer {

    default void onArkMqEventSubscriber(ArkMqEventSubscriber subscriber) {

    }

    default void onArkMqEventMessageListener(ArkMqEventMessageListener listener) {

    }

    default void onRabbitMessageListenerContainer(RabbitArkMqEventMessageListenerContainer container) {

    }
}
