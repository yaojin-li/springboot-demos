-- --------------------------------------------------------
-- 主机:                           192.168.100.130
-- 服务器版本:                        5.7.32-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  10.2.0.5704
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 springdemo 的数据库结构
CREATE DATABASE IF NOT EXISTS `springdemo` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `springdemo`;

-- 导出  表 springdemo.images 结构
CREATE TABLE IF NOT EXISTS `images` (
  `ID` int(10) NOT NULL AUTO_INCREMENT COMMENT '图片ID',
  `TITLE` varchar(100) NOT NULL COMMENT '图片名称',
  `TYPE` varchar(10) NOT NULL COMMENT '图片类型',
  `UUID` varchar(100) NOT NULL COMMENT '图片唯一ID',
  `REMARK` varchar(100) DEFAULT NULL COMMENT '表备注',
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `NOTE1` varchar(100) DEFAULT NULL COMMENT '扩展字段1',
  `NOTE2` varchar(100) DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='图片相关';

-- 正在导出表  springdemo.images 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
INSERT INTO `images` (`ID`, `TITLE`, `TYPE`, `UUID`, `REMARK`, `CREATE_TIME`, `UPDATE_TIME`, `NOTE1`, `NOTE2`) VALUES
	(1, '33.JPG', 'PNG', 'c30a90eb-0acc-4b73-935d-ec52976e45fb', '备注测试111', '2020-12-16 19:39:32', '2020-12-16 19:39:32', NULL, NULL);
/*!40000 ALTER TABLE `images` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
