cmake_minimum_required(VERSION 3.24)
project(section_00)

set(CMAKE_CXX_STANDARD 17)

aux_source_directory(. SRCS)
message(${SRCS})
add_library(section_00 ${SRCS})
