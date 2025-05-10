#!/usr/bin/env bash
if [ -z "$(docker images -q sonatype/nexus3:latest 2> /dev/null)" ]; then
  echo "Downloading Nexus Docker image"
  docker pull sonatype/nexus3
fi
if docker ps -a | grep -q nexus; then
  echo "Starting"
  docker start nexus
else
  echo "Running"
  docker run -d -p 8081:8081 --name nexus sonatype/nexus3
fi
docker ps