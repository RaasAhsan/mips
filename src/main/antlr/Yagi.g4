grammar Yagi;

valAssignment
    : VAL VARIABLE_NAME '=' expression
    ;

expression
    : INT
    ;

INT
    : [0-9]+
    ;

VAL
    : 'val'
    ;

VARIABLE_NAME
    : [A-Za-z0-9]+
    ;
