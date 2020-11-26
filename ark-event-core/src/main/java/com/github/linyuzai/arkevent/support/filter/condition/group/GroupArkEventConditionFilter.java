package com.github.linyuzai.arkevent.support.filter.condition.group;

import com.github.linyuzai.arkevent.ArkEvent;
import com.github.linyuzai.arkevent.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.ArkEventSubscriber;

public class GroupArkEventConditionFilter implements ArkEventConditionFilter {

    private String[] groups;

    private boolean requireGroupCondition;

    public GroupArkEventConditionFilter(String[] groups, boolean requireGroupCondition) {
        this.groups = groups == null ? new String[]{} : groups;
        this.requireGroupCondition = requireGroupCondition;
    }

    @Override
    public boolean filter(ArkEventSubscriber subscriber, ArkEvent event, Object... args) {
        EventGroup eventGroup = event.getClass().getAnnotation(EventGroup.class);
        if (eventGroup == null) {
            return !requireGroupCondition;
        } else {
            return include(eventGroup.value());
        }
    }

    private boolean include(String[] targets) {
        if (targets == null) {
            return false;
        }
        for (String target : targets) {
            for (String group : groups) {
                if (target.equals(group)) {
                    return true;
                }
            }
        }
        return false;
    }
}
