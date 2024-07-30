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
        var res = securityConfig.passwordEncoder().encode("md0oiImej__oieg");
        System.out.println(res);
    }

    @Test
    public void test2() {
        String s = "$2a$10$gx20gC5nA8hJ1.jG/kC2geunUIfYUkmEGQBdw9ZLaO85ATzTkdZvS";
        assert(securityConfig.passwordEncoder().matches("md0oiImej__oieg", s));
    }
}
