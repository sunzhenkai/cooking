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

    #include "driver.hpp"

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

list_option : END
    | list END;

list : item
    | list item
    ;

item: WORD      { driver.add_word($1); }
    | NEWLINE   { driver.add_line(); }
    | CHAR      { driver.add_char(); }
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

#if !defined(yyFlexLexerOnce)

#include <FlexLexer.h>

#endif

#include "parser.tab.hh"
#include "location.hh"

namespace wii {
    class WCScanner : public yyFlexLexer {
    public:
        WCScanner(std::istream *in) : yyFlexLexer(in) { // 为 lexer 指定输入流
        }

        virtual ~WCScanner() {}

        //get rid of override virtual function warning
        using FlexLexer::yylex;

        virtual
        int yylex(wii::WCParser::semantic_type *const lval,
                  wii::WCParser::location_type *location);
        // YY_DECL defined in mc_lexer.l
        // Method body created by flex in mc_lexer.yy.cc

    private:
        wii::WCParser::semantic_type *yylval = nullptr;
    };
}

#endif //UNIVERSAL_SCANNER_H
```

### `driver.hpp` & `driver.cpp`

定义 驱动类，包含如下信息。

- 语法解析中 action 需要执行的方法，供 parser 调用
- 自定义输入流，供程序调用

**driver.hpp**

```c++
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
```

### `lex.l`

```c++
%{

#include <string>

/* Implementation of yyFlexScanner */
#include "scanner.hpp"
#undef YY_DECL
#define YY_DECL int wii::WCScanner::yylex(wii::WCParser::semantic_type * const lval, wii::WCParser::location_type *loc)

/* typedef to make the returns for the tokens shorter */
using token = wii::WCParser::token;

/* define yyterminate as this instead of NULL */
#define yyterminate() return( token::END )

/* msvc2010 requires that we exclude this header file. */
#define YY_NO_UNISTD_H

/* update location on matching */
#define YY_USER_ACTION loc->step(); loc->columns(yyleng);

%}

%option debug
%option nodefault
%option yyclass="wii::WCScanner"
%option noyywrap
%option c++

%%

%{          /** Code executed at the beginning of yylex **/
            yylval = lval;
%}

[a-zA-Z]+   {
    yylval->build<std::string>(yytext);
    return token::WORD;
}

\n          {
    // Update line number
    loc->lines();
    return token::NEWLINE;
}

. {
    return token::CHAR;
}

%%
```

### `main.cpp`

解析器主程序，用于读取输入，并调用 driver 的解析方法。

```c++
#include <iostream>

#include "driver.hpp"

int main(int argc, char **argv) {
    assert(argc == 2);
    wii::WCDriver driver;
    driver.parse(argv[1]);
    driver.print();
    return 0;
}
```

## makefile

```makefile
DEPS_DIR:=../../../../deps # bison & flex 安装路径
LIB_DIR:=$(DEPS_DIR)/lib  # 包含 bison & flex 及其它依赖的库文件
BIN_DIR:=$(DEPS_DIR)/bin  # 包含 bison & flex 二进制文件
INCLUDE_DIR:=$(DEPS_DIR)/include  # 包含 bison & flex 头文件
PATH:=$(DEPS_DIR)/bin:$(PATH)
LD_LIBRARY_PATH=$(LIB_DIR)
LIBRARY_PATH=$(LIB_DIR)
DYLD_LIBRARY_PATH=$(LIB_DIR)
CXXFLAGS=-std=c++11 -L $(LIB_DIR) -I $(INCLUDE_DIR) -Wno-unused-command-line-argument

FBF=parser lexer   	# bison & flex source files
PJF=driver main		# for main programs
FILES=$(addsuffix .cpp, $(PJF))		# source files
OPF=$(addsuffix .o, $(PJF))		# main programs output
FBO=$(addsuffix .o, $(FBF))		# parser & lexer output file
EXE=wc

.PHONY: wc

all: wc

wc: $(FILES)
	@export PATH=$(PATH) && \
		export DYLD_LIBRARY_PATH=$(DYLD_LIBRARY_PATH) && \
		export LIBRARY_PATH=$(LIBRARY_PATH) && \
		$(MAKE) $(FBF) && \
		$(MAKE) $(OPF)  && \
		$(CXX) $(CXXFLAGS) -o $(EXE) $(OPF) $(FBO) && \
		echo "[PROCESS] compile wc done"


parser: parser.yy
	@export PATH=$(PATH) && \
		export DYLD_LIBRARY_PATH=$(DYLD_LIBRARY_PATH) && \
		export LIBRARY_PATH=$(LIBRARY_PATH) && \
		bison -v -d $< && \
		$(CXX) $(CXXFLAGS) -c -o parser.o parser.tab.cc && \
		echo "[PROCESS] compile parser done"

lexer: lexer.l
	@export PATH=$(PATH) && \
		export DYLD_LIBRARY_PATH=$(DYLD_LIBRARY_PATH) && \
		export LIBRARY_PATH=$(LIBRARY_PATH) && \
		flex -V && \
		flex --outfile=lexer.yy.cc $< && \
		$(CXX) $(CXXFLAGS) -c -o lexer.o lexer.yy.cc && \
      	echo "[PROCESS] compile lexer done"

clean:
	@rm parser.tab.* parser.output location.hh position.hh stack.hh lexer.yy.cc \
		$(FBO) $(OPF) $(EXE)

test:
	@wc wc.in
```

## 生成

```shell
$ make
[PROCESS] compile parser done
flex 2.6.4
[PROCESS] compile lexer done
c++ -std=c++11 -L ../../../../deps/lib -I ../../../../deps/include -Wno-unused-command-line-argument   -c -o driver.o driver.cpp
c++ -std=c++11 -L ../../../../deps/lib -I ../../../../deps/include -Wno-unused-command-line-argument   -c -o main.o main.cpp
[PROCESS] compile wc done
```

## 测试

```shell
$ make test
chars: 56
words: 9
lines: 2
```

## 清除

清除 flex & bison 生成文件，及编译生成的中间文件、可执行文件。

```shell
$ make clean
```

# 参考 

- https://github.com/jonathan-beard/simple_wc_example