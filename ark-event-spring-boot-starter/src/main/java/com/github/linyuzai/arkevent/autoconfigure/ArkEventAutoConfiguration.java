package com.github.linyuzai.arkevent.autoconfigure;

import com.github.linyuzai.arkevent.autoconfigure.listener.DefaultArkEventPublishListener;
import com.github.linyuzai.arkevent.core.*;
import com.github.linyuzai.arkevent.core.impl.ArkEventDispatcherImpl;
import com.github.linyuzai.arkevent.support.ArkEventPlugin;
import com.github.linyuzai.arkevent.core.impl.filter.condition.group.GroupArkEventConditionFilterFactory;
import com.github.linyuzai.arkevent.core.impl.filter.condition.type.TypeArkEventConditionFilterFactory;
import com.github.linyuzai.arkevent.core.impl.handler.exception.logger.Slf4jArkEventExceptionHandlerAdapter;
import com.github.linyuzai.arkevent.core.impl.handler.exception.rethrow.RethrowArkEventExceptionHandlerAdapter;
import com.github.linyuzai.arkevent.core.impl.strategy.publish.SimpleArkEventPublishStrategyAdapter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class ArkEventAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(TypeArkEventConditionFilterFactory.class)
    public TypeArkEventConditionFilterFactory typeArkEventConditionFilterFactory() {
        return new TypeArkEventConditionFilterFactory();
    }

    @Bean
    @ConditionalOnMissingBean(GroupArkEventConditionFilterFactory.class)
    public GroupArkEventConditionFilterFactory groupArkEventConditionFilterFactory() {
        return new GroupArkEventConditionFilterFactory();
    }

    @Bean
    @ConditionalOnMissingBean(SimpleArkEventPublishStrategyAdapter.class)
    public SimpleArkEventPublishStrategyAdapter simpleArkEventPublishStrategyAdapter() {
        return new SimpleArkEventPublishStrategyAdapter();
    }

    @Bean
    @ConditionalOnMissingBean(RethrowArkEventExceptionHandlerAdapter.class)
    public RethrowArkEventExceptionHandlerAdapter rethrowArkEventExceptionHandlerAdapter() {
        return new RethrowArkEventExceptionHandlerAdapter();
    }

    @Bean
    @ConditionalOnMissingBean(Slf4jArkEventExceptionHandlerAdapter.class)
    public Slf4jArkEventExceptionHandlerAdapter slf4jArkEventExceptionHandlerAdapter() {
        return new Slf4jArkEventExceptionHandlerAdapter();
    }

    @Bean
    @ConditionalOnMissingBean(ArkEventPublishListener.class)
    public DefaultArkEventPublishListener defaultArkEventPublishListener() {
        return new DefaultArkEventPublishListener();
    }

    @Bean
    @ConditionalOnMissingBean(ArkEventDispatcher.class)
    public ArkEventDispatcherImpl arkEventDispatcher(List<ArkEventSubscriber> subscribers,
                                                     List<ArkEventPublishListener> publishListeners,
                                                     List<ArkEventConditionFilter.Factory> conditionFilterFactories,
                                                     List<ArkEventPublishStrategy.Adapter> publishStrategyAdapters,
                                                     List<ArkEventExceptionHandler.Adapter> exceptionHandlerAdapters,
                                                     List<ArkEventPublishSorter> publishSorters) {
        ArkEventDispatcherImpl dispatcher = new ArkEventDispatcherImpl();
        dispatcher.addPublishListener(publishListeners);
        dispatcher.addConditionFilterFactory(conditionFilterFactories);
        dispatcher.addPublishStrategyAdapter(publishStrategyAdapters);
        dispatcher.addExceptionHandlerAdapter(exceptionHandlerAdapters);
        dispatcher.addPublishSorter(publishSorters);
        dispatcher.registerSubscriber(subscribers);
        ArkEventPlugin.setDispatcher(dispatcher);
        return dispatcher;
    }
}
