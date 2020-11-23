package com.github.linyuzai.arkevent.autoconfigure;

import com.github.linyuzai.arkevent.*;
import com.github.linyuzai.arkevent.impl.DefaultArkEventDispatcher;
import com.github.linyuzai.arkevent.mq.ArkMqEventDecoder;
import com.github.linyuzai.arkevent.mq.ArkMqEventEncoder;
import com.github.linyuzai.arkevent.mq.ArkMqEventSender;
import com.github.linyuzai.arkevent.mq.condition.exclude.ArkMqEventExcludeSelfConditionFilterFactory;
import com.github.linyuzai.arkevent.mq.condition.type.ArkMqEventConditionFilterFactory;
import com.github.linyuzai.arkevent.mq.impl.DefaultArkMqEventReceiver;
import com.github.linyuzai.arkevent.mq.impl.DefaultArkMqEventSubscriber;
import com.github.linyuzai.arkevent.plugin.ArkEventPlugin;
import com.github.linyuzai.arkevent.support.condition.group.GroupArkEventConditionFilterFactory;
import com.github.linyuzai.arkevent.support.condition.type.TypeArkEventConditionFilterFactory;
import com.github.linyuzai.arkevent.support.strategy.async.AsyncArkEventPublishStrategyAdapter;
import com.github.linyuzai.arkevent.support.strategy.direct.DirectArkEventPublishStrategyAdapter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class ArkEventAutoConfiguration {

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

    @Bean
    public TypeArkEventConditionFilterFactory typeArkEventConditionFilterFactory() {
        return new TypeArkEventConditionFilterFactory();
    }

    @Bean
    public GroupArkEventConditionFilterFactory groupArkEventConditionFilterFactory() {
        return new GroupArkEventConditionFilterFactory();
    }

    @Bean
    public DirectArkEventPublishStrategyAdapter directArkEventPublishStrategyAdapter() {
        return new DirectArkEventPublishStrategyAdapter();
    }

    @Bean
    public AsyncArkEventPublishStrategyAdapter asyncArkEventPublishStrategyAdapter() {
        return new AsyncArkEventPublishStrategyAdapter();
    }

    @Bean
    public ArkEventDispatcher arkEventDispatcher(List<ArkEventSubscriber> subscribers,
                                                 List<ArkEventConditionFilter.Factory> conditionFilterFactories,
                                                 List<ArkEventPublishStrategy.Adapter> publishStrategyAdapters,
                                                 List<ArkEventExceptionHandler.Adapter> exceptionHandlerAdapters) {
        DefaultArkEventDispatcher dispatcher = new DefaultArkEventDispatcher();
        dispatcher.addConditionFilterFactory(conditionFilterFactories);
        dispatcher.addPublishStrategyAdapter(publishStrategyAdapters);
        dispatcher.addExceptionHandlerAdapter(exceptionHandlerAdapters);
        dispatcher.registerSubscriber(subscribers);
        ArkEventPlugin.setDispatcher(dispatcher);
        return dispatcher;
    }
}
