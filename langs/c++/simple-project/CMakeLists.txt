cmake_minimum_required(VERSION 3.28)
project(simple_project VERSION 0.0.1)

set(CMAKE_CXX_STANDARD 17)
include(GNUInstallDirs)

execute_process(
        COMMAND sh -c "git describe --exact-match --tags 2> /dev/null || git rev-parse --short HEAD"
        OUTPUT_VARIABLE TAG_REVERSION
        OUTPUT_STRIP_TRAILING_WHITESPACE
)
if (TAG_REVERSION STREQUAL "")
    set(TAG_REVERSION "UNKNOWN")
endif ()
add_definitions(-DTAG_REVERSION="${TAG_REVERSION}")

add_executable(simple_project main.cpp)

set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${CMAKE_BINARY_DIR}/output)
message(STATUS "CKPT ${simple_project_VERSION} - ${simple_project_VERSION_MAJOR} - ${simple_project_VERSION_MINOR}")
message(STATUS "CKPT ${PROJECT_NAME} - ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}")
set_target_properties(${PROJECT_NAME} PROPERTIES
        VERSION ${simple_project_VERSION}
        SOVERSION "${simple_project_VERSION_MAJOR}.${simple_project_VERSION_MINOR}"
        COMPILE_PDB_NAME ${PROJECT_NAME}
        COMPILE_PDB_OUTPUT_DIRECTORY ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}
        FOLDER ${PROJECT_NAME}
)