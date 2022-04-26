include(cmakes/test-function.cmake)

function(ARG version url)
    message(STATUS "version: ${version}, url: ${url}")
endfunction(ARG)

message(STATUS "go")
ARG(1.0.0 www.so.com 2)

function(ARG2)
    message(STATUS "a1=${a1} ARGN=${ARGN} ARGV=${ARGV}")
    foreach (arg IN LISTS ARGN)
        message(STATUS "arg=${arg}")
    endforeach ()
endfunction(ARG2)

ARG2(1 2 3)

function(ARG3)
    set(options OPTIONAL FAST)
    set(oneValueArgs DEP_NAME DEP_URL)
    set(multiValueArgs TARGETS CONFIGURATIONS)
    cmake_parse_arguments(ARG3 "${options}" "${oneValueArgs}" "${multiValueArgs}" ${ARGN})
    message(STATUS "DEP_NAME=${ARG3_DEP_NAME} DEP_URL=${ARG3_DEP_URL} DEP_N=${ARG3_DEP_N}")

    set(options None)
    set(oneValueArgs DEP_NAME DEP_URL DEP_CUR_DIR)
    set(multiValueArgs NoneMulti)
    cmake_parse_arguments(_ARG_ "${options}" "${oneValueArgs}" "${multiValueArgs}" ${ARGN})
endfunction(ARG3)

ARG3(DEP_NAME hello DEP_URL kitty)


list(APPEND CMAKE_PREFIX_PATH ${_DEP_PREFIX})
string(REPLACE ";" "|" T_CMAKE_PREFIX_PATH "${CMAKE_PREFIX_PATH}")
message(STATUS "build ${_DEP_NAME}, T_CMAKE_PREFIX_PATH=${T_CMAKE_PREFIX_PATH}")

#include(ExternalProject)
#set(CMAKE_ARGS
#        -DCMAKE_BUILD_TYPE=Release
#        -DCMAKE_PREFIX_PATH=${T_CMAKE_PREFIX_PATH}
#        -DCMAKE_INSTALL_PREFIX=${_DEP_PREFIX}
#        -DBINARY_DIR=${_DEP_CUR_DIR}/build
#        -DBUILD_STATIC_LIB=ON
#        -DBUILD_SHARED_LIB=OFF)
#ExternalProject_Add(
#        spdlog_spdlog
#        SOURCE_DIR "${_DEP_CUR_DIR}/src"
#        LIST_SEPARATOR |
#        CMAKE_ARGS ${CMAKE_ARGS}
#)

list(APPEND EXTERNAL_LIST a)
list(APPEND EXTERNAL_LIST b)
message(STATUS "EXTERNAL_LIST=${EXTERNAL_LIST}")


function(ARG4)
    set(options OPTIONAL FAST)
    set(oneValueArgs NAME URL)
    set(multiValueArgs KEY)
    cmake_parse_arguments(PREFIX "${options}" "${oneValueArgs}" "${multiValueArgs}" ${ARGN})
    message(STATUS "FAST=${PREFIX_FAST} NAME=${PREFIX_NAME} URL=${PREFIX_URL} KET=${PREFIX_KEY}")
endfunction(ARG4)

ARG4(
        URL www.so.com
        KEY band price
)