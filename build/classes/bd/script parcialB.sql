
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema basedparcial
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema basedparcial
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `basedparcial` DEFAULT CHARACTER SET utf8mb4 ;
USE `basedparcial` ;

-- -----------------------------------------------------
-- Table `basedparcial`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `basedparcial`.`cliente` (
  `cedula` BIGINT(20) NOT NULL,
  `nombre` VARCHAR(255) NOT NULL,
  `apellido` VARCHAR(255) NOT NULL,
  `telefono` INT(11) NOT NULL,
  `direccion` VARCHAR(255) NOT NULL,
  `correo` VARCHAR(255) NOT NULL,
  `genero` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`cedula`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `basedparcial`.`productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `basedparcial`.`productos` (
  `nombreProducto` VARCHAR(255) NOT NULL,
  `precio` DECIMAL(15,0) NOT NULL,
  `cantidad` INT(11) NOT NULL,
  PRIMARY KEY (`nombreProducto`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
