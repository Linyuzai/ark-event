package com.github.linyuzai.arkevent.support.condition.group;

public interface ArkEventGroup {

    String[] arkEventGroups();

    default boolean requireGroupCondition() {
        return false;
    }
}
