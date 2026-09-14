# Student Management System

A Spring Boot based Student and Contract Management System developed using Java, Spring Boot, Spring Data JPA, PostgreSQL and REST APIs.

## Project Overview

This project provides a secure management system for handling students, users and contracts.

It includes authentication, role-based access control, contract management, document management, version tracking and audit logging.

## Features

### User Management
- User Registration
- User Login
- User Logout
- Password Encryption using BCrypt
- Change Password
- Role-Based Access Control
- USER and ADMIN roles

### Student Management
- Add Student
- View Student
- Update Student
- Delete Student
- Search Students
- Pagination and Sorting

### Contract Management
- Create Contract
- View Contract
- Update Contract
- Delete Contract
- Search Contracts
- Filter Contracts by Status
- Pagination and Sorting
- Contract Status Management

Supported contract statuses:

- DRAFT
- ACTIVE
- EXPIRED
- TERMINATED

### Contract Documents
- Upload Contract Documents
- Download Contract Documents
- Store document information
- Track uploaded user and upload time

### Contract Version Management
- Maintain contract versions
- Track version number
- Track changes
- View contract version history

### Modification Requests
- Users can request contract modifications
- Admin can review modification requests
- Admin can approve or reject requests
- Track request and review information

### Audit Logs
The system records important activities such as:

- Login
- Logout
- Registration
- Create
- Update
- Delete
- Password Change

### Activity Logs
Admin users can:

- View activity logs
- Search logs
- Filter logs
- Filter logs by date
- Export filtered logs to CSV

### Security
- BCrypt password encryption
- Role-based authorization
- Protected APIs
- Authentication and authorization
- Global exception handling
- Input validation

## Technologies Used

- Java 21
- Spring Boot 4.1.0
- Spring Data JPA
- Hibernate
- Spring Security
- PostgreSQL
- REST API
- Maven
- HTML
- CSS
- JavaScript
- Thymeleaf

## Database

The project uses PostgreSQL.

Default database name:

```text
student_db
