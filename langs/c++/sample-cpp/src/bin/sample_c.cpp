#include <iostream>

using namespace std;

class A {
public:
    std::string a;
};

std::string &f(A &a) {
    a.a = "hello";
    return a.a;
//    return a.a;
}

class B {
public:
    int v;

    B() = default;

    B(B &&b) : v(b.v) {
        v = 10;
    }
};

class C {
public:
    C() {
        std::cout << "create c" << std::endl;
    }

    ~C() {
        std::cout << "erase c" << std::endl;
    }
};

typedef std::function<void(int)> FUN;

class D {
public:
    C c;
    FUN fun_;

    void SetC(C &&c) {
        cout << "a3" << endl;
        c = c;
        cout << "a4" << endl;
    }

    void SetFun(FUN &&fun) {
        cout << "3 " << &fun_ << endl;
        fun_ = std::move(fun);
        cout << "4 " << &fun_ << endl;
    }
};

void fc(C &&c, FUN &&fun) {
    cout << "2 " << &fun << endl;
    cout << "a1" << endl;
    D d;
    cout << "a2" << endl;
    d.SetC(std::move(c));
    d.SetFun(std::move(fun));
}

int main() {
    std::string sss = "hi";
    cout << &sss << endl;
    std::string ssa = std::move(sss);
    cout << &ssa << " " << &sss << endl;


    C ccc;
    auto fun = [&](int v) {
        cout <<&ccc << endl;
    };

    cout << "1 " << &fun << endl;
    C cc;
    fc(std::move(cc), std::move(fun));



//    A a;
//    std::string as = std::move(f(a));
//    std::cout << as << " - " << a.a << std::endl;
//
//    B b;
//    b.v = 15;
//    B bb = std::move(b);
//    std::cout << bb.v << " " << b.v << std::endl;
//
//    std::string sa = "a";
//    std::string sb = sa;
//    std::string sc = std::move(sa);
//    std::cout << sa << " - " << sb << " - " << sc << std::endl;
//
//    char *c;
//    c = "hello";
//    std::cout << &c << std::endl;
//    std::cout << *c << std::endl;
    return 0;
}