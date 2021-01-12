//
// Created by Wii on 2021/1/11.
//

#include <iostream>
#include <gflags/gflags.h>

DEFINE_int32(port, 80, "port");
DEFINE_string(service_name, "undefined_service_name", "service name");

int main(int argc, char *argv[]) {
    gflags::ParseCommandLineFlags(&argc, &argv, true);
    std::cout << "port: " << FLAGS_port << ", service name: " << FLAGS_service_name << std::endl;
}