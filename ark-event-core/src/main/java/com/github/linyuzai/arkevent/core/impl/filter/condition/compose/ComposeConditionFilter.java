package com.github.linyuzai.arkevent.core.impl.filter.condition.compose;

import com.github.linyuzai.arkevent.core.ArkEvent;
import com.github.linyuzai.arkevent.core.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;

import java.util.List;
import java.util.Map;

public class ComposeConditionFilter implements ArkEventConditionFilter {

    private List<ArkEventConditionFilter> conditionFilters;

    public ComposeConditionFilter(List<ArkEventConditionFilter> conditionFilters) {
        this.conditionFilters = conditionFilters;
    }

    @Override
    public boolean filter(ArkEventSubscriber subscriber, ArkEvent event, Map<Object, Object> args) {
        for (ArkEventConditionFilter filter : conditionFilters) {
            if (filter.filter(subscriber, event, args)) {
                return true;
            }
        }
        return false;
    }
}
