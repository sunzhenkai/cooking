#include "iostream"
#include "map"
#include "vector"

#define Cc() {{1, 2}, \
{2, 3},                  \
}

#define MULTI(args...) {args}


std::string f() {
    return "hello";
}


int main() {
    std::string ss = std::move(f());
    std::vector<std::string> vvs;
    vvs.emplace_back(std::move(f()));

    std::map<int, int> m = Cc();
    std::cout << m.size() << std::endl;
    std::vector<int> vs = {1, 2, 3};

    std::vector<int> vi = MULTI(1, 2, 3);
    std::cout << vi.size() << std::endl;

    std::map<int, int> mc = MULTI({ 1, 2 }, { 2, 3 });
    std::cout << mc.size() << std::endl;
    return 0;
}