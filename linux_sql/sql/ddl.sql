--Connect to database
\c host_agent;


--DDL for host_info table
CREATE TABLE IF NOT EXISTS PUBLIC.host_info(
    id SERIAL NOT NULL,
    hostname VARCHAR NOT NULL,
    cpu_number INTEGER NOT NULL,
    cpu_architecture VARCHAR NOT NULL,
    cpu_model VARCHAR NOT NULL,
    cpu_mhz DECIMAL NOT NULL,
    L2_cache INTEGER NOT NULL,
    total_mem INTEGER NOT NULL,
    "timestamp" TIMESTAMP NOT NULL
);

--DDL for host_usage table
CREATE TABLE IF NOT EXISTS PUBLIC.host_usage(
    "timestamp" TIMESTAMP NOT NULL,
    host_id SERIAL NOT NULL,
    memory_free INTEGER NOT NULL,
    cpu_idle INTEGER NOT NULL,
    cpu_kernel INTEGER NOT NULL,
    disk_io BIGINT NOT NULL,
    disk_available INTEGER NOT NULL
);