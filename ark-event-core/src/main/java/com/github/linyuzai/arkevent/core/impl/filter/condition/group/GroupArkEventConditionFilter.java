package com.github.linyuzai.arkevent.core.impl.filter.condition.group;

import com.github.linyuzai.arkevent.core.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.core.ArkEventSubscriber;

import java.util.Map;

public class GroupArkEventConditionFilter implements ArkEventConditionFilter {

    private String[] groups;

    private boolean requireGroupCondition;

    public GroupArkEventConditionFilter(String[] groups, boolean requireGroupCondition) {
        this.groups = groups == null ? new String[]{} : groups;
        this.requireGroupCondition = requireGroupCondition;
    }

    public String[] getGroups() {
        return groups;
    }

    public void setGroups(String[] groups) {
        this.groups = groups;
    }

    public boolean isRequireGroupCondition() {
        return requireGroupCondition;
    }

    public void setRequireGroupCondition(boolean requireGroupCondition) {
        this.requireGroupCondition = requireGroupCondition;
    }

    @Override
    public boolean filter(ArkEventSubscriber subscriber, Object event, Map<Object, Object> args) {
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
