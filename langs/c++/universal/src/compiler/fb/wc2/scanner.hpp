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
