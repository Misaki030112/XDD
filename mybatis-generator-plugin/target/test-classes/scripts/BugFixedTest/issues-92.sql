/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : mybatis-generator-plugin

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2017-06-27 11:17:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb
-- ----------------------------
DROP TABLE IF EXISTS `tb`;
CREATE TABLE `tb` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '注释1',
  `field1` varchar(255) DEFAULT NULL COMMENT '注释2',
  `my_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb
-- ----------------------------
INSERT INTO `tb` VALUES ('1', 'fd1', '1');
INSERT INTO `tb` VALUES ('2', null, '2');
INSERT INTO `tb` VALUES ('3', 'fd3', '3');