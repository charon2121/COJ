package com.coj.cojbackend.service.user;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coj.cojbackend.model.entity.User;
import com.coj.cojbackend.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author charon
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2024-07-17 19:45:01
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
}




