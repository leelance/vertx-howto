/*
 Navicat Premium Data Transfer

 Source Server         : lance
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : 127.0.0.1:3306
 Source Schema         : v_example

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 09/01/2022 23:24:16
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `user_id`     int NOT NULL AUTO_INCREMENT,
    `username`    varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `password`    varchar(32)                                            DEFAULT NULL,
    `age`         int                                                    DEFAULT NULL,
    `status`      tinyint                                                DEFAULT NULL,
    `create_time` datetime                                               DEFAULT NULL,
    `update_time` datetime                                               DEFAULT NULL,
    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user`
VALUES (1, 'admin', '123456', 20, 1, '2022-01-09 12:41:01', '2022-01-09 12:41:05');
INSERT INTO `t_user`
VALUES (2, 'Benedict Cumberbatch', 'abc@23456', 26, 1, '2022-01-09 13:44:06', '2022-01-09 13:44:06');
COMMIT;

SET
FOREIGN_KEY_CHECKS = 1;
