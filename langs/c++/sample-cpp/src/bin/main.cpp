#include <iostream>
#include "sample_class.h"
#include "sample_include.h"

using namespace std;

int main() {
    int a = 10;
    std::string r;
    r.append(std::to_string(a)).append("abc");
    std::cout << r << std::endl;
    return 0;
}
