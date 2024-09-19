#include <iostream>
#include <utility>

using namespace std;

class A {
public:
    A() = default;

    A(string av) : a(std::move(av)) {}

    string a;

    ~A() {
        cout << "finish " + a << endl;
    }
};

void f(A &a) {
    a.a = "hello";
    cout << "here 0" << endl;
    a = A("hi");
    cout << "here 1" << endl;
}

void f(std::shared_ptr<const A> &pa) {
    pa = std::make_shared<A>("hi b");
    cout << "make pa" << endl;
//    const A* pta = new A("new pta");
    A *pta = new A("new pta");
    pa.reset(pta);
}

void f(A *&a) {
    A na;
    a = &na;
    cout << "b ptr: " << a << endl;
}

int main() {
    A a("yeah");
    cout << &a << endl;
    f(a);
    cout << &a << endl;
    cout << a.a << endl;
    cout << "here 2" << endl;

    std::shared_ptr<const A> pa = std::make_shared<A>("hi a");
    cout << pa->a << endl;
    f(pa);
    cout << pa->a << endl;

    A *ptr = nullptr;
    f(ptr);
    cout << "ptr: " << ptr << endl;
    return 0;
}