package com.github.linyuzai.arkevent.basic;

import com.github.linyuzai.arkevent.ArkEventPlugin;

public interface ArkEvent {

    default void publish(Object... args) {
        ArkEventPlugin.getDispatcher().dispatch(this, args);
    }
}
