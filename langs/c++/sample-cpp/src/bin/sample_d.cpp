#include <iostream>
#include <map>
#include "unordered_map"

typedef std::unordered_map<uint64_t, uint64_t> UM;

//typedef void (*EF)();
//
//EF fun2(UM &um) {
//    auto f = [&] {
//        um.emplace(1, 2);
//        return 0;
//    };
//    return f;
//}

class A {
public:
    int a;
};

typedef std::shared_ptr<A> SA;

std::function<void()> fun(UM &um) {
    auto f = [&] {
        um.emplace(1, 2);
    };
    return f;
}

std::function<void()> fun(SA sa) {
    auto f = [&] {
        sa->a = 1;
    };
    return f;
}


std::function<void()> fun() {
    std::shared_ptr<A> sa = std::make_shared<A>();
    sa->a = 2;
    auto f = [&, sa] {
        std::cout << "sa-> " << sa->a << std::endl;
        sa->a = 1;
    };
    return f;
}

void test() {
    std::cout << "----- in test -----" << std::endl;
    fun()();
    std::cout << "----- out test -----" << std::endl;
}

int main() {
    auto tp = std::make_tuple(1, 2, 3);
    std::cout << std::get<0>(tp) << std::endl;

    std::map<int, int> m;
    m.emplace(1, 2);
    m.emplace(1, 3);
    std::cout << m[1] << std::endl;
    m[1] = 3;
    m[2] = 4;
    std::cout << m[1] << " " << m[2] << std::endl;
    m.insert({1, 4});
    std::cout << m[1] << std::endl;

    std::cout << "----" << std::endl;

    UM um;
    auto ff = fun(um);
    ff();
    std::cout << um[1] << std::endl;
    test();
    return 0;
}