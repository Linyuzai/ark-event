package com.github.linyuzai.arkevent.core.impl.filter.condition.compose;

import com.github.linyuzai.arkevent.core.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;

import java.util.ArrayList;
import java.util.List;

public class ComposeConditionFilterFactory implements ArkEventConditionFilter.Factory {

    private List<ArkEventConditionFilter.Factory> factories;

    public ComposeConditionFilterFactory(List<ArkEventConditionFilter.Factory> factories) {
        this.factories = factories;
    }

    @Override
    public ArkEventConditionFilter getConditionFilter(ArkEventSubscriber subscriber) {
        List<ArkEventConditionFilter> filters = new ArrayList<>();
        for (ArkEventConditionFilter.Factory factory : factories) {
            ArkEventConditionFilter filter = factory.getConditionFilter(subscriber);
            if (filter != null) {
                filters.add(filter);
            }
        }
        if (filters.isEmpty()) {
            return null;
        } else {
            return new ComposeConditionFilter(filters);
        }
    }
}
