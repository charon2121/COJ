package com.coj.cojbackend.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.security.GeneralSecurityException;

@SpringBootTest
public class JwtUtilTest {
    @Test
    public void testGenerateToken() throws GeneralSecurityException, IOException {
        JwtUtil jwtUtil = new JwtUtil();
        String token = jwtUtil.generateToken("test");
        System.out.println(token);
    }

    @Test
    public void testValidateToken() throws GeneralSecurityException, IOException {
        JwtUtil jwtUtil = new JwtUtil();
        System.out.println(jwtUtil.getClaims("eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNzIyMjU2MjkxLCJleHAiOjE3MjI4NjEwOTF9.aK6gvqTqB9I1usLU_TV6sg3rn5ur109gwtAwCrHMB4Knv4F36MtTtG8UTB_PmQ43MDNYnyopY5VUiabURyPAsxUtdm0ecma8BGvdDtwACRJiYZrJ2SBUpFR5sQ9CdZHUtlv3NYa0Eafa_i72JDqeuUtS8ZIMOCQvj9yxLvFMavHyJzV87mfIpMyqTrRaRRhq_2GSR_HhztW6fLuac02s2jB5TOcBX8Aym2Z8uPwQVpMeNk0U3yTRLnnGHZ-Y_JkJCjTdxPevKCZbL5ZPaT8on32AuSZBmXigWUhZNX8Bi8EYU7oJlT41I14g4CtWPjxcAiqcZF5dGgtbGr2RCEmoAg\n"));
    }
}
