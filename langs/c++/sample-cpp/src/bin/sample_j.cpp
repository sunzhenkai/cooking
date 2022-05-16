#include "iostream"
#include "memory"

#define Concat(a, b) a##b
#define ConcatX(x) "X"#x"X"
#define AppendX(a) a##x
#define XAppend(a) x##a
#define ToString(a) #a

typedef void (*F)();

class A {
public:
    static void f() {
        std::cout << "call f" << std::endl;
    }
};

void df(F f) {
    f();
}

class B {
public:
    virtual void f() = 0;
};

class C : public B {
public:
    void f() override;
};

void C::f() {
    std::cout << "no" << std::endl;
}

#define ConcatM(...) #__VA_ARGS__

int main() {
    std::shared_ptr<int> va = std::make_shared<int>(10);
    auto f1 = [ca=std::move(va)] {
        std::cout << "ca:" << *ca << std::endl;
    };

    auto f2 = [da=std::move(va)] {
        std::cout << "da:" << da << std::endl;

    };
    f2();
    std::cout << "va:" << va << std::endl;
    f1();

    std::cout << ConcatM(a, b, c) << std::endl;

    C c;
    c.f();

    A a;
    df(&(a.f));

    int ab = 1, ax = 2, xa = 3;
    std::cout << Concat(a, b) << std::endl;
    std::cout << AppendX(a) << std::endl;
    std::cout << XAppend(a) << std::endl;
    std::cout << ToString(abc) << std::endl;
    std::cout << ConcatX(a) << std::endl;

    uint64_t uid = 2819395867UL;
    int32_t id = uid;
    std::cout << id << std::endl;
    return 0;
}