#include <iostream>
#include "fmt/format.h"

int main() {
    std::cout << fmt::format("hello {}", "world") << std::endl;
    return 0;
}