# Introduction
This app executes SQL queries to fetch data from a database, process it, and display the results to users. JDBC is used in this app to establish a connection between it and the database. In addition, PostgreSQL is used as the Relational Database Management System (RDBMS). In this project, Docker is used to manage a PostgreSQL instance. Also, Maven is utilized for managing this project and its dependencies. 

# Implementation
## ER Diagram
![ERD](./assets/ERD.png)

## Design Patterns
This section discusses DAO pattern and Repository pattern. DAO, which stands for Data Access Object, provides an abstraction layer between JDBC and the business logic and also separation of concerns in code. It can be either an abstraction layer or an object. If it is the former, then a DTO, which stands for Data Transfer Object, is necessary as it gives single-domain data, fully encapsulates an object, and may contain subobjects. A single DTO is the output and the input of a single DAO.

Meanwhile, Repository pattern is centred on single-table access per class. In this case, a DAO could be a repository. Unlike DAO pattern, where join operations are performed in the database, the join operations in Repository pattern are performed in the code.

In this application, DAO pattern is used. Also, DAOs are abstraction layers in this project, meaning that a DTO is necessary for each of those.

# Test
For testing this app, by executing several DDL files, a database was created with several tables, which had data inserted into each of them afterwards. Create, read, update, and delete operations were tested twice each, first by executing SQL operations while accessing the database on a PostgreSQL instance, followed by running the app and comparing both results.