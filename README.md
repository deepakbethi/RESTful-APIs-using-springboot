# Backend Shopping Application

This repository contains the backend code for a robust shopping application designed and implemented with the Spring Framework. The backend services include the Product Service, Payment Service, and Order Service, all of which enable seamless communication between the frontend and backend. Additionally, service discovery and load balancing are achieved by connecting these services using Eureka Server, providing a scalable and resilient architecture.

# Key Features:

Product Service: This service handles product-related functionality, including product information retrieval, creation, modification, and deletion.

Payment Service: The payment service facilitates secure payment processing, integrating various payment gateways and ensuring a smooth and secure transaction experience for users.

Order Service: The order service manages order creation, tracking, and fulfillment, ensuring a seamless shopping experience for customers.

Eureka Server: Eureka Server is used for service discovery and load balancing, making it easier to manage and scale the application's services.

MySQL Database: The application utilizes a MySQL database to store and manage product data, user information, payment records, and order details, ensuring data persistence and reliability.

# Technologies Used:
Spring Framework: The core of the application is built on the Spring Framework, providing a solid foundation for building RESTful APIs.

Eureka Server: Service discovery and load balancing are achieved through Eureka Server, enhancing the application's reliability and scalability.

MySQL Database: MySQL is used as the database system to store and manage data efficiently and securely.

# Project Structure:
product-service: This module contains the Product Service implementation.
payment-service: The Payment Service implementation is housed within this module.
order-service: The Order Service implementation is located here.
eureka-server: The Eureka Server configuration and setup reside in this module.
