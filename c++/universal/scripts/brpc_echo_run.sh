#!/bin/sh
set -x

echo "preparing to run script $0 under folder $(pwd)"

./build/brpc-echo-service --flagfile=cmds/brpc_echo.cmd