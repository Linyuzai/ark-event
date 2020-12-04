package com.github.linyuzai.arkevent.core;

import com.github.linyuzai.arkevent.support.ArkEventPlugin;

import java.util.Map;

public interface ArkEvent {

    default void publish() {
        publish(null);
    }

    default void publish(Map<Object, Object> args) {
        ArkEventPlugin.getPublisher().publish(this, args);
    }
}
