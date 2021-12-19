USE EZ_DataBase;

Create Table reviews(

id int PRIMARY KEY,

reviewScore int,

user_id int REFERENCES users(USERNAME),

postedDate date,

reviewText longtext

);

