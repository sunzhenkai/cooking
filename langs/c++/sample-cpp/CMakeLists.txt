cmake_minimum_required(VERSION 3.16)
project(sample-cpp)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

add_executable(main src/bin/main.cpp)

add_executable(sample_a src/bin/sample_a.cpp)
add_executable(sample_b src/bin/sample_b.cpp)
add_executable(sample src/bin/sample_e.cpp)