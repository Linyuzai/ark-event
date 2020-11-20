package com.github.linyuzai.arkevent.autoconfigure;

import com.github.linyuzai.arkevent.*;
import com.github.linyuzai.arkevent.impl.DefaultArkEventDispatcher;
import com.github.linyuzai.arkevent.plugin.ArkEventPlugin;
import com.github.linyuzai.arkevent.support.condition.group.GroupArkEventConditionFilterFactory;
import com.github.linyuzai.arkevent.support.condition.type.TypeArkEventConditionFilterFactory;
import com.github.linyuzai.arkevent.support.strategy.async.AsyncArkEventPublishStrategyAdapter;
import com.github.linyuzai.arkevent.support.strategy.direct.DirectArkEventPublishStrategyAdapter;
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
    public AsyncArkEventPublishStrategyAdapter asyncArkEventPublishStrategyAdapter() {
        return new AsyncArkEventPublishStrategyAdapter();
    }

    @Bean
    public ArkEventDispatcher arkEventDispatcher(List<ArkEventSubscriber> subscribers,
                                                 List<ArkEventConditionFilter.Factory> factories,
                                                 List<ArkEventPublishStrategy.Adapter> adapters,
                                                 ArkEventExceptionHandler handler) {
        DefaultArkEventDispatcher dispatcher = new DefaultArkEventDispatcher();
        dispatcher.addConditionFilterFactory(factories);
        dispatcher.addPublishStrategyAdapter(adapters);
        dispatcher.registerSubscriber(subscribers);
        dispatcher.setExceptionHandler(handler);
        ArkEventPlugin.setDispatcher(dispatcher);
        return dispatcher;
    }
}
