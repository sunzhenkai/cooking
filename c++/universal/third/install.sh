#!/bin/bash
set -x
source tools.sh

work_dir=$(pwd)
deps_dir=${work_dir}/deps
src_dir=${work_dir}/src
build_dir=${work_dir}/build

make_thread_num="$(tool::get_cpu_num)"

export LDFLAGS="-L${deps_dir}/lib"
export CPPFLAGS="-I${deps_dir}/include"
export CXXFLAGS="-std=c++11 -fPIC -mavx -maes -O3 -Wno-sign-compare -g -Wno-narrowing -Wno-unused-function -Wno-unused-variable -Wno-error=unused-command-line-argument"
export CFLAGS=-fPIC

export LD_LIBRARY_PATH="${deps_dir}/lib:${LD_LIBRARY_PATH}"
export LIBRARY_PATH="${deps_dir}/lib:${LIBRARY_PATH}"
export C_INCLUDE_PATH="${deps_dir}/include:${C_INCLUDE_PATH}"
export PATH=${deps_dir}/bin:$PATH

flag_clean="Y"
flag_concat_boost="Y"
flag_openssl="Y"
flag_gflags="Y"
flag_glog="Y"
flag_boost="Y"
flag_event="Y"
flag_proto="Y"
flag_bison="Y"
flag_thrift="Y"
flag_leveldb="Y"
flag_googletest="Y"
flag_brpc="Y"

# thrift_version=0.9.3.1
thrift_version=0.11.0

## clean
[[ $flag_clean ]] && (
  [ -e "${deps_dir}" ] && rm -rf "${deps_dir}"
  mkdir -p "${deps_dir}/lib" && mkdir -p "${deps_dir}/include" && mkdir -p "${deps_dir}/bin"
)

[[ $flag_clean ]] && (
  [ -e "${build_dir}" ] && rm -rf "${build_dir}"
  mkdir -p "${build_dir}"
)

## concat boost
[[ $flag_concat_boost ]] && (
  cat "${src_dir}"/boost_1_71_0.tar.gz.part* >"${src_dir}"/boost_1_71_0.tar.gz
)

## compile

# openssl
if [ ! -z $flag_openssl ]; then
  cd "${build_dir}" || exit
  tar -zxf "${src_dir}"/openssl-OpenSSL_1_1_1.tar.gz
  cd "${build_dir}"/openssl-OpenSSL_1_1_1 || exit
  ./config --prefix="${deps_dir}" && make -j ${make_thread_num} && make install
fi

# gflags
if [ ! -z $flag_gflags ]; then
  cd "${build_dir}" || exit
  tar -zxf "${src_dir}/gflags-2.2.2.tar.gz" || exit
  cd "${build_dir}"/gflags-2.2.2 || exit

  mkdir build
  cd build || exit
#  cmake -DCMAKE_INSTALL_PREFIX:PATH="${deps_dir}" .. || exit
  cmake -DCMAKE_INSTALL_PREFIX:PATH="${deps_dir}" -DBUILD_SHARED_LIBS=1 .. || exit
  #  cmake -DCMAKE_INSTALL_PREFIX:PATH="${deps_dir}" -DBUILD_SHARED_LIBS=1 -DBUILD_STATIC_LIBS=1 .. || exit
  make -j ${make_thread_num} && make install || exit
#  (cmake . && make -j ${make_thread_num}) || exit
#  cp -a include/* "${deps_dir}"/include/
#  cp -a lib/* "${deps_dir}"/lib/
fi

# glog
if [ ! -z $flag_glog ]; then
  cd "${build_dir}" || exit
  tar -zxf "${src_dir}/glog-0.4.0.tar.gz" || exit
  cd "${build_dir}/glog-0.4.0" || exit
  (./autogen.sh && ./configure --prefix="${deps_dir}" && make -j ${make_thread_num} && make install) || exit
# echo "./autogen.sh && ./configure --prefix='${deps_dir}' && make -j ${make_thread_num} && make install" > build.sh
# bash build.sh
fi

# boost
if [ ! -z $flag_boost ]; then
  cd "${build_dir}" || exit
  tar -zxf "${src_dir}/boost_1_71_0.tar.gz" || exit
  cd "${build_dir}"/boost_1_71_0 || exit
  ./bootstrap.sh --without-libraries=mpi,python, graph, graph_parallel
  (./b2 --prefix="${deps_dir}" -j ${make_thread_num} threading=multi address-model=64 variant=release stage install) || exit
fi

# libevent
if [ ! -z $flag_event ]; then
  cd "${build_dir}" || exit
  tar -zxf "${src_dir}/libevent-2.1.11-stable.tar.gz" || exit
  cd "${build_dir}"/libevent-2.1.11-stable || exit
  (./configure --prefix="${deps_dir}" && make -j ${make_thread_num} && make install -j ${make_thread_num}) || exit
fi

# protobuf
if [ ! -z $flag_proto ]; then
  cd "${build_dir}" || exit
  tar -zxf "${src_dir}"/protobuf-cpp-3.9.1.tar.gz || exit
  cd "${build_dir}"/protobuf-3.9.1 || exit
  (./autogen.sh && ./configure --prefix="${deps_dir}" && make -j ${make_thread_num} && make install) || exit
#export CFLAGS=-fPIC && export CXXFLAGS=-fPIC && ./autogen.sh && ./configure --prefix="${deps_dir}" && make -j ${make_thread_num} && make install
fi

# bison
if [ ! -z $flag_bison ]; then
  cd "${build_dir}" || exit
  tar -zxf "${src_dir}/bison-3.7.4.tar.gz" || exit
  cd bison-3.7.4 || exit
  (./configure --prefix="${deps_dir}" && make -j ${make_thread_num} && make install) || exit
fi

# thrift
if [ ! -z $flag_thrift ]; then
  cd "${build_dir}" || exit
  tar -zxf "${src_dir}/thrift-$thrift_version.tar.gz" || exit
  ls -l
  cd thrift-$thrift_version || exit

  if [[ "$(tool::os_type)" != "darwin" ]]; then
    export CXXFLAGS="${CXXFLAGS} -lssl -lcrypto"
  fi
  (./bootstrap.sh && ./configure --prefix="${deps_dir}" --with-boost="${deps_dir}" --with-libevent="${deps_dir}" \
    --with-ruby=no --with-python=no --with-java=no --with-go=no --with-perl=no --with-php=no --with-csharp=no \
    --with-haskell=no --with-erlang=no --with-lua=no --with-nodejs=no --with-qt4=no && make -j ${make_thread_num} && make install) || exit
fi

# leveldb
if [ ! -z $flag_leveldb ]; then
  cd "${build_dir}" || exit
  tar -zxf "${src_dir}/leveldb-1.22.tar.gz" || exit
  cd "${build_dir}"/leveldb-1.22 || exit
  mkdir -p build
  cd build || exit
  cmake -DCMAKE_BUILD_TYPE=Release -DCMAKE_INSTALL_PREFIX:PATH="${deps_dir}" .. && cmake --build .
  make -j ${make_thread_num} && make install
fi

# googletest
if [ ! -z $flag_googletest ]; then
  cd "${build_dir}" || exit
  tar -zxf "${src_dir}/googletest-release-1.10.0.tar.gz" || exit
  cd "${build_dir}"/googletest-release-1.10.0/googletest || exit
  mkdir build
  cd build || exit
  cmake -DCMAKE_INSTALL_PREFIX:PATH="${deps_dir}" .. && cmake --build .
  (make -j ${make_thread_num}) || exit
  cp -a ../include/* "${deps_dir}/include/"
  cp -a lib/* "${deps_dir}/lib/"
fi

# brpc
if [ ! -z $flag_brpc ]; then
  cd "${build_dir}" || exit
  tar -zxf "${src_dir}/incubator-brpc-0.9.7.tar.gz" || exit
  cd "${build_dir}"/incubator-brpc-0.9.7 || exit
  bash config_brpc.sh --headers="${deps_dir}/include/" --libs="${deps_dir}/lib/" --with-glog --with-thrift
  (make -j ${make_thread_num}) || exit
  cp -a output/include/* "${deps_dir}/include/"
  cp -a output/lib/* "${deps_dir}/lib/"
  cp -a output/bin/* "${deps_dir}/lib/"
fi

echo "All is done."
