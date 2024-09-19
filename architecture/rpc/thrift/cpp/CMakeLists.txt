cmake_minimum_required(VERSION 3.10)
project("Thrift Demo Project")

set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_FLAGS "-std=c++11 -g -mavx -maes -O3 -w -fPIC -fno-omit-frame-pointer")

set(DEPS_DIR ${CMAKE_SOURCE_DIR}/third/deps)
set(DEPS_LIB_DIR ${DEPS_DIR}/lib)
set(DEPS_INCLUDE_DIR ${DEPS_DIR}/include)
set(DEPS_THRIFT_GEN_DIR ${CMAKE_SOURCE_DIR}/thrift/gen-cpp)

message(STATUS "DEPS_DIR = " ${DEPS_DIR})
message(STATUS "DEPS_INCLUDE_DIR = " ${DEPS_INCLUDE_DIR})
message(STATUS "DEPS_THRIFT_GEN_DIR = " ${DEPS_THRIFT_GEN_DIR})

include_directories(${DEPS_INCLUDE_DIR})
include_directories(${DEPS_INCLUDE_DIR}/thrift)
include_directories(${DEPS_THRIFT_GEN_DIR})

find_library (GFLAGS gflags ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library (GLOG glog ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library (BOOST_SYSTEM boost_system ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library (PROTO_BUFFER protobuf ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library (PROTO_LITE protobuf-lite ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library (PROTOC protoc ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library (THRIFT thrift ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library (THRIFT_NB thriftnb ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library (EVENT event ${DEPS_LIB_DIR} NO_DEFAULT_PATH)

set(EXTERNAL_LIB ${BOOST_SYSTEM} ${THRIFT} ${THRIFT_NB} ${PROTO_BUFFER} ${PROTO_LITE} ${PROTOC} ${EVENT} ${GFLAGS} ${GLOG})

file (GLOB SOURCE_FILES ${DEPS_THRIFT_GEN_DIR}/*.cpp)
add_library (service-lib ${SOURCE_FILES})

add_executable(main src/main.cpp)
target_link_libraries(main service-lib ${EXTERNAL_LIB})

add_executable(client src/client/client.cpp)
target_link_libraries(client service-lib ${EXTERNAL_LIB})

add_executable(server src/server/server.cpp)
target_link_libraries(server service-lib ${EXTERNAL_LIB})

