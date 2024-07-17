package com.coj.cojbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coj.cojbackend.model.entity.User;
import com.coj.cojbackend.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@WithMockUser
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test getUser method
     *
     * @throws Exception
     */
    @Test
    public void testGetUser() throws Exception {
        // 创建一个测试用户
        User user = new User();
        user.setUserId(1L);
        user.setUsername("charon");
        user.setPasswordHash("password123");
        user.setGmtCreate(LocalDateTime.now());

        // 模拟 userService.getOne 方法的行为
        when(userService.getOne(any(QueryWrapper.class))).thenReturn(user);

        // 执行 GET 请求并验证结果
        mockMvc.perform(get("/api/user/charon"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("charon"))
                .andExpect(jsonPath("$.passwordHash").value("password123"));
    }
}