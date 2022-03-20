//package com.example.yangt.middleware.kafka;
//
//import org.apache.kafka.clients.producer.ProducerInterceptor;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.apache.kafka.clients.producer.RecordMetadata;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
//@Component
//public class KafkaInterceptor implements ProducerInterceptor<String,String> {
//
//    private Long successNum =0L;
//    private Long errorNum=0L;
//
//    @Override
//    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
//        //可以重新new 一个record 返回 可以修改他的值等；
//        return record;
//    }
//
//    @Override
//    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
//
//        if(exception == null){
//            successNum++;
//        }else{
//            errorNum++;
//        }
//        System.out.println("发送成功数目===>"+successNum);
//        System.out.println("发送失败数目===>"+errorNum);
//
//    }
//
//    @Override
//    public void close() {
//        // 执行完kafkaProducer.close() 后 进入;
//        System.out.println("kafka 发送 关闭.....");
//    }
//
//    @Override
//    public void configure(Map<String, ?> configs) {
//
//    }
//}
