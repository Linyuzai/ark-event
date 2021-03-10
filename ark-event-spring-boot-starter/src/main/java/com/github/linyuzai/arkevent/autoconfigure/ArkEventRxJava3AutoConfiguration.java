package com.github.linyuzai.arkevent.autoconfigure;

import com.github.linyuzai.arkevent.rxjava3.EventRxJava3;
import com.github.linyuzai.arkevent.rxjava3.strategy.publish.RxJava3ArkEventPublishStrategyAdapter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@ConditionalOnClass(EventRxJava3.class)
public class ArkEventRxJava3AutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public RxJava3ArkEventPublishStrategyAdapter rxJava3ArkEventPublishStrategyAdapter() {
        return new RxJava3ArkEventPublishStrategyAdapter();
    }
}
