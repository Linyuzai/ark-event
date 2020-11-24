package com.github.linyuzai.arkevent.mq.rabbit;

import com.github.linyuzai.arkevent.exception.ArkEventException;
import com.github.linyuzai.arkevent.mq.ArkMqEventSender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitArkMqEventSender implements ArkMqEventSender {

    private RabbitTemplate template;

    public RabbitArkMqEventSender(RabbitTemplate template) {
        if (template == null) {
            throw new ArkEventException("RabbitTemplate is null");
        }
        this.template = template;
    }

    @Override
    public void send(String event) {
        template.convertAndSend("", "", event);
    }
}
