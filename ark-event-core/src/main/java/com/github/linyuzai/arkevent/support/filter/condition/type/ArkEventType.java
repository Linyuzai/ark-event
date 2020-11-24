package com.github.linyuzai.arkevent.support.filter.condition.type;

public interface ArkEventType {

    Class<?>[] arkEventTypes();

    default boolean inherited() {
        return false;
    }
}
