package com.github.linyuzai.arkevent.autoconfigure;

import com.github.linyuzai.arkevent.*;
import com.github.linyuzai.arkevent.impl.ArkEventDispatcherImpl;
import com.github.linyuzai.arkevent.support.ArkHolder;
import com.github.linyuzai.arkevent.support.filter.condition.group.GroupArkEventConditionFilterFactory;
import com.github.linyuzai.arkevent.support.filter.condition.type.TypeArkEventConditionFilterFactory;
import com.github.linyuzai.arkevent.support.handler.exception.rethrow.RethrowArkExceptionHandlerAdapter;
import com.github.linyuzai.arkevent.support.strategy.publish.async.AsyncArkPublishStrategyAdapter;
import com.github.linyuzai.arkevent.support.strategy.publish.direct.DirectArkPublishStrategyAdapter;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class ArkEventAutoConfiguration {

    @Bean
    public TypeArkEventConditionFilterFactory typeArkEventConditionFilterFactory() {
        return new TypeArkEventConditionFilterFactory();
    }

    @Bean
    public GroupArkEventConditionFilterFactory groupArkEventConditionFilterFactory() {
        return new GroupArkEventConditionFilterFactory();
    }

    @Bean
    public DirectArkPublishStrategyAdapter directArkEventPublishStrategyAdapter() {
        return new DirectArkPublishStrategyAdapter();
    }

    @Bean
    public AsyncArkPublishStrategyAdapter asyncArkEventPublishStrategyAdapter() {
        return new AsyncArkPublishStrategyAdapter();
    }

    @Bean
    public RethrowArkExceptionHandlerAdapter rethrowArkEventExceptionHandlerAdapter() {
        return new RethrowArkExceptionHandlerAdapter();
    }

    @Bean
    public ArkEventDispatcher arkEventDispatcher(List<ArkEventSubscriber> subscribers,
                                                 List<ArkEventConditionFilter.Factory> conditionFilterFactories,
                                                 List<ArkEventPublishStrategy.Adapter> publishStrategyAdapters,
                                                 List<ArkEventExceptionHandler.Adapter> exceptionHandlerAdapters) {
        ArkEventDispatcherImpl dispatcher = new ArkEventDispatcherImpl();
        dispatcher.addConditionFilterFactory(conditionFilterFactories);
        dispatcher.addPublishStrategyAdapter(publishStrategyAdapters);
        dispatcher.addExceptionHandlerAdapter(exceptionHandlerAdapters);
        dispatcher.registerEventSubscriber(subscribers);
        ArkHolder.setDispatcher(dispatcher);
        return dispatcher;
    }
}
