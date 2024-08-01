package com.coj.cojbackend.service.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coj.cojbackend.model.entity.User;
import com.coj.cojbackend.mapper.UserMapper;
import com.coj.cojbackend.model.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author charon
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2024-07-17 19:45:01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public BaseResponse userLogin(String username, String password) {
        User user = getUserByUsername(username);
        // 用户名不存在
        return null;
    }
}
