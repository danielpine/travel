/*
Navicat MySQL Data Transfer

Source Server         : 1_local
Source Server Version : 50731
Source Host           : localhost:3306
Source Database       : travel

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2021-05-25 10:52:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('123', 'admin', '$2a$10$iahjoGH35xAQ6W4YM1Rmo.Jfp35ther4dc5L4gmEuzH.uJ93OFogO');

-- ----------------------------
-- Table structure for `carousel`
-- ----------------------------
DROP TABLE IF EXISTS `carousel`;
CREATE TABLE `carousel` (
  `id` varchar(255) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `scenictime` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of carousel
-- ----------------------------

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL,
  `comment_type` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `root_id` bigint(20) DEFAULT NULL,
  `to_user_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs1slvnkuemjsq2kj4h3vhx7i1` (`post_id`),
  KEY `FKiqwjvq4ot3ap0vvmtfanse4fg` (`root_id`),
  KEY `FK8400ne63906ddj3h6mqmboxp0` (`to_user_id`),
  KEY `FK8kcum44fvpupyw6f5baccx25c` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('666', 'main', '阿达', '2021-05-21 17:41:42', '661', null, '1393432544690593792', '1393432544690593792');
INSERT INTO `comment` VALUES ('667', 'sub', '对对对', '2021-05-21 17:41:46', null, '666', '1393432544690593792', '1393432544690593792');
INSERT INTO `comment` VALUES ('668', 'next', '大大', '2021-05-21 17:41:49', null, '666', '1393432544690593792', '1393432544690593792');
INSERT INTO `comment` VALUES ('673', 'main', '123', '2021-05-24 09:25:20', '661', null, '1393432544690593792', '1393432544690593792');
INSERT INTO `comment` VALUES ('674', 'next', '13', '2021-05-24 09:25:23', null, '666', '1393432544690593792', '1393432544690593792');
INSERT INTO `comment` VALUES ('675', 'next', '123', '2021-05-24 09:25:27', null, '666', '1393432544690593792', '1393432544690593792');
INSERT INTO `comment` VALUES ('676', 'sub', '13', '2021-05-24 09:25:31', null, '673', '1393432544690593792', '1393432544690593792');
INSERT INTO `comment` VALUES ('706', 'main', '22', '2021-05-24 17:48:12', '701', null, '1393432544690593792', '1393432544690593792');
INSERT INTO `comment` VALUES ('724', 'main', 'hhh', '2021-05-24 19:15:45', '697', null, '1393432544690593792', '1393432544690593792');
INSERT INTO `comment` VALUES ('728', 'main', '22', '2021-05-24 19:16:28', '725', null, '1393432544690593792', '1393432544690593792');

-- ----------------------------
-- Table structure for `favorite`
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite` (
  `id` bigint(20) NOT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKgmcgt9cgrsvdegu453v1tp98d` (`user_id`,`post_id`),
  KEY `FKh20v4bwlpu57uv12dl7i2qipe` (`post_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of favorite
-- ----------------------------

-- ----------------------------
-- Table structure for `gallery`
-- ----------------------------
DROP TABLE IF EXISTS `gallery`;
CREATE TABLE `gallery` (
  `id` varchar(255) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gallery
-- ----------------------------

-- ----------------------------
-- Table structure for `hibernate_sequence`
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('758');

-- ----------------------------
-- Table structure for `hotel`
-- ----------------------------
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel` (
  `id` varchar(255) NOT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `bed` int(11) DEFAULT NULL,
  `day` int(11) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `miaoshu` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `star` int(11) DEFAULT NULL,
  `startdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hotel
-- ----------------------------

-- ----------------------------
-- Table structure for `hotel_orders`
-- ----------------------------
DROP TABLE IF EXISTS `hotel_orders`;
CREATE TABLE `hotel_orders` (
  `id` varchar(255) NOT NULL,
  `begin` varchar(255) DEFAULT NULL,
  `end` varchar(255) DEFAULT NULL,
  `payment` decimal(19,2) DEFAULT NULL,
  `paytime` datetime DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `scenicid` varchar(255) DEFAULT NULL,
  `scenicname` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hotel_orders
-- ----------------------------

-- ----------------------------
-- Table structure for `image`
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `id` bigint(20) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe2l07hc93u2bbjnl80meu3rn4` (`post_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of image
-- ----------------------------

-- ----------------------------
-- Table structure for `location`
-- ----------------------------
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `lat` float DEFAULT NULL,
  `lng` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of location
-- ----------------------------

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` varchar(255) NOT NULL,
  `payment` decimal(19,2) DEFAULT NULL,
  `paytime` datetime DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `scenicid` varchar(255) DEFAULT NULL,
  `scenicname` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for `post`
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` bigint(20) NOT NULL,
  `post_time` datetime DEFAULT NULL,
  `post_type` varchar(255) DEFAULT NULL,
  `text` longtext,
  `title` varchar(255) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `topic` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKskbjob42y1o9hnesfkrnj7gbk` (`post_id`),
  KEY `FK72mt33dhhs48hf9gcqrq4fxte` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('753', '2021-05-25 10:03:59', 'moments', '123', null, null, '1393432544690593791', null, 'normal');
INSERT INTO `post` VALUES ('754', '2021-05-25 10:04:08', 'topic', '<p>22</p>', '22', null, '1393432544690593791', '22', 'normal');

-- ----------------------------
-- Table structure for `report`
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report` (
  `id` bigint(20) NOT NULL,
  `report_type` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK9cymd8xyx2o6dovkrgxxxwuna` (`user_id`,`post_id`),
  KEY `FKnuqod1y014fp5bmqjeoffcgqy` (`post_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of report
-- ----------------------------

-- ----------------------------
-- Table structure for `scenic`
-- ----------------------------
DROP TABLE IF EXISTS `scenic`;
CREATE TABLE `scenic` (
  `id` varchar(255) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `contry` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `miaoshu` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `start` int(11) DEFAULT NULL,
  `startdate` datetime DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of scenic
-- ----------------------------

-- ----------------------------
-- Table structure for `thumb`
-- ----------------------------
DROP TABLE IF EXISTS `thumb`;
CREATE TABLE `thumb` (
  `id` bigint(20) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK3lwtwd0p0efxmbwg855dfksbj` (`user_id`,`post_id`),
  KEY `FK38c4n3tepsl98shwfyhc5itvt` (`post_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of thumb
-- ----------------------------
INSERT INTO `thumb` VALUES ('672', '1393432544690593792', '671');
INSERT INTO `thumb` VALUES ('680', '1393432544690593792', '661');
INSERT INTO `thumb` VALUES ('682', '1393432544690593792', '681');
INSERT INTO `thumb` VALUES ('688', '1393432544690593792', '683');
INSERT INTO `thumb` VALUES ('695', '1393432544690593792', '694');
INSERT INTO `thumb` VALUES ('705', '1393432544690593792', '701');
INSERT INTO `thumb` VALUES ('718', '1393432544690593792', '716');
INSERT INTO `thumb` VALUES ('722', '1393432544690593792', '719');
INSERT INTO `thumb` VALUES ('723', '1393432544690593792', '697');
INSERT INTO `thumb` VALUES ('727', '1393432544690593792', '725');
INSERT INTO `thumb` VALUES ('746', '1393432544690593791', '719');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `avator` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1393432544690593792', '22', 'daniel@163.com', '17622123011', 'user', '$2a$10$9DG9vMxxTWszn6sv7NhqiuY0BAPHX0SFEluFniHC56kUuE6O4VIN6', null, '女', '一路向北丶', '/static/upload/00018.jpg');
INSERT INTO `user` VALUES ('1393432544690593791', '22', '123@sina.com', '13122337756', 'user2', '$2a$10$9DG9vMxxTWszn6sv7NhqiuY0BAPHX0SFEluFniHC56kUuE6O4VIN6', null, '男', '月光', null);
