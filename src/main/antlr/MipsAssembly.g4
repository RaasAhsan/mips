grammar MipsAssembly;

prog
    : (line NEWLINE)*
    ;

line
    : label?
    ;

label
    : labelName ':'
    ;

labelName
    : ALPHA
    ;

/* registers */

gpr
    : zero_register
    | at_register
    | v_register
    | a_register
    | t_register
    | s_register
    | k_register
    | gp_register
    | sp_register
    | fp_register
    | ra_register
    ;

zero_register
    : '$0'
    ;

at_register
    : '$at'
    ;

v_register
    : '$v0' | '$v1'
    ;

a_register
    : '$a0' | '$a1' | '$a2' | '$a3'
    ;

t_register
    : '$t0' | '$t1' | '$t2' | '$t3' | '$t4' | '$t5' | '$t6' | '$t7' | '$t8' | '$t9'
    ;

s_register
    : '$s0' | '$s1' | '$s2' | '$s3' | '$s4' | '$s5' | '$s6' | '$s7'
    ;

k_register
    : '$k0' | '$k1'
    ;

gp_register
    : '$gp'
    ;

sp_register
    : '$sp'
    ;

fp_register
    : '$fp'
    ;

ra_register
    : '$ra'
    ;

NEWLINE : [\r\n]+ ;

INT     : [0-9]+ ;

ALPHA
    : [A-Za-z]+
    ;

/* Instruction opcodes */

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
