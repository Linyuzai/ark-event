package com.github.linyuzai.arkevent.mq.rabbit.impl;

import com.github.linyuzai.arkevent.core.ArkEventException;
import com.github.linyuzai.arkevent.mq.ArkMqEventDecoder;
import com.github.linyuzai.arkevent.mq.ArkMqEventIdempotentManager;
import com.github.linyuzai.arkevent.mq.ArkMqEventMessageListener;
import com.github.linyuzai.arkevent.mq.impl.handler.exception.ArkMqEventExceptionHandler;
import com.github.linyuzai.arkevent.mq.rabbit.RabbitArkMqEventChannelHandler;
import com.github.linyuzai.arkevent.support.ArkEventPlugin;
import com.github.linyuzai.arkevent.transaction.manager.ArkEventTransactionManager;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

import java.util.HashMap;
import java.util.Map;

public class RabbitArkMqEventMessageListener implements ArkMqEventMessageListener, ChannelAwareMessageListener {

    private static final Logger log = LoggerFactory.getLogger(RabbitArkMqEventMessageListener.class);

    private ArkEventTransactionManager transactionManager;

    private ArkMqEventIdempotentManager idempotentManager;

    private ArkMqEventDecoder decoder;

    private ArkMqEventExceptionHandler exceptionHandler;

    private RabbitArkMqEventChannelHandler channelHandler;

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

    public RabbitArkMqEventChannelHandler getChannelHandler() {
        return channelHandler;
    }

    public void setChannelHandler(RabbitArkMqEventChannelHandler channelHandler) {
        this.channelHandler = channelHandler;
    }

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            if (channelHandler != null) {
                channelHandler.onMessageReceived(message, channel);
            }
            String eventId = message.getMessageProperties().getHeader(ArkEventPlugin.HEADER_EVENT_ID);
            if (idempotentManager.isEventHandled(eventId, decoder, message)) {
                idempotentManager.onEventRepeated(eventId, decoder, message);
            } else {
                Map<Object, Object> args = new HashMap<>();
                args.put(ArkEventPlugin.ARGS_REMOTE_KEY, "mq");
                decoder.decode(message).publish(args);
            }
            if (channelHandler != null) {
                channelHandler.onMessageHandled(message, channel);
            }
        } catch (Throwable e) {
            try {
                if (channelHandler != null) {
                    channelHandler.onMessageExceptionThrown(e, message, channel);
                }
                Map<Object, Object> args = new HashMap<>();
                args.put(ArkEventPlugin.ARGS_REMOTE_MESSAGE_EXCEPTION_KEY, "mq");
                exceptionHandler.handle(new ArkEventException(e, args));
                if (channelHandler != null) {
                    channelHandler.onMessageExceptionHandled(e, message, channel);
                }
            } catch (Throwable re) {
                handleRethrowException(re);
            }
        }
    }

    protected void handleRethrowException(Throwable e) {
        log.error("Rabbit message listener rethrow", e);
    }
}
