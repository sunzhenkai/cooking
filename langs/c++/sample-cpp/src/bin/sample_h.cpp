#include <variant>
#include "iostream"

int main() {
    std::variant<int, std::string> v;
    v = "hello";
    auto r = std::visit([](auto &t) { return "hello"; }, v);
    std::cout << r << std::endl;
    return 0;
}