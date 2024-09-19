#!/bin/bash
set -x

#[ -e build ] && rm -rf build
#mkdir build
#cd build || exit
#cmake ..
#make -j8

[ -z "$1" ] && all=true
#[ -e build ] && rm -rf build
cmake -B build -D "${1}"=true -D all=${all}
cmake --build build -j8
