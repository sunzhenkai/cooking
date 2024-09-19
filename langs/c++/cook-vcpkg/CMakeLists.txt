cmake_minimum_required(VERSION 3.24)
project(cook-vcpkg)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
find_package(fmt REQUIRED)

add_executable(main src/main.cpp)
target_link_libraries(main fmt::fmt)
