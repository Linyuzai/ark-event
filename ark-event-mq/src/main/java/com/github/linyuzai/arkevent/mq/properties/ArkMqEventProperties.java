package com.github.linyuzai.arkevent.mq.properties;

public class ArkMqEventProperties {

    private String exchangeName = "Exchange@ArkEvent.Topic";

    private String queuePrefix;

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getQueuePrefix() {
        return queuePrefix;
    }

    public void setQueuePrefix(String queuePrefix) {
        this.queuePrefix = queuePrefix;
    }
}
