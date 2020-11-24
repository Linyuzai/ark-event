package com.github.linyuzai.arkevent.support;

public interface ArkOrder {

    default int order() {
        return 0;
    }
}
