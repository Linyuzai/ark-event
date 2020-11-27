package com.github.linyuzai.arkevent.remote;

import com.github.linyuzai.arkevent.basic.ArkEvent;

public interface ArkEventEncoder<T> {

    T encode(ArkEvent event) throws Throwable;
}
