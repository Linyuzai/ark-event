package com.github.linyuzai.arkevent.sample3;

import com.github.linyuzai.arkevent.autoconfigure.EnableArkEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableArkEvent
@SpringBootApplication
public class ArkEventSample3Application {

    public static void main(String[] args) {
        SpringApplication.run(ArkEventSample3Application.class, args);
    }

}
