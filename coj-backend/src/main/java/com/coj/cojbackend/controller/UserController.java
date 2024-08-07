package com.coj.cojbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coj.cojbackend.annotation.HandleValidationErrors;
import com.coj.cojbackend.model.entity.User;
import com.coj.cojbackend.model.request.user.LoginRequestBody;
import com.coj.cojbackend.model.response.BaseResponse;
import com.coj.cojbackend.service.user.UserService;
import com.coj.cojbackend.utils.ResponseUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{username}")
    public String getUser(@PathVariable String username) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userService.getOne(queryWrapper);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String jsonString = "{}";

        try {
            // 将对象转换为 JSON 字符串
            jsonString = objectMapper.writeValueAsString(user);
            System.out.println(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    @HandleValidationErrors
    @PostMapping("/login")
    public ResponseEntity<BaseResponse> authenticateUser(@Valid @RequestBody LoginRequestBody loginRequest, BindingResult bindingResult) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        userService.userLogin(username, password);

        return null;
    }
}

