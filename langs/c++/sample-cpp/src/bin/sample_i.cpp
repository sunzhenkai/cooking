#include "iostream"
#include "map"
#include "vector"
#include "memory"
#include "unordered_map"

class A {
public:
    int v_;
    A(int v) : v_(v) {
        std::cout << "new a" << std::endl;
    }

//    A(const A &a) {
//        std::cout << "new &a" << std::endl;
//    }

    ~A() {
        std::cout << "destroy a" << std::endl;
    }
};

int main() {
    std::unordered_map<int, A> m;
//    std::map<int, std::shared_ptr<A>> mp;
//    std::shared_ptr<A> sa = std::make_shared<A>();
//    std::cout << sa.get() << std::endl;
//    mp.emplace(0, sa);
    {
        A a(111);
        std::cout << "A - " << a.v_ << std::endl;
        m.emplace(0, std::move(a));
        std::cout << "m size " << m.size() << std::endl;
        std::cout << "A - " << a.v_ << std::endl;
        std::cout << "A in map - " << m.begin()->second.v_ << std::endl;
    }

    {
        std::shared_ptr<A> a = std::make_shared<A>(1);
        std::shared_ptr<A> b = std::move(a);
        std::cout << "AA " << a.get() << std::endl;
        std::cout << "BB " << b->v_ << std::endl;
    }

//    m[0] = a;
//    m.emplace(0, std::move(a));
//    std::vector<A> va;
//    A a;
//    m[0] = a;
//    A &b = m[0];
//    va.emplace_back(std::move(a));
    return 0;
}