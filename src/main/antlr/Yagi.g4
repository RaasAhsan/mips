grammar Yagi;

valAssignment
    : VAL identifier '=' expression
    ;

identifier
    : IDENTIFIER
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

/* no universal equality */
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
