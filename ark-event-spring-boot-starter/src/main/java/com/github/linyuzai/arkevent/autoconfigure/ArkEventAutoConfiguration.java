package com.github.linyuzai.arkevent.autoconfigure;

import com.github.linyuzai.arkevent.autoconfigure.listener.DefaultArkEventPublishListener;
import com.github.linyuzai.arkevent.core.*;
import com.github.linyuzai.arkevent.core.impl.DefaultArkEventPublisher;
import com.github.linyuzai.arkevent.core.impl.filter.condition.expression.ExpressionArkEventConditionFilterFactory;
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
    @ConditionalOnMissingBean(ExpressionArkEventConditionFilterFactory.class)
    public ExpressionArkEventConditionFilterFactory expressionArkEventConditionFilterFactory() {
        return new ExpressionArkEventConditionFilterFactory();
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
    @ConditionalOnMissingBean(ArkEventPublisher.class)
    public DefaultArkEventPublisher defaultArkEventPublisher(List<ArkEventSubscriber> subscribers,
                                                             List<ArkEventArgsProcessor> argsProcessors,
                                                             List<ArkEventPublishListener> publishListeners,
                                                             List<ArkEventConditionFilter.Factory> conditionFilterFactories,
                                                             List<ArkEventPublishStrategy.Adapter> publishStrategyAdapters,
                                                             List<ArkEventExceptionHandler.Adapter> exceptionHandlerAdapters,
                                                             List<ArkEventPublishSorter> publishSorters) {
        DefaultArkEventPublisher publisher = new DefaultArkEventPublisher();
        publisher.addPublishListener(publishListeners);
        publisher.addArgsProcessor(argsProcessors);
        publisher.addConditionFilterFactory(conditionFilterFactories);
        publisher.addPublishStrategyAdapter(publishStrategyAdapters);
        publisher.addExceptionHandlerAdapter(exceptionHandlerAdapters);
        publisher.addPublishSorter(publishSorters);
        publisher.registerSubscriber(subscribers);
        ArkEventPlugin.setPublisher(publisher);
        return publisher;
    }
}
