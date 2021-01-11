#!/bin/sh
set -x

echo "preparing to run script $0 under folder $(pwd)"

./build/gflags-test-foo --flagfile="$(pwd)/cmds/gflags_test.cmd"
./build/gflags-test-bar --flagfile="$(pwd)/cmds/gflags_test.cmd"

### output
# $ bash scripts/gflags_test.sh
# ++ pwd
# + echo 'preparing to run script scripts/gflags_test.sh under folder /Users/wii/Git/cooking/c++/universal'
# preparing to run script scripts/gflags_test.sh under folder /Users/wii/Git/cooking/c++/universal
# ++ pwd
# + ./build/gflags-test-foo --flagfile=/Users/wii/Git/cooking/c++/universal/cmds/gflags_test.cmd
# port: 10000, service name: gflags_test_foo
# ++ pwd
# + ./build/gflags-test-bar --flagfile=/Users/wii/Git/cooking/c++/universal/cmds/gflags_test.cmd
# port: 20000, service name: gflags_test_bar