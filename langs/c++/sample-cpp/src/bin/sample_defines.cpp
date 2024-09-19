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

class A {
public:
    A() {
        std::cout << "construct A" << std::endl;
    }

    ~A() {
        std::cout << "destroy A" << std::endl;
    }
};

int main() {
    std::shared_ptr<A> ap = std::make_shared<A>();
//    ap.reset();
    std::shared_ptr<A> bp = std::move(ap);

    std::string s1 = "request_total";
    std::string s3 = "tal";
    std::string s2 = "_total";
    std::cout << std::equal(s2.rbegin(), s2.rend(), s1.rbegin()) << std::endl;
    std::cout << std::equal(s2.rbegin(), s2.rend(), s3.rbegin()) << std::endl;

//    std::vector<A> av1;
//    av1.reserve(3);
//    std::cout << " --- " << std::endl;
//    av1.resize(3);
//    std::cout << " === " << std::endl;
//
//    std::string ss = std::move(f());
//    std::vector<std::string> vvs;
//    vvs.emplace_back(std::move(f()));
//
//    std::map<int, int> m = Cc();
//    std::cout << m.size() << std::endl;
//    std::vector<int> vs = {1, 2, 3};
//
//    std::vector<int> vi = MULTI(1, 2, 3);
//    std::cout << vi.size() << std::endl;
//
//    std::map<int, int> mc = MULTI({ 1, 2 }, { 2, 3 });
//    std::cout << mc.size() << std::endl;
    return 0;
}