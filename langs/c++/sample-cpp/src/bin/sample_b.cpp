#include <utility>

#include "iostream"

using namespace std;

class A {
public:
    explicit A(string s) : s_(std::move(s)) {};
    string s_;
};

int main() {
    const A *a;
    a = new A("a"); // ok
//    a->s_ = "c"; // error
    cout << a->s_ << endl;
    delete a;
    a = new A("b"); // ok
    cout << a->s_ << endl;
    delete a;
    return 0;
}