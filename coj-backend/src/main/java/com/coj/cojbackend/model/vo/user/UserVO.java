package com.coj.cojbackend.model.vo.user;

import lombok.Data;

import java.time.LocalDate;
/**
 * 用户登录展示给前端的数据
 */
@Data
public class UserVO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户昵称昵称
     */
    private String nickname;

    /**
     * 性别 0:保密 1:男 2:女
     */
    private Integer gender;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 个人简介
     */
    private String introduction;

    /**
     * 学校
     */
    private String school;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * github地址
     */
    private String github;

    /**
     * 个人博客地址
     */
    private String blog;

    /**
     * 用户头像地址
     */
    private String avatarUrl;

    /**
     * 用户偏好语言
     */
    private Integer preferenceLanguage;

    /**
     * 提交题目次数
     */
    private Integer submitCount;

    /**
     * 解决题目数量
     */
    private Integer solvedCount;

    /**
     * 注册时间
     */
    private LocalDate registerTime;
}
