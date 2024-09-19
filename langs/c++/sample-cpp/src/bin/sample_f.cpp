#include <iostream>

class A {
public:
    int v;

    A() = default;

    A(const A &a) = default;

    A(A &&a) = default;

    void f() {
        v = 1;
    }
};

int main() {
    A a{2};
    A b = a;
    std::cout << "b.v=" << b.v << std::endl;
    auto f = [b = b]() mutable {
        b.f();
    };
    f();
    std::cout << "b.v=" << b.v << std::endl;

    auto fa = [](A &&a) {
        std::cout << a.v << std::endl;
    };
    return 0;
}