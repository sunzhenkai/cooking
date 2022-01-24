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

std::function<void()> fun(UM &um) {
    auto f = [&] {
        um.emplace(1, 2);
    };
    return f;
}

int main() {
    std::map<int, int> m;
    m.emplace(1, 2);
    m.emplace(1, 3);
    std::cout << m[1] << std::endl;

    UM um;
    auto ff = fun(um);
    ff();
    std::cout << um[1] << std::endl;
    return 0;
}