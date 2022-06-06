FROM postgres:9.6-alpine
ENV POSTGRES_DB jrvstrading
COPY sql_ddl/init_db.sql /docker-entrypoint-initdb.d/
COPY sql_ddl/schema.sql /docker-entrypoint-initdb.d/