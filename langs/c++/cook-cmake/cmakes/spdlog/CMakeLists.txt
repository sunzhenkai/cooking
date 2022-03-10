set(_DEP_SUB "spdlog")
set(_DEP_SUB_B "spdlog")
set(_DEP_SUB "spdlog" PARENT_SCOPE)

get_filename_component(_DEP_NAME ${CMAKE_CURRENT_LIST_DIR} NAME)

message(STATUS "under sub. _DEP_NAME=${_DEP_NAME}")
message(STATUS "under sub. PARENT_V=${PARENT_V}")

function("Build_${PARENT_V}")
    message(STATUS "function - F. Build_${PARENT_V}")
endfunction()

cmake_language(CALL "Build_${PARENT_V}")

message(STATUS "INT_V=${INT_V}")