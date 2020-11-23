package com.github.linyuzai.arkevent.mq.rabbit;

import com.github.linyuzai.arkevent.mq.ArkMqEventReceiver;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitMessageListener implements MessageListener {

    private ArkMqEventReceiver receiver;

    public RabbitMessageListener(ArkMqEventReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void onMessage(Message message) {
        receiver.receive(new String(message.getBody()));
    }
}
