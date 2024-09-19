#!/bin/bash
set -xe
cur_dir="$(pwd)"
cd thrift-defines && bash install.sh
cd "$cur_dir"

cd thrift-defines-0.5.0 && bash install.sh
cd "$cur_dir"

cd thrift-defines-0.13.0 && bash install.sh
cd "$cur_dir"

cd thrift-example-0.5.0 && mvn clean package

cd thrift-example-0.13.0 && mvn clean package

echo "done"