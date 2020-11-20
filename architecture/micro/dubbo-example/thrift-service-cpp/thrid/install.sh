#!/bin/bash
set -x

if [ ! -e src ]; then
    echo "src dir not exists"
    exit 1
fi

work_dir=$(pwd)
deps_dir=${work_dir}/deps
src_dir=${work_dir}/src
build_dir=${work_dir}/build

make_thread_num=8

## clean
[ -e "${deps_dir}" ] && rm -rf "${deps_dir}"
mkdir -p "${deps_dir}/lib" && mkdir -p "${deps_dir}/include" && mkdir -p "${deps_dir}/bin"

[ -e "${build_dir}" ] && rm -rf "${build_dir}"
mkdir -p "${build_dir}"
cd "${build_dir}" || exit

## concat boost
cat "${src_dir}"/boost_1_71_0.tar.gz.part* > "${src_dir}"/boost_1_71_0.tar.gz

## extractor files
tar -zxf "${src_dir}"/openssl-OpenSSL_1_1_1.tar.gz
tar -zxf "${src_dir}"/gflags-2.2.2.tar.gz || exit
tar -zxf "${src_dir}"/glog-0.4.0.tar.gz || exit
tar -zxf "${src_dir}"/boost_1_71_0.tar.gz || exit
tar -zxf "${src_dir}"/libevent-2.1.11-stable.tar.gz || exit
tar -zxf "${src_dir}"/protobuf-cpp-3.9.1.tar.gz || exit
tar -zxf "${src_dir}"/thrift-0.11.0.tar.gz || exit
tar -zxf "${src_dir}"/leveldb-1.22.tar.gz || exit
tar -zxf "${src_dir}"/incubator-brpc-0.9.6.tar.gz || exit

## compile
export LDFLAGS="-L${deps_dir}/lib"
export CPPFLAGS="-I${deps_dir}/include"
export CXXFLAGS="-std=c++11 -fPIC"
export CFLAGS=-fPIC
export PATH=${deps_dir}/bin:$PATH


# openssl
cd "${build_dir}"/openssl-OpenSSL_1_1_1 || exit
./config --prefix="${deps_dir}" && make -j ${make_thread_num} && make install

# gflags
cd "${build_dir}"/gflags-2.2.2 || exit
(cmake . && make -j ${make_thread_num}) || exit
cp -a include/* "${deps_dir}"/include/
cp -a lib/* "${deps_dir}"/lib/

# glog
cd "${build_dir}"/glog-0.4.0 || exit
(./autogen.sh && ./configure --prefix="${deps_dir}" && make -j ${make_thread_num} && make install)

# boost
cd "${build_dir}"/boost_1_71_0 || exit
./bootstrap.sh --without-libraries=mpi,python, graph, graph_parallel
./b2 --prefix="${deps_dir}" -j ${make_thread_num} threading=multi address-model=64 variant=release stage install

# libevent
cd "${build_dir}"/libevent-2.1.11-stable || exit
(./configure --prefix="${deps_dir}" && make -j ${make_thread_num} && make install -j ${make_thread_num}) || exit

# thrift
cd "${build_dir}"/thrift-0.11.0 || exit
(./bootstrap.sh && ./configure --prefix="${deps_dir}" --with-boost="${deps_dir}" --with-libevent="${deps_dir}" \
--with-ruby=no --with-python=no --with-java=no --with-go=no --with-perl=no --with-php=no --with-csharp=no \
--with-erlang=no --with-lua=no --with-nodejs=no && make -j ${make_thread_num} && make install) || exit

# protobuf
cd "${build_dir}"/protobuf-3.9.1 || exit
./autogen.sh && ./configure --prefix="${deps_dir}" && make -j ${make_thread_num} && make install
#export CFLAGS=-fPIC && export CXXFLAGS=-fPIC && ./autogen.sh && ./configure --prefix="${deps_dir}" && make -j ${make_thread_num} && make install

# leveldb
cd "${build_dir}"/leveldb-1.22 || exit
cmake -DCMAKE_BUILD_TYPE=Release . && cmake --build . -j ${make_thread_num}
cp -a include/* "${deps_dir}"/include/ || exit
cp -a libleveldb.a "${deps_dir}"/lib/ || exit

# brpc
cd "${build_dir}"/incubator-brpc-0.9.6 || exit
sh config_brpc.sh --headers="${deps_dir}"/include --libs="${deps_dir}"/lib --with-thrift && make -j ${make_thread_num}
cp -a output/include/* "${deps_dir}"/include/ || exit
cp -a output/lib/* "${deps_dir}"/lib/ || exit
cp -a output/bin/* "${deps_dir}"/bin/ || exit

echo "done" && exit