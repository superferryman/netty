/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : chat

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2019-05-12 15:59:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `groupId` int(11) NOT NULL AUTO_INCREMENT COMMENT '群 id',
  `groupName` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '群名称',
  `groupDesc` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '群描述',
  `creatorId` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '群主 id',
  `avator` int(10) NOT NULL COMMENT '群头像编号',
  PRIMARY KEY (`groupId`),
  UNIQUE KEY `groupName` (`groupName`),
  KEY `creatorId` (`creatorId`),
  CONSTRAINT `group_ibfk_1` FOREIGN KEY (`creatorId`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=10002 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of group
-- ----------------------------
INSERT INTO `group` VALUES ('10001', '测试用群', '这好像是一个群', '1001', '5');

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
  `userId` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '用户 id',
  `friendId` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '用户 id 对应的好友 id',
  PRIMARY KEY (`userId`,`friendId`),
  KEY `friendId` (`friendId`),
  CONSTRAINT `friend_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userid`),
  CONSTRAINT `friend_ibfk_2` FOREIGN KEY (`friendId`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES ('1002', '1001');
INSERT INTO `friend` VALUES ('1003', '1001');
INSERT INTO `friend` VALUES ('1001', '1002');
INSERT INTO `friend` VALUES ('1003', '1002');
INSERT INTO `friend` VALUES ('1001', '1003');
INSERT INTO `friend` VALUES ('1002', '1003');

-- ----------------------------
-- Table structure for groupmember
-- ----------------------------
DROP TABLE IF EXISTS `groupmember`;
CREATE TABLE `groupmember` (
  `groupId` int(11) NOT NULL COMMENT '群组 id',
  `userId` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '群中的用户 id',
  `memberNickname` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '群成员名称',
  `avator` int(10) NOT NULL COMMENT '群成员头像',
  PRIMARY KEY (`groupId`,`userId`),
  KEY `userId` (`userId`),
  CONSTRAINT `groupmember_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userid`),
  CONSTRAINT `groupmember_ibfk_2` FOREIGN KEY (`groupId`) REFERENCES `group` (`groupid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of groupmember
-- ----------------------------
INSERT INTO `groupmember` VALUES ('10001', '1001', 'admin', '0');
INSERT INTO `groupmember` VALUES ('10001', '1002', 'user', '1');
INSERT INTO `groupmember` VALUES ('10001', '1003', 'test', '9');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '消息编号，用于排序',
  `messageType` tinyint(5) NOT NULL COMMENT '消息类型，0标识单聊，1标识群聊',
  `senderId` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '发送方 id',
  `receiverId` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '接收方 id',
  `content` varchar(1024) COLLATE utf8_bin NOT NULL COMMENT '消息内容',
  `senderName` varchar(30) COLLATE utf8_bin NOT NULL,
  `senderAvator` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '0', '1001', '1002', 'hh');
INSERT INTO `message` VALUES ('2', '1', '1002', '10001', '哦豁');
INSERT INTO `message` VALUES ('3', '0', '1002', '1001', 'emm');
INSERT INTO `message` VALUES ('4', '1', '1001', '10001', '行吧');
INSERT INTO `message` VALUES ('5', '1', '1001', '10001', '恶？魔');
INSERT INTO `message` VALUES ('6', '0', '1001', '1002', '哦豁');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '用户id，TCP唯一标识',
  `username` varchar(30) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '用户密码',
  `avator` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '头像，用0-n号表示第几张头像',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1001', 'admin', '123456', '0');
INSERT INTO `user` VALUES ('1002', 'user', '123456', '1');
INSERT INTO `user` VALUES ('1003', 'test', '123456', '9');