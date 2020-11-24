package com.github.linyuzai.arkevent;

import com.github.linyuzai.arkevent.support.ArkHolder;

public interface ArkEvent {

    default void publish(Object... args) {
        ArkHolder.getDispatcher().dispatch(this, args);
    }
}
