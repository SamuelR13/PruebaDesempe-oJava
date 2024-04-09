CREATE DATABASE  IF NOT EXISTS `b04vplxuhaeb0cu01fqq` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `b04vplxuhaeb0cu01fqq`;
-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: b04vplxuhaeb0cu01fqq-mysql.services.clever-cloud.com    Database: b04vplxuhaeb0cu01fqq
-- ------------------------------------------------------
-- Server version	8.0.22-13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ 'a05a675a-1414-11e9-9c82-cecd01b08c7e:1-491550428,
a38a16d0-767a-11eb-abe2-cecd029e558e:1-379822611';

--
-- Table structure for table `Coder`
--

DROP TABLE IF EXISTS `Coder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Coder` (
  `id_coder` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `apellidos` varchar(255) NOT NULL,
  `documento` varchar(255) NOT NULL,
  `cohorte` int NOT NULL,
  `cv` text NOT NULL,
  `clan` varchar(255) NOT NULL,
  PRIMARY KEY (`id_coder`),
  UNIQUE KEY `documento` (`documento`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Coder`
--

LOCK TABLES `Coder` WRITE;
/*!40000 ALTER TABLE `Coder` DISABLE KEYS */;
INSERT INTO `Coder` VALUES (1,'Samuel','Rodriguez Velasquez','1000089781',1,'Lorem Impsum valor calmper lor han JavaScript CSS HTML Java','Lovelace'),(2,'Tomas','Montoya Quirico','1586784785',1,'lorem impsum lorn upte jsuq jaise CSS HTML ','Lovelace'),(3,'Samuel','Pamplona Rojo','1547654210',2,'huae mauet jahste ajkbq CSS Java C++','Meta'),(4,'Mariana','Montoya Ospina','7458654125',2,'heya lume upsu lito rempa CSS','Meta');
/*!40000 ALTER TABLE `Coder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Contratacion`
--

DROP TABLE IF EXISTS `Contratacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Contratacion` (
  `id_contratacion` int NOT NULL AUTO_INCREMENT,
  `id_vacante` int NOT NULL,
  `id_coder` int NOT NULL,
  `fecha_aplicacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `estado` varchar(255) NOT NULL,
  `salario` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_contratacion`),
  KEY `fk_Contratacion_Vacante` (`id_vacante`),
  KEY `fk_Contratacion_Coder` (`id_coder`),
  CONSTRAINT `fk_Contratacion_Coder` FOREIGN KEY (`id_coder`) REFERENCES `Coder` (`id_coder`) ON DELETE CASCADE,
  CONSTRAINT `fk_Contratacion_Vacante` FOREIGN KEY (`id_vacante`) REFERENCES `Vacante` (`id_vacante`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Contratacion`
--

LOCK TABLES `Contratacion` WRITE;
/*!40000 ALTER TABLE `Contratacion` DISABLE KEYS */;
INSERT INTO `Contratacion` VALUES (3,2,1,'2024-04-09 17:57:36','ACTIVO',8500000.20),(4,2,1,'2024-04-09 18:00:56','Pendiente',8500500.00),(5,1,2,'2024-04-09 18:08:21','ACTIVO',3500548.20);
/*!40000 ALTER TABLE `Contratacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Empresa`
--

DROP TABLE IF EXISTS `Empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Empresa` (
  `id_empresa` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `sector` varchar(255) NOT NULL,
  `ubicacion` varchar(255) NOT NULL,
  `contacto` varchar(255) NOT NULL,
  PRIMARY KEY (`id_empresa`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Empresa`
--

LOCK TABLES `Empresa` WRITE;
/*!40000 ALTER TABLE `Empresa` DISABLE KEYS */;
INSERT INTO `Empresa` VALUES (1,'Sistecredito','Bancario/Financiero','Medellin','contacto@sistecredito.com'),(2,'Avianca','Transporte','Rionegro','contacto@avianca.com'),(3,'Solutions S.A','Tecnologico','Medellin','contacto@solutions.com'),(4,'Interrapidisimo','Servicios','Bogota','contacto@interrapidisimo.com');
/*!40000 ALTER TABLE `Empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Vacante`
--

DROP TABLE IF EXISTS `Vacante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Vacante` (
  `id_vacante` int NOT NULL AUTO_INCREMENT,
  `id_empresa` int NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `descripcion` text NOT NULL,
  `duracion` varchar(255) NOT NULL,
  `estado` varchar(50) NOT NULL,
  `tecnologia` varchar(255) NOT NULL,
  PRIMARY KEY (`id_vacante`),
  KEY `fk_Vacante_Empresa` (`id_empresa`),
  CONSTRAINT `fk_Vacante_Empresa` FOREIGN KEY (`id_empresa`) REFERENCES `Empresa` (`id_empresa`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Vacante`
--

LOCK TABLES `Vacante` WRITE;
/*!40000 ALTER TABLE `Vacante` DISABLE KEYS */;
INSERT INTO `Vacante` VALUES (1,2,'Desarrollador Frontend Junioe','lorem ipsum lore true etem serem','12 meses','INACTIVO','HTML'),(2,1,'Desarrollador Junior JavaScript','lorem ipsum lore true etem serem','6 meses','ACTIVO','JavaScript'),(4,3,'TeachLeader Backend','lorem ipsum front yul limpa treu','2 años','ACTIVO','Java');
/*!40000 ALTER TABLE `Vacante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'b04vplxuhaeb0cu01fqq'
--

--
-- Dumping routines for database 'b04vplxuhaeb0cu01fqq'
--
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-09 13:22:05
