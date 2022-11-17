CREATE DATABASE IF NOT EXISTS mydb;
USE mydb;
DROP TABLE IF EXISTS  people;
CREATE TABLE people
(
    id INT  UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255),
    surname VARCHAR(255),
    address VARCHAR(255)
);
INSERT INTO People(firstname,surname,addresssys_configsys_config)
VALUES 
   ('John','Do','Canada'),
   ('Salim','Salem','Morocco'),
   ('Yuri','Puturin','Russia'),
   ('Juan','De La Cruiz','Mexico');