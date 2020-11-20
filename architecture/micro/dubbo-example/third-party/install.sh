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

make_thread_num=4

libevent_version='2.0.22'
thrift_version='0.8.0'

## clean
#[ -e "${deps_dir}" ] && rm -rf "${deps_dir}"
#mkdir -p "${deps_dir}/lib" && mkdir -p "${deps_dir}/include" && mkdir -p "${deps_dir}/bin"

#[ -e "${build_dir}" ] && rm -rf "${build_dir}"
#mkdir -p "${build_dir}"
cd "${build_dir}" || exit

## concat boost
#cat "${src_dir}"/boost_1_71_0.tar.gz.part* > "${src_dir}"/boost_1_71_0.tar.gz

## extractor files
#tar -zxf "${src_dir}"/openssl-OpenSSL_1_1_1.tar.gz
#tar -zxf "${src_dir}"/openssl-OpenSSL_1_0_2u.tar.gz
#tar -zxf "${src_dir}"/boost_1_71_0.tar.gz || exit
#tar -zxf "${src_dir}"/libevent-2.1.11-stable.tar.gz || exit
#tar -zxf "${src_dir}"/libevent-${libevent_version}-stable.tar.gz || exit
#tar -zxf "${src_dir}"/thrift-thrift-${thrift_version}.tar.gz || exit

## compile
export LDFLAGS="-arch x86_64 $LDFLAGS -L${deps_dir}/lib/"
#export LDFLAGS="$LDFLAGS -L/usr/lib/"
export CFLAGS="-arch x86_64 $CFLAGS -I${deps_dir}/include/"
#export LDFLAGS="-L/usr/local/opt/openssl@1.1/lib"
#export CPPFLAGS="-I/usr/local/opt/openssl@1.1/include"
#export PKG_CONFIG_PATH="/usr/local/opt/openssl@1.1/lib/pkgconfig"
#export CFLAGS="$CFLAGS -I/usr/include/"
#export DYLD_FALLBACK_LIBRARY_PATH="${deps_dir}/lib";$DYLD_FALLBACK_LIBRARY_PATH
#export LD_LIBRARY_PATH="$LD_LIBRARY_PATH:${deps_dir}/lib"
#export LIBRARY_PATH="${deps_dir}/lib"
#export LD_RUN_PATH="${deps_dir}/lib"
#export C_INCLUDE_PATH="${deps_dir}/include"
#export CPLUS_INCLUDE="${deps_dir}/include"
#export CPLUS_INCLUDE_PATH="${deps_dir}/include"
export CPPFLAGS="-arch x86_64 $CPPFLAGS -I${deps_dir}/include -fPIC"
export CXXFLAGS="$CXXFLAGS -std=c++11 -fPIC -Wno-c++11-narrowing -I${deps_dir}/include"
export PATH=${deps_dir}/bin:$PATH
export ARCHFLAGS="-arch x86_64"


# openssl
#cd "${build_dir}"/openssl-OpenSSL_1_0_2u || exit
#./Configure shared darwin64-x86_64-cc --prefix="${deps_dir}" && make clean && make -j ${make_thread_num} && make install
#./config --prefix="${deps_dir}" && make clean && make -j ${make_thread_num} && make install

# boost
#cd "${build_dir}"/boost_1_71_0 || exit
#./bootstrap.sh --without-libraries=mpi,python, graph, graph_parallel
#./b2 --prefix="${deps_dir}" -j ${make_thread_num} threading=multi address-model=64 variant=release stage install

# libevent
# cd "${build_dir}"/libevent-${libevent_version}-stable || exit
#(./configure --prefix="${deps_dir}" && make -j ${make_thread_num} && make install -j ${make_thread_num}) || exit

# thrift
cd "${build_dir}"/thrift-thrift-${thrift_version} || exit
(
./bootstrap.sh &&
./configure --prefix="${deps_dir}" \
--with-openssl=${deps_dir} --with-boost="${deps_dir}" --with-libevent="${deps_dir}" \
--with-ruby=no --with-python=no --with-java=no --with-cpp=no --with-go=no --with-perl=no --with-php=no --with-csharp=no \
--with-erlang=no --with-lua=no --with-nodejs=no && make clean && make && make install) || exit

echo "done" && exit