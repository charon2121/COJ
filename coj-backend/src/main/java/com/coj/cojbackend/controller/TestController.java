package com.coj.cojbackend.controller;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class TestController {
    @GetMapping("/health")
    public String health() {
        return "ok";
    }

    @GetMapping("/userId")
    public String userId() {
        long workerId = 1;
        long datacenterId = 1;

        Snowflake snowflake = IdUtil.getSnowflake(workerId, datacenterId);

        return String.valueOf(snowflake.nextId());
    }

    @GetMapping("/longpoll")
    public String longpoll() throws JsonProcessingException {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Person person = new Person("coj", 18);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(person);
    }
}


class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}