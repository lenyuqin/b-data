/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.16 : Database - bdata
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bdata` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `bdata`;

/*Table structure for table `b_video_data` */

DROP TABLE IF EXISTS `b_video_data`;

CREATE TABLE `b_video_data` (
  `BV_NUMBER` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '视频BV号',
  `BV_UP` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'up主名字',
  `BV_TITLE` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '视频标题',
  `BV_LIKENUM` bigint(50) DEFAULT NULL COMMENT '点赞数量',
  `BV_COINNUM` bigint(50) DEFAULT NULL COMMENT '硬币数量',
  `BV_COLLECTNUM` bigint(50) DEFAULT NULL COMMENT '收藏数量',
  `BV_SHARENUM` bigint(50) DEFAULT NULL COMMENT '分享数量',
  `BV_VIEWNUM` bigint(50) DEFAULT NULL COMMENT '播放量',
  `BV_DMNUM` bigint(50) DEFAULT NULL COMMENT '弹幕数量',
  `BV_COMMENTNUM` bigint(50) DEFAULT NULL COMMENT '评论数量',
  KEY `BV_NUMBER` (`BV_NUMBER`),
  CONSTRAINT `b_video_data_ibfk_1` FOREIGN KEY (`BV_NUMBER`) REFERENCES `b_video_history` (`BV_NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `b_video_data` */

/*Table structure for table `b_video_history` */

DROP TABLE IF EXISTS `b_video_history`;

CREATE TABLE `b_video_history` (
  `BV_NUMBER` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '视频BV号',
  `BV_UP` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'up主名字',
  `BV_TITLE` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '视频标题',
  `BV_IMGADDRESS` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '封面图片地址',
  PRIMARY KEY (`BV_NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `b_video_history` */

/*Table structure for table `b_video_rank` */

DROP TABLE IF EXISTS `b_video_rank`;

CREATE TABLE `b_video_rank` (
  `BV_NUMBER` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '视频BV号',
  `BV_UP` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'up主名字',
  `BV_TITLE` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '视频标题',
  `BV_SCORE` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '视频得分',
  `BV_RANKZONE` int(50) DEFAULT NULL COMMENT '视频分区的数字',
  `BV_RANKNUM` int(50) DEFAULT NULL COMMENT '视频排名',
  `BV_IMGADDRESS` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '封面图片地址'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `b_video_rank` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
