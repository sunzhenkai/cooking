#include <iostream>
#include "sample_class.h"
#include "sample_include.h"

using namespace std;

int main() {
    cout << "hello world" << endl;
    cout << sample::i << ", " << sample::ci << ", " << sample::sci << ", "
         << sample::si << std::endl;
    return 0;
}