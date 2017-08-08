# Host: localhost  (Version: 5.5.23)
# Date: 2015-12-02 14:01:05
# Generator: MySQL-Front 5.3  (Build 4.263)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "admin"
#

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `adminId` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `name` varchar(50) DEFAULT NULL COMMENT '管理员名称',
  `password` varchar(32) DEFAULT NULL COMMENT '密码 MD5加密',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',·
  PRIMARY KEY (`adminId`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='管理员';

#
# Data for table "admin"
#

INSERT INTO `admin` VALUES (1,'admin','e10adc3949ba59abbe56e057f20f883e','2012-08-08 00:00:00'),(2,'admin1','e10adc3949ba59abbe56e057f20f883e','2015-11-12 11:29:02');

#
# Structure for table "config"
#

DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `key` varchar(45) NOT NULL COMMENT 'Key',
  `value` varchar(45) DEFAULT NULL COMMENT '值',
  `description` text COMMENT '描述',
  `createTime` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='网站配置';

#
# Data for table "config"
#

INSERT INTO `config` VALUES ('dinghao_headline_image_height','420','首页头图的高（px）','2012-08-08 00:00:00'),('dinghao_headline_image_width','858','首页头图的宽（px）','2012-08-08 00:00:00'),('dinghao_seo_headline','杭州鼎好科技专用系统','网站口号','2012-08-08 00:00:00'),('dinghao_seo_title','杭州鼎好科技','网站名称','2012-08-08 00:00:00'),('dinghao_static','false','是否启用全站静态化','2012-08-08 00:00:00'),('dinghao_template','front','模板','2012-08-08 00:00:00');

#
# Structure for table "weixin"
#

DROP TABLE IF EXISTS `weixin`;
CREATE TABLE `weixin` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '微信公众号',
  `fetchNum` int(11) NOT NULL DEFAULT '1' COMMENT '抓取天数间隔',
  `status` int(11) DEFAULT NULL COMMENT '删除标记（0未删除，1已删除）',
  `createTime` datetime DEFAULT NULL COMMENT '插入时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `adminId` int(11) DEFAULT NULL COMMENT '管理员ID',
  `souGouPage` int(11) DEFAULT '1' COMMENT '搜狗页码',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

#
# Data for table "weixin"
#

INSERT INTO `weixin` VALUES (1,'专注JavaWeb开发',89,0,'2015-11-12 00:00:00','2015-12-02 13:30:04',1,1);

#
# Structure for table "weixin_text"
#

DROP TABLE IF EXISTS `weixin_text`;
CREATE TABLE `weixin_text` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `WeiXinId` int(11) DEFAULT NULL COMMENT '微信ID',
  `WeiXinURL` varchar(1000) DEFAULT NULL COMMENT '微信url',
  `content` mediumtext COMMENT '微信内容',
  `title` varchar(2000) DEFAULT NULL COMMENT '微信标题',
  `postTime` datetime DEFAULT NULL COMMENT '微信发布时间',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` varchar(255) DEFAULT NULL COMMENT '更新时间',
  `status` int(11) DEFAULT NULL COMMENT '删除标记(0未删除,1删除)',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `fieldText` varchar(255) DEFAULT NULL COMMENT '备用字段',
  `fromUrl` varchar(1000) DEFAULT NULL COMMENT '原文URL',
  `adminId` int(11) DEFAULT NULL COMMENT '管理员ID',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

#
# Data for table "weixin_text"
#