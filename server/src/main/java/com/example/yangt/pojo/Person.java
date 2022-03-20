package com.example.yangt.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Component
@PropertySource(value={"classpath:person.properties"})
@ConfigurationProperties(value = "person")
public class Person implements Serializable {

    private String username;

    private Integer age;

    private List<String> list;

    private Map<String,String> map;
}
