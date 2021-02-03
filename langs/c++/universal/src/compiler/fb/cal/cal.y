%{
#include <stdio.h>
%}

/* 声明 Tokens */
%token NUMBER
%token ADD SUB MUL DIV ABS
%token EOL

%%

calclist: /* 空规则 */
	| calclist exp EOL { printf("= %d\n", $2); }
	;

exp: factor
	| exp ADD factor { $$ = $1 + $3; }
	| exp SUB factor { $$ = $1 - $3; }
	;

factor: term
    | factor MUL term { $$ = $1 * $3; }
    | factor DIV term { $$ = $1 / $3; }
    ;

term: NUMBER
    | ABS term { $$ = $2 >= 0 ? $2 : -$2; }
    ;

%%

void main(int argc, char **argv)
{
    yyparse();
}

void yyerror(char *s)
{
    fprintf(stderr, "error: %s\n", s);
}