package com.github.linyuzai.arkevent.basic;

import com.github.linyuzai.arkevent.ArkHolder;

public interface ArkEvent {

    default void publish(Object... args) {
        ArkHolder.getDispatcher().dispatch(this, args);
    }
}
