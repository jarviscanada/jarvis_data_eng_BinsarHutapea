#!/bin/bash

#Read CLI arguments
cmd=$1
db_username=$2
db_password=$3

#Start Docker
sudo systemctl status docker || sudo systemctl start docker

#Check container status
docker container inspect jrvs-psql
container_status=$?

#Handle options
case $cmd in
  create)

    if [ $container_status -eq 0 ]; then
      echo 'Container exists'
      exit 1
    fi

    #Check # of CLI arguments
    if [ $# -ne 3 ]; then
      echo 'Username and password are required for Create'
      exit 1
    fi

    #Create container
    docker volume create pgdata
    docker run --name jrvs-psql -e POSTGRES_PASSWORD=db_password -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres:9.6-alpine
    exit $?
    ;;

    start|stop)
    if [ $container_status -ne 0 ]; then
      exit 1
    fi

    docker container $cmd jrvs-psql
    exit $?
    ;;

    *)
    echo 'Illegal command'
    echo 'Commands: start|stop|create'
    exit 1
    ;;
  esac
