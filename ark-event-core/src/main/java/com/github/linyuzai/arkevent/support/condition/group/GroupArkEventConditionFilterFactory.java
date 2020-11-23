package com.github.linyuzai.arkevent.support.condition.group;

import com.github.linyuzai.arkevent.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.ArkEventSubscriber;

public class GroupArkEventConditionFilterFactory implements ArkEventConditionFilter.Factory {

    @Override
    public ArkEventConditionFilter getConditionFilter(ArkEventSubscriber subscriber) {
        if (subscriber instanceof ArkEventGroup) {
            ArkEventGroup arkEventGroup = (ArkEventGroup) subscriber;
            String[] groups = arkEventGroup.arkEventGroups();
            boolean requireGroupCondition = arkEventGroup.requireGroupCondition();
            return new GroupArkEventConditionFilter(groups, requireGroupCondition);
        }
        OnArkEventGroup onArkEventGroup = subscriber.getClass().getAnnotation(OnArkEventGroup.class);
        if (onArkEventGroup != null) {
            String[] groups = onArkEventGroup.value();
            boolean requireGroupCondition = onArkEventGroup.requireGroupCondition();
            return new GroupArkEventConditionFilter(groups, requireGroupCondition);
        }
        return null;
    }
}
