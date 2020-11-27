package com.github.linyuzai.arkevent.remote;

import java.util.Arrays;

public interface ArkEventFromRemote {

    static boolean isRemote(Object... args) {
        long count = Arrays.stream(args).filter(it -> it instanceof ArkEventFromRemote).count();
        return count == 0;
    }
}
