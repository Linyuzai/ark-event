package com.github.linyuzai.arkevent.mq.rabbit;

import com.github.linyuzai.arkevent.mq.ArkMqEventReceiver;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitArkMqEventMessageListener implements MessageListener {

    private ArkMqEventReceiver receiver;

    public RabbitArkMqEventMessageListener(ArkMqEventReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void onMessage(Message message) {
        try {
            receiver.receive(new String(message.getBody()));
        } catch (Throwable e) {
            //throw new ArkEventException(e);
            e.printStackTrace();
        }
    }
}
