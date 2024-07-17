package com.coj.cojbackend.mapper;

import com.coj.cojbackend.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author charon
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2024-07-17 19:45:01
* @Entity com.coj.cojbackend.model.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
