package com.github.linyuzai.arkevent;

public interface ArkEventEncoder<T> {

    T encode(ArkEvent event) throws Throwable;
}
