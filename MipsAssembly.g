grammar MipsAssembly;

prog:	(expr NEWLINE)* ;

expr:	expr ('*'|'/') expr
    |	expr ('+'|'-') expr
    |	INT
    |	'(' expr ')'
    ;

NEWLINE : [\r\n]+ ;

INT     : [0-9]+ ;

ADD
    : 'add'
    ;

ADDI
    : 'addi'
    ;

ADDIU
    : 'addiu'
    ;

ADDU
    : 'addu'
    ;

AND
    : 'and'
    ;

ANDI
    : 'andi'
    ;

B
    : 'b'
    ;

BAL
    : 'bal'
    ;

BEQ
    : 'beq'
    ;

BEQL
    : 'beql'
    ;

BGEZ
    : 'bgez'
    ;

BGEZAL
    : 'bgezal'
    ;

BGEZALL
    : 'bgezall'
    ;

BGEZL
    : 'bgezl'
    ;

BGTZ
    : 'bgtz'
    ;

BGTZL
    : 'bgtzl'
    ;

BLEZ
    : 'blez'
    ;

BLEZL
    : 'blezl'
    ;

BLTZ
    : 'bltz'
    ;

BLTZAL
    : 'bltzal'
    ;

BLTZALL
    : 'bltzall'
    ;

BLTZL
    : 'bltzl'
    ;

BNE
    : 'bne'
    ;

BNEL
    : 'bnel'
    ;

BREAK
    : 'break'
    ;

CACHE
    : 'cache'
    ;

DIV
    : 'div'
    ;

DIVU
    : 'divu'
    ;

J
    : 'j'
    ;

JAL
    : 'jal'
    ;

JALR
    : 'jalr'
    ;

JR
    : 'jr'
    ;

LB
    : 'lb'
    ;

LBU
    : 'lbu'
    ;

LH
    : 'lh'
    ;

LHU
    : 'lhu'
    ;

LUI
    : 'lui'
    ;

LW
    : 'lw'
    ;

MFHI
    : 'mfhi'
    ;

MFLO
    : 'mflo'
    ;

MTHI
    : 'mthi'
    ;

MTLO
    : 'mtlo'
    ;

MUL
    : 'mul'
    ;

MULT
    : 'mult'
    ;

MULTU
    : 'multu'
    ;

NOP
    : 'nop'
    ;

NOR
    : 'nor'
    ;

OR
    : 'or'
    ;

ORI
    : 'ori'
    ;

PREF
    : 'pref'
    ;

SB
    : 'sb'
    ;

SC
    : 'sc'
    ;

SH
    : 'sh'
    ;

SLL
    : 'sll'
    ;

SRL
    : 'srl'
    ;

SUB
    : 'sub'
    ;

SUBU
    : 'subu'
    ;

SW
    : 'sw'
    ;

SYNC
    : 'sync'
    ;

SYSCALL
    : 'syscall'
    ;

TEQ
    : 'teq'
    ;

TEQI
    : 'teqi'
    ;

TGE
    : 'tge'
    ;

TGEI
    : 'tgei'
    ;

TGEIU
    : 'tgeiu'
    ;

TGEU
    : 'tgeu'
    ;

TLT
    : 'tlt'
    ;

TLTI
    : 'tlti'
    ;

TLTIU
    : 'tltiu'
    ;

TLTU
    : 'tltu'
    ;

TNE
    : 'tne'
    ;

TNEI
    : 'tnei'
    ;

XOR
    : 'xor'
    ;

XORI
    : 'xori'
    ;


