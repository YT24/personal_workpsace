package com.example.bserver.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "kafka-topic-1")
    public void onMessage1(String message){
        System.out.println(message);
        log.info("kafka-topic-1接收结果:{}",message);
    }
}