package com.example.yangt.middleWare.kafka;
/**
 * KafkaProviderService.java
 * Created at 2018-06-06
 * Created by ZuoJian
 * Copyright(C)2018SAIC|SHANGHAI VOLKSWAGEN, All rights reserved.
 */

import org.springframework.stereotype.Service;

/**
 * <p>ClassName:KafkaProviderService.java</p>
 * <p>Description:KafkaProviderService</p>
 * <p>author:ZuoJan</p>
 * <p>Date:2018-6-12</p>
 */
@Service("kafkaService")
public class KafkaProviderService {
//    /**
//     * <p>Field LOG:LOG</p>
//     */
//    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProviderService.class);
//
//    /**
//     * <p>Field kafkaTemplate:kafkaTemplate</p>
//     */
//    //@Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//
//    /**
//     * <p>Description:send message kafka</p>
//     *
//     * @param topic   主题
//     * @param message 消息
//     */
//    public void send(String topic, String message) {
//        ListenableFuture<SendResult<String, String>> res = this.kafkaTemplate.send(topic, message);
//        if (res != null) {
//            try {
//                SendResult r = res.get();//检查result结果集
//                Long offsetIndex = r.getRecordMetadata().offset();
//                if (offsetIndex != null && offsetIndex >= 0) {
//                    LOGGER.debug("code:" + KafkaMesConstant.SUCCESS_CODE);
//                    LOGGER.debug("message:" + KafkaMesConstant.SUCCESS_MES);
//                } else {
//                    LOGGER.debug("code:" + KafkaMesConstant.KAFKA_NO_OFFSET_CODE);
//                    LOGGER.debug("message:" + KafkaMesConstant.KAFKA_NO_OFFSET_MES);
//                }
//            } catch (InterruptedException e) {
//                LOGGER.debug("code:" + KafkaMesConstant.KAFKA_SEND_ERROR_CODE);
//                LOGGER.debug("message:" + KafkaMesConstant.KAFKA_SEND_ERROR_MES);
//            } catch (ExecutionException e) {
//                LOGGER.debug("code:" + KafkaMesConstant.KAFKA_SEND_ERROR_CODE);
//                LOGGER.debug("message:" + KafkaMesConstant.KAFKA_SEND_ERROR_MES);
//            }
//        } else {
//            LOGGER.debug("code:" + KafkaMesConstant.KAFKA_NO_RESULT_CODE);
//            LOGGER.debug("message:" + KafkaMesConstant.KAFKA_NO_RESULT_MES);
//        }
//
//    }
//
//    class KafkaMesConstant {
//
//        public static final String SUCCESS_CODE = "00000";
//        public static final String SUCCESS_MES = "成功";
//
//        /*kakfa-code*/
//        public static final String KAFKA_SEND_ERROR_CODE = "30001";
//        public static final String KAFKA_NO_RESULT_CODE = "30002";
//        public static final String KAFKA_NO_OFFSET_CODE = "30003";
//
//        /*kakfa-mes*/
//        public static final String KAFKA_SEND_ERROR_MES = "发送消息超时,联系相关技术人员";
//        public static final String KAFKA_NO_RESULT_MES = "未查询到返回结果,联系相关技术人员";
//        public static final String KAFKA_NO_OFFSET_MES = "未查到返回数据的offset,联系相关技术人员";
//
//
//    }
//
//    @Autowired
//    KafkaProducer<String,String> kafkaProducer;
//
//    public void sendKafkaProducer(){
//        kafkaProducer.send(new ProducerRecord<String, String>("TEST","12345"),(recordMetadata,e) ->{
//            if(e==null){
//                System.out.println("发送成功~~~~");
//            }else{
//                //记录到数据库
//                System.out.println("发送失败!!!!! ===>"+e.getMessage());
//            }
//        });
//        kafkaProducer.close();
//    }
//
//    public void sendByKafkaTemplate(String topic,String message){
//        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(topic,message);
//        listenableFuture.addCallback(//添加成功发送消息的回调和失败的回调
//                success -> System.out.println("success"),
//                error -> System.out.println("error==>"+ error.getMessage()));
//        listenableFuture.addCallback(success ->{},error ->{});
//        }
}
