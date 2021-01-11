#!/bin/bash
set -x

[ -e build ] && rm -rf build
mkdir build
cd build || exit
cmake ..
make -j8
