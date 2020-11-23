package com.github.linyuzai.arkevent.mq.rabbit;

import com.github.linyuzai.arkevent.mq.ArkMqEventSender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitArkMqEventSender implements ArkMqEventSender {

    private RabbitTemplate template;

    public RabbitArkMqEventSender(RabbitTemplate template) {
        this.template = template;
    }

    @Override
    public void send(String event) {
        template.convertAndSend("", "", event);
    }
}
