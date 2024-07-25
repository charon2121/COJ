package com.coj.cojbackend.controller;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Log4j2
@RestController
@RequestMapping("/api")
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

    @GetMapping("/log")
    public String logtest() {

        log.info("test....");
        return "ok";
    }
}
