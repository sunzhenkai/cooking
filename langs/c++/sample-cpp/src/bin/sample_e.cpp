#include <iostream>

class A {
public:
  int v;
};

class B {
public:
  A a;
  A &b;
};

int main() {
  A a{1};
  A &b = a;
  std::string s = "abc";
  s = "bcd";
  std::cout << s << std::endl;
  return 0;
}