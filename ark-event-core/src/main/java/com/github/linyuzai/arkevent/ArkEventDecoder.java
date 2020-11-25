package com.github.linyuzai.arkevent;

public interface ArkEventDecoder<T> {

    ArkEvent decode(T event) throws Throwable;
}
