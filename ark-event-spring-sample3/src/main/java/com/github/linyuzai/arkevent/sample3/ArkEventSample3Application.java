package com.github.linyuzai.arkevent.sample3;

import com.github.linyuzai.arkevent.autoconfigure.mq.EnableArkMqEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableArkMqEvent
@SpringBootApplication
public class ArkEventSample3Application {

    public static void main(String[] args) {
        SpringApplication.run(ArkEventSample3Application.class, args);
    }

}
