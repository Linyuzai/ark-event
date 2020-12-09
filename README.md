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

- 标记MQ事务事件
```
@MqEvent(transaction = true)
public class SampleEvent implements ArkEvent {

}
```

- 最终一致性，在消费者的服务中，标记了`@EventTransaction`的消费者在异常时也会调用`TransactionArkEventExceptionHandler#handleTransactionException()`作为补救措施

### 高级用法

- args处理，可自定义`ArkEventArgsProcessor`处理args
- 条件过滤，可自定义`ArkEventConditionFilter`和`ArkEventConditionFilter.Factory`添加事件到消费者的过滤条件
- 发布策略，可自定义`ArkEventPublishStrategy`和`ArkEventPublishStrategy.Adapter`扩展事件到消费者发布流程
- 异常处理，可自定义`ArkEventExceptionHandler`和`ArkEventExceptionHandler.Adapter`扩展消费者处理异常的补救措施
- 发布顺序，可自定义`ArkEventPublishSorter`定义消费者处理顺序
- 发布监听，可自定义`ArkEventPublishListener`监听发布流程

- 事务管理，可自定义`ArkEventTransactionManager`管理事务状态
- 事务异常，可自定义`TransactionArkEventExceptionHandler`补救事务异常

- MQ配置，可自定义`ArkMqEventProperties`中的参数配置交换机和队列
- 模块通道，可自定义`ArkMqEventModuleIdProvider`和`ArkMqEventModulesProvider`配置事件服务模块通道
- 消息编码，可自定义`ArkMqEventEncoder`编码消息
- 消息解码，可自定义`ArkMqEventDecoder`解码消息
- 消息幂等，可自定义`ArkMqEventIdempotentManager`处理消息幂等
