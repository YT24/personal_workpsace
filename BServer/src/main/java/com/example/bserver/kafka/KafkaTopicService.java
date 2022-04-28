package com.example.bserver.kafka;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Component
public class KafkaTopicService {

    @Autowired
    private AdminClient adminClient;


    /**
     * 创建topic
     * @param topicName
     * @return
     */
    public void createTopic(String topicName) {
        // 2 : 两个分区， 1 : 一个副本
        NewTopic topic = new NewTopic(topicName, 2, (short) 1);
        adminClient.createTopics(Arrays.asList(topic));
    }


    /**
     * 查询topic
     * @param topicName
     * @return
     */
    public String queryTopic(String topicName) {
        DescribeTopicsResult result = adminClient.describeTopics(Arrays.asList(topicName));
        StringBuffer sb = new StringBuffer("topic信息:");
        try {
            result.all().get().forEach((k,v)->sb.append("key").append(k).append(";v:").append(v));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    /**
     * 查看 topic 列表
     * @return
     */
    public List<String> queryTopicList() throws ExecutionException, InterruptedException {
        List list = new ArrayList();
        ListTopicsResult listTopicsResult = adminClient.listTopics();
        KafkaFuture<Collection<TopicListing>> listings = listTopicsResult.listings();
        Collection<TopicListing> topicListings = listings.get();
        for (TopicListing topicListing : topicListings) {
            list.add(topicListing.toString());
        }
        return list;
    }

}
