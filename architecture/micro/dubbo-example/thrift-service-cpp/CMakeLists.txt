cmake_minimum_required(VERSION 3.13)
project(thrift_service_cpp)

set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set (CMAKE_CXX_FLAGS "-std=c++11 -g -mavx -maes -O3 -w -fPIC -fno-omit-frame-pointer")

set (DEPS_DIR ${CMAKE_SOURCE_DIR}/thrid/deps)
set (DEPS_LIB_DIR ${DEPS_DIR}/lib)


include_directories(include/gen-cpp)
include_directories(thrid/deps/include)

find_library (GFLAGS gflags ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library (GLOG glog ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library (BOOST_SYSTEM boost_system ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library (THRIFT thrift ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library (THRIFT_NB thriftnb ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library (EVENT event ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library (PROTO_BUFFER protobuf ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library (PROTO_LITE protobuf-lite ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library (PROTOC protoc ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library (LEVEL_DB leveldb ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library (BRPC brpc ${DEPS_LIB_DIR} NO_DEFAULT_PATH)

#set (EXTERNAL_LIB ${BOOST_SYSTEM} ${THRIFT} ${THRIFT_NB} ${EVENT} ${PROTO_BUFFER}
#        ${PROTO_LITE} ${PROTOC} ${LEVEL_DB} ${BRPC} ${GFLAGS} ${GLOG})

set (EXTERNAL_LIB ${GLOG} ${GFLAGS})

#add_executable(server src/server.cpp)
add_executable(server_consul src/server_consul.cpp)
add_executable(main src/main.cpp)

file (GLOB SOURCE_FILES src/*.cpp include/gen-cpp/*.cpp)
add_library (service-lib ${SOURCE_FILES})

#target_link_libraries(server ${service-lib} ${EXTERNAL_LIB})
target_link_libraries(server_consul ${service-lib} ${EXTERNAL_LIB})
