grammar Yagi;

valAssignment
    : VAL identifier '=' expression
    ;

identifier
    : IDENTIFIER
    ;

expression
    : intExpression
    ;

intExpression
    : integer
    ;

integer
    : INT
    ;

VAL
    : 'val'
    ;

IDENTIFIER
    : [A-Za-z] [A-Za-z0-9]*
    ;

INT
    : [0-9]+
    ;

TRUE
    : 'true'
    ;

FALSE
    : 'false'
    ;
