cmake_minimum_required(VERSION 3.10)
project(universal)

set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

#SET(CMAKE_FIND_LIBRARY_SUFFIXES ".a")
#SET(BUILD_SHARED_LIBS OFF)
#SET(CMAKE_EXE_LINKER_FLAGS "-static")

message("Options: all=" ${all})

set(DEPS_DIR ${CMAKE_SOURCE_DIR}/third/deps)
set(DEPS_LIB_DIR ${DEPS_DIR}/lib)
set(DEPS_INCLUDE_DIR ${DEPS_DIR}/include)
set(SRC_THRIFT_GEN_DIR ${CMAKE_SOURCE_DIR}/thrift/gen-cpp)
set(SRC_INCLUDE_DIR ${CMAKE_SOURCE_DIR}/include)

message(STATUS "DEPS_DIR = " ${DEPS_DIR})
message(STATUS "DEPS_INCLUDE_DIR = " ${DEPS_INCLUDE_DIR})
message(STATUS "SRC_THRIFT_GEN_DIR = " ${SRC_THRIFT_GEN_DIR})
message(STATUS "SRC_INCLUDE_DIR = " ${SRC_INCLUDE_DIR})

include_directories(${DEPS_INCLUDE_DIR})
include_directories(${DEPS_INCLUDE_DIR}/thrift)
include_directories(${SRC_THRIFT_GEN_DIR})
include_directories(${SRC_INCLUDE_DIR})

find_library(GFLAGS gflags ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library(GLOG glog ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library(BOOST_SYSTEM boost_system ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library(PROTO_BUFFER protobuf ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library(PROTO_LITE protobuf-lite ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library(PROTOC protoc ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library(THRIFT thrift ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library(THRIFT_NB thriftnb ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library(EVENT event ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library(LEVELDB leveldb ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library(GTEST gtest ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library(SSL ssl ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library(CRYPTO crypto ${DEPS_LIB_DIR} NO_DEFAULT_PATH)
find_library(BRPC brpc ${DEPS_LIB_DIR} NO_DEFAULT_PATH)

#get gflags namespace
execute_process(
        COMMAND bash -c "grep \"namespace [_A-Za-z0-9]\\+ {\" ${DEPS_INCLUDE_DIR}/gflags/gflags_declare.h | head -1 | awk '{print $2}' | tr -d '\n'"
        OUTPUT_VARIABLE GFLAGS_NS
)

if (${GFLAGS_NS} STREQUAL "GFLAGS_NAMESPACE")
    execute_process(
            COMMAND bash -c "grep \"#define GFLAGS_NAMESPACE [_A-Za-z0-9]\\+\" ${DEPS_INCLUDE_DIR}/gflags/gflags_declare.h | head -1 | awk '{print $3}' | tr -d '\n'"
            OUTPUT_VARIABLE GFLAGS_NS
    )
endif ()
message("gflags namespace: " ${GFLAGS_NS})

set(CMAKE_CXX_FLAGS "-std=c++11 -g -O3 -w -fPIC -fno-omit-frame-pointer -Wno-sign-compare -pthread -DGFLAGS_NS=${GFLAGS_NS}")
set(EXTERNAL_LIB ${SSL} ${CRYPTO} ${BOOST_SYSTEM} ${PROTO_BUFFER} ${PROTO_LITE} ${PROTOC} ${EVENT} ${GFLAGS} ${GLOG} ${GTEST} ${LEVELDB} ${THRIFT_NB} ${THRIFT} ${BRPC})

add_definitions(-DBRPC_WITH_GLOG=1)

file(GLOB SOURCE_FILES ${SRC_THRIFT_GEN_DIR}/*.cpp src/*.cpp src/brpc/*.cpp src/gflags/*.cpp)
add_library(service-lib STATIC ${SOURCE_FILES})

if (${universal})
    add_executable(universal src/main.cpp)
    target_link_libraries(universal ${EXTERNAL_LIB} -v)
endif ()

if (${thrift_server})
    add_executable(thrift-server src/brpc/thrift_server.cpp)
    target_link_libraries(thrift-server service-lib ${EXTERNAL_LIB} -v)
endif ()

if (${brpc_echo_service})
    add_executable(brpc-echo-service src/brpc/brpc_echo_service.cpp)
    target_link_libraries(brpc-echo-service service-lib ${EXTERNAL_LIB} -v)
endif ()

if (${gflags_test_foo})
    add_executable(gflags-test-foo src/gflags/gflags_test_foo.cpp)
    target_link_libraries(gflags-test-foo ${EXTERNAL_LIB} -v)
endif ()

if (${gflags_test_bar})
    add_executable(gflags-test-bar src/gflags/gflags_test_bar.cpp)
    target_link_libraries(gflags-test-bar ${EXTERNAL_LIB} -v)
endif ()