package com.github.linyuzai.arkevent.autoconfigure.mq;

import com.github.linyuzai.arkevent.mq.ArkMqEventDecoder;
import com.github.linyuzai.arkevent.mq.ArkMqEventEncoder;
import com.github.linyuzai.arkevent.mq.ArkMqEventSender;
import com.github.linyuzai.arkevent.mq.condition.exclude.ArkMqEventExcludeSelfConditionFilterFactory;
import com.github.linyuzai.arkevent.mq.condition.type.ArkMqEventConditionFilterFactory;
import com.github.linyuzai.arkevent.mq.impl.DefaultArkMqEventReceiver;
import com.github.linyuzai.arkevent.mq.impl.DefaultArkMqEventSubscriber;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

public class ArkMqEventAutoConfiguration {

    @Bean
    @ConditionalOnClass(name = "com.github.linyuzai.arkevent.mq.ArkMqEvent")
    public ArkMqEventConditionFilterFactory arkMqEventConditionFilterFactory() {
        return new ArkMqEventConditionFilterFactory();
    }

    @Bean
    @ConditionalOnClass(name = "com.github.linyuzai.arkevent.mq.ArkMqEvent")
    public ArkMqEventExcludeSelfConditionFilterFactory arkMqEventExcludeSelfConditionFilterFactory() {
        return new ArkMqEventExcludeSelfConditionFilterFactory();
    }

    @Bean
    @ConditionalOnClass(name = "com.github.linyuzai.arkevent.mq.ArkMqEvent")
    public DefaultArkMqEventSubscriber defaultArkMqEventSubscriber(ArkMqEventEncoder encoder, ArkMqEventSender sender) {
        return new DefaultArkMqEventSubscriber(encoder, sender);
    }

    @Bean
    @ConditionalOnClass(name = "com.github.linyuzai.arkevent.mq.ArkMqEvent")
    public DefaultArkMqEventReceiver defaultArkMqEventReceiver(ArkMqEventDecoder decoder) {
        return new DefaultArkMqEventReceiver(decoder);
    }
}
