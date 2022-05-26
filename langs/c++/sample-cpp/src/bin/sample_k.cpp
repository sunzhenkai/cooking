#include "iostream"
#include "memory"
#include "vector"
#include "algorithm"
#include "chrono"
#include "map"
#include "unordered_map"
#include <sys/stat.h>

struct Complex {
    int64_t a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z,
            a1, b1, c1, d1, e1, f1, g1, h1, i1, j1, k1, l1, m1, n1, o1, p1, q1, r1, s1, t1, u1, v1, w1, x1, y1, z1,
            a2, b2, c2, d2, e2, f2, g2, h2, i2, j2, k2, l2, m2, n2, o2, p2, q2, r2, s2, t2, u2, v2, w2, x2, y2, z2,
            a3, b3, c3, d3, e3, f3, g3, h3, i3, j3, k3, l3, m3, n3, o3, p3, q3, r3, s3, t3, u3, v3, w3, x3, y3, z3;
};

std::chrono::time_point<std::chrono::system_clock> now() {
    return std::chrono::system_clock::now();
}

long elapsed(std::chrono::time_point<std::chrono::system_clock> &start) {
    auto e = std::chrono::system_clock::now() - start;
    return std::chrono::duration_cast<std::chrono::milliseconds>(e).count();
}

template<typename K, typename V>
using Map = std::unordered_map<K, V>;

class B {
public:
    B() = default;
    B(B &&b) noexcept {
        v = b.v;
        std::cout << "b move construct" << std::endl;
    }
    int v;
};

//class A {
//public:
//    std::vector<int> is;
//    std::vector<B> bs;
//
//    B b{};
//};

class A {
public:
    A() {
        std::cout << "construct A" << std::endl;
    }

    A(const A &a) {
        std::cout << "copy construct A" << std::endl;
    }

    A(A &&a) {
        std::cout << "move construct A" << std::endl;
    }

    ~A() {
        std::cout << "destruct A" << std::endl;
    }
};

class C {
public:
    int64_t r;

    C() {}

    C(C &&c) : r(std::move(c.r)) {}
};

long GetFileSize(const std::string &filename) {
    struct stat stat_buf{};
    int rc = stat(filename.c_str(), &stat_buf);
    return rc == 0 ? stat_buf.st_size : -1;
}

int main() {
    std::cout << GetFileSize("/Users/wii/flamegraph.svg") << std::endl;

    C c;
    c.r = 10010;
    C c2 = std::move(c);
    std::cout << c.r << ", " << c2.r << std::endl;

    uint64_t t = 2827187938;
    int64_t t2 = t;
    std::cout << t << ", " << t2 << std::endl;

//    B b{}, bb{};
//    std::vector<B> vbs;
//    vbs.reserve(10);
//    vbs.emplace_back(std::move(b));
//    vbs.emplace_back(std::move(bb));
//    std::cout << "ok" << std::endl;
//    std::vector<B> vbc = std::move(vbs);
//    std::cout << "done" << std::endl;

//    std::vector<int> va;
//    va.emplace_back(1);
//    va.emplace_back(2);
//
//    std::vector<int> vb;
//    vb = std::move(va);
//
//    std::cout << va.size() << ", " << vb.size() << std::endl;
//
//    A a;
//    a.is.emplace_back(1);
//    a.b.v = 10;
//    A b = std::move(a);
//    std::cout << a.is.size() << ", " << b.is.size() << std::endl;
//    std::cout << a.b.v << ", " << b.b.v << std::endl;
//
//    std::map<int, int> mi;
//    int v = 1;
//    mi[v] = std::move(v);
//
//    int loop = 1000 * 10000;
//    std::vector<Complex> complexes;
////complexes.reserve(loop);
//    auto start = now();
//    complexes.resize(loop);
////for (int i = 0; i < loop; ++i) {
////    Complex complex{};
////}
//    std::cout << "elapsed: " << elapsed(start) << std::endl;

//    std::map<int, A> ma;
//    {
//        ma[1];
//    }

//    typedef int64_t BaseType;
////    typedef std::shared_ptr<BaseType> MapV;
//    typedef BaseType MapV;
//    int loop = 500 * 10000;
//    MapV complex{};// = std::make_shared<BaseType>();
//    Map<int, MapV> m;
//    auto start = now();
//    for (int i = 0; i < loop; ++i) {
//        m[i] = complex;
//    }
//    std::cout << "elapsed: " << elapsed(start) << std::endl;

//    Map<int, int> mi;
//    std::cout << mi[1] << ", " << mi.size() << std::endl;

//    Complex complex{};
//    std::vector<Complex> complexes;
//    int loop = 500 * 10000;
//    complexes.reserve(loop);
//    for (int i = 0; i < loop; ++i) {
//        complexes.emplace_back(complex);
//    }
//
//    Map<int, Complex> m;
//    Map<int, std::shared_ptr<Complex>> mp;
//    m.reserve(loop);
//    auto start = now();
//    for (int i = 0; i < loop; ++i) {
////        std::shared_ptr<Complex> cp = std::make_shared<Complex>();
////        mp.emplace(i, cp);
//        m[i];
//    }
//    std::cout << "elapsed: " << elapsed(start) << std::endl;
    return 0;
}