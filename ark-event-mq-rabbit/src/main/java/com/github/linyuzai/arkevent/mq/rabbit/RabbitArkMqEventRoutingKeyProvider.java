package com.github.linyuzai.arkevent.mq.rabbit;

import java.util.Collection;

public interface RabbitArkMqEventRoutingKeyProvider {

    Collection<String> getRoutingKeys();

    String getRoutingKey();
}
