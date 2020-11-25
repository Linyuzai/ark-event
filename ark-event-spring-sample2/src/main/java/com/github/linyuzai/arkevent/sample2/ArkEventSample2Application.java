package com.github.linyuzai.arkevent.sample2;

import com.github.linyuzai.arkevent.autoconfigure.mq.EnableArkMqEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableArkMqEvent
@SpringBootApplication
public class ArkEventSample2Application {

    public static void main(String[] args) {
        SpringApplication.run(ArkEventSample2Application.class, args);
    }

}
