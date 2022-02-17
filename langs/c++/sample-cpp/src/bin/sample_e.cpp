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

void f(std::string &c) {
  std::string lc = "hello";
  std::cout << lc << " " << c << std::endl;
  c.swap(lc);
  std::cout << lc << " " << c << std::endl;
}

int main() {
  A a{1};
  A &b = a;
  std::string s = "abc";
  s = "bcd";
  std::cout << s << std::endl;

  std::string tc = "hi";
  f(tc);
  std::cout << tc << std::endl;
  return 0;
}