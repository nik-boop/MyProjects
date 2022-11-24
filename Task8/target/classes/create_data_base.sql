CREATE DATABASE IF NOT EXISTS mydb;
USE mydb;
DROP TABLE IF EXISTS  people;
CREATE TABLE people
(
    id INT  UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    firstname      VARCHAR(255),
    surname        VARCHAR(255),
    address        VARCHAR(255),
    date_of_birth  DATE,
    Place_of_birth VARCHAR(255),
    Salary         FLOAT,
    Marital_status BOOL
);
INSERT INTO People(firstname,surname,address)
VALUES 
   ('John','Do','Canada'),
   ('Salim','Salem','Morocco'),
   ('Yuri','Puturin','Russia'),
   ('Juan','De La Cruiz','Mexico');