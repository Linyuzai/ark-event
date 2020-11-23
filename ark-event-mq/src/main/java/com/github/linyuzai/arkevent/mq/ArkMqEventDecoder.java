package com.github.linyuzai.arkevent.mq;

import com.github.linyuzai.arkevent.ArkEvent;

public interface ArkMqEventDecoder {

    ArkEvent decode(String event);
}
