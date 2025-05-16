#!/usr/bin/env bash
set -e
cd "$(dirname "$0")"

function buildExample() {
  dir="$1"
  shift 1
  pushd "$dir"
    echo ""
    echo "********************************"
    echo "Building $(basename "$dir") using uso"
    echo "********************************"
    ./uso "$@"
    echo ""
    echo "*********************************"
    echo "Building $(basename "$dir") using usas"
    echo "*********************************"
    ./usas "$@"
  popd
}

pushd uso-tasks
  echo ""
  echo "********************"
  echo "Building uso-tasks..."
  echo "********************"
  ./build deployLocal
popd
pushd uso-core
  echo ""
  echo "********************"
  echo "Building uso-core..."
  echo "********************"
  ./build deployLocal
popd
buildExample examples/simpleExample clean compile
buildExample examples/dependencies clean jar
buildExample examples/publish clean deployLocal
buildExample examples/boms clean deployLocal
echo ""
echo "*****"
echo "Done!"
echo "*****"