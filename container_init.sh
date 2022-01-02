#!/bin/bash

useradd flink_system

# Initialize postgres
echo "starting postgres"
service postgresql start

# Setup database
sudo -u postgres psql -f ./database_init.sql
sudo -u postgres psql -d flink -f ./schema_init.sql
sudo -u postgres psql -d flink -f ./database_test_data.sql

# run flink application server
sudo -u flink_system java -jar flink_deploy.jar
