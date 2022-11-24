CREATE DATABASE IF NOT EXISTS mydb;
USE mydb;
DROP TABLE IF EXISTS  people;

CREATE TABLE IF NOT EXISTS `mydb`.`people` (
  `id` INT NOT NULL,
  `firstname` VARCHAR(255) NULL DEFAULT NULL,
  `surname` VARCHAR(255) NULL DEFAULT NULL,
  `date_of_birth` DATE NULL DEFAULT NULL,
  `place_of_birth` VARCHAR(255) NULL DEFAULT NULL,
  `salary` FLOAT NULL DEFAULT NULL,
  `marital_status` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`));
  
INSERT INTO `mydb`.`people` (`id`,`firstname`,`surname`,`date_of_birth`,`place_of_birth`,`salary`,`marital_status`) VALUES (1,'John','Do','1998-05-25','Plase_1',1234.56,1);
INSERT INTO `mydb`.`people` (`id`,`firstname`,`surname`,`date_of_birth`,`place_of_birth`,`salary`,`marital_status`) VALUES (2,'Salim','Salem','1984-01-12','Morocco',6543.21,1);
INSERT INTO `mydb`.`people` (`id`,`firstname`,`surname`,`date_of_birth`,`place_of_birth`,`salary`,`marital_status`) VALUES (3,'Yuri','Puturin','1980-02-29','Russia',178949,0);
INSERT INTO `mydb`.`people` (`id`,`firstname`,`surname`,`date_of_birth`,`place_of_birth`,`salary`,`marital_status`) VALUES (4,'Juan','De La Cruiz','1598-04-10','Mexico',0.1,1);