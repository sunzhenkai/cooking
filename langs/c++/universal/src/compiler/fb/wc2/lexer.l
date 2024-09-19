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