package com.github.linyuzai.arkevent;

import com.github.linyuzai.arkevent.plugin.ArkEventPlugin;

import java.util.*;

public interface ArkEvent {

    default void publish(Object... args) {
        ArkEventPlugin.getDispatcher().dispatch(this, args);
    }
}
