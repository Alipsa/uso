#!/usr/bin/env bash
set -e
cd "$(dirname "$0")"

function buildExample() {
  pushd "$1"
    echo ""
    echo "********************************"
    echo "Building $(basename $PWD) using uso"
    echo "********************************"
    ./uso clean test
    echo ""
    echo "*********************************"
    echo "Building $(basename $PWD) using usas"
    echo "*********************************"
    ./usas clean test
  popd
}

pushd uso-core
  echo ""
  echo "********************"
  echo "Building uso-core..."
  echo "********************"
  ./build deployLocal
popd
buildExample examples/simpleExample
# buildExample examples/dependencies
echo ""
echo "*****"
echo "Done!"
echo "*****"