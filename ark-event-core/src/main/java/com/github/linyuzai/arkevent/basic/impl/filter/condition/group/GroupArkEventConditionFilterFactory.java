package com.github.linyuzai.arkevent.basic.impl.filter.condition.group;

import com.github.linyuzai.arkevent.basic.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.basic.ArkEventSubscriber;

public class GroupArkEventConditionFilterFactory implements ArkEventConditionFilter.Factory {

    @Override
    public ArkEventConditionFilter getConditionFilter(ArkEventSubscriber subscriber) {
        OnEventGroup onEventGroup = subscriber.getClass().getAnnotation(OnEventGroup.class);
        if (onEventGroup != null) {
            String[] groups = onEventGroup.value();
            boolean requireGroupCondition = onEventGroup.requireGroupCondition();
            return new GroupArkEventConditionFilter(groups, requireGroupCondition);
        }
        return null;
    }
}
