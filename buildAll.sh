#!/usr/bin/env bash
set -e
cd "$(dirname "$0")"

pushd uso-core
  echo ""
  echo "********************"
  echo "Building uso-core..."
  echo "********************"
  ./build deployLocal
popd
pushd examples/simpleExample
  echo ""
  echo "********************************"
  echo "Building simpleExample using uso"
  echo "********************************"
  ./uso clean compile
  echo ""
  echo "*********************************"
  echo "Building simpleExample using usas"
  echo "*********************************"
  ./usas clean compile
popd
echo ""
echo "*****"
echo "Done!"
echo "*****"