package com.github.linyuzai.arkevent.sample2;

import com.github.linyuzai.arkevent.autoconfigure.EnableArkEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableArkEvent
@SpringBootApplication
public class ArkEventSample2Application {

    public static void main(String[] args) {
        SpringApplication.run(ArkEventSample2Application.class, args);
    }

}
