# employee-management-system-java
Java Swing + JDBC Employee Management System

## Author

Rajashekharayya S Hiremath  
Electronics and Communication Engineering    
LinkedIn: linkedin.com/in/rajashekharayya-s-hiremath-68580b301/


# Employee Management System

A desktop application built using **Java Swing and JDBC** to manage employee records with a MySQL database.

## Features

• Insert new employee details  
• Search employee by **ID or Name**  
• Fetch all employees using **JTable**  
• Update employee details  
• Delete employee records  
• Input validation and confirmation dialogs  
• Clean GUI built with Java Swing

## Technologies Used

- Java
- Java Swing
- JDBC
- MySQL

## Project Structure

src/company

MainFrame.java  
InsertEmployeeFrame.java  
GetEmployeeFrame.java  
UpdateEmployeeFrame.java  
DeleteEmployeeFrame.java  
FetchAllEmployeeFrame.java  
DBConnection.java

## Database Setup

Create the database in MySQL:

```sql
CREATE DATABASE employee_db;

USE employee_db;

CREATE TABLE employee(
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(50),
age INT,
salary DOUBLE
);
