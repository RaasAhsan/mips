grammar MipsAssembly;

prog
    : (line NEWLINE)*
    ;

line
    : instruction
    ;

label
    : labelName ':'
    ;

labelName
    : ALPHA
    ;

/* registers */

gpr
    : ZERO_REGISTER
    | AT_REGISTER
    | V_REGISTER
    | A_REGISTER
    | T_REGISTER
    | S_REGISTER
    | K_REGISTER
    | GP_REGISTER
    | SP_REGISTER
    | FP_REGISTER
    | RA_REGISTER
    ;

immediate
    : INT
    ;

instruction
    : add
    | addi
    | addiu
    | addu
    ;

add
    : OPCODE_ADD gpr COMMA gpr COMMA gpr
    ;

addi
    : OPCODE_ADDI gpr COMMA gpr COMMA immediate
    ;

addiu
    : OPCODE_ADDIU gpr COMMA gpr COMMA immediate
    ;

addu
    : OPCODE_ADDU gpr COMMA gpr COMMA gpr
    ;

/* instruction opcodes */

OPCODE_ADD
    : 'add'
    ;

OPCODE_ADDI
    : 'addi'
    ;

OPCODE_ADDIU
    : 'addiu'
    ;

OPCODE_ADDU
    : 'addu'
    ;

OPCODE_AND
    : 'and'
    ;

OPCODE_ANDI
    : 'andi'
    ;

OPCODE_B
    : 'b'
    ;

OPCODE_BAL
    : 'bal'
    ;

OPCODE_BEQ
    : 'beq'
    ;

OPCODE_BEQL
    : 'beql'
    ;

OPCODE_BGEZ
    : 'bgez'
    ;

OPCODE_BGEZAL
    : 'bgezal'
    ;

OPCODE_BGEZALL
    : 'bgezall'
    ;

OPCODE_BGEZL
    : 'bgezl'
    ;

OPCODE_BGTZ
    : 'bgtz'
    ;

OPCODE_BGTZL
    : 'bgtzl'
    ;

OPCODE_BLEZ
    : 'blez'
    ;

OPCODE_BLEZL
    : 'blezl'
    ;

OPCODE_BLTZ
    : 'bltz'
    ;

OPCODE_BLTZAL
    : 'bltzal'
    ;

OPCODE_BLTZALL
    : 'bltzall'
    ;

OPCODE_BLTZL
    : 'bltzl'
    ;

OPCODE_BNE
    : 'bne'
    ;

OPCODE_BNEL
    : 'bnel'
    ;

OPCODE_BREAK
    : 'break'
    ;

OPCODE_CACHE
    : 'cache'
    ;

OPCODE_DIV
    : 'div'
    ;

OPCODE_DIVU
    : 'divu'
    ;

OPCODE_J
    : 'j'
    ;

OPCODE_JAL
    : 'jal'
    ;

OPCODE_JALR
    : 'jalr'
    ;

OPCODE_JR
    : 'jr'
    ;

OPCODE_LB
    : 'lb'
    ;

OPCODE_LBU
    : 'lbu'
    ;

OPCODE_LH
    : 'lh'
    ;

OPCODE_LHU
    : 'lhu'
    ;

OPCODE_LUI
    : 'lui'
    ;

OPCODE_LW
    : 'lw'
    ;

OPCODE_MFHI
    : 'mfhi'
    ;

OPCODE_MFLO
    : 'mflo'
    ;

OPCODE_MTHI
    : 'mthi'
    ;

OPCODE_MTLO
    : 'mtlo'
    ;

OPCODE_MUL
    : 'mul'
    ;

OPCODE_MULT
    : 'mult'
    ;

OPCODE_MULTU
    : 'multu'
    ;

OPCODE_NOP
    : 'nop'
    ;

OPCODE_NOR
    : 'nor'
    ;

OPCODE_OR
    : 'or'
    ;

OPCODE_ORI
    : 'ori'
    ;

OPCODE_PREF
    : 'pref'
    ;

OPCODE_SB
    : 'sb'
    ;

OPCODE_SC
    : 'sc'
    ;

OPCODE_SH
    : 'sh'
    ;

OPCODE_SLL
    : 'sll'
    ;

OPCODE_SRL
    : 'srl'
    ;

OPCODE_SUB
    : 'sub'
    ;

OPCODE_SUBU
    : 'subu'
    ;

OPCODE_SW
    : 'sw'
    ;

OPCODE_SYNC
    : 'sync'
    ;

OPCODE_SYSCALL
    : 'syscall'
    ;

OPCODE_TEQ
    : 'teq'
    ;

OPCODE_TEQI
    : 'teqi'
    ;

OPCODE_TGE
    : 'tge'
    ;

OPCODE_TGEI
    : 'tgei'
    ;

OPCODE_TGEIU
    : 'tgeiu'
    ;

OPCODE_TGEU
    : 'tgeu'
    ;

OPCODE_TLT
    : 'tlt'
    ;

OPCODE_TLTI
    : 'tlti'
    ;

OPCODE_TLTIU
    : 'tltiu'
    ;

OPCODE_TLTU
    : 'tltu'
    ;

OPCODE_TNE
    : 'tne'
    ;

OPCODE_TNEI
    : 'tnei'
    ;

OPCODE_XOR
    : 'xor'
    ;

OPCODE_XORI
    : 'xori'
    ;

/* register */

ZERO_REGISTER
    : '$0'
    ;

AT_REGISTER
    : '$at'
    ;

V_REGISTER
    : '$v0' | '$v1'
    ;

A_REGISTER
    : '$a0' | '$a1' | '$a2' | '$a3'
    ;

T_REGISTER
    : '$t0' | '$t1' | '$t2' | '$t3' | '$t4' | '$t5' | '$t6' | '$t7' | '$t8' | '$t9'
    ;

S_REGISTER
    : '$s0' | '$s1' | '$s2' | '$s3' | '$s4' | '$s5' | '$s6' | '$s7'
    ;

K_REGISTER
    : '$k0' | '$k1'
    ;

GP_REGISTER
    : '$gp'
    ;

SP_REGISTER
    : '$sp'
    ;

FP_REGISTER
    : '$fp'
    ;

RA_REGISTER
    : '$ra'
    ;



NEWLINE
    : [\r\n]+
    ;

INT
    : [0-9]+
    ;

COMMA
    : ','
    ;

ALPHA
    : [A-Za-z]+
    ;

WS
    : ' ' -> skip
    ;
