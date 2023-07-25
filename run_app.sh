#!bin/bash

# Build the Maven project
mvn clean install

# Build the Docker image
docker build -t patient-service .

# Stop and remove existing containers
docker-compose-down

# Start the application using Docker Compose
docker-compose up