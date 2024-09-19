get_filename_component(_DEP_NAME ${CMAKE_CURRENT_LIST_DIR} NAME)

function(SubBuild)
    set(_DEP_D "sub-build")
    set(_DEP_E "sub-build" PARENT_SCOPE)
endfunction()

function(Build)
    SubBuild()
    set(_DEP protobuf)
    set(_DEP_B "build")
    set(_DEP_C "build")
    set(_DEP_B ${_DEP_B} PARENT_SCOPE)
    message(STATUS "under build. _DEP_D=${_DEP_D}")
    message(STATUS "under build. _DEP_E=${_DEP_E}")
endfunction()

set(FN "func")
function("Build_${FN}")
    message(STATUS "under function. INT_V=${INT_V}")
endfunction()

cmake_language(CALL "Build_${FN}")

Build()

unset(Build)

set(PARENT_V "VALUE" CACHE STRING "PARENT_V")
message(STATUS "test cache. PARENT_V=${PARENT_V}")

set(INT_V "INT_V" CACHE INTERNAL "INT_V")