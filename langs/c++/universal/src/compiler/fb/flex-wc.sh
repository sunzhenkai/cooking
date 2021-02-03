#!/bin/bash
#set -x

cd wc || exit
DEPS_PATH=../../../../third/deps
LIB_PATH=${DEPS_PATH}/lib
export PATH=${DEPS_PATH}/bin:$PATH
flex wc.l

export LD_LIBRARY_PATH=${LIB_PATH}
export LIBRARY_PATH=${LIB_PATH}
export DYLD_LIBRARY_PATH=${LIB_PATH}

gcc -L"${LIB_PATH}" -lfl -o wc lex.yy.c
./wc
