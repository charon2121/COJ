DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
    `user_id`  BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
    `username` CHAR(32) NOT NULL COMMENT '用户名',
    `nickname` VARCHAR(16) NOT NULL DEFAULT '' COMMENT '用户昵称昵称',
    `gender` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '性别 0:保密 1:男 2:女',
    `birthday` DATE DEFAULT NULL COMMENT '生日',
    `introduction` VARCHAR(255) DEFAULT NULL COMMENT '个人简介',
    `password_hash` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '加密后的用户密码',
    `school` VARCHAR(32) DEFAULT NULL COMMENT '学校',
    `phone` VARCHAR(16) DEFAULT NULL COMMENT '用户手机号',
    `email` VARCHAR(128) DEFAULT NULL COMMENT '用户邮箱',
    `github` VARCHAR(128) DEFAULT NULL COMMENT 'github地址',
    `blog` VARCHAR(255) DEFAULT NULL COMMENT '个人博客地址',
    `avatar_url` VARCHAR(255) DEFAULT NULL COMMENT '用户头像地址',
    `permission` VARCHAR(32) NOT NULL COMMENT '用户权限字符串',
    `status` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户状态 0:正常 1:禁用',
    `preference_language` INT UNSIGNED DEFAULT NULL COMMENT '用户偏好语言',
    `submit_count` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '提交题目次数',
    `solved_count` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '解决题目数量',
    `register_time` DATETIME DEFAULT NULL COMMENT '注册时间',
    `last_login` DATETIME DEFAULT NULL COMMENT '最后登陆时间',
    `last_login_ip` VARCHAR(64) DEFAULT NULL COMMENT '最后登录IP',
    `last_online` DATETIME DEFAULT NULL COMMENT '最后在线时间',
    `is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除 0:未删除 1:已删除',
    `gmt_create` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `unique_username` (`username`),
    UNIQUE KEY `unique_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

DROP TABLE IF EXISTS `code`;

CREATE TABLE `code` (
    `code_id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '代码ID',
    `language_id` INT UNSIGNED NOT NULL COMMENT '语言ID',
    `code_content` TEXT NOT NULL COMMENT '代码内容',
    `is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除',
    `gmt_create` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`code_id`),
);