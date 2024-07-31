package com.coj.cojbackend.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class SecurityConfigTest {
    @Autowired
    SecurityConfig securityConfig;
    @Test
    public void test() {
        var res = securityConfig.passwordEncoder().encode("tao072500");
        System.out.println(res);
    }

    @Test
    public void test2() {
        String s = "$2a$10$bVanvHaDg2rrQpPuFdNC9use7cGNRzUUlpcozpqlm/mHGYTruCjTm";
        assert(securityConfig.passwordEncoder().matches("tao072500", s));
    }
}
