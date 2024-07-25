package com.coj.cojbackend.service.user;

import com.coj.cojbackend.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author charon
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2024-07-17 19:45:01
*/
public interface UserService extends IService<User> {
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户
     */
    User getUserByUsername(String username);
}
