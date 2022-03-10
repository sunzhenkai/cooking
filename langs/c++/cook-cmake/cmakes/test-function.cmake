function(ARG version url flag)
    message(STATUS "version: ${version}, url: ${url}, flag: ${flag}")
endfunction(ARG)

ARG(1.0.0 www.so.com true)