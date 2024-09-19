#include "iostream"
#include "thread"
#include <unistd.h>
#include <shared_mutex>

class A {
public:
    A() {
        std::cout << "init a" << std::endl;
    };

    explicit A(int v) : v_(v) {
        std::cout << "init a with " << v << std::endl;
    }

    int v_ = 1;
};

class B {
public:
    A a;
    std::shared_mutex mutex_;
    std::unique_lock<std::shared_mutex> lock_;

    B() = default;

    explicit B(int v) : a(v) {}

    void f() {
        std::unique_lock lock(mutex_);
        std::cout << "enter aaa" << std::endl;
        sleep(2);
        std::cout << "out bbb" << std::endl;
    }

    std::pair<std::unique_lock<std::shared_mutex>, A> l() {
        std::unique_lock lock(mutex_);
        std::cout << "enter fff" << std::endl;
        // TODO reference
        return std::make_pair(std::move(lock), a);
    }
};

void run() {
    while (true) {
        std::cout << "hello" << std::endl;
        sleep(1);
    }
}

int main() {
    A a1;
    A a2(2);
    std::cout << a1.v_ << " - " << a2.v_ << std::endl;

    std::cout << "---" << std::endl;
    B b1(2);
    B b2;
    std::cout << b1.a.v_ << std::endl;
    std::cout << b2.a.v_ << std::endl;

    std::cout << "---" << std::endl;
    std::thread td1(&B::f, &b1);
    std::thread td2(&B::f, &b1);
    {
        sleep(1);
        std::cout << "ddd" << std::endl;
        std::unique_lock lock = b1.l();
        std::cout << "ggg" << std::endl;
    }
//    std::thread td(run);
    sleep(10);
    return 0;
}