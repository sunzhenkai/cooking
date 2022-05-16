//    m[0] = a;
//    m.emplace(0, std::move(a));
//    std::vector<A> va;
//    A a;
//    m[0] = a;
//    A &b = m[0];
//    va.emplace_back(std::move(a));
#include <shared_mutex>
#include "iostream"
#include "vector"
#include "unordered_map"
#include "chrono"

struct MessageHeader {
    uint64_t mMetaBufferSize = 0;
    uint64_t mDataBufferSize = 0;
    uint64_t mProcessorClassId = 0;
    uint64_t mRequestId = 0;

    MessageHeader() = default;
};

#ifndef GET_OR_DEFAULT
#define GET_OR_DEFAULT(dest, m, key, dft) auto it = (m).find(key); (dest) = it == (m).end() ? (dft) : it->second
#endif //GET_OR_DEFAULT

#ifndef GET_OR_DEFAULT_V2
#define GET_OR_DEFAULT_V2(m, key, dft) auto it = (m).find(key);it == (m).end() ? (dft) : it->second
#endif //GET_OR_DEFAULT_V2


int main() {
    std::unordered_map<std::string, std::string> m{
            {"a", "a"},
            {"b", "b"}
    };
    std::string dest;
    GET_OR_DEFAULT(dest, m, "a", "no");
    std::cout << dest << std::endl;

    std::cout << GET_OR_DEFAULT_V2(m, "a", "no") << std::endl;

    std::shared_mutex current_mutex_;
    std::shared_lock lock(current_mutex_);

    MessageHeader h;
    std::cout << sizeof(struct MessageHeader) << ", " << sizeof(h) << std::endl;
    h.mDataBufferSize = 10;
    std::cout << sizeof(struct MessageHeader) << ", " << sizeof(h) << std::endl;

    std::unordered_map<int, int> ma;
    std::unordered_map<int, int> s1;
    std::unordered_map<int, int> s2;
    int sum = 1000 * 10000;
    int mid = sum / 8;
    for (int i = 0; i < sum; ++i) {
        ma.emplace(i, i);
        if (i < mid) {
            s1.emplace(i, i);
        } else {
            s2.emplace(i, i);
        }
    }

    auto start = std::chrono::system_clock::now();
    for (int i = 0; i < sum; ++i) {
        ma.find(i);
    }
    auto cost = std::chrono::duration_cast<std::chrono::microseconds>(std::chrono::system_clock::now() - start).count();
    std::cout << "cost 1 = " << cost << std::endl; // output: 902771

    start = std::chrono::system_clock::now();
    for (int i = 0; i < sum; ++i) {
        if (s1.find(i) != s1.end()) continue;
        s2.find(i);
    }
    cost = std::chrono::duration_cast<std::chrono::microseconds>(std::chrono::system_clock::now() - start).count();
    std::cout << "cost 2 = " << cost << std::endl; // output: 1528848
    return 0;
}
