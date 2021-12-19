create Database EZ_DataBase;


USE EZ_DataBase;

Create Table users 
(
id int,

USERNAME varchar(50),

PASSWORD varchar(50),

ACCOUNT_STATUS varchar(50)
);

INSERT INTO users
VALUES (1, 'Aaron411', 'password', 'Customer_Account');

Select * 
From users;

