package com.github.linyuzai.arkevent.autoconfigure.mq;

import com.github.linyuzai.arkevent.mq.ArkMqEventDecoder;
import com.github.linyuzai.arkevent.mq.ArkMqEventEncoder;
import com.github.linyuzai.arkevent.mq.ArkMqEventSender;
import com.github.linyuzai.arkevent.mq.condition.exclude.ArkMqEventExcludeSelfConditionFilterFactory;
import com.github.linyuzai.arkevent.mq.condition.type.ArkMqEventConditionFilterFactory;
import com.github.linyuzai.arkevent.mq.impl.ArkMqEventReceiverImpl;
import com.github.linyuzai.arkevent.mq.impl.ArkMqEventSubscriberImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

public class ArkMqEventAutoConfiguration {

    @Bean
    @ConditionalOnClass(name = "com.github.linyuzai.arkevent.mq.condition.ArkMqEvent")
    public ArkMqEventConditionFilterFactory arkMqEventConditionFilterFactory() {
        return new ArkMqEventConditionFilterFactory();
    }

    @Bean
    @ConditionalOnClass(name = "com.github.linyuzai.arkevent.mq.condition.ArkMqEvent")
    public ArkMqEventExcludeSelfConditionFilterFactory arkMqEventExcludeSelfConditionFilterFactory() {
        return new ArkMqEventExcludeSelfConditionFilterFactory();
    }

    @Bean
    @ConditionalOnClass(name = "com.github.linyuzai.arkevent.mq.condition.ArkMqEvent")
    public ArkMqEventSubscriberImpl defaultArkMqEventSubscriber(ArkMqEventEncoder encoder, ArkMqEventSender sender) {
        return new ArkMqEventSubscriberImpl(encoder, sender);
    }

    @Bean
    @ConditionalOnClass(name = "com.github.linyuzai.arkevent.mq.condition.ArkMqEvent")
    public ArkMqEventReceiverImpl defaultArkMqEventReceiver(ArkMqEventDecoder decoder) {
        return new ArkMqEventReceiverImpl(decoder);
    }
}
