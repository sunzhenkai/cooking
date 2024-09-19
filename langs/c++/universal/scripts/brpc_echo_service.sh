#!/bin/sh
set -x

echo "preparing to run script $0 under folder $(pwd), with cmd file: $(cat cmds/brpc_echo.cmd)"

bash build.sh brpc_echo_service
./build/brpc-echo-service --flagfile=cmds/brpc_echo.cmd