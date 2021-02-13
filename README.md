# Shopping Application(Design Patterns)
**In this application, used singleton, proxy, bridge, proxy, strategy design patterns.**


## Singleton Design Pattern
**Singleton design pattern was used in the application in the ShoppingCart class. 
In the application, the user can have only one shopping cart (object) as long as it is active. 
A singleton design pattern was used to create a single object from the ShoppingCart class and use this object from different classes.**

## Proxy Design Pattern
**The proxy design pattern was used while creating the order in the application.** 

**Instead of completing the order directly on an order class, payment transactions are controlled by adding a proxy class in between.**

## Bridge Design Pattern
**Bridge design pattern provides modeling and application of objects in separate class structures. 
Bridge design pattern has been used because a shopping application should contain many message types and message services.**

## Strategy Design Pattern
**Different payment types can be included in the shopping application. 
New payment methods can be added with new versions.
The purpose of using the strategy design model is that a payment transaction can have multiple methods.
These methods can be changed independently of each other and new payment methods added.**

## Observer Design Pattern
**In shopping applications, the user can follow the price of a product.
As soon as there is a change in the price of the product, the user is notified of this change with email and toast messages notifications. 
This structure was developed with Observer design pattern.**

## What's Done In This Project
**-Product objects are created in the MainActivity class at runtime. The price of the product named Ps5 is updated after 2 seconds and the user is notified.**

**-There is only one active user in the application.**

**-The application has been tested as a view on pixel devices.**

**-Recyclerview was used in home fragment and shopping cart fragment.**

**-Glide library was used while editing images.**

**-A prototype for purchasing products has been developed.**



