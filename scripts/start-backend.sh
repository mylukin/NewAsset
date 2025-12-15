#!/bin/bash
# Start backend Spring Boot application

echo "Starting Asset Management Backend..."

cd "$(dirname "$0")/.."

# Check if Maven wrapper exists
if [ -f "./mvnw" ]; then
    ./mvnw spring-boot:run
else
    mvn spring-boot:run
fi
