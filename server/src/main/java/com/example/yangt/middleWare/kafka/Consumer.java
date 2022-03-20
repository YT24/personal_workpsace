package com.example.yangt.middleWare.kafka;//package com.example.demo.kafka;

import org.springframework.stereotype.Component;

@Component
public class Consumer {

    /*@Autowired
    KafkaConsumer kafkaConsumer;

    @KafkaListener(topics = {"TEST"})
    public void consumer(ConsumerRecord<?, ?> consumerRecord) {
        //判断是否为null
        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        if (kafkaMessage.isPresent()) {
            //得到Optional实例中的值
            Object message = kafkaMessage.get();
            System.err.println("消费消息: scope" + message);
        }
    }*/
}
