create database db;

use db;

create table Assignment (MenuDesc char(10), MealType char(10), ItemName char(100), Price int(10), Energy int(10), Protein float, Carbohydrates  float, Total_fat  float, Dietary_fibre  float, Menu_Item_ID int);

-- LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Assignment2Data.csv'
LOAD DATA INFILE '/Users/palaktank/Desktop/swinRestaurant'
 INTO TABLE Assignment FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n' IGNORE 1 ROWS;
