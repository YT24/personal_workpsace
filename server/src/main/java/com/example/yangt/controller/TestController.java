package com.example.yangt.controller;

import com.example.yangt.middleWare.redis.subscribe.RedisProducer;
import com.example.yangt.pojo.Person;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@RestController
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RedisProducer redisSender;

    @Autowired
    Person person;

    @RequestMapping("/mvc")
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED,isolation = Isolation.READ_UNCOMMITTED)
    public String mvcTest(HttpServletRequest request, HttpServletResponse response){


        return "MVC TEST";
    }

    @RequestMapping("/test1/{id}")
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED,isolation = Isolation.READ_UNCOMMITTED)
    public String index(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response){

        Map<String, Object> map1 = jdbcTemplate.queryForMap("select * from t_user_00 where id = '1'");
//
        Map<String, Object> map2 = jdbcTemplate.queryForMap("select * from t_user_00 where id = '1'");

        jdbcTemplate.execute("update t_user_00 set name  = '12345' where id = '1'");
        //jdbcTemplate.execute("insert into t_user_00 (id,user_id,name,age) values (12,2,'kobe',42)");

        Map<String, Object> map3 = jdbcTemplate.queryForMap("select * from t_user_00 where id = '1'");
        Map<String, Object> map = new HashMap<>();
        map = new ConcurrentHashMap<>();
        map.put("key","value");
        //update();
        return id;
    }

    public void update(){
        jdbcTemplate.execute("update t_user_00 set name  = '12345' where id = '11'");
        int x = 10/0;
        jdbcTemplate.execute("update t_user_00 set name  = '12345' where id = '22'");
    }

    @RequestMapping("/lock/mysql")
    @Transactional(rollbackFor = {Exception.class,NullPointerException.class})
    public String mysqlLock(){

        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from t_user_00");

        jdbcTemplate.execute("update t_user_00 set name  = '12345' where id = '1'");

        return "Success";
    }


    @RequestMapping(value = "/query")
    public String query(HttpServletRequest request, HttpServletResponse response) {
        MDC.put("api_url",request.getRequestURI());
        MDC.put("host_name","spring-cloud-producer");
        MDC.put("error_msg","Success");
        MDC.put("response_code","0");
        log.info("");
        //log.info(">> >> >> spring-cloud-sleuth......producer..... tranceid={},spanid={} >> >> >>", request.getHeader("X-B3-TraceId"), request.getHeader("X-B3-SpanId"));
        return "ok";
    }

    @RequestMapping(value = "/buy/{product}", method = RequestMethod.GET)
    public String buy(@PathVariable String product, HttpServletRequest request, HttpServletResponse response) {
        log.info("TOKEN >> >> >> >> >> >> >>" + request.getHeader("Token"));
        log.info("I want to by :" + product + "   >>>>>>> producer >>>>>>>> two");
        return "I want to by :" + product;
    }

    @PostMapping(value = "/post")
    public String post(@RequestBody Map<String,String> map, HttpServletRequest request, HttpServletResponse response) {
        log.info("param: {}",map);
        log.info(">> >> >> spring-cloud-sleuth......producer..... tranceid={},spanid={} >> >> >>", request.getHeader("X-B3-TraceId"), request.getHeader("X-B3-SpanId"));
        return "ok";
    }

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ChannelTopic topic;


    @GetMapping("/redis")
    public String redis(HttpServletRequest request,HttpServletResponse response) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("system.properties");
        Properties properties = new Properties();
        properties.load(new InputStreamReader(classPathResource.getInputStream(),"UTF-8"));

        ThreadLocal<Person> sThreadLocal = new ThreadLocal<>();
        sThreadLocal.set(new Person());
        sThreadLocal.get();



        redisSender.sendChannelMess("test","12345678");
        return "success";

    }

    public static void main(String[] args) {
        Map<Integer, Integer> ma = new HashMap<>();
        for (int i = 0; i < 17; i++) {
            ma.put(i,i);
        }
        ma = new ConcurrentHashMap<>();
        ma.put(3243,2134);
        ma.put(3243,1234);
        List<String> list = new ArrayList<>();
        for (int i = 0; i <15 ; i++) {
            list.add("1");
            list.get(i);
        }
        List<String> listLink = new LinkedList<>();
        for (int i = 0; i <15 ; i++) {
            listLink.add(i+"");
            list.get(i);
        }
        for (int i = 0; i < listLink.size(); i++) {
            System.out.println(listLink.get(i));
        }

        Set<String> set = new HashSet<>();
        set = new TreeSet<>();
        set.add("1");
    }

}