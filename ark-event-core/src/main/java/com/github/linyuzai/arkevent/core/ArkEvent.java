package com.github.linyuzai.arkevent.core;

import com.github.linyuzai.arkevent.support.ArkEventPlugin;

public interface ArkEvent {

    default void publish(Object... args) {
        ArkEventPlugin.getDispatcher().dispatch(this, args);
    }
}
