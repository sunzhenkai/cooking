#!/bin/bash
#set -x

echo "process scene $1"
scene=$1
cd "$scene" || exit
DEPS_PATH=../../../../third/deps
LIB_PATH=${DEPS_PATH}/lib
export PATH=${DEPS_PATH}/bin:$PATH

# 词法解析
flex "$scene.l"

export LD_LIBRARY_PATH=${LIB_PATH}
export LIBRARY_PATH=${LIB_PATH}
# 对应 Mac OS 需要制定该变量，用于编译的可执行文件查找动态库
export DYLD_LIBRARY_PATH=${LIB_PATH}

# 编译词法解析程序，该过程需要连接 flex 库
gcc -L"${LIB_PATH}" -lfl -o "$scene" lex.yy.c
./"$scene"
