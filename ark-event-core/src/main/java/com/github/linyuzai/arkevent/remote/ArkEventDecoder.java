package com.github.linyuzai.arkevent.remote;

import com.github.linyuzai.arkevent.basic.ArkEvent;

public interface ArkEventDecoder<T> {

    ArkEvent decode(T event) throws Throwable;
}
