package com.github.linyuzai.arkevent.autoconfigure;

import com.github.linyuzai.arkevent.basic.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.basic.ArkEventExceptionHandler;
import com.github.linyuzai.arkevent.basic.ArkEventPublishStrategy;
import com.github.linyuzai.arkevent.basic.ArkEventSubscriber;
import com.github.linyuzai.arkevent.basic.impl.ArkEventDispatcherImpl;
import com.github.linyuzai.arkevent.ArkHolder;
import com.github.linyuzai.arkevent.basic.impl.filter.condition.group.GroupArkEventConditionFilterFactory;
import com.github.linyuzai.arkevent.basic.impl.filter.condition.type.TypeArkEventConditionFilterFactory;
import com.github.linyuzai.arkevent.basic.impl.handler.exception.logger.Slf4jArkEventExceptionHandlerAdapter;
import com.github.linyuzai.arkevent.basic.impl.handler.exception.rethrow.RethrowArkEventExceptionHandlerAdapter;
import com.github.linyuzai.arkevent.basic.impl.strategy.publish.DirectArkEventPublishStrategyAdapter;
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
    public DirectArkEventPublishStrategyAdapter directArkEventPublishStrategyAdapter() {
        return new DirectArkEventPublishStrategyAdapter();
    }

    @Bean
    public RethrowArkEventExceptionHandlerAdapter rethrowArkEventExceptionHandlerAdapter() {
        return new RethrowArkEventExceptionHandlerAdapter();
    }

    @Bean
    public Slf4jArkEventExceptionHandlerAdapter slf4jArkEventExceptionHandlerAdapter() {
        return new Slf4jArkEventExceptionHandlerAdapter();
    }

    @Bean
    public ArkEventDispatcherImpl arkEventDispatcher(List<ArkEventSubscriber> subscribers,
                                                 List<ArkEventConditionFilter.Factory> conditionFilterFactories,
                                                 List<ArkEventPublishStrategy.Adapter> publishStrategyAdapters,
                                                 List<ArkEventExceptionHandler.Adapter> exceptionHandlerAdapters) {
        ArkEventDispatcherImpl dispatcher = new ArkEventDispatcherImpl();
        dispatcher.addConditionFilterFactory(conditionFilterFactories);
        dispatcher.addPublishStrategyAdapter(publishStrategyAdapters);
        dispatcher.addExceptionHandlerAdapter(exceptionHandlerAdapters);
        dispatcher.registerSubscriber(subscribers);
        ArkHolder.setDispatcher(dispatcher);
        return dispatcher;
    }
}
