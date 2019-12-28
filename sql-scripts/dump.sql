-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: jcart
-- ------------------------------------------------------
-- Server version	8.0.11

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
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `addresses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address_line1` varchar(255) DEFAULT NULL,
  `address_line2` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` VALUES (1,'aa','a','Quận 11','IN','Phường 3','00084'),(2,'aa','a','Quận 11','IN','Phường 3','00084');
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(1024) DEFAULT NULL,
  `disabled` bit(1) NOT NULL,
  `disp_order` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_t8o6pivur7nn124jehx7cygw5` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'MÁY TÍNH','\0',0,'MÁY TÍNH'),(2,'Hàng chế phẩm sinh học','\0',1,'Hàng chế phẩm sinh học');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rfbvkrffamfql7cjmen8v976v` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'ungtq0805@gmail.com','a','a','$2a$10$osz42Tulhy8KBTs9sdd8reQaCbxOJ8irg0jANCuzn0KNfDmSgZhEK','0937146031');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mst_common`
--

DROP TABLE IF EXISTS `mst_common`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mst_common` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_no` varchar(255) NOT NULL,
  `class_name` varchar(255) NOT NULL,
  `common_no` varchar(255) NOT NULL,
  `char_data_1` varchar(255) DEFAULT NULL,
  `char_data_2` varchar(255) DEFAULT NULL,
  `char_data_3` varchar(255) DEFAULT NULL,
  `char_data_4` varchar(255) DEFAULT NULL,
  `char_data_5` varchar(255) DEFAULT NULL,
  `char_data_6` varchar(255) DEFAULT NULL,
  `flg_data_1` bit(1) DEFAULT NULL,
  `flg_data_2` bit(1) DEFAULT NULL,
  `flg_data_3` bit(1) DEFAULT NULL,
  `flg_data_4` bit(1) DEFAULT NULL,
  `flg_data_5` bit(1) DEFAULT NULL,
  `flg_data_6` bit(1) DEFAULT NULL,
  `last_update_emp_id` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `num_data_1` decimal(19,2) DEFAULT NULL,
  `num_data_2` decimal(19,2) DEFAULT NULL,
  `num_data_3` decimal(19,2) DEFAULT NULL,
  `num_data_4` decimal(19,2) DEFAULT NULL,
  `num_data_5` decimal(19,2) DEFAULT NULL,
  `num_data_6` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fx10ucv4dmhghncbtsmvrtjsh` (`common_no`,`class_no`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mst_common`
--

LOCK TABLES `mst_common` WRITE;
/*!40000 ALTER TABLE `mst_common` DISABLE KEYS */;
INSERT INTO `mst_common` VALUES (1,'001','Company Info','00000001','CÔNG TY TNHH PHẦN MỀM CTS (CT SOLUTIONS)','0937146031','TRAN QUỐC ỨNG','www.cts.com.vn','ungtq0805@gmail.com','<p><span style=\"color:#e74c3c\">C&Ocirc;NG TY PHẦN MỀM CƯỜNG THỊNH</span></p>\r\n',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'002','Unit','00000002','Cái',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'003','Unit','00000002','Đơn vị',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,'004','Unit','00000002','Slot',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'0000','Save Temp','00000003','Lưu tạm',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,'0001','Apply','00000003','Đang chờ duyệt',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,'2007','Approve','00000003','Đã Duyệt',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,'0002','Withdraw','00000003','Withdraw',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `mst_common` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` decimal(19,2) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9gap2fmw66v092ntb58rtohwh` (`order_id`),
  KEY `FK_3fea23hxar30bx7m7h8ed25n9` (`product_id`),
  CONSTRAINT `FK_3fea23hxar30bx7m7h8ed25n9` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FK_9gap2fmw66v092ntb58rtohwh` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (1,90000.00,1,1,2),(2,20000.00,1,1,1);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `order_number` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `billing_addr_id` int(11) DEFAULT NULL,
  `cust_id` int(11) DEFAULT NULL,
  `delivery_addr_id` int(11) DEFAULT NULL,
  `payment_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nthkiu7pgmnqnu86i2jyoe2v7` (`order_number`),
  KEY `FK_psbth5fimngkrdrdjdu1bsg2s` (`billing_addr_id`),
  KEY `FK_s32ku57qouy9bwgs8uhxv3s0j` (`cust_id`),
  KEY `FK_j5qdw9i0lqgb7um2xoae5k60r` (`delivery_addr_id`),
  KEY `FK_haujdjk1ohmeixjhnhslchrp1` (`payment_id`),
  CONSTRAINT `FK_haujdjk1ohmeixjhnhslchrp1` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`id`),
  CONSTRAINT `FK_j5qdw9i0lqgb7um2xoae5k60r` FOREIGN KEY (`delivery_addr_id`) REFERENCES `addresses` (`id`),
  CONSTRAINT `FK_psbth5fimngkrdrdjdu1bsg2s` FOREIGN KEY (`billing_addr_id`) REFERENCES `addresses` (`id`),
  CONSTRAINT `FK_s32ku57qouy9bwgs8uhxv3s0j` FOREIGN KEY (`cust_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'2019-04-17 20:57:10','1555509429915','NEW',1,1,2,1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `page_manager`
--

DROP TABLE IF EXISTS `page_manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `page_manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `page_name` varchar(255) NOT NULL,
  `content` blob NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `upd_date` datetime DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `upd_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_esgrrlxelxn9kay286h7gnhu7` (`page_name`),
  KEY `FK_1m7ghqn7awp2ehfy0ibpatpg4` (`create_user`),
  KEY `FK_o03vtpvbxkmk8a4c779e2h7fo` (`upd_user`),
  CONSTRAINT `FK_1m7ghqn7awp2ehfy0ibpatpg4` FOREIGN KEY (`create_user`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_o03vtpvbxkmk8a4c779e2h7fo` FOREIGN KEY (`upd_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='This is the page manager that user admin can manager';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `page_manager`
--

LOCK TABLES `page_manager` WRITE;
/*!40000 ALTER TABLE `page_manager` DISABLE KEYS */;
INSERT INTO `page_manager` VALUES (1,'WHO WE ARRE','<p>WE ARE A GOOD ENGINEERING</p>\r\n','2018-07-15 10:48:58','2018-07-15 10:48:58',NULL,NULL),(2,'Loi gioi thieu','<p style=\"text-align:center\">VE CONG TY CHUNG TOI</p>\r\n\r\n<p>VE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOIVE CONG TY CHUNG TOI</p>\r\n','2018-07-15 10:59:53','2018-07-15 10:59:53',NULL,NULL);
/*!40000 ALTER TABLE `page_manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` decimal(19,2) DEFAULT NULL,
  `cc_number` varchar(255) DEFAULT NULL,
  `cvv` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` VALUES (1,NULL,'111111','22222222222');
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(1024) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pnvtwliis6p05pn6i3ndjrqt2` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` VALUES (1,NULL,'MANAGE_CATEGORIES'),(2,NULL,'MANAGE_PRODUCTS'),(3,NULL,'MANAGE_ORDERS'),(4,NULL,'MANAGE_CUSTOMERS'),(5,NULL,'MANAGE_PAYMENT_SYSTEMS'),(6,NULL,'MANAGE_USERS'),(7,NULL,'MANAGE_ROLES'),(8,NULL,'MANAGE_PERMISSIONS'),(9,NULL,'MANAGE_SETTINGS'),(10,NULL,'ROLE_INOUTFLOW_APPROVE'),(11,NULL,'ROLE_INOUTFLOW_INPUT');
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `description` text,
  `disabled` bit(1) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `is_hot` bit(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `price` decimal(19,2) NOT NULL,
  `sku` varchar(255) NOT NULL,
  `cat_id` int(11) DEFAULT NULL,
  `unit_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fhmd06dsmj6k0n90swsh8ie9g` (`sku`),
  KEY `FK_mrb6cdmhln6h36nx2u3b5hv79` (`cat_id`),
  KEY `FK_q6x17u9hhhe7wpireh445d1ju` (`unit_id`),
  CONSTRAINT `FK_mrb6cdmhln6h36nx2u3b5hv79` FOREIGN KEY (`cat_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `FK_q6x17u9hhhe7wpireh445d1ju` FOREIGN KEY (`unit_id`) REFERENCES `mst_common` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'2018-06-16 18:39:20','LOA NAY NGHE HAY LAM','\0',NULL,NULL,'LOA AMPLY',20000.00,'0001',1,2),(2,'2018-06-16 18:47:16','MAY TINH DELL','\0',NULL,'','MAY TINH XACH TAY',90000.00,'0002',1,3);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission` (
  `role_id` int(11) NOT NULL,
  `perm_id` int(11) NOT NULL,
  KEY `FK_sckhpvoqvxqg9rjmeud18a6dr` (`perm_id`),
  KEY `FK_j89g87bvih4d6jbxjcssrybks` (`role_id`),
  CONSTRAINT `FK_j89g87bvih4d6jbxjcssrybks` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FK_sckhpvoqvxqg9rjmeud18a6dr` FOREIGN KEY (`perm_id`) REFERENCES `permissions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(2,1),(2,2),(2,3),(2,4),(2,5),(2,9),(3,1),(3,2),(5,1),(5,2),(5,3),(5,4),(5,5),(5,6),(5,7),(5,8),(5,9),(5,10),(5,11),(6,1),(6,2),(6,3),(6,4),(6,5),(6,6),(6,7),(6,8),(6,9),(6,10),(6,11);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(1024) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ofx66keruapi6vyqpv6f2or37` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,NULL,'ROLE_SUPER_ADMIN'),(2,NULL,'ROLE_ADMIN'),(3,NULL,'ROLE_CMS_ADMIN'),(4,'','ROLE_USER'),(5,'MANAGE_ROLE_INOUTFLOW_APPROVE','MANAGE_ROLE_INOUTFLOW_APPROVE'),(6,'MANAGE_ROLE_INOUTFLOW_INPUT','MANAGE_ROLE_INOUTFLOW_INPUT');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `FK_it77eq964jhfqtu54081ebtio` (`role_id`),
  KEY `FK_apcc8lxk2xnug8377fatvbn04` (`user_id`),
  CONSTRAINT `FK_apcc8lxk2xnug8377fatvbn04` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_it77eq964jhfqtu54081ebtio` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (2,1),(2,5),(2,6),(1,1),(1,2),(1,3),(1,4),(1,5),(1,6);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `disabled` bit(1) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `password_reset_token` varchar(255) DEFAULT NULL,
  `phone_no` varchar(255) DEFAULT NULL,
  `shipper` bit(1) DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'\0','admin@gmail.com','Administrator',NULL,'$2y$12$BjLIUfLuys9W4yrLiRhYseTluPHOFDA8eRIIuHGGCsdINQxPcYW0K','$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS','','\0','admin'),(2,'\0','ungtq0805@gmail.com','ungtq',NULL,'$2a$10$RJJlTc.RCZjNHiZypZA1beV7Qy/lZ6SYXd1dLUIyN1SH8fDWmQMzG',NULL,'','','ungtq');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wh_customers`
--

DROP TABLE IF EXISTS `wh_customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wh_customers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `representative` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wh_customers`
--

LOCK TABLES `wh_customers` WRITE;
/*!40000 ALTER TABLE `wh_customers` DISABLE KEYS */;
/*!40000 ALTER TABLE `wh_customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wh_goods`
--

DROP TABLE IF EXISTS `wh_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wh_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `units` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wh_goods`
--

LOCK TABLES `wh_goods` WRITE;
/*!40000 ALTER TABLE `wh_goods` DISABLE KEYS */;
/*!40000 ALTER TABLE `wh_goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wh_inflows`
--

DROP TABLE IF EXISTS `wh_inflows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wh_inflows` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NOT NULL,
  `apply_date` date DEFAULT NULL,
  `approve_date` date DEFAULT NULL,
  `inflowdate` date NOT NULL,
  `last_upd_date` datetime DEFAULT NULL,
  `price` decimal(11,2) DEFAULT NULL,
  `statuskbn` varchar(255) DEFAULT NULL,
  `apply_person` int(11) DEFAULT NULL,
  `approve_person` int(11) DEFAULT NULL,
  `product` int(11) NOT NULL,
  `shipper` int(11) DEFAULT NULL,
  `warehouse` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_sg5ugwsqunmjutp19n67rrovl` (`apply_person`),
  KEY `FK_jh1a1jgtl26gmapvhlwvgo5s8` (`approve_person`),
  KEY `FK_bfja69bel74yonehr3u7eo6q5` (`product`),
  KEY `FK_2ln3koodtu0pxsjb19pv82k3u` (`shipper`),
  KEY `FK_j2xch9p60wbtm8vfg7asp25i6` (`warehouse`),
  CONSTRAINT `FK_2ln3koodtu0pxsjb19pv82k3u` FOREIGN KEY (`shipper`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_bfja69bel74yonehr3u7eo6q5` FOREIGN KEY (`product`) REFERENCES `products` (`id`),
  CONSTRAINT `FK_j2xch9p60wbtm8vfg7asp25i6` FOREIGN KEY (`warehouse`) REFERENCES `wh_warehouses` (`id`),
  CONSTRAINT `FK_jh1a1jgtl26gmapvhlwvgo5s8` FOREIGN KEY (`approve_person`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_sg5ugwsqunmjutp19n67rrovl` FOREIGN KEY (`apply_person`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wh_inflows`
--

LOCK TABLES `wh_inflows` WRITE;
/*!40000 ALTER TABLE `wh_inflows` DISABLE KEYS */;
INSERT INTO `wh_inflows` VALUES (2,5,'2019-04-17','2019-04-17','2018-06-16','2019-04-17 21:08:27',900000.00,'2007',2,1,1,2,1);
/*!40000 ALTER TABLE `wh_inflows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wh_outflows`
--

DROP TABLE IF EXISTS `wh_outflows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wh_outflows` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NOT NULL,
  `apply_date` date DEFAULT NULL,
  `approve_date` date DEFAULT NULL,
  `last_upd_date` datetime DEFAULT NULL,
  `outflowdate` date NOT NULL,
  `price` decimal(19,2) NOT NULL,
  `statuskbn` varchar(255) DEFAULT NULL,
  `apply_person` int(11) DEFAULT NULL,
  `approve_person` int(11) DEFAULT NULL,
  `customer` int(11) NOT NULL,
  `inflow` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_dd01ws2ew0x073fvrlci1i03a` (`apply_person`),
  KEY `FK_sh8p1g1oh7rwxcbw6cfjhhymt` (`approve_person`),
  KEY `FK_fhujk5e79a5ecvta751cunbbc` (`customer`),
  KEY `FK_knb0lf56mykux5g9ldssvt2rd` (`inflow`),
  CONSTRAINT `FK_dd01ws2ew0x073fvrlci1i03a` FOREIGN KEY (`apply_person`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_fhujk5e79a5ecvta751cunbbc` FOREIGN KEY (`customer`) REFERENCES `customers` (`id`),
  CONSTRAINT `FK_knb0lf56mykux5g9ldssvt2rd` FOREIGN KEY (`inflow`) REFERENCES `wh_inflows` (`id`),
  CONSTRAINT `FK_sh8p1g1oh7rwxcbw6cfjhhymt` FOREIGN KEY (`approve_person`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wh_outflows`
--

LOCK TABLES `wh_outflows` WRITE;
/*!40000 ALTER TABLE `wh_outflows` DISABLE KEYS */;
INSERT INTO `wh_outflows` VALUES (1,1,'2019-04-17','2019-04-17','2019-04-17 21:09:41','2019-04-17',20000.00,'2007',1,1,1,2);
/*!40000 ALTER TABLE `wh_outflows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wh_shippers`
--

DROP TABLE IF EXISTS `wh_shippers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wh_shippers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `representative` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wh_shippers`
--

LOCK TABLES `wh_shippers` WRITE;
/*!40000 ALTER TABLE `wh_shippers` DISABLE KEYS */;
/*!40000 ALTER TABLE `wh_shippers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wh_warehouses`
--

DROP TABLE IF EXISTS `wh_warehouses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wh_warehouses` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(128) NOT NULL,
  `employees` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wh_warehouses`
--

LOCK TABLES `wh_warehouses` WRITE;
/*!40000 ALTER TABLE `wh_warehouses` DISABLE KEYS */;
INSERT INTO `wh_warehouses` VALUES (1,'444/3 CMT8',3,'Nhà Kho 1');
/*!40000 ALTER TABLE `wh_warehouses` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-28 20:29:21
