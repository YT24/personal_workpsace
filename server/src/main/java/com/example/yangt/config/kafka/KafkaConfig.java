package com.example.yangt.config.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Properties;

//@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String server;
    @Value("${spring.kafka.consumer.key-deserializer}")
    private String kKeserializer;
    @Value("${spring.kafka.consumer.value-deserializer}")
    private String vKeserializer;
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;
    @Value("${spring.kafka.producer.key-serializer}")
    private String key;
    @Value("${spring.kafka.producer.value-serializer}")
    private String value;


//    @Bean
    public KafkaConsumer get(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers",server);
        properties.put("key.deserializer",kKeserializer);
        properties.put("value.deserializer",vKeserializer);
        properties.put("group.id",groupId);
       return new KafkaConsumer(properties);

    }


//    @Bean
    public KafkaProducer<String,String> producerGet(Properties properties){
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,key);
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,value);
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,server);

      /*  // 重试次数，0为不启用重试机制
        properties.put(ProducerConfig.RETRIES_CONFIG, 0);
        //同步到副本, 默认为1
        // acks=0 把消息发送到kafka就认为发送成功
        // acks=1 把消息发送到kafka leader分区，并且写入磁盘就认为发送成功
        // acks=all 把消息发送到kafka leader分区，并且leader分区的副本follower对消息进行了同步就任务发送成功
        properties.put(ProducerConfig.ACKS_CONFIG, 1);
        // 生产者空间不足时，send()被阻塞的时间，默认60s
        properties.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 6000);
        // 控制批处理大小，单位为字节
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 4096);
        // 批量发送，延迟为1毫秒，启用该功能能有效减少生产者发送消息次数，从而提高并发量
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        // 生产者可以使用的总内存字节来缓冲等待发送到服务器的记录
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 40960);
        // 消息的最大大小限制,也就是说send的消息大小不能超过这个限制, 默认1048576(1MB)
        properties.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG,1048576);
        // 压缩消息，支持四种类型，分别为：none、lz4、gzip、snappy，默认为none。
        // 消费者默认支持解压，所以压缩设置在生产者，消费者无需设置。
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG,"none");*/

        //添加拦截器
        properties.setProperty(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, "com.example.yangt.middleware.kafka.KafkaInterceptor");


        //2.retries使用建议
        //发送失败重试
        //使用retries的默认值交给使用方自己去控制，结果往往是不处理。所以通用设置建议设置如下：
        //retries = Integer.MAX_VALUE
        //max.in.flight.requests.per.connection = 1
        properties.setProperty(ProducerConfig.RETRIES_CONFIG,String.valueOf(Integer.MAX_VALUE));
        properties.setProperty(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION,String.valueOf(1));

        KafkaProducer<String,String> kafkaProducer = new KafkaProducer<String, String>(properties);
        return kafkaProducer;
    }

//    @PostConstruct
    public void initKafka(){

        System.out.println("@PostConstruct initKafka");
    }

}
