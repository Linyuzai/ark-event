# Ark Event

简单的事件发布框架，支持事务，mq（目前支持spring-boot-amqp中的rabbitmq）传输，以及基于mq的简单的分布式事务

### Spring boot 集成

- 启用注解`@EnableArkEvent`

- 定义事件
```
public class SampleEvent implements ArkEvent {

}
```
- 定义消费者
```
@Component
@OnEventType(SampleEvent.class)
public class SampleEventSubscriber implements ArkEventSubscriber {

    @Override
    public void onSubscribe(ArkEvent event, Map<Object, Object> map) throws Throwable {
        
    }
}
```
- 发布事件
```
new SampleEvent().publish();
```
### 事务支持

- 启用注解`@EnableArkEvent(transaction = true)`

- 事务消费者
```
@Component
@EventTransaction
@OnEventType(SampleEvent.class)
public class SampleEventSubscriber implements ArkEventSubscriber {

    @Override
    public void onSubscribe(ArkEvent event, Map<Object, Object> map) throws Throwable {
        
    }
}
```
- 事务异常处理，@Transactional中会直接抛出异常触发回滚，否则调用`handleTransactionException()`方法，默认将会打印异常堆栈
```
@Component
public class SampleTransactionEventExceptionHandler extends TransactionArkEventExceptionHandler {

    public EnerTransactionEventExceptionHandler(ArkEventTransactionManager transactionManager) {
        super(transactionManager);
    }

    //默认打印日志
    @Override
    public void handleTransactionException(ArkEventException ex) {
        //
    }
}
```

### MQ支持

- 启用注解`@EnableArkEvent(mq = true)`

- 标记MQ事件
```
@MqEvent
public class SampleEvent implements ArkEvent {

}
```
- 定义MQ事件通道，如需要服务A发布消息，服务B接收消息，则需要同时配置服务A和服务B的`ArkMqEventModulesProvider`，并都返回服务A和服务B的`spring.application.name`
```
@Component
public class SampleMqEventModulesProvider implements ArkMqEventModulesProvider {

    @Override
    public String[] getModules() {
        return new String[] {"service-A", "service-B"};
    }
}
```

### MQ事务支持

- 最终一致性，在消费者的服务中，标记了`@EventTransaction`的消费者在异常时也会调用`TransactionArkEventExceptionHandler#handleTransactionException()`作为补救措施
