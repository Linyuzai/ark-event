package com.github.linyuzai.arkevent.mq.rabbit.impl;

import com.github.linyuzai.arkevent.core.ArkEventException;
import com.github.linyuzai.arkevent.mq.ArkMqEventDecoder;
import com.github.linyuzai.arkevent.mq.ArkMqEventIdempotentManager;
import com.github.linyuzai.arkevent.mq.impl.handler.exception.ArkMqEventExceptionHandler;
import com.github.linyuzai.arkevent.support.ArkEventPlugin;
import com.github.linyuzai.arkevent.transaction.manager.ArkEventTransactionManager;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class RabbitArkMqEventMessageListener implements MessageListener {

    private ArkEventTransactionManager transactionManager;

    private ArkMqEventIdempotentManager idempotentManager;

    private ArkMqEventDecoder decoder;

    private ArkMqEventExceptionHandler exceptionHandler;

    public ArkEventTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(ArkEventTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public ArkMqEventIdempotentManager getIdempotentManager() {
        return idempotentManager;
    }

    public void setIdempotentManager(ArkMqEventIdempotentManager idempotentManager) {
        this.idempotentManager = idempotentManager;
    }

    public ArkMqEventDecoder getDecoder() {
        return decoder;
    }

    public void setDecoder(ArkMqEventDecoder decoder) {
        this.decoder = decoder;
    }

    public ArkMqEventExceptionHandler getExceptionHandler() {
        return exceptionHandler;
    }

    public void setExceptionHandler(ArkMqEventExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public void onMessage(Message message) {
        try {
            String eventId = message.getMessageProperties().getCorrelationId();
            if (idempotentManager.isEventHandled(eventId, decoder, message)) {
                idempotentManager.onEventRepeated(eventId, decoder, message);
            } else {
                decoder.decode(message).publish(ArkEventPlugin.remoteArgs());
            }
        } catch (Throwable e) {
            exceptionHandler.handle(new ArkEventException(e));
        }
    }
}
