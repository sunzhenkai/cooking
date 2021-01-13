//
// Created by Wii on 2021/1/11.
//

#include <iostream>
#include "brpc/flags.h"

int main(int argc, char *args[]) {
    ::gflags::ParseCommandLineFlags(&argc, &args, true);
    std::cout << "PORT: " << ::universal::FLAGS_port << std::endl;
    return 0;
}