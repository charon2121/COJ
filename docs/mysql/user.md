# 用户表

## user 表

### 表结构

| 字段名              | 字段类型         | 字段含义                                                             |
| ------------------- | ---------------- | -------------------------------------------------------------------- |
| user_id             | BIGINT UNSIGNED  | 用户 ID，内部使用，对外不可见；不重复、不能修改、不为空，使用雪花 ID |
| username            | VARCHAR(32)      | 用户名，对外展示，系统生成；不重复、不能修改、不为空                 |
| nickname            | VARCHAR(64)      | 用户昵称，对外展示使用，系统自动生成，用户可修改，不可空             |
| gender              | TINYINT UNSIGNED | 性别 0:保密 1:男 2:女                                                |
| birthday            | DATE             | 生日                                                                 |
| introduction        | VARCHAR(255)     | 个人简介                                                             |
| password_hash       | VARCHAR(255)     | 加密后的用户密码                                                     |
| school              | VARCHAR(32)      | 学校                                                                 |
| phone               | VARCHAR(32)      | 用户手机号                                                           |
| email               | VARCHAR(128)     | 用户邮箱                                                             |
| github              | VARCHAR(128)     | github地址                                                           |
| blog                | VARCHAR(255)     | 博客地址                                                             |
| avatar_url          | VARCHAR(255)     | 用户头像地址                                                         |
| permission          | VARCHAR(64)      | 用户权限字符串                                                       |
| status              | TINYINT UNSIGNED | 用户状态 0:正常 1:禁用                                               |
| perference_language | INT UNSIGNED     | 用户偏好语言，外键，对应了 language 表的 id                          |
| submit_count        | INT UNSIGNED     | 提交题目次数                                                         |
| solved_count        | INT UNSIGNED     | 解决题目数量                                                         |
| register_time       | DATETIME         | 注册时间                                                             |
| last_login          | DATETIME         | 最后登录时间                                                         |
| last_login_ip       | VARCHAR(64)      | 最后登录IP                                                           |
| last_online         | DATETIME         | 最后在线时间                                                         |
| is_deleted          | TINYINT UNSIGNED | 是否删除 0:未删除 1:已删除                                           |
| gmt_create          | DATETIME         | 创建时间                                                             |
| gmt_modified        | DATETIME         | 修改时间                                                             |

### 建表语句

```sql
CREATE TABLE IF NOT EXISTS `user` (
    `user_id`  BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
    `username` VARCHAR(32) NOT NULL COMMENT '用户名',
    `nickname` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '用户昵称昵称',
    `gender` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '性别 0:保密 1:男 2:女',
    `birthday` DATE DEFAULT NULL COMMENT '生日',
    `introduction` VARCHAR(255) DEFAULT NULL COMMENT '个人简介',
    `password_hash` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '加密后的用户密码',
    `school` VARCHAR(32) DEFAULT NULL COMMENT '学校',
    `phone` VARCHAR(32) DEFAULT NULL COMMENT '用户手机号',
    `email` VARCHAR(128) DEFAULT NULL COMMENT '用户邮箱',
    `github` VARCHAR(128) DEFAULT NULL COMMENT 'github地址',
    `blog` VARCHAR(255) DEFAULT NULL COMMENT '博客地址',
    `avatar_url` VARCHAR(255) DEFAULT NULL COMMENT '用户头像地址',
    `permission` VARCHAR(64) DEFAULT NULL COMMENT '用户权限字符串',
    `status` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户状态 0:正常 1:禁用',
    `perference_language` INT UNSIGNED DEFAULT NULL COMMENT '用户偏好语言',
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
    UNIQUE KEY `UNIQUE_USERNANE` (`username`),
    UNIQUE KEY `UNIQUE_EMAIL` (`email`)
)
```

## user_activity 表

设计 user_activity 的目标是记录用户在系统上特定的操作。具体来说是记录用户执行的操作的类型以及受到这些操作影响的对象。

例如，用户通过了某道算法题，则应该记录为 user passed question。用户提交但是未通过某道算法题，则应该记录为 user attempted question。用户给某个评论点赞，则应该记录为 user liked comment。

user 表示用户，使用 user_id 进行记录。

passed、submitted、liked... 表示用户的执行的操作类型，使用 action_type 进行记录。其中，action_type 选择 unsigned tinyint（0～255）类型，使用不同的数字定义不同的操作，可扩展性强（至少能够支持 256 种操作，完全足够使用）。再通过对 user_id 和 action_type 建立联合索引，能够大幅度提高查询效率。

对于用户操作影响到的对应，使用 affected_object_id 进行记录。

### 表结构

| 字段名             | 字段类型         | 字段含义                   |
| ------------------ | ---------------- | -------------------------- |
| user_activity_id   | BIGINT UNSIGNED  | 用户行为 ID                |
| user_id            | BIGINT UNSIGNED  | 用户 ID                    |
| action_type        | TINYINT UNSIGNED | 行为类型                   |
| affected_object_id | BIGINT UNSIGNED  | 影响对象 ID                |
| is_deleted         | TINYINT UNSIGNED | 是否删除 0:未删除 1:已删除 |
| gmt_create         | DATETIME         | 创建时间                   |
| gmt_modified       | DATETIME         | 修改时间                   |

#### 行为类型枚举

| 枚举值 | 枚举描述       | 影响对象 ID 对应的 对象 ID      |
| ------ | -------------- | ------------------------------- |
| 0      | 通过了某道题目 | affected_object_id = problem_id |
| 1      | 尝试了某道题目 | affected_object_id = problem_id |
| 2      | 给评论点赞     | affected_object_id = comment_id |
| 3      | 给回复点赞     | affected_object_id = reply_id   |

### 建表语句

```sql
CREATE TABLE IF NOT EXISTS `user_activity` (
    `user_activity_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户行为ID',
    `user_id` BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
    `action_type` TINYINT UNSIGNED NOT NULL COMMENT '行为类型',
    `affected_object_id` BIGINT UNSIGNED NOT NULL COMMENT '受影响的对象ID',
    `is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除',
    `gmt_create` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`user_activity_id`),
    INDEX `index_user_action` (`user_id`, `action_type`)
)
```

## 题目表

题目表和语言表是多对多的关系，

修改部分内容。

