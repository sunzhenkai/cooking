//
// Created by Wii on 2021/2/9.
//

#include <fstream>
#include <cassert>

#include "driver.hpp"

wii::WCDriver::~WCDriver() {
    delete(scanner);
    scanner = nullptr;
    delete(parser);
    parser = nullptr;
}

void wii::WCDriver::parse(std::istream &iss) {
    if (!iss.good() || iss.eof()) {
        return;
    }

    delete(scanner);
    scanner = new wii::WCScanner(&iss);
    delete(parser);
    parser = new wii::WCParser((*scanner) /* scanner */, (*this) /* driver */);
    const int accept(0);
    if (parser->parse() != accept) {
        std::cerr << "pars failed!" << std::endl;
    }
}

void wii::WCDriver::print() {
    std::cout << "chars: " << chars << std::endl
        << "words: " << words << std::endl
        << "lines: " << lines << std::endl;
}

void wii::WCDriver::parse(const char *const fn) {
    assert(fn != nullptr);
    std::ifstream inf(fn);
    assert(inf.good());
    parse(inf);
}

void wii::WCDriver::add_char() {
    ++chars;
}

void wii::WCDriver::add_line() {
    ++chars;
    ++lines;
}

void wii::WCDriver::add_word(const std::string &word) {
    chars += word.size();
    ++words;
}