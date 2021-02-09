# Description

文档计数。

# Files

## 定义顺序

### `parser.yy`

定义 bison 语法解析器源文件。

```c++
%skeleton "lalr1.cc"
%require  "3.0"
%defines
%define api.namespace {wii}
%define api.parser.class {WCParser}

%code requires {
    namespace wii {
        class WCDriver;     // 声明类
        class WCScanner;    // 声明类
    }
}

%parse-param { WCScanner &scanner }
%parse-param { WCDriver &driver }

%code {
    #include <iostream>
    #include <cstdlib>
    #include <fstream>

    #include "driver.h"

    #undef yylex
    #define yylex scanner.yylex
}

%define api.value.type variant
%define parse.assert

%token               END    0
%token               UPPER
%token               LOWER
%token <std::string> WORD
%token               NEWLINE
%token               CHAR

%locations

%%

list_option : END | list END;

list
  : item
  | list item
  ;

item
  : UPPER   { driver.add_upper(); }
  | LOWER   { driver.add_lower(); }
  | WORD    { driver.add_word( $1 ); }
  | NEWLINE { driver.add_newline(); }
  | CHAR    { driver.add_char(); }
  ;

%%

void
wii::WCParser::error( const location_type &l, const std::string &err_message )
{
   std::cerr << "Error: " << err_message << " at " << l << "\n";
}
```

### `scanner.hpp`

定义 flexer 类。

```c++
#ifndef UNIVERSAL_SCANNER_H
#define UNIVERSAL_SCANNER_H

#include <FlexLexer.h>

#include "parser.tab.hh"
#include "location.hh"

namespace wii {
    class Scanner : public yyFlexLexer {
    public:
        Scanner(std::istream *in) : yyFlexLexer(in) { // 为 lexer 指定输入流
        }

        virtual ~Scanner() {}

        //get rid of override virtual function warning
        using FlexLexer::yylex;

        virtual
        int yylex( wii::WCParser::semantic_type * const lval,
                   wii::WCParser::location_type *location );
        // YY_DECL defined in mc_lexer.l
        // Method body created by flex in mc_lexer.yy.cc

    private:
        wii::WCParser::semantic_type *yylval = nullpter;
    };
}

#endif //UNIVERSAL_SCANNER_H
```

### `driver.hpp` & `driver.cpp`

定义 驱动类，包含语法解析中 action 需要执行的方法。

**driver.hpp**

```c++
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

    private:
        std::size_t chars = 0;
        std::size_t words = 0;
        std::size_t lines = 0;
        wii::WCParser *parser = nullptr;
        wii::WCScanner *scanner = nullptr;
    };
}


#endif //UNIVERSAL_DRIVER_H
```

**driver.cpp**

```c++
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
```

### `lex.l`



### `main.cpp`

- 解析器主程序，用于读取输入，并调用 driver 的解析方法

## 生成

```shell
$ bison -d -v parser.y
$ flex lexer.l
```

