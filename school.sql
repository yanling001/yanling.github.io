-- MySQL dump 10.13  Distrib 5.7.27, for Win64 (x86_64)
--
-- Host: localhost    Database: school
-- ------------------------------------------------------
-- Server version	5.7.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `collect`
--

DROP TABLE IF EXISTS `collect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `collect` (
  `collect_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`collect_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collect`
--

LOCK TABLES `collect` WRITE;
/*!40000 ALTER TABLE `collect` DISABLE KEYS */;
INSERT INTO `collect` VALUES (1,1,1),(2,2,1);
/*!40000 ALTER TABLE `collect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image` (
  `image_id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_id` int(11) NOT NULL DEFAULT '-1',
  `product_id` int(11) NOT NULL DEFAULT '-1',
  `img_address` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,1,-1,'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJEVmpaEF9QiayJdicaBGwxUJl8XfS8Lpw6VDuDc2kJvG1NjFxZDUg1ICMRFXzmBFVOiagALMu0I4oSQ/132'),(2,1,-1,'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJEVmpaEF9QiayJdicaBGwxUJl8XfS8Lpw6VDuDc2kJvG1NjFxZDUg1ICMRFXzmBFVOiagALMu0I4oSQ/132'),(3,2,-1,'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJEVmpaEF9QiayJdicaBGwxUJl8XfS8Lpw6VDuDc2kJvG1NjFxZDUg1ICMRFXzmBFVOiagALMu0I4oSQ/132'),(4,2,-1,'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJEVmpaEF9QiayJdicaBGwxUJl8XfS8Lpw6VDuDc2kJvG1NjFxZDUg1ICMRFXzmBFVOiagALMu0I4oSQ/132'),(5,-1,1,'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJEVmpaEF9QiayJdicaBGwxUJl8XfS8Lpw6VDuDc2kJvG1NjFxZDUg1ICMRFXzmBFVOiagALMu0I4oSQ/132'),(6,-1,1,'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJEVmpaEF9QiayJdicaBGwxUJl8XfS8Lpw6VDuDc2kJvG1NjFxZDUg1ICMRFXzmBFVOiagALMu0I4oSQ/132'),(7,-1,2,'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJEVmpaEF9QiayJdicaBGwxUJl8XfS8Lpw6VDuDc2kJvG1NjFxZDUg1ICMRFXzmBFVOiagALMu0I4oSQ/132');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invitation`
--

DROP TABLE IF EXISTS `invitation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invitation` (
  `invitation_id` int(11) NOT NULL AUTO_INCREMENT,
  `uesr_id` int(11) DEFAULT NULL,
  `content` text,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `invitation_status` int(2) DEFAULT NULL,
  `accept_user_id` int(11) DEFAULT NULL,
  `price` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`invitation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invitation`
--

LOCK TABLES `invitation` WRITE;
/*!40000 ALTER TABLE `invitation` DISABLE KEYS */;
INSERT INTO `invitation` VALUES (1,1,'帮取快递-西区体育场东南角-圆通快递-货号:3900','2020-02-01 02:21:22','2020-02-01 02:21:22',1,2,NULL),(2,1,'帮取快递-西区水房-妈妈驿站-货号:3901','2020-02-01 02:22:57','2020-02-01 02:22:57',0,NULL,NULL),(3,1,'帮取快递-西区-顺丰快递-货号：39003','2020-02-01 03:11:11','2020-02-01 06:30:36',0,NULL,NULL),(4,1,'帮取快递-西区-顺丰快递-货号：39003','2020-02-01 03:11:11','2020-02-01 07:20:36',0,NULL,NULL),(5,2,'取快递-西区水房-货号1234','2020-02-01 03:11:11','2020-02-02 03:10:05',0,NULL,NULL),(6,1,'帮取快递-西区-顺丰快递-货号：39003','2020-02-01 03:11:11','2020-02-21 08:44:13',0,NULL,NULL),(7,1,'帮取快递-西区-顺丰快递-货号：39003','2020-02-01 03:11:11','2020-02-21 08:45:03',0,NULL,NULL);
/*!40000 ALTER TABLE `invitation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invitation_collect`
--

DROP TABLE IF EXISTS `invitation_collect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invitation_collect` (
  `invitation_collect_id` int(11) NOT NULL AUTO_INCREMENT,
  `invitation_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`invitation_collect_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invitation_collect`
--

LOCK TABLES `invitation_collect` WRITE;
/*!40000 ALTER TABLE `invitation_collect` DISABLE KEYS */;
/*!40000 ALTER TABLE `invitation_collect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordertoproduct`
--

DROP TABLE IF EXISTS `ordertoproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordertoproduct` (
  `id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `productid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordertoproduct`
--

LOCK TABLES `ordertoproduct` WRITE;
/*!40000 ALTER TABLE `ordertoproduct` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordertoproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `price` decimal(20,2) DEFAULT NULL,
  `product_name` varchar(100) DEFAULT NULL,
  `product_img` varchar(1000) DEFAULT NULL,
  `product_describle` text,
  `create_time` datetime DEFAULT NULL,
  `shop_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_comment`
--

DROP TABLE IF EXISTS `product_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `content` text,
  `create_time` datetime DEFAULT NULL,
  `star` int(10) DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_comment`
--

LOCK TABLES `product_comment` WRITE;
/*!40000 ALTER TABLE `product_comment` DISABLE KEYS */;
INSERT INTO `product_comment` VALUES (1,1,2,'质量很好','2020-02-07 05:10:15',5),(2,2,2,'质量一般，开机还可以','2020-02-07 05:13:16',4),(3,2,2,'质量很不错，服务很好','2020-02-07 05:16:36',5),(4,1,2,'质量很不错，服务很好','2020-02-07 05:17:27',5);
/*!40000 ALTER TABLE `product_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `second_hand`
--

DROP TABLE IF EXISTS `second_hand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `second_hand` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(100) NOT NULL,
  `name` varchar(1000) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `content` text,
  `create_time` datetime DEFAULT NULL,
  `video_address` varchar(1000) DEFAULT NULL,
  `price` decimal(20,2) DEFAULT NULL,
  `status` int(4) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `second_hand`
--

LOCK TABLES `second_hand` WRITE;
/*!40000 ALTER TABLE `second_hand` DISABLE KEYS */;
INSERT INTO `second_hand` VALUES (1,'家具','课桌',1,'9成新','2020-02-07 05:10:15','https://www.bilibili.com/video/av87096463?from=search&seid=9430981759363756771',99.00,NULL),(2,'数码产品','笔记本--华为',1,'9成新','2020-02-07 05:13:16','https://www.bilibili.com/video/av87096463?from=search&seid=9430981759363756771',1999.00,NULL);
/*!40000 ALTER TABLE `second_hand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop` (
  `shop_id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(100) DEFAULT NULL,
  `shopname` varchar(1000) DEFAULT NULL,
  `location` varchar(1000) DEFAULT NULL,
  `tel` varchar(12) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(100) DEFAULT NULL,
  `email` varchar(1024) DEFAULT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `openid_web` varchar(1024) DEFAULT NULL,
  `city` varchar(40) DEFAULT NULL,
  `province` varchar(40) DEFAULT NULL,
  `country` varchar(30) DEFAULT NULL,
  `avatarurl` varchar(1000) DEFAULT NULL,
  `openid_anr` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'张宏耀','1964074623@qq.com','17392408292','2020-02-01 02:34:14','2020-02-01 02:34:14','',NULL,NULL,NULL,NULL,NULL),(2,'傅一航','1604267198@qq.com','13245677898','2020-02-01 07:25:44','2020-02-01 07:25:44','',NULL,NULL,NULL,NULL,NULL),(3,'飞翔的梦',NULL,NULL,NULL,NULL,'zhangsanlisi','Ankang','Shaanxi','China','https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJEVmpaEF9QiayJdicaBGwxUJl8XfS8Lpw6VDuDc2kJvG1NjFxZDUg1ICMRFXzmBFVOiagALMu0I4oSQ/132',NULL),(4,'飞翔的梦',NULL,NULL,NULL,NULL,NULL,'Ankang','Shaanxi','China','https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJEVmpaEF9QiayJdicaBGwxUJl8XfS8Lpw6VDuDc2kJvG1NjFxZDUg1ICMRFXzmBFVOiagALMu0I4oSQ/132',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userorder`
--

DROP TABLE IF EXISTS `userorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userorder` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `status` int(4) DEFAULT NULL,
  `price` decimal(20,2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `shop_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `remark` text,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userorder`
--

LOCK TABLES `userorder` WRITE;
/*!40000 ALTER TABLE `userorder` DISABLE KEYS */;
/*!40000 ALTER TABLE `userorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wechat`
--

DROP TABLE IF EXISTS `wechat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wechat` (
  `wechat_id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(100) NOT NULL,
  `city` varchar(40) DEFAULT NULL,
  `province` varchar(40) DEFAULT NULL,
  `country` varchar(30) DEFAULT NULL,
  `avatarurl` varchar(1000) DEFAULT NULL,
  `gender` int(4) DEFAULT NULL,
  `unionid` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`wechat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wechat`
--

LOCK TABLES `wechat` WRITE;
/*!40000 ALTER TABLE `wechat` DISABLE KEYS */;
INSERT INTO `wechat` VALUES (1,'飞翔的梦','Ankang','Shaanxi','China','https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJEVmpaEF9QiayJdicaBGwxUJl8XfS8Lpw6VDuDc2kJvG1NjFxZDUg1ICMRFXzmBFVOiagALMu0I4oSQ/132',1,NULL);
/*!40000 ALTER TABLE `wechat` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-22 18:43:32
