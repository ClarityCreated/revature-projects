<!-- markdownlint-disable MD032 MD033-->
<!-- 
<p align="center">
    <img width="100%" src="https://imgur.com/a/4E9ZdbX" alt="Project Banner">

  <br>

</p> -->
<!-- ![afullname](https://user-images.githubusercontent.com/81045242/146610747-fa152293-dc83-4303-b715-7868eefb9356.PNG) -->

![again](https://user-images.githubusercontent.com/81045242/146613527-a61b7eb1-0e2d-4b74-b337-5b87cf1011ee.PNG)


---

## **EZGo Game Store**

* A store where users can login
* View available games
* Employees can add games to the store
* You can add a review as a customer
* Checkout your purchases


---

## **Project Description**

For this project I worked in a group with 3 other people. My role in this project was to fullstack-develop the reviews feature. I also worked on the styling, navigation, and routing for the front-end.

## **Technologies Used**

* Java (intelliJ IDEA)
* JavaScript (Visual Studio Code)
* MySQL Database

### Front-end Framework

* Angular 2
* Bootstrap 

### Back-end Dependencies

* Spring-Boot
* JWT
* Bcrypt
* Lombok
* Jackson (XML)

## **Challenges**

It took some time to figure out how to use GitHub. We struggled figuring out the best practices but once we got it figured out the project started going smoothly. This was our first group project and everyone was working on seperate features. It was hard for me to focus only on my feature once I got to the front-end. Everything had to be tied together and not everyone was able to complete their part of the project. 

---

## **Install & Run**

Copy the repository

### **Front-end**

Open a new terminal at the "EzGameStore" directory and run 
```
npm i
npm start
```

### **Back-end**

Under the file src/resources/application.yml
* Add your database followed by username and password
* Change jpa: ddl-auto: create and run the application
```
jpa:
    show-sql: true
    hibernate:
      ddl-auto: create
```
* After you run the application change jpa: ddl-auto: update
```
jpa:
    show-sql: true
    hibernate:
      ddl-auto: update
```
This method will allow you to auto generate the tables we set up for our database. 

---

