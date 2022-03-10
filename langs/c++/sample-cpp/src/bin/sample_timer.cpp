#include <iostream>
#include <sys/timeb.h>
#include "sys/time.h"

static inline time_t getSecond() {
  struct timeb t{};
  ftime(&t);
  return t.time;
}

int64_t nowUs() {
  timeval now = {0, 0};
  gettimeofday(&now, nullptr);
  return now.tv_sec * 1000000 + now.tv_usec;
}

static inline int64_t nowMs() {
  timeval now = {0, 0};
  gettimeofday(&now, nullptr);
  return now.tv_sec * 1000 + now.tv_usec / 1000;
}

struct Record {
  int64_t time = nowUs();
};

class Timer {
  using time_point_t = typename std::chrono::steady_clock::time_point;

};

int main() {
  auto start = std::chrono::steady_clock::now();
  std::cout << std::chrono::duration_cast<std::chrono::milliseconds>(std::chrono::steady_clock::now() - start).count()
            << std::endl;

  std::cout << getSecond() << std::endl;
  std::cout << nowUs() << std::endl;
  std::cout << nowMs() << std::endl;
  int64_t n = nowUs();
  std::cout << n << std::endl;

  Record r;
  std::cout << r.time << std::endl;

  int32_t m = 102332;
  std::cout << int(m * 0.5) << std::endl;
  return 0;
}