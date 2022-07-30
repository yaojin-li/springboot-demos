/*
 Navicat Premium Data Transfer

 Source Server         : localhost root&123456789
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : springboot-demos

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 28/02/2021 18:49:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for excel_info
-- ----------------------------
DROP TABLE IF EXISTS `excel_info`;
CREATE TABLE `excel_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` int(1) NULL DEFAULT NULL,
  `borrow_num` decimal(20, 0) NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `note` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Excel文件demo' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of excel_info
-- ----------------------------
INSERT INTO `excel_info` VALUES (14, '张三', 1, 123456789, 'test!@#', '2021-02-28 18:46:02', '2021-02-28 18:46:02', '435');
INSERT INTO `excel_info` VALUES (15, '李四', 0, 841315131225, 'ucosang#@%', '2021-02-28 18:46:02', '2021-02-28 18:46:02', '798');
INSERT INTO `excel_info` VALUES (16, '王五', 1, 123456789, 'test!@#', '2021-02-28 18:46:02', '2021-02-28 18:46:02', '123');
INSERT INTO `excel_info` VALUES (17, '赵六', 0, 841315131225, 'ucosang#@%', '2021-02-28 18:46:02', '2021-02-28 18:46:02', '234');

SET FOREIGN_KEY_CHECKS = 1;
