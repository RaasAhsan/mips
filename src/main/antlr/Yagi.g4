grammar Yagi;

valAssignment
    : VAL identifier '=' expression
    ;

expression
    : intExpression | booleanExpression
    ;

intExpression
    : integer
    ;

integer
    : INT
    ;

booleanExpression
    : boolean
    ;

boolean
    : (true | false)
    ;

true
    : TRUE
    ;

false
    : FALSE
    ;

identifier
    : IDENTIFIER
    ;

INT
    : [0-9]+
    ;

VAL
    : 'val'
    ;

IDENTIFIER
    : [A-Za-z] [A-Za-z0-9]*
    ;

TRUE
    : 'true'
    ;

FALSE
    : 'false'
    ;
