/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2016-09-15 17:42:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_goods`
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goodsname` varchar(200) DEFAULT NULL,
  `goodsspec` varchar(200) DEFAULT NULL,
  `CostPrice` decimal(10,2) DEFAULT NULL,
  `retailPrice` decimal(10,2) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `modifytime` datetime DEFAULT NULL,
  `createid` bigint(20) DEFAULT NULL,
  `modifyid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES ('1', '一品香', '斤', '10.50', '20.50', '2016-09-15 15:34:36', null, '1', null);
INSERT INTO `t_goods` VALUES ('2', '二品香', '斤', '20.50', '40.50', '2016-09-15 15:59:42', '2016-09-15 15:59:42', '1', '1');

-- ----------------------------
-- Table structure for `t_inventory`
-- ----------------------------
DROP TABLE IF EXISTS `t_inventory`;
CREATE TABLE `t_inventory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goodsid` bigint(20) DEFAULT NULL,
  `goodsnum` bigint(20) DEFAULT NULL,
  `inType` varchar(20) DEFAULT NULL,
  `remark` varchar(2000) DEFAULT NULL,
  `createid` bigint(20) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_inventory
-- ----------------------------
INSERT INTO `t_inventory` VALUES ('2', '1', '5000', 'buy', 'dfgdfg', '1', '2016-09-15 16:30:16');

-- ----------------------------
-- Table structure for `t_sell`
-- ----------------------------
DROP TABLE IF EXISTS `t_sell`;
CREATE TABLE `t_sell` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goodsid` bigint(20) DEFAULT NULL,
  `goodsnum` bigint(20) DEFAULT NULL,
  `goodsPrice` decimal(10,2) DEFAULT NULL,
  `finalPrice` decimal(10,2) DEFAULT NULL,
  `outType` varchar(20) DEFAULT NULL,
  `remark` varchar(2000) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `createid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sell
-- ----------------------------

-- ----------------------------
-- Table structure for `t_storage`
-- ----------------------------
DROP TABLE IF EXISTS `t_storage`;
CREATE TABLE `t_storage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goodsid` bigint(20) DEFAULT NULL,
  `num` bigint(20) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_storage
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `userpass` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `realname` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `address` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `modifytime` datetime DEFAULT NULL,
  `createid` bigint(20) DEFAULT NULL,
  `modifyid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'admin', '系统管理员', '18888888888', '宇宙地球中国', '2016-09-14 10:05:49', '2016-09-14 10:13:03', '1', '1');
