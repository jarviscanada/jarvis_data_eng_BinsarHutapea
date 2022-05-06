# Linux Cluster Monitoring Agent

# Introduction
The Jarvis Linux Cluster Administration (LCA) team needs to record hardware specifications and resource usage of every node on a cluster managed by them. A minimum viable product (MVP) was designed to meet the customer's needs for this project. It works by execution of scripts, which retrieve hardware information and usage and store the data on a database so that it can be used for generating reports.

The LCA team performs the tasks by installing the MVP. It consists of an SQL instance and a bash agent, which, upon installation, executes two scripts. One script gathers hardware specifications of the machine it is installed on, while the other is automatically and periodically executed to retrieve the resource usage statistics of that machine. The collected data is then stored on a database so that the LCA team can retrieve it for reporting purposes.

In this project, bash is utilized for scripting purposes. In addition, PSQL is used to interact with the database, with Docker providing a container for managing PSQL instances. Also, Github is used as a repository for the project.

# Quick Start
Follow these steps in order to set up and utilize the MVP properly. Navigate to the *linux_sql* folder before proceeding.
- Start a PSQL instance using *psql_docker.sh*

``
./scripts/psql_docker.sh create <PSQL username> <PSQL password>
``

``
./scripts/psql_docker.sh start
``

- Create tables using *ddl.sql*

``
    psql -h localhost -U postgres -d host_agent -f sql/ddl.sql
``

- Insert hardware specs data into the database using *host_info.sh*

``
    ./scripts/host_info.sh <PSQL hostname> <PSQL port> host_agent <PSQL user> <PSQL password>
``

- Insert hardware usage data into the database using *host_usage.sh*

``
    ./scripts/host_usage.sh <PSQL host address> <PSQL port> host_agent <PSQL username> <PSQL password>
``

- Crontab setup

``
bash crontab -e
``

Add the following to crontab
```
* * * * * <absolute_path_to_linux_sql_project_folder>/scripts/host_usage.sh <psql_host> <psql_port> host_agent <psql_user> <psql_password> > /tmp/host_usage.log
```

# Implementation

## Architecture
![Architecture Diagram](./assets/linux-sql-architecture.jpg)

## Scripts
### psql_docker.sh


This script is for managing PSQL instance on the node that runs the database.

Usage:

``
psql_docker.sh [OPTION]
``

Options:

``
create <PSQL username> <PSQL password>
``
: creates a container for PSQL.

``
start
``
: starts a PSQL instance.

``
stop
``
: stops the PSQL instance.


### host_info.sh

This script retrieves hardware information of a node. Users only need to execute this only once, and the hardware information will then be saved to database.

Usage:

``
host_info.sh <PSQL host address> <PSQL port> <DB name> <PSQL username> <PSQL password>
``

Parameters:
- PSQL host address: enter **localhost** if you are executing this script from within the node that contains the database, otherwise enter the **IP address** of said node.
- PSQL port: the port of the PSQL instance. Usually it is **5432**.
- DB name: The name of the database that stores the **host_info** table. On this project, it is set to **host_agent**.
- PSQL username: the username for accessing the database.
- PSQL password: the password for accessing the database.

### host_usage.sh 

This script retrieves hardware usage statistics of a node. This section explains the usage of this script if users want to collect hardware usage statistics manually.

Usage:

``
host_usage.sh <PSQL host address> <PSQL port> <DB name> <PSQL username> <PSQL password>
``

Parameters:
- PSQL host address: enter **localhost** if you are executing this script from within the node that contains the database, otherwise enter the **IP address** of said node.
- PSQL port: the port of the PSQL instance. Usually it is **5432**.
- DB name: The name of the database that stores the **host_info** table. On this project, it is set to **host_agent**.
- PSQL username: the username for accessing the database.
- PSQL password: the password for accessing the database.


### crontab

Setup **crontab** if users want to schedule automated execution of host_usage.sh.

Usage:


``
crontab -e
``
: This command opens the crontab editor

``
<cron value> <absolute_path_to_linux_sql_mvp_folder>/scripts/host_usage.sh <PSQL host address> <PSQL port> <DB name> <PSQL username> <PSQL ppassword> > /tmp/host_usage.log
``

Cron value has the following format:

``
[minutes] [hours] [day of the month] [month] [day of week]
``

Note: Set the cron value to * * * * * if users want to set the scheduler so that it executes the host_usage.sh script every minute.

### queries.sql

This file contains SQL commands for querying information by grouping hosts by hardware info and average memory usage and also detecting host failures. In the case of detection of host failures, it is assumed that a server has failed when it inserts less than three data points into the database within every 5-minute interval.

Usage:

``
psql -h <PSQL host address> -U <PSQL username> -d <DB name> -f sql/queries.sql
``

##  Database Modeling

### `host_info`

| id | hostname | cpu_number          | cpu_architecture           | cpu_model | cpu_mhz | L2_cache | total_mem            | timestamp |
|----|----------|---------------------|----------------------------|-----------|---------|----------|----------------------|-----------|
| Host ID (auto-increment) | Host name | Number of CPU cores | CPU architecture | CPU model | CPU frequency | L2 cache in KB | Total memory (in KB) | Current time in UTC time zone | 

### `host_usage`

| timestamp | host_id                        | memory_free         | cpu_idle                        | cpu_kernel | disk_io | disk_available |
|-----------|--------------------------------|---------------------|---------------------------------|------------|---------|----------------|
| UTC time zone | Host ID from *host_info* table | Free memory (in MB) | Time spent idle (in percentage) | Time spent running kernel code (in percentage) | Number of disk IO operations | Available disk space (in MB) |  

# Test
The bash scripts and SQL scripts were tested by executing them one at a time.

- The first script to be executed were psql_docker.sh, and accessing the PSQL instance using the terminal was performed to verify that the execution was successful.
- The second script to be executed was the ddl.sql, and verification was performed by accessing the host_agent database and checking if tables host_info and host_usage were created properly.
- The third script to be executed was host_info.sh, and accessing the host_info table on the database host_agent and showing all entries was the method of verifying if the execution was successful.
- The fourth script was host_usage.sh, which was first executed manually and verified by checking the host_info table on the database, and then secondly executed automatically by configuring crontab, and also verified by checking the aforementioned table.
- The last script to be tested was queries.sql, and it was verified by checking if the queries returned the correct information.

# Deployment
The MVP folder containing the necessary script files is hosted on a Github repository. It needs to be cloned/downloaded to each of the nodes on the cluster. One node hosts the database via Docker. As for the scheduler for retrieving hardware usage, crontab configuration needs to be performed on each node.

# Improvements
It would be nice to improve or add the following:
- A script that updates host_info table if there is an update to the hardware.
- A script that runs the entire set up by calling the other scripts in the MVP.
- A script that allows users to set up crontab just by inputting the cron values.
- A table that informs LCA team on what actions that are suggested in case of low memory or disk space on each node.