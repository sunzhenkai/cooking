//
// Created by Wii on 2021/2/9.
//

#ifndef UNIVERSAL_DRIVER_H
#define UNIVERSAL_DRIVER_H

#include <string>
#include <cstddef>
#include <istream>

#include "scanner.hpp"
#include "parser.tab.hh"

namespace wii {
    class WCDriver {
    public:
        WCDriver() = default;
        virtual ~WCDriver();

        void parse(const char * const fn);
        void parse(std::istream &iss);
        void add_line();
        void add_char();
        void add_word(const std::string &word);
        void print();

    private:
        std::size_t chars = 0;
        std::size_t words = 0;
        std::size_t lines = 0;
        wii::WCParser *parser = nullptr;
        wii::WCScanner *scanner = nullptr;
    };
}


#endif //UNIVERSAL_DRIVER_H
