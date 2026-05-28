-- MySQL dump 10.13  Distrib 9.5.0, for Win64 (x86_64)
--
-- Host: localhost    Database: gamehubbd
-- ------------------------------------------------------
-- Server version	9.5.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bitacora_acciones`
--

DROP TABLE IF EXISTS `bitacora_acciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bitacora_acciones` (
  `id_bitacora` int NOT NULL AUTO_INCREMENT,
  `id_usuario` varchar(50) NOT NULL,
  `fecha_hora` datetime NOT NULL,
  `tipo_operacion` enum('Inicio de sesion','ALTA_SUSCRIPCION','CAMBIO_ESTATUS_SUSCRIPCION','CREACION_ORDEN','CAMBIO_ESTATUS_ORDEN') NOT NULL,
  `descripcion_breve` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_bitacora`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `bitacora_acciones_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bitacora_acciones`
--

LOCK TABLES `bitacora_acciones` WRITE;
/*!40000 ALTER TABLE `bitacora_acciones` DISABLE KEYS */;
INSERT INTO `bitacora_acciones` VALUES (1,'gabriel_hernandez','2026-05-18 22:28:55','ALTA_SUSCRIPCION','El usuario contrató un plan. ID Suscripcion: 1000'),(2,'daniel_perez','2026-05-18 22:29:16','CREACION_ORDEN','Se generó una nueva orden de compra. Total: $599.00'),(3,'victor_vasquez','2026-05-18 22:34:53','ALTA_SUSCRIPCION','El usuario contrató un plan. ID Suscripcion: 1001');
/*!40000 ALTER TABLE `bitacora_acciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_orden`
--

DROP TABLE IF EXISTS `detalle_orden`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_orden` (
  `id_orden` int NOT NULL,
  `id_videojuego` int NOT NULL,
  `id_plataforma` int NOT NULL,
  `precio_unitario` decimal(10,2) NOT NULL,
  `cantidad` int NOT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_orden`,`id_videojuego`,`id_plataforma`),
  KEY `id_videojuego` (`id_videojuego`),
  KEY `id_plataforma` (`id_plataforma`),
  CONSTRAINT `detalle_orden_ibfk_1` FOREIGN KEY (`id_orden`) REFERENCES `orden` (`id_orden`),
  CONSTRAINT `detalle_orden_ibfk_2` FOREIGN KEY (`id_videojuego`) REFERENCES `videojuego` (`id_videojuego`),
  CONSTRAINT `detalle_orden_ibfk_3` FOREIGN KEY (`id_plataforma`) REFERENCES `plataforma` (`id_plataforma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_orden`
--

LOCK TABLES `detalle_orden` WRITE;
/*!40000 ALTER TABLE `detalle_orden` DISABLE KEYS */;
INSERT INTO `detalle_orden` VALUES (1,10,1,599.00,1,599.00),(889,10,1,599.00,1,599.00);
/*!40000 ALTER TABLE `detalle_orden` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genero`
--

DROP TABLE IF EXISTS `genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genero` (
  `id_genero` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id_genero`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genero`
--

LOCK TABLES `genero` WRITE;
/*!40000 ALTER TABLE `genero` DISABLE KEYS */;
INSERT INTO `genero` VALUES (1,'Acción'),(2,'Aventura'),(3,'RPG'),(4,'Shooter'),(5,'Deportes'),(6,'Estrategia'),(7,'Terror'),(8,'Plataformas');
/*!40000 ALTER TABLE `genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orden`
--

DROP TABLE IF EXISTS `orden`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orden` (
  `id_orden` int NOT NULL AUTO_INCREMENT,
  `id_usuario` varchar(50) NOT NULL,
  `fecha_orden` datetime NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `estatus_orden` enum('Pendiente','Pagada','Completada','Cancelada') NOT NULL,
  PRIMARY KEY (`id_orden`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `orden_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=890 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orden`
--

LOCK TABLES `orden` WRITE;
/*!40000 ALTER TABLE `orden` DISABLE KEYS */;
INSERT INTO `orden` VALUES (1,'Arclyne_01','2026-04-30 20:30:22',599.00,'Pagada'),(889,'daniel_perez','2026-05-18 22:29:16',599.00,'Pagada');
/*!40000 ALTER TABLE `orden` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `TRG_AI_Orden_Auditoria` AFTER INSERT ON `orden` FOR EACH ROW BEGIN
    DECLARE v_descripcion VARCHAR(255);
    SET v_descripcion = CONCAT('Se generó una nueva orden de compra. Total: $', NEW.total);

    CALL SP_Registrar_Bitacora(NEW.id_usuario, 'CREACION_ORDEN', v_descripcion);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `TRG_AU_Orden_Auditoria` AFTER UPDATE ON `orden` FOR EACH ROW BEGIN
    DECLARE v_descripcion VARCHAR(255);

    IF OLD.estatus_orden != NEW.estatus_orden THEN
        SET v_descripcion = CONCAT('Cambio de estatus de orden: de "', OLD.estatus_orden, '" a "', NEW.estatus_orden, '".');
        CALL SP_Registrar_Bitacora(NEW.id_usuario, 'CAMBIO_ESTATUS_ORDEN', v_descripcion);
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `plan_suscripcion`
--

DROP TABLE IF EXISTS `plan_suscripcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plan_suscripcion` (
  `id_plan` int NOT NULL AUTO_INCREMENT,
  `nombre_plan` varchar(100) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `duracion_cantidad` int NOT NULL,
  `duracion_unidad` enum('Dias','Meses') NOT NULL,
  `descripcion_beneficios` text NOT NULL,
  `estado_plan` enum('Vigente','Descontinuado') NOT NULL,
  PRIMARY KEY (`id_plan`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan_suscripcion`
--

LOCK TABLES `plan_suscripcion` WRITE;
/*!40000 ALTER TABLE `plan_suscripcion` DISABLE KEYS */;
INSERT INTO `plan_suscripcion` VALUES (1,'Premium',99.00,1,'Meses','Acceso ilimitado','Vigente');
/*!40000 ALTER TABLE `plan_suscripcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plataforma`
--

DROP TABLE IF EXISTS `plataforma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plataforma` (
  `id_plataforma` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id_plataforma`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plataforma`
--

LOCK TABLES `plataforma` WRITE;
/*!40000 ALTER TABLE `plataforma` DISABLE KEYS */;
INSERT INTO `plataforma` VALUES (1,'PC');
/*!40000 ALTER TABLE `plataforma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisher`
--

DROP TABLE IF EXISTS `publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publisher` (
  `id_publisher` int NOT NULL AUTO_INCREMENT,
  `nombre_comercial` varchar(150) NOT NULL,
  `pais_origen` varchar(100) NOT NULL,
  `datos_contacto` varchar(255) NOT NULL,
  PRIMARY KEY (`id_publisher`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisher`
--

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` VALUES (1,'FromSoftware','Japón','contact@from.jp');
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recargos_renovacion_suscripcion`
--

DROP TABLE IF EXISTS `recargos_renovacion_suscripcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recargos_renovacion_suscripcion` (
  `id_recargo` int NOT NULL AUTO_INCREMENT,
  `id_suscripcion` int NOT NULL,
  `id_pago` int NOT NULL,
  `monto_base` decimal(10,2) NOT NULL,
  `porcentaje_recargo` decimal(5,2) NOT NULL,
  `monto_recargo` decimal(10,2) NOT NULL,
  `monto_total` decimal(10,2) NOT NULL,
  `fecha_aplicacion` datetime NOT NULL,
  `motivo` varchar(255) NOT NULL,
  PRIMARY KEY (`id_recargo`),
  KEY `fk_recargo_suscripcion` (`id_suscripcion`),
  KEY `fk_recargo_pago` (`id_pago`),
  CONSTRAINT `fk_recargo_pago` FOREIGN KEY (`id_pago`) REFERENCES `registro_pago` (`id_pago`),
  CONSTRAINT `fk_recargo_suscripcion` FOREIGN KEY (`id_suscripcion`) REFERENCES `suscripcion` (`id_suscripcion`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recargos_renovacion_suscripcion`
--

LOCK TABLES `recargos_renovacion_suscripcion` WRITE;
/*!40000 ALTER TABLE `recargos_renovacion_suscripcion` DISABLE KEYS */;
INSERT INTO `recargos_renovacion_suscripcion` VALUES (1,1001,9003,100.00,5.00,5.00,105.00,'2026-05-18 22:34:53','Renovacion de suscripcion vencida');
/*!40000 ALTER TABLE `recargos_renovacion_suscripcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registro_pago`
--

DROP TABLE IF EXISTS `registro_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registro_pago` (
  `id_pago` int NOT NULL AUTO_INCREMENT,
  `id_suscripcion` int DEFAULT NULL,
  `id_orden` int DEFAULT NULL,
  `fecha_pago` datetime NOT NULL,
  `monto` decimal(10,2) NOT NULL,
  `metodo_pago` enum('Tarjeta de Credito','Tarjeta de Debito','PayPal','Transferencia') NOT NULL,
  `referencia_transaccion` varchar(255) DEFAULT NULL,
  `estatus_pago` enum('Pendiente','Aprobado','Rechazado') NOT NULL,
  PRIMARY KEY (`id_pago`),
  KEY `id_suscripcion` (`id_suscripcion`),
  KEY `id_orden` (`id_orden`),
  CONSTRAINT `registro_pago_ibfk_1` FOREIGN KEY (`id_suscripcion`) REFERENCES `suscripcion` (`id_suscripcion`),
  CONSTRAINT `registro_pago_ibfk_2` FOREIGN KEY (`id_orden`) REFERENCES `orden` (`id_orden`)
) ENGINE=InnoDB AUTO_INCREMENT=9004 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registro_pago`
--

LOCK TABLES `registro_pago` WRITE;
/*!40000 ALTER TABLE `registro_pago` DISABLE KEYS */;
INSERT INTO `registro_pago` VALUES (1,NULL,1,'2026-04-30 20:30:22',599.00,'PayPal','TRANS-12345','Aprobado'),(2,501,NULL,'2026-04-30 21:02:25',149.00,'PayPal','SUB-PAY-001','Aprobado'),(9001,1000,NULL,'2026-05-18 22:28:55',149.00,'PayPal','SUB-PAY-001','Aprobado'),(9002,NULL,889,'2026-05-18 22:29:16',599.00,'PayPal','TRANS-COMPRA-001','Aprobado'),(9003,1001,NULL,'2026-05-18 22:34:53',105.00,'Tarjeta de Credito','REC-001','Aprobado');
/*!40000 ALTER TABLE `registro_pago` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `TRG_BI_Registro_Pago` BEFORE INSERT ON `registro_pago` FOR EACH ROW BEGIN
    DECLARE v_aplica_recargo TINYINT DEFAULT 0;
    DECLARE v_monto_recargo DECIMAL(10,2) DEFAULT 0.00;
    DECLARE v_monto_total DECIMAL(10,2) DEFAULT NEW.monto;

    IF NEW.id_suscripcion IS NOT NULL THEN
        CALL SP_Calcular_Recargo_Vencimiento(
            NEW.id_suscripcion,
            NEW.monto,
            v_monto_total,
            v_monto_recargo,
            v_aplica_recargo
        );

        SET NEW.monto = v_monto_total;

        SET @aplica_recargo_sesion = v_aplica_recargo;
        SET @monto_base_sesion = NEW.monto - v_monto_recargo;
        SET @monto_recargo_sesion = v_monto_recargo;
        SET @monto_total_sesion = v_monto_total;
    ELSE
        SET @aplica_recargo_sesion = 0;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `TRG_AI_Registro_Pago` AFTER INSERT ON `registro_pago` FOR EACH ROW BEGIN
    IF @aplica_recargo_sesion = 1 THEN
        INSERT INTO recargos_renovacion_suscripcion (
            id_suscripcion, id_pago, monto_base, porcentaje_recargo,
            monto_recargo, monto_total, fecha_aplicacion, motivo
        ) VALUES (
            NEW.id_suscripcion,
            NEW.id_pago,
            @monto_base_sesion,
            5.00,
            @monto_recargo_sesion,
            @monto_total_sesion,
            NOW(),
            'Renovacion de suscripcion vencida'
        );

        SET @aplica_recargo_sesion = 0;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id_rol` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) NOT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suscripcion`
--

DROP TABLE IF EXISTS `suscripcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suscripcion` (
  `id_suscripcion` int NOT NULL AUTO_INCREMENT,
  `id_usuario` varchar(50) NOT NULL,
  `id_plan` int NOT NULL,
  `fecha_inicio` datetime NOT NULL,
  `fecha_vencimiento` datetime NOT NULL,
  `estado_suscripcion` enum('Activa','Cancelada','Vencida') NOT NULL,
  `fecha_cancelacion` datetime DEFAULT NULL,
  `motivo_cancelacion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_suscripcion`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_plan` (`id_plan`),
  CONSTRAINT `suscripcion_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `suscripcion_ibfk_2` FOREIGN KEY (`id_plan`) REFERENCES `plan_suscripcion` (`id_plan`)
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suscripcion`
--

LOCK TABLES `suscripcion` WRITE;
/*!40000 ALTER TABLE `suscripcion` DISABLE KEYS */;
INSERT INTO `suscripcion` VALUES (501,'Arclyne_01',1,'2026-04-30 21:02:25','2026-05-30 21:02:25','Activa',NULL,NULL),(1000,'gabriel_hernandez',1,'2026-05-18 22:28:55','2026-06-18 22:28:55','Activa',NULL,NULL),(1001,'victor_vasquez',1,'2026-01-01 00:00:00','2026-02-01 00:00:00','Vencida',NULL,NULL);
/*!40000 ALTER TABLE `suscripcion` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `TRG_AI_Suscripcion_Auditoria` AFTER INSERT ON `suscripcion` FOR EACH ROW BEGIN
    DECLARE v_descripcion VARCHAR(255);
    SET v_descripcion = CONCAT('El usuario contrató un plan. ID Suscripcion: ', NEW.id_suscripcion);

    CALL SP_Registrar_Bitacora(NEW.id_usuario, 'ALTA_SUSCRIPCION', v_descripcion);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `TRG_AU_Suscripcion_Auditoria` AFTER UPDATE ON `suscripcion` FOR EACH ROW BEGIN
    DECLARE v_descripcion VARCHAR(255);

    IF OLD.estado_suscripcion != NEW.estado_suscripcion THEN
        SET v_descripcion = CONCAT('Cambio de estatus: de "', OLD.estado_suscripcion, '" a "', NEW.estado_suscripcion, '".');
        CALL SP_Registrar_Bitacora(NEW.id_usuario, 'CAMBIO_ESTATUS_SUSCRIPCION', v_descripcion);
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` varchar(50) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `correo_electronico` varchar(150) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `fecha_registro` date NOT NULL,
  `estado_cuenta` enum('Activa','Suspendida','Desactivada') NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `correo_electronico` (`correo_electronico`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('admin_01','Ángel Gabriel','Aguilar Hernández','arclyne@ejemplo.com','password123','2026-05-12','Activa'),('Arclyne_01','Angel','Aguilar','angel@ejemplo.com','pass123','2026-04-30','Activa'),('daniel_perez','Daniel','Pérez Santiago','daniel@ejemplo.com','1234','2026-05-18','Activa'),('gabriel_hernandez','Gabriel','Hernández Martínez','gabriel@ejemplo.com','1234','2026-05-18','Activa'),('victor_vasquez','Víctor Hugo','Vásquez','victor@ejemplo.com','1234','2026-05-18','Activa');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_rol`
--

DROP TABLE IF EXISTS `usuario_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_rol` (
  `id_usuario` varchar(50) NOT NULL,
  `id_rol` int NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_rol`),
  KEY `id_rol` (`id_rol`),
  CONSTRAINT `usuario_rol_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `usuario_rol_ibfk_2` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_rol`
--

LOCK TABLES `usuario_rol` WRITE;
/*!40000 ALTER TABLE `usuario_rol` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videojuego`
--

DROP TABLE IF EXISTS `videojuego`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videojuego` (
  `id_videojuego` int NOT NULL AUTO_INCREMENT,
  `id_publisher` int NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `descripcion` text NOT NULL,
  `fecha_lanzamiento_base` datetime NOT NULL,
  `clasificacion_edades` varchar(10) NOT NULL,
  `precio_base` decimal(10,2) NOT NULL,
  `estado_disponibilidad_base` enum('Disponible','No Disponible') NOT NULL,
  PRIMARY KEY (`id_videojuego`),
  KEY `id_publisher` (`id_publisher`),
  CONSTRAINT `videojuego_ibfk_1` FOREIGN KEY (`id_publisher`) REFERENCES `publisher` (`id_publisher`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videojuego`
--

LOCK TABLES `videojuego` WRITE;
/*!40000 ALTER TABLE `videojuego` DISABLE KEYS */;
INSERT INTO `videojuego` VALUES (1,1,'Halo 2','Juego de disparos','2004-11-09 00:00:00','M',500.00,'Disponible'),(10,1,'Elden Ring','RPG de acción','2022-02-25 00:00:00','18+',599.00,'Disponible');
/*!40000 ALTER TABLE `videojuego` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videojuego_genero`
--

DROP TABLE IF EXISTS `videojuego_genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videojuego_genero` (
  `id_videojuego` int NOT NULL,
  `id_genero` int NOT NULL,
  PRIMARY KEY (`id_videojuego`,`id_genero`),
  KEY `id_genero` (`id_genero`),
  CONSTRAINT `videojuego_genero_ibfk_1` FOREIGN KEY (`id_videojuego`) REFERENCES `videojuego` (`id_videojuego`),
  CONSTRAINT `videojuego_genero_ibfk_2` FOREIGN KEY (`id_genero`) REFERENCES `genero` (`id_genero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videojuego_genero`
--

LOCK TABLES `videojuego_genero` WRITE;
/*!40000 ALTER TABLE `videojuego_genero` DISABLE KEYS */;
/*!40000 ALTER TABLE `videojuego_genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videojuego_plataforma`
--

DROP TABLE IF EXISTS `videojuego_plataforma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videojuego_plataforma` (
  `id_videojuego` int NOT NULL,
  `id_plataforma` int NOT NULL,
  `precio_plataforma` decimal(10,2) NOT NULL,
  `fecha_lanzamiento_plataforma` datetime NOT NULL,
  `estado_disponibilidad_plataforma` enum('Disponible','No Disponible') NOT NULL,
  PRIMARY KEY (`id_videojuego`,`id_plataforma`),
  KEY `id_plataforma` (`id_plataforma`),
  CONSTRAINT `videojuego_plataforma_ibfk_1` FOREIGN KEY (`id_videojuego`) REFERENCES `videojuego` (`id_videojuego`),
  CONSTRAINT `videojuego_plataforma_ibfk_2` FOREIGN KEY (`id_plataforma`) REFERENCES `plataforma` (`id_plataforma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videojuego_plataforma`
--

LOCK TABLES `videojuego_plataforma` WRITE;
/*!40000 ALTER TABLE `videojuego_plataforma` DISABLE KEYS */;
INSERT INTO `videojuego_plataforma` VALUES (10,1,599.00,'2026-04-30 20:30:15','Disponible');
/*!40000 ALTER TABLE `videojuego_plataforma` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-28 10:48:21
