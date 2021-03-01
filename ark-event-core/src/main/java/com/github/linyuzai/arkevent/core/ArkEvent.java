package com.github.linyuzai.arkevent.core;

import com.github.linyuzai.arkevent.support.ArkEventPlugin;

import java.util.HashMap;
import java.util.Map;

public interface ArkEvent {

    default void publish() {
        publish(null);
    }

    default void publish(Map<Object, Object> args) {
        ArkEventPlugin.getPublisher().publish(this, args);
    }

    @SuppressWarnings("unchecked")
    default void publish(Object args) {
        if (args instanceof Map) {
            publish((Map<Object, Object>) args);
        } else {
            Map<Object, Object> map = new HashMap<>();
            map.put(args, args);
            publish(map);
        }
    }
}
