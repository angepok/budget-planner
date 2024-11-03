-- MariaDB dump 10.19  Distrib 10.11.2-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: budgetplanner
-- ------------------------------------------------------
-- Server version	10.11.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `budgetplanner`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `budgetplanner` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `budgetplanner`;

--
-- Table structure for table `budget`
--

DROP TABLE IF EXISTS `budget`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `budget` (
  `balance` decimal(38,2) DEFAULT NULL,
  `total_expenses` decimal(38,2) DEFAULT NULL,
  `total_income` decimal(38,2) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `budget`
--

LOCK TABLES `budget` WRITE;
/*!40000 ALTER TABLE `budget` DISABLE KEYS */;
INSERT INTO `budget` VALUES
(1500.00,0.00,1500.00,1,'JUNE 2024'),
(-200.00,200.00,0.00,2,'JUNE 2023'),
(1500.00,0.00,1500.00,3,'JUNE 2024');
/*!40000 ALTER TABLE `budget` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expense`
--

DROP TABLE IF EXISTS `expense`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expense` (
  `amount` decimal(17,2) NOT NULL,
  `date` date NOT NULL,
  `year` int(11) NOT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `month` tinyint(4) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `budget_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf7fru65n5syjnfkfov9ma34g1` (`budget_id`),
  CONSTRAINT `FKf7fru65n5syjnfkfov9ma34g1` FOREIGN KEY (`budget_id`) REFERENCES `budget` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expense`
--

LOCK TABLES `expense` WRITE;
/*!40000 ALTER TABLE `expense` DISABLE KEYS */;
INSERT INTO `expense` VALUES
(1200.00,'2024-10-01',2024,'2024-11-03 07:26:47.000000',1,10,'Rent',NULL),
(250.50,'2024-10-05',2024,'2024-11-03 07:27:19.000000',2,10,'Groceries',NULL),
(150.75,'2024-10-03',2024,'2024-11-03 07:37:06.000000',3,10,'Utilities',NULL),
(60.00,'2024-08-08',2024,'2024-11-03 07:37:54.000000',4,8,'Transportation',NULL),
(100.00,'2023-12-12',2023,'2024-11-03 07:38:52.000000',5,11,'Dining Out',NULL),
(15.00,'2021-07-15',2021,'2024-11-03 07:39:26.000000',6,7,'Netflix Subscription',NULL),
(45.00,'2022-08-20',2022,'2024-11-03 07:39:53.000000',7,8,'Gym Membership',NULL),
(80.99,'2022-11-25',2022,'2024-11-03 07:40:16.000000',8,11,'Phone Bill',NULL),
(200.00,'2023-05-10',2023,'2024-11-03 07:41:02.000000',9,5,'Car Insurance',NULL),
(150.00,'2024-01-15',2024,'2024-11-03 07:41:39.000000',10,1,'Utilities',NULL),
(1200.00,'2024-10-05',2024,NULL,11,9,'Ski trip',NULL);
/*!40000 ALTER TABLE `expense` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `income`
--

DROP TABLE IF EXISTS `income`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `income` (
  `amount` decimal(17,2) NOT NULL,
  `date_received` date NOT NULL,
  `income_date` date DEFAULT NULL,
  `month` tinyint(4) DEFAULT NULL,
  `year` int(11) NOT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `budget_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8q2b4fohgn3i0yjktl4nxi261` (`budget_id`),
  CONSTRAINT `FK8q2b4fohgn3i0yjktl4nxi261` FOREIGN KEY (`budget_id`) REFERENCES `budget` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `income`
--

LOCK TABLES `income` WRITE;
/*!40000 ALTER TABLE `income` DISABLE KEYS */;
INSERT INTO `income` VALUES
(4000.00,'2020-01-31','2020-01-31',0,2020,'2024-11-03 07:58:12.000000',1,'Salary',NULL),
(1200.00,'2021-02-15','2021-02-15',1,2021,'2024-11-03 07:59:06.000000',2,'Freelance Work',NULL),
(300.00,'2022-03-10','2022-03-10',2,2022,'2024-11-03 07:59:39.000000',3,'Investment Income',NULL),
(2000.00,'2023-04-01','2023-04-01',3,2023,'2024-11-03 08:00:01.000000',4,'Rental Income',NULL),
(800.00,'2024-05-20','2024-05-20',4,2024,'2024-11-03 08:00:32.000000',5,'Side Business',NULL),
(1500.00,'2024-06-15','2024-06-15',5,2024,'2024-11-03 08:01:03.000000',6,'Bonus',NULL),
(450.00,'2024-07-30','2024-07-30',6,2024,'2024-11-03 08:01:33.000000',7,'Stock Dividends',NULL),
(900.00,'2023-08-12','2023-08-12',7,2023,'2024-11-03 08:02:03.000000',8,'Consulting',NULL),
(250.00,'2021-09-28','2021-09-28',8,2021,'2024-11-03 08:02:47.000000',9,'Interest Income',NULL),
(1200.00,'2022-10-05','2022-10-05',9,2022,'2024-11-03 08:03:16.000000',10,'Part-Time Job',NULL);
/*!40000 ALTER TABLE `income` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-03 22:32:44
