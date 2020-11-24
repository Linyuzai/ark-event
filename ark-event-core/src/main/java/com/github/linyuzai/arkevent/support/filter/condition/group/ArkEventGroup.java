package com.github.linyuzai.arkevent.support.filter.condition.group;

public interface ArkEventGroup {

    String[] arkEventGroups();

    default boolean requireGroupCondition() {
        return false;
    }
}
