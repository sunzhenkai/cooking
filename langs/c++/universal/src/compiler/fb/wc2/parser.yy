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