#include <variant>
#include "iostream"
#include <filesystem>

#include <cstdio>
#include <memory>
#include <stdexcept>
#include <array>
#include <atomic>
#include "map"
#include "shared_mutex"

std::pair<std::string, int> exec(const char *cmd) {
    std::array<char, 128> buffer{};
    std::string result;
    int return_code = -1;
    auto pclose_wrapper = [&return_code](FILE *cmd) { return_code = pclose(cmd); };
    { // scope is important, have to make sure the ptr goes out of scope first
        const std::unique_ptr<FILE, decltype(pclose_wrapper)> pipe(popen(cmd, "r"), pclose_wrapper);
        if (!pipe) throw std::runtime_error("popen() failed!");
        while (fgets(buffer.data(), buffer.size(), pipe.get()) != nullptr) {
            result += buffer.data();
        }
    }
    return make_pair(result, return_code);
}

class A {
    friend std::ostream &operator<<(std::ostream &os, const A &a) {
        os << "hello " << a.v;
        return os;
    }
public:
    std::atomic<bool> closed{false};
    int v;

    A(int vv) : v(vv) {
        std::cout << "construct a with" << vv << std::endl;
    }

    ~A() {
        std::cout << "destroy a with " << v << std::endl;
    };

    void Close() {
        if (!closed) {
            std::cout << "do no" << std::endl;
        } else {
            std::cout << "do ys" << std::endl;
        }
        if (!closed.exchange(true)) {
            std::cout << "do close" << std::endl;
        } else {
            std::cout << "already closed" << std::endl;
        }
    }
};

class B {
public:
    A a;

    B(int v) : a(v) {
        std::cout << "construct b" << std::endl;
    }
};

namespace fs = std::filesystem;

template<typename K, typename V>
using Map = std::map<K, V>;

void f(int a, int b = 0) {
    std::cout << a << " -- " << b << std::endl;
}

int main(int argc, char *argv[]) {
    const A *ap = nullptr;
    A aa(0);
    ap = &aa;

    std::cout << *ap << std::endl;

    f(1);
    f(1, 2);
    std::shared_mutex mtx;
    auto start = std::chrono::system_clock::now();
    for (int i = 0; i < 10000 * 10000; ++i) {
        std::shared_lock lock(mtx); // without lock: 167ms, with lock: 4334ms
    }
    auto now = std::chrono::system_clock::now();
    auto duration = std::chrono::duration_cast<std::chrono::milliseconds>(now - start).count();
    std::cout << "duration = " << duration << "ms" << std::endl;

    A a(0);
    a.Close();
    a.Close();

    std::cout << argv[0] << std::endl;
    std::cout << fs::path(argv[0]).parent_path() << std::endl;
    std::cout << fs::path(argv[0]).root_path() << std::endl;
    std::cout << fs::path(argv[0]).parent_path().parent_path() << std::endl;
//    std::variant<int, std::string> v;
//    v = "hello";
//    auto r = std::visit([](auto &t) { return "hello"; }, v);
//    std::cout << r << std::endl;
//
//    std::string cmd = "ls";
//    auto res = exec(cmd.data());
//    std::cout << res.first << " " << res.second << std::endl;
//    assert(1);
//    assert(0);
    std::shared_ptr<A> si2 = std::make_shared<A>(1);
    std::shared_ptr<A> si = std::make_shared<A>(0);
    {
        std::cout << "ckpt 1" << std::endl;
        std::cout << "ckpt 2" << std::endl;
//        si.reset(si2.get());
        si2 = si;
        std::cout << "ckpt 3" << std::endl;
        std::cout << si2->v << std::endl;
        std::cout << "ckpt 4" << std::endl;
        std::shared_ptr<A> n{nullptr};
        std::cout << n.get() << std::endl;
    }

    B(1);

    std::shared_ptr<A> a_ptr(nullptr);
    if (a_ptr != nullptr) {
        std::cout << "no" << std::endl;
    } else {
        std::cout << "yes" << std::endl;
    }

//    a_ptr = std::make_shared<A>(A(0));
//    if (a_ptr != nullptr) {
//        std::cout << "no" << std::endl;
//    } else {
//        std::cout << "yes" << std::endl;
//    }
    return 0;
}