package com.github.linyuzai.arkevent.support.condition.group;

import com.github.linyuzai.arkevent.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.ArkEventSubscriber;

public class GroupArkEventConditionFilterFactory implements ArkEventConditionFilter.Factory {

    @Override
    public ArkEventConditionFilter getConditionFilter(ArkEventSubscriber subscriber) {
        if (subscriber instanceof ArkEventGroup) {
            ArkEventGroup arkEventGroup = (ArkEventGroup) subscriber;
            String[] groups = arkEventGroup.arkEventGroups();
            boolean forceGroupCondition = arkEventGroup.forceGroupCondition();
            return new GroupArkEventConditionFilter(groups, forceGroupCondition);
        }
        OnArkEventGroup onArkEventGroup = subscriber.getClass().getAnnotation(OnArkEventGroup.class);
        if (onArkEventGroup != null) {
            String[] groups = onArkEventGroup.value();
            boolean forceGroupCondition = onArkEventGroup.forceGroupCondition();
            return new GroupArkEventConditionFilter(groups, forceGroupCondition);
        }
        return null;
    }
}
