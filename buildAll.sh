#!/usr/bin/env bash
set -e
cd "$(dirname "$0")"

function banner() {
    msg="* $* *"
    edge=$(echo "$msg" | sed 's/./*/g')
    echo "$edge"
    echo "$msg"
    echo "$edge"
}

function buildExample() {
  dir="$1"
  shift 1
  pushd "$dir"
    echo ""
    banner "Building $(basename "$dir")"
    ./uso "$@"
  popd
}

pushd uso-tasks
  echo ""
  banner "Building uso-tasks..."
  ./build publishLocal
popd
pushd uso-core
  echo ""
  banner "Building uso-core..."
  ./build publishLocal
popd
buildExample examples/simpleExample clean compile
buildExample examples/dependencies clean jar
buildExample examples/publish clean publishLocal
buildExample examples/boms clean publishLocal
buildExample examples/layout compile
buildExample examples/multimodule buildAll
echo ""
banner "Done!"