cmake_minimum_required(VERSION 3.10)
project(cook_cmake)

set(CMAKE_CXX_STANDARD 14)
set(PARENT_V "cook_cmake")

include(cmakes/include.cmake)
add_subdirectory(cmakes/spdlog)

message(STATUS "_DEP=${_DEP}")
message(STATUS "_DEP_B=${_DEP_B}")
message(STATUS "_DEP_C=${_DEP_C}")
message(STATUS "_DEP_D=${_DEP_D}")
message(STATUS "_DEP_E=${_DEP_E}")

Build()

message(STATUS "_DEP_SUB=${_DEP_SUB}")
message(STATUS "_DEP_SUB_B=${_DEP_SUB_B}")
add_library(cook_cmake library.cpp)
