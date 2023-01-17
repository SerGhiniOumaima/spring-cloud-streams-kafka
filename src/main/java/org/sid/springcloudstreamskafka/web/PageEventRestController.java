package org.sid.springcloudstreamskafka.web;

import org.sid.springcloudstreamskafka.entities.PageEvent;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;
@RestController
public class PageEventRestController {

        //methode permettant de publier un event dans un topic kafka

        private StreamBridge streamBridge;

    public PageEventRestController(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @GetMapping("/publish/{topic}/{namepage}")
        public PageEvent publish(@PathVariable String topic,@PathVariable String namepage)
        {
            //envoyer un msg au topic kafka
            PageEvent pageEvent=new PageEvent(namepage,Math.random()>0.5?"U1":"U2",new Date(),new Random().nextInt(9000));
            streamBridge.send(topic,pageEvent);
            return pageEvent;

        }

    }
