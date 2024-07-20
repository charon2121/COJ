# 用户表

## user 表

### 表结构

| 字段名              | 字段类型         | 是否为空 | 字段含义                                             |
| ------------------- | ---------------- | -------- | ---------------------------------------------------- |
| user_id             | BIGINT UNSIGNED  | 否       | 用户 ID，内部使用，对外不可见；使用雪花 ID           |
| username            | CHAR(32)         | 否       | 用户名，对外展示，系统生成；不重复、不能修改、不为空 |
| nickname            | VARCHAR(16)      | 否       | 用户昵称，对外展示使用，用户可修改                   |
| gender              | TINYINT UNSIGNED | 否       | 性别 0:保密 1:男 2:女                                |
| birthday            | DATE             |          | 用户生日                                             |
| introduction        | VARCHAR(255)     |          | 个人简介                                             |
| password_hash       | VARCHAR(255)     | 否       | 加密后的用户密码                                     |
| school              | VARCHAR(32)      |          | 学校                                                 |
| phone               | VARCHAR(16)      |          | 用户手机号                                           |
| email               | VARCHAR(128)     |          | 用户邮箱                                             |
| github              | VARCHAR(128)     |          | github地址                                           |
| blog                | VARCHAR(255)     |          | 博客地址                                             |
| avatar_url          | VARCHAR(255)     |          | 用户头像地址                                         |
| permission          | VARCHAR(64)      | 否       | 用户权限字符串                                       |
| status              | TINYINT UNSIGNED | 否       | 用户状态 0:正常 1:禁用                               |
| preference_language | INT UNSIGNED     |          | 用户偏好语言，外键，对应了 language 表的 id          |
| submit_count        | INT UNSIGNED     | 否       | 提交题目次数                                         |
| solved_count        | INT UNSIGNED     | 否       | 解决题目数量                                         |
| register_time       | DATETIME         |          | 注册时间                                             |
| last_login          | DATETIME         |          | 最后登录时间                                         |
| last_login_ip       | VARCHAR(64)      |          | 最后登录IP                                           |
| last_online         | DATETIME         |          | 最后在线时间                                         |
| is_deleted          | TINYINT UNSIGNED | 否       | 是否删除 0:未删除 1:已删除                           |
| gmt_create          | DATETIME         | 否       | 创建时间                                             |
| gmt_modified        | DATETIME         | 否       | 修改时间                                             |

### 建表语句

```sql
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
```

## 代码表

系统中有很多的代码需要存储。

| 字段名       | 字段类型         | 是否允许为空 | 字段含义                   |
| ------------ | ---------------- | ------------ | -------------------------- |
| code_id      | INT UNSIGNED     | 否           | 代码ID                     |
| language_id  | INT UNSIGNED     | 否           | 语言ID                     |
| code_content | TEXT             | 否           | 代码内容                   |
| is_deleted   | TINYINT UNSIGNED | 否           | 是否删除 0:未删除 1:已删除 |
| gmt_create   | DATETIME         | 否           | 创建时间                   |
| gmt_modified | DATETIME         | 否           | 修改时间                   |


### 建表语句

```sql
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
```

## 题目表

题目表和语言表是多对多的关系，

修改部分内容。

既然要做 OJ，就应该做一个功能完善的。

完善应该从那些方面体现。

1. 支持多种语言，可扩展。
2. 支持两种模式，自己处理输入输出的 ACM 模式和 LeetCode 那种核心代码模式（OI）不用自己处理输入输出。
3. 对于如何判断题目的正确性，其实只有两个大的类别，一是通过比对用户输出和正确输出，二是通过执行一段特殊的代码来判断。（spj）

在数据库设计阶段，比较麻烦的是两种ACM 模式和 LeetCode 那种核心代码模式的题目的处理，是不是应该放到同一张表里。

一般来说，核心代码模式由于没有输入输出，题目的所有的描述都是放在 description 字段里。

而 ACM 模式，题目的描述放在 description 字段里，输入输出还存在描述，需要放在 input_description、output_description（hustoj，hoj，包括 鱼皮 的 OJ 都是这么设计的）。

并且这三个 OJ 都只做了 ACM 模式的 OJ。如果按照 ACM 模式的设计，那么核心代码模式的记录就会多出来两个空白字段。

这还不是最严重的。在 ACM 模式下，题目还存在输入示例，输出示例，有些题目还存在好几组输入示例和输出示例，输入输出示例往往还伴随着解释。

即：

ACM 模式 的 description 和 input_description、output_description 统一划分到 description 字段。

ACM 模式的 description 划分完毕了之后。

### 表格

| 字段名         | 字段类型         | 字段含义                                                      |
| -------------- | ---------------- | ------------------------------------------------------------- |
| problem_id     | INT UNSIGNED     | 主键，题目 ID                                                 |
| title          | VARCHAR(255)     | 题目标题                                                      |
| slug           | VARCHAR(512)     | 题目标识，用于创建友好链接，用于反爬虫                        |
| description    | TEXT             | 题目描述，包含输入描述和输出描述                              |
| author         | BIGINT UNSIGNED  | 外键，题目作者 ID，                                           |
| is_spj         | TINYINT UNSIGNED | 特殊判题，枚举值，0：不是，1：是                              |
| mode           | TINYINT UNSIGNED | 题目类型，枚举值，0：ACM 模式，1：核心代码模式                |
| difficulty     | TINYINT UNSIGNED | 题目难度，枚举值，0：入门，1：简单，2：中等，3：困难，4：极难 |
| status         | TINYINT UNSIGNED | 题目状态，枚举值，0：内测中，1：已发布，2：已废弃             |
| scene          | TINYINT UNSIGNED | 题目场景，枚举值，0：全站使用，其余场景自定义                 |
| scene_id       | INT UNSIGNED     | 题目场景 ID，题目出现的场景 ID                                |
| tags           | VARCHAR(512)     | 题目标签，字符串数组，存储 tags 表中的主键                    |
| hints          | TEXT             | 题目提示，字符串数组，用于存储题目的提示，可能会有多个        |
| spj_code_id    | INT UNSIGNED     | 特殊判题代码，用于特殊判题的题目                              |
| test_code_id   | INT UNSIGNED     | 测试代码，用于生成测试用例                                    |
| check_code_id  | INT UNSIGNED     | 检查代码，用于检查测试代码生成的测试用例是否符合要求          |
| submit_count   | INT UNSIGNED     | 题目提交次数                                                  |
| accepted_count | INT UNSIGNED     | 题目通过次数                                                  |
| is_deleted     | TINYINT UNSIGNED | 是否删除，枚举值，0：未删除 1：已删除                         |
| gmt_create     | DATETIME         | 创建时间                                                      |
| gmt_modified   | DATETIME         | 修改时间                                                      |

注释：

**1、slug**

slug 是题目标识，用于唯一确定一道题目。在许多在线判题系统（OJ）上，题目通常用数字编号，例如 problem_id=1006，这样就可以唯一确定一道题目。在添加题目的过程中，problem_id 通常被设计为主键并递增，这样如果写一个爬虫，通过 for 循环就能爬取一个 OJ 上的所有题目。

为了保护题目内容，并且使题目链接更加语义化，使用字符串标识题目是一个更好的选择。这样链接会更直观，例如 "/problem/two-sum"。

**2、is_spj**

用于标识本道题是否属于特殊判题。一般来说，在线判题系统（OJ）在判题时，最常见的方式是通过输入输出比对。

具体做法是使用一套正确的代码处理输入，得到正确的输出。然后再使用用户提交的代码处理相同的输入，得到用户代码的输出。最后，通过**比对**用户输出和正确输出，来判断用户的代码是否正确。

但是有些题目具有特殊要求，例如：“请输出任意一个大于 3 的数字。”，在这种情况下，**无法通过比对输出结果**来判断用户代码的正确性，因为我们不可能把所有大于 3 的数字全列举出来。这时就需要使用特殊判题。

一般来说，特殊判题是借助一段特定的代码来完成的，通常包括三个步骤。

1. 首先获取**题目的输入**，**用户提交代码的输出**，**正确代码的输出**（很少用到）。
2. 将**题目的输入、正确代码的输出、用户提交代码的输出**作为特殊代码的**输入**。
3. 特殊代码通过一段逻辑来判断用户的输出是否符合要求，然后判定用户代码是否通过。

以之前的题目为例，如果用户输出了 4，4 就会成为特殊代码的输入，这时这段特殊代码就会判断该输出确实大于 3，然后判定用户的代码正确，让其通过（AC）。如果用户输出是 2，这段特殊代码就会判定用户代码错误，让其错误（WA）。

这只是一个简单的例子，具体实现特殊判题的思路就是这样。不过在实际应用中，这段特殊的代码可能会更加复杂，有时甚至比题目本身还要难（bushi。

```cpp
/**
 * @param problem_input 题目输入
 * @param correct_output 正确代码的输出
 * @param user_output 用户代码的输出
 * @return bool 判定用户输出是否符合要求
 */
bool spj_code(problem_input, correct_output, user_output) {
    if (user_output > 3) return true; // AC
    return false;  // WA
}
```

### 建表语句

```sql
DROP TABLE IF EXISTS `problem`;

CREATE TABLE `problem` (
    `problem_id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键，题目 ID',
    `title` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '题目标题',
    `slug` VARCHAR(512) NOT NULL DEFAULT '' COMMENT '题目标识，用于创建友好链接，用于反爬虫',
    `description` TEXT NOT NULL DEFAULT '' COMMENT '题目描述，包含输入描述和输出描述',
    `author` BIGINT UNSIGNED DEFAULT NULL COMMENT '作者ID',
    `is_spj` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '特殊判题，枚举值，0：不是，1：是',
    `mode` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '题目类型，枚举值，0：ACM 模式，1：核心代码模式',
    `difficulty` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '题目难度',
    `status` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '题目状态，枚举值，0：内测中，1：已发布，2：已废弃',
    `scene` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '题目场景，枚举值，0：全站使用，其余场景自定义',
    `scene_id` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '题目场景 ID',
    `tags` VARCHAR(512) NOT NULL DEFAULT '' COMMENT '题目标签，字符串数组，存储 tags 表中的主键',
    `hints` TEXT NOT NULL DEFAULT '' COMMENT '题目提示，字符串数组，用于存储题目的提示，可能会有多个',
    `spj_code_id` INT UNSIGNED DEFAULT NULL COMMENT '特殊判题代码，用于特殊判题的题目',
    `test_code_id` INT UNSIGNED DEFAULT NULL COMMENT '测试代码，用于生成测试用例',
    `check_code_id` INT UNSIGNED DEFAULT NULL COMMENT '检查代码，用于检查测试代码生成的测试用例是否符合要求',
    `submit_count` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '提交次数',
    `accepted_count` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '通过次数',
    `is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除，枚举值，0：未删除，1：已删除',
    `gmt_create` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`problem_id`),
    UNIQUE KEY `uk_slug` (`slug`),
    INDEX `idx_problem_id` (`problem_id`),
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT='题目表';
```

## 题目样例输入输出表

### 表格

| 字段名        | 字段类型         | 字段含义                               |
| ------------- | ---------------- | -------------------------------------- |
| problem_id    | INT UNSIGNED     | 主键，题目 ID                          |
| sample_input  | TEXT             | 样例输入                               |
| sample_output | TEXT             | 样例输出                               |
| explanation   | TEXT             | 样例解释                               |
| is_deleted    | TINYINT UNSIGNED | 是否删除，枚举值，0：未删除，1：已删除 |
| gmt_create    | DATETIME         | 创建时间                               |
| gmt_modified  | DATETIME         | 修改时间                               |

### 建表语句

```sql
DROP TABLE IF EXISTS `problem_sample_io`;

CREATE TABLE `problem_sample_io` (
    `problem_sample_io_id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键，样例输入输出 ID',
    `problem_id` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '题目 ID',
    `sample_input` TEXT NOT NULL DEFAULT '' COMMENT '样例输入',
    `sample_output` TEXT NOT NULL DEFAULT '' COMMENT '样例输出',
    `explanation` TEXT NOT NULL DEFAULT '' COMMENT '样例解释',
    `is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除，枚举值，0：未删除，1：已删除',
    `gmt_create` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`problem_sample_io_id`),
    INDEX `idx_problem_id` (`problem_id`)
)
```

## 语言表

### 表格

| 字段名         | 字段类型         | 字段含义                               |
| -------------- | ---------------- | -------------------------------------- |
| language_id    | INT UNSIGNED     | 主键，语言 ID                          |
| name           | VARCHAR(64)      | 编程语言名称                           |
| file_extension | VARCHAR(16)      | 语言扩展名                             |
| compiler       | VARCHAR(64)      | 编译器名称                             |
| version        | VARCHAR(32)      | 语言版本                               |
| is_deleted     | TINYINT UNSIGNED | 是否删除，枚举值，0：未删除，1：已删除 |
| gmt_create     | DATETIME         | 创建时间                               |
| gmt_modified   | DATETIME         | 修改时间                               |

语言表用于存储编程语言的信息，包括语言名称、扩展名、版本等。

### 建表语句

```sql
DROP TABLE IF EXISTS `language`;

CREATE TABLE `language` (
    `language_id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键，语言 ID',
    `name` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '编程语言名称',
    `file_extension` VARCHAR(16) NOT NULL DEFAULT '' COMMENT '语言扩展名',
    `compiler` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '编译器名称',
    `version` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '语言版本',
    `is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除，枚举值，0：未删除，1：已删除',
    `gmt_create` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`language_id`),
)
```

## 题目语言表

### 表格

| 字段名              | 字段类型         | 字段含义                               |
| ------------------- | ---------------- | -------------------------------------- |
| problem_language_id | INT UNSIGNED     | 主键，语言题目 ID                      |
| problem_id          | INT UNSIGNED     | 外键，题目 ID                          |
| language_id         | INT UNSIGNED     | 外键，语言 ID                          |
| template_code_id    | INT UNSIGNED     | 题目模版代码，用于核心代码模式的题目   |
| limit_code_id       | INT UNSIGNED     | 限制代码，用于生成时间和空间限制的代码 |
| standard_code_id    | INT UNSIGNED     | 标准代码，用户生成正确输出的代码       |
| time_limit          | INT UNSIGNED     | 时间限制                               |
| memory_limit        | INT UNSIGNED     | 内存限制                               |
| stack_limit         | INT UNSIGNED     | 栈限制                                 |
| is_deleted          | TINYINT UNSIGNED | 是否删除，枚举值，0：未删除，1：已删除 |
| gmt_create          | DATETIME         | 创建时间                               |
| gmt_modified        | DATETIME         | 修改时间                               |

### sql

```sql
DROP TABLE IF EXISTS `problem_language`;

CREATE TABLE `problem_language` (
    `problem_language_id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键，语言题目 ID',
    `problem_id` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '外键，题目 ID',
    `language_id` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '外键，语言 ID',
    `template_code_id` INT UNSIGNED DEFAULT NULL COMMENT '题目模版代码，用于核心代码模式的题目',
    `limit_code_id` INT UNSIGNED DEFAULT NULL COMMENT '限制代码，用于生成时间和空间限制的代码',
    `standard_code_id` INT UNSIGNED DEFAULT NULL COMMENT '标准代码，用户生成正确输出的代码',
    `time_limit` INT UNSIGNED NOT NULL DEFAULT 1000 COMMENT '时间限制,单位 ms',
    `memory_limit` INT UNSIGNED NOT NULL DEFAULT 65535 COMMENT '内存限制,单位 kb',
    `stack_limit` INT UNSIGNED NOT NULL DEFAULT 1000 COMMENT '栈限制,单位 kb',
    `is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除，枚举值，0：未删除，1：已删除',
    `gmt_create` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`problem_language_id`),
    UNIQUE KEY `uk_problem_language` (`problem_id`, `language_id`),
    INDEX `idex_problem_language` (`problem_id`, `language_id`),
)
```
