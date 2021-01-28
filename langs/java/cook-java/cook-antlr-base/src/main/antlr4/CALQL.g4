grammar CALQL;

@parser::header {
    package pub.wii.cook.antlr.antlr;
}

@parser::members {
    public static final String VERSION = "1.0";
}

prog: stat+;
stat: expr                  // expr
    | expr ID '=' expr      // assign
    | 'print(' ID ')'       // print
    ;

expr: <assoc=right> expr '^' expr # POWER
    | expr op=(MUL|DIV) expr    # MulDiv
    | expr op=(ADD|SUB) expr    # AddSub
    | sign=(ADD|SUB)?NUMBER     # Number
    | ID                        # Id
    | '(' expr ')'              # Parents
    ;

ID: [a-zA-Z]+;
NUMBER: [0-9]+('.'([0-9]+)?)?
    | [0-9]+;
COMMENT : '/*' .*? '*/' -> skip;
LINE_COMMENT : '//' .*? '\r'? '\n' -> skip;
WS   : [ \t\r\n]+ -> skip;
MUL  : '*';
DIV  : '/';
ADD  : '+';
SUB  : '-';