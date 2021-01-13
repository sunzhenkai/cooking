#!/bin/sh
set -x

echo "preparing to run script $0 under folder $(pwd)"

bash build.sh universal
./build/universal