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

 Date: 25/02/2021 23:04:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for excel_info
-- ----------------------------
DROP TABLE IF EXISTS `excel_info`;
CREATE TABLE `excel_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` int(1) NULL DEFAULT NULL,
  `borrow_num` decimal(10, 0) NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `note` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = 'Excel文件demo' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of excel_info
-- ----------------------------
INSERT INTO `excel_info` VALUES (1, '张三', 1, 1000002, 'abcd1234!@#', '2021-02-25 23:02:12', '2021-02-25 23:03:40', NULL);
INSERT INTO `excel_info` VALUES (2, '李四', 2, 489132132, 'jlsajkg', '2021-02-25 23:02:33', '2021-02-25 23:03:49', NULL);

SET FOREIGN_KEY_CHECKS = 1;
