/*
 Navicat Premium Dump SQL

 Source Server         : Edu_Sphere
 Source Server Type    : MySQL
 Source Server Version : 80037 (8.0.37)
 Source Host           : 10.168.89.204:3306
 Source Schema         : Edu_Sphere

 Target Server Type    : MySQL
 Target Server Version : 80037 (8.0.37)
 File Encoding         : 65001

 Date: 07/05/2025 09:45:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for powers
-- ----------------------------
DROP TABLE IF EXISTS `powers`;
CREATE TABLE `powers`
(
    `power_id`   int                                                           NOT NULL AUTO_INCREMENT COMMENT '权限id',
    `power_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '中文名',
    PRIMARY KEY (`power_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限的名字'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of powers
-- ----------------------------
INSERT INTO `powers`
VALUES (null, '学生端进入');
INSERT INTO `powers`
VALUES (null, '教师端进入');
INSERT INTO `powers`
VALUES (null, '管理员端进入');

-- ----------------------------
-- Table structure for role_power
-- ----------------------------
DROP TABLE IF EXISTS `role_power`;
CREATE TABLE `role_power`
(
    `role_id`  int NULL DEFAULT NULL COMMENT '角色id',
    `power_id` int NULL DEFAULT NULL COMMENT '权限id'
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '每个角色能做什么，一般来说给一个用户，返回一个权限集合吧'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_power
-- ----------------------------
INSERT INTO `role_power`
VALUES (1, 1);
INSERT INTO `role_power`
VALUES (2, 1);
INSERT INTO `role_power`
VALUES (2, 2);
INSERT INTO `role_power`
VALUES (3, 1);
INSERT INTO `role_power`
VALUES (3, 2);
INSERT INTO `role_power`
VALUES (3, 3);

-- ----------------------------
-- Table structure for role_user
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user`
(
    `user_id` int NULL DEFAULT NULL COMMENT '用户id',
    `role_id` int NULL DEFAULT NULL COMMENT '角色id'
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户和角色的对应表，一个用户可以有多个角色'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_user
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`
(
    `role_id`   int                                                           NOT NULL AUTO_INCREMENT COMMENT '角色id',
    `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '中文名',
    PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色的名字，以免忘记'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles`
VALUES (null, '学生');
INSERT INTO `roles`
VALUES (null, '教师');
INSERT INTO `roles`
VALUES (null, '管理员');
INSERT INTO `roles`
VALUES (null, '图书管理员');
INSERT INTO `roles`
VALUES (null, '财务');
INSERT INTO `roles`
VALUES (null, '数学老师');
INSERT INTO `roles`
VALUES (null, '英语老师');
INSERT INTO `roles`
VALUES (null, '计算机老师');
INSERT INTO `roles`
VALUES (null, '班主任');
INSERT INTO `roles`
VALUES (null, '无权限人员');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `user_id`       bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `user_name`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
    `user_email`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电子邮件',
    `user_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户密码',
    `user_token`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '当前密钥',
    PRIMARY KEY (`user_id`) USING BTREE,
    UNIQUE INDEX `users_pk` (`user_name` ASC) USING BTREE,
    UNIQUE INDEX `users_pk_2` (`user_email` ASC) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users`
VALUES (null, '李氏民', '3115089759@qq.com', 'Huawei@123', 'ce7275f8-ee5a-49a4-95ea-359863953bad');
INSERT INTO `users`
VALUES (null, '李翔', 'lx31150lx@2925.com', 'Huawei@123', 'a6bb4eb8-a043-40f1-b34d-18f1502e2406');

-- ----------------------------
-- Table structure for verification_codes
-- ----------------------------
DROP TABLE IF EXISTS `verification_codes`;
CREATE TABLE `verification_codes`
(
    `code_id`      bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '主键，验证码id',
    `code_number`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '验证码内容',
    `code_email`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '邮箱',
    `expiry_date`  datetime(6)                                                   NOT NULL COMMENT '过期时间',
    `code_purpose` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '验证码用途',
    PRIMARY KEY (`code_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '验证码表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of verification_codes
-- ----------------------------
INSERT INTO `verification_codes`
VALUES (null, '825477', '3115089759@qq.com', '2025-05-06 21:08:17.976471', 'REGISTER');
INSERT INTO `verification_codes`
VALUES (null, '893647', '3115089759@qq.com', '2025-05-06 21:41:15.184055', 'RESET_PASSWORD');
INSERT INTO `verification_codes`
VALUES (null, '299794', 'lx31150lx@2925.com', '2025-05-06 22:46:37.474684', 'REGISTER');
INSERT INTO `verification_codes`
VALUES (null, '315380', 'lx31150lx@2925.com', '2025-05-06 22:47:16.144750', 'LOGIN');
INSERT INTO `verification_codes`
VALUES (null, '767294', 'lx31150lx@2925.com', '2025-05-06 22:47:31.386486', 'RESET_PASSWORD');

SET FOREIGN_KEY_CHECKS = 1;
