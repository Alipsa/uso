#!/usr/bin/env bash

check() {
    local artifact_name="$1"
    local message="Evaluating dependencies for $artifact_name"
    local len=${#message}
    local line=$(printf '%.0s-' $(seq 1 $len))

    echo -e "--$line--"
    echo -e "| $message |"
    echo -e "--$line--"
  ./checkGrabs.groovy $1
  echo ""
}
check uso-tasks/build
check uso-core/build
