//package com.example.bserver.kafka;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
///**
// * kafka消息发送者
// */
//@Component
//public class KafkaSender {
//
//    @Autowired
//    private KafkaTemplate<String,Object> kafkaTemplate;
//
//    /**
//     *  发送文字消息
//     * @param message
//     * @return
//     */
//    public String sendStr(String message){
//        kafkaTemplate.send("kafka-topic-1",message);
//        return message;
//    }
//}
//
