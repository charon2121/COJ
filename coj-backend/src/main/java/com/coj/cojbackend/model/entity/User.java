package com.coj.cojbackend.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 用户ID
     */
    @TableId(value = "user_id")
    private Long userId;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 用户昵称昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 性别 0:保密 1:男 2:女
     */
    @TableField(value = "gender")
    private Integer gender;

    /**
     * 生日
     */
    @TableField(value = "birthday")
    private LocalDate birthday;

    /**
     * 个人简介
     */
    @TableField(value = "introduction")
    private String introduction;

    /**
     * 加密后的用户密码
     */
    @TableField(value = "password_hash")
    private String passwordHash;

    /**
     * 学校
     */
    @TableField(value = "school")
    private String school;

    /**
     * 用户手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 用户邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * github地址
     */
    @TableField(value = "github")
    private String github;

    /**
     * 个人博客地址
     */
    @TableField(value = "blog")
    private String blog;

    /**
     * 用户头像地址
     */
    @TableField(value = "avatar_url")
    private String avatarUrl;

    /**
     * 用户权限字符串
     */
    @TableField(value = "permission")
    private String permission;

    /**
     * 用户状态 0:正常 1:禁用
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 用户偏好语言
     */
    @TableField(value = "preference_language")
    private Integer preferenceLanguage;

    /**
     * 提交题目次数
     */
    @TableField(value = "submit_count")
    private Integer submitCount;

    /**
     * 解决题目数量
     */
    @TableField(value = "solved_count")
    private Integer solvedCount;

    /**
     * 注册时间
     */
    @TableField(value = "register_time")
    private Date registerTime;

    /**
     * 最后登陆时间
     */
    @TableField(value = "last_login")
    private Date lastLogin;

    /**
     * 最后登录IP
     */
    @TableField(value = "last_login_ip")
    private String lastLoginIp;

    /**
     * 最后在线时间
     */
    @TableField(value = "last_online")
    private Date lastOnline;

    /**
     * 是否删除 0:未删除 1:已删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_create")
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    @TableField(value = "gmt_modified")
    private LocalDateTime gmtModified;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
            && (this.getIntroduction() == null ? other.getIntroduction() == null : this.getIntroduction().equals(other.getIntroduction()))
            && (this.getPasswordHash() == null ? other.getPasswordHash() == null : this.getPasswordHash().equals(other.getPasswordHash()))
            && (this.getSchool() == null ? other.getSchool() == null : this.getSchool().equals(other.getSchool()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getGithub() == null ? other.getGithub() == null : this.getGithub().equals(other.getGithub()))
            && (this.getBlog() == null ? other.getBlog() == null : this.getBlog().equals(other.getBlog()))
            && (this.getAvatarUrl() == null ? other.getAvatarUrl() == null : this.getAvatarUrl().equals(other.getAvatarUrl()))
            && (this.getPermission() == null ? other.getPermission() == null : this.getPermission().equals(other.getPermission()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getPreferenceLanguage() == null ? other.getPreferenceLanguage() == null : this.getPreferenceLanguage().equals(other.getPreferenceLanguage()))
            && (this.getSubmitCount() == null ? other.getSubmitCount() == null : this.getSubmitCount().equals(other.getSubmitCount()))
            && (this.getSolvedCount() == null ? other.getSolvedCount() == null : this.getSolvedCount().equals(other.getSolvedCount()))
            && (this.getRegisterTime() == null ? other.getRegisterTime() == null : this.getRegisterTime().equals(other.getRegisterTime()))
            && (this.getLastLogin() == null ? other.getLastLogin() == null : this.getLastLogin().equals(other.getLastLogin()))
            && (this.getLastLoginIp() == null ? other.getLastLoginIp() == null : this.getLastLoginIp().equals(other.getLastLoginIp()))
            && (this.getLastOnline() == null ? other.getLastOnline() == null : this.getLastOnline().equals(other.getLastOnline()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getGmtModified() == null ? other.getGmtModified() == null : this.getGmtModified().equals(other.getGmtModified()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        result = prime * result + ((getIntroduction() == null) ? 0 : getIntroduction().hashCode());
        result = prime * result + ((getPasswordHash() == null) ? 0 : getPasswordHash().hashCode());
        result = prime * result + ((getSchool() == null) ? 0 : getSchool().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getGithub() == null) ? 0 : getGithub().hashCode());
        result = prime * result + ((getBlog() == null) ? 0 : getBlog().hashCode());
        result = prime * result + ((getAvatarUrl() == null) ? 0 : getAvatarUrl().hashCode());
        result = prime * result + ((getPermission() == null) ? 0 : getPermission().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getPreferenceLanguage() == null) ? 0 : getPreferenceLanguage().hashCode());
        result = prime * result + ((getSubmitCount() == null) ? 0 : getSubmitCount().hashCode());
        result = prime * result + ((getSolvedCount() == null) ? 0 : getSolvedCount().hashCode());
        result = prime * result + ((getRegisterTime() == null) ? 0 : getRegisterTime().hashCode());
        result = prime * result + ((getLastLogin() == null) ? 0 : getLastLogin().hashCode());
        result = prime * result + ((getLastLoginIp() == null) ? 0 : getLastLoginIp().hashCode());
        result = prime * result + ((getLastOnline() == null) ? 0 : getLastOnline().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtModified() == null) ? 0 : getGmtModified().hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", userId=" + userId +
                ", username=" + username +
                ", nickname=" + nickname +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", introduction=" + introduction +
                ", passwordHash=" + passwordHash +
                ", school=" + school +
                ", phone=" + phone +
                ", email=" + email +
                ", github=" + github +
                ", blog=" + blog +
                ", avatarUrl=" + avatarUrl +
                ", permission=" + permission +
                ", status=" + status +
                ", preferenceLanguage=" + preferenceLanguage +
                ", submitCount=" + submitCount +
                ", solvedCount=" + solvedCount +
                ", registerTime=" + registerTime +
                ", lastLogin=" + lastLogin +
                ", lastLoginIp=" + lastLoginIp +
                ", lastOnline=" + lastOnline +
                ", isDeleted=" + isDeleted +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}