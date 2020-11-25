package com.github.linyuzai.arkevent.sample1;

import com.github.linyuzai.arkevent.samplevent.SampleArkMqEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample1")
public class Sample1Controller {

    @GetMapping("/publish")
    public void publish() {
        new SampleArkMqEvent("1").publish();
    }
}
