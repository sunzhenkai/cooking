#include <iostream>
#include <cassert>
#include "sample_class.h"
#include "sample_include.h"
#include "vector"
#include "map"
#include "mutex"

using namespace std;

std::chrono::time_point<std::chrono::system_clock> now() {
    return std::chrono::system_clock::now();
}

long elapsed(std::chrono::time_point<std::chrono::system_clock> &start) {
    auto e = std::chrono::system_clock::now() - start;
    return std::chrono::duration_cast<std::chrono::milliseconds>(e).count();
}

int main() {
    int w1 = 10000;
    int loop = w1 * 10;
    std::mutex mtx;
    auto start = now();
    for (int i = 0; i < loop; ++i) {}
    std::cout << "elapsed: " << elapsed(start) << std::endl;

    start = now();
    for (int i = 0; i < loop; ++i) {
        std::lock_guard lock(mtx);
    }
    auto elp = elapsed(start);
    std::cout << "elapsed: " << elp / 1000 << std::endl;
    //    std::cout << std::is_nothrow_move_constructible<A>() << std::endl;
    //    std::map<std::string, int> ms;
    //    std::map<std::string, int> ms2;
    //    ms2["a"] = 2;
    //    ms["a"] = 1;
    //    ms.insert(ms2.begin(), ms2.end());
    //    std::cout << ms["a"] << std::endl;
    //    std::map<int, int> mi;
    //    for (int i = 0; i < 100000; ++i) {
    //        assert(mi[i] == 0);
    //    }
    //    ms["a"] += 2;
    //    std::cout << ms["a"] << std::endl;

    //    auto f = std::stof("1.11022302463e-16");
    //    std::cout << f << std::endl;
    //    std::string r = "abc";
    //    std::string b = std::move(r);
    //    std::cout << r << std::endl;
    //    r = "sc";
    //    int a = 10;
    //    std::string r;
    //    r.append(std::to_string(a)).append("abc");
    //    std::cout << r << std::endl;
    //    int *a = nullptr;
    //    std::cout << *(a) << std::endl;
    //    std::vector<int> vi;
    //    vi.resize(10, 12);
    //    std::cout << vi[8] << std::endl;
    //
    //    bool isLowerRnp = true;
    //    int lower_rnp = (isLowerRnp == true) ? 1 : 0;
    //    std::cout << lower_rnp << std::endl;
    //
    //    std::string m =                 "select "
    //                                    "event_type, event "
    //                                    "from "
    //                                    "user_action.m_realtime_events"
    //
    //                                    " where "
    //                                    "event_type in ('imp','cli','ins','dsp_imp','dsp_cli','dsp_ins', 'postback_install', 'activation', 'dsp_activation', 'prob_install', 'dsp_prob_install') and device_id = ? "
    //                                    "PER PARTITION LIMIT 100;";
    //    std::cout << m << std::endl;
    return 0;
}
