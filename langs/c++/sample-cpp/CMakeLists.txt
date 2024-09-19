cmake_minimum_required(VERSION 3.16)
project(sample-cpp)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

include(cmakes/sample.cmake)
TestParentScope()
message(STATUS "NOT_EXISTS_V=${NOT_EXISTS_V}, FUN_NOT_EXISTS_V=${FUN_NOT_EXISTS_V}")
if (${FUN_NOT_EXISTS_V})
    message(STATUS "TRUE")
endif ()

include_directories(src/bin)
add_executable(main src/bin/main.cpp)

add_executable(sample_a src/bin/sample_a.cpp)
add_executable(sample_b src/bin/sample_b.cpp)
add_executable(sample src/bin/sample_defines.cpp)
target_link_libraries(sample pthread)
