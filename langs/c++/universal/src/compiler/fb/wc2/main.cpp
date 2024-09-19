#include <iostream>

#include "driver.hpp"


int main(int argc, char **argv) {
    assert(argc == 2);
    wii::WCDriver driver;
    driver.parse(argv[1]);
    driver.print();
    return 0;
}