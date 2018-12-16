
/*
 * MIPS CPU instructions are divided into the following functional groups:
 * - Load and store
 * - ALU
 * - Jump and Branch
 * - Miscellaneous
 * - Coprocessor
 *
 * GPR - general purpose registers
 *
 * A CPU instruction is a 32-bit aligned word.
 *
 * Immediate (I) instructions
 * Bits 31 - 26 - opcode
 * Bits 25 - 21 - rs
 * Bits 20 - 16 - rt
 * Bits 15 - 0  - immediate
 *
 * Jump (J) instructions
 * Bits 31 - 26 - opcode
 * Bits 25 - 0  - instruction
 *
 * Register (R) instructions
 * Bits 31 - 26 - opcode
 * Bits 25 - 21 - rs
 * Bits 20 - 16 - rt
 * Bits 15 - 11 - rd
 * Bits 10 - 6  - sa
 * Bits 5  - 0  - function
 *
 */

sealed class Instruction

// CPU Instruction Set

data class Add(rs: Int, rt: Int, rd: Int) : Instruction()
data class Addi(rs: Int, rt: Int, immediate: Int) : Instruction()
data class Addiu(rs: Int, rt: Int, immediate: Int) : Instruction()
data class Addu(rs: Int, rt: Int, rd: Int) : Instruction()

data class And(rs: Int, rt: Int, rd: Int) : Instruction()
data class Andi(rs: Int, rt: Int, immediate: Int) : Instruction()

data class Beq(rs: Int, rt: Int, offset: Int) : Instruction()
data class Beql(rs: Int, rt: Int, offset: Int) : Instruction()
data class Bgez(rs: Int, offset: Int) : Instruction()
data class Bgezal(rs: Int, offset: Int) : Instruction()
data class Bgezall(rs: Int, offset: Int) : Instruction()
data class Bgezl(rs: Int, offset: Int) : Instruction()
data class Bgtz(rs: Int, offset: Int) : Instruction()
data class Bgtzl(rs: Int, offset: Int) : Instruction()
data class Blez(rs: Int, offset: Int) : Instruction()
data class Blezl(rs: Int, offset: Int) : Instruction()
data class Bltz(rs: Int, offset: Int) : Instruction()
data class Bltzal(rs: Int, offset: Int) : Instruction()
data class Bltzall(rs: Int, offset: Int) : Instruction()
data class Bltzal(rs: Int, offset: Int) : Instruction()
data class Bne(rs: Int, rt: Int, offset: Int) : Instruction()
data class Bnel(rs: Int, rt: Int, offset: Int) : Instruction()
data class Break(code: Int) : Instruction()

data class Copz(z: Int, copFun: Int) : Instruction()

data class Dadd(rs: Int, rt: Int, rd: Int) : Instruction()
data class Daddi(rs: Int, rt: Int, immediate: Int) : Instruction()
data class Daddiu(rs: Int, rt: Int, immediate: Int) : Instruction()
data class Daddu(rs: Int, rt: Int, rd: Int) : Instruction()
data class Ddiv(rs: Int, rt: Int) : Instruction()
data class Ddivu(rs: Int, rt: Int) : Instruction()
data class Div(rs: Int, rt: Int) : Instruction()
data class Divu(rs: Int, rt: Int) : Instruction()
data class Dmult(rs: Int, rt: Int) : Instruction()
data class Dmultu(rs: Int, rt: Int) : Instruction()
data class Dsll(rt: Int, rd: Int, sa: Int) : Instruction()
data class Dsll32(rt: Int, rd: Int, sa: Int) : Instruction()
data class Dsllv(rs: Int, rt: Int, rd: Int) : Instruction()
data class Dsra(rt: Int, rd: Int, sa: Int) : Instruction()
data class Dsra32(rt: Int, rd: Int, sa: Int) : Instruction()
data class Dsrav(rs: Int, rt: Int, rd: Int) : Instruction()
data class Dsrl(rt: Int, rd: Int, sa: Int) : Instruction()
data class Dsrl32(rt: Int, rd: Int, sa: Int) : Instruction()
data class Dsrlv(rs: Int, rt: Int, rd: Int) : Instruction()
data class Dsub(rs: Int, rt: Int, rd: Int) : Instruction()
data class Dsubu(rs: Int, rt: Int, rd: Int) : Instruction()

data class J(instrIndex: Int) : Instruction()
data class Jal(instrIndex: Int) : Instruction()
data class Jalr(rs: Int, rd: Int) : Instruction()
data class Jr(rs: Int) : Instruction()

data class Lb(base: Int, rt: Int, offset: Int) : Instruction()
data class Lbu(base: Int, rt: Int, offset: Int) : Instruction()
data class Ld(base: Int, rt: Int, offset: Int) : Instruction()
data class Ldcz(z: Int, base: int, rt: Int, offset: Int) : Instruction()
data class Ldl(base: Int, rt: Int, offset: Int) : Instruction()
data class Ldr(base: Int, rt: Int, offset: Int) : Instruction()
data class Lh(base: Int, rt: Int, offset: Int) : Instruction()
data class Lhu(base: Int, rt: Int, offset: Int) : Instruction()
data class Ll(base: Int, rt: Int, offset: Int) : Instruction()
data class Lld(base: Int, rt: Int, offset: Int) : Instruction()
data class Lui(rt: Int, immediate: Int) : Instruction()
data class Lw(base: Int, rt: Int, offset: Int) : Instruction()
data class Lwcz(z: Int, base: Int, rt: Int, offset: Int) : Instruction()
data class Lwl(base: Int, rt: Int, offset: Int) : Instruction()
data class Lwr(base: Int, rt: Int, offset: Int) : Instruction()
data class Lwu(base: Int, rt: Int, offset: Int) : Instruction()

data class Mfhi(rd: Int) : Instruction()
data class Mflo(rd: Int) : Instruction()
data class Movn(rs: Int, rt: Int, rd: Int) : Instruction()
data class Movz(rs: Int, rt: Int, rd: Int) : Instruction()
data class Mthi(rs: Int) : Instruction()
data class Mtlo(rs: Int) : Instruction()

data class Mult(rs: Int, rd: Int) : Instruction()
data class Multu(rs: Int, rd: Int) : Instruction()
data class Nor(rs: Int, rt: Int, rd: Int) : Instruction()
data class Or(rs: Int, rt: Int, rd: Int) : Instruction()
data class Ori(rs: Int, rt: Int, immediate: Int) : Instruction()

data class Pref(base: Int, hint: Int, offset: Int) : Instruction()

data class Sb(base: Int, rt: Int, offset: Int) : Instruction()
data class Sc(base: Int, rt: Int, offset: Int) : Instruction()
data class Scd(base: Int, rt: Int, offset: Int) : Instruction()
data class Sd(base: Int, rt: Int, offset: Int) : Instruction()
data class Sdcz(z: Int, base: Int, rt: Int, offset: Int) : Instruction()
data class Sdl(base: Int, rt: Int, offset: Int) : Instruction()
data class Sdr(base: Int, rt: Int, offset: Int) : Instruction()
data class Sh(base: Int, rt: Int, offset: Int) : Instruction()
data class Sll(rt: Int, rd: Int, sa: Int) : Instruction()
data class Sllv(rs: Int, rt: Int, rd: Int) : Instruction()

data class Slt(rs: Int, rt: Int, rd: Int) : Instruction()
data class Slti(rs: Int, rt: Int, immediate: Int) : Instruction()
data class Sltiu(rs: Int, rt: Int, immediate: Int) : Instruction()
data class Sltu(rs: Int, rt: Int, rd: Int) : Instruction()

data class Sra(rt: Int, rd: Int, sa: Int) : Instruction()
data class Srav(rs: Int, rt: Int, sa: Int) : Instruction()

data class Slr(rt: Int, rd: Int, sa: Int) : Instruction()
data class Slrv(rs: Int, rt: Int, rd: Int) : Instruction()
data class Sub(rs: Int, rt: Int, rd: Int) : Instruction()
data class Subu(rs: Int, rt: Int, rd: Int) : Instruction()

data class Sw(base: Int, rt: Int, offset: Int) : Instruction()
data class Swcz(z: Int, base: Int, rt: Int, offset: Int) : Instruction()

data class Swl(base: Int, rt: Int, offset: Int) : Instruction()
data class Swr(base: Int, rt: Int, offset: Int) : Instruction()

data class Sync(stype: Int) : Instruction()
data class Syscall(code: Int) : Instruction()

data class Teq(rs: Int, rt: Int, code: Int) : Instruction()
data class Teqi(rs: Int, immediate: Int) : Instruction()
data class Tge(rs: Int, rt: Int, code: Int) : Instruction()
data class Tgei(rs: Int, immediate: Int) : Instruction()
data class Tgeiu(rs: Int, immediate: Int) : Instruction()
data class Tgeu(rs: Int, rt: Int, code: Int) : Instruction()
data class Tlt(rs: Int, rt: Int, code: Int) : Instruction()
data class Tlti(rs: Int, immediate: Int) : Instruction()
data class Tltiu(rs: Int, immediate: Int) : Instruction()
data class Tltu(rs: Int, rt: Int, code: Int) : Instruction()
data class Tne(rs: Int, rt: Int, code: Int) : Instruction()
data class Tnei(rs: Int, immediate: Int) : Instruction()

data class Xor(rs: Int, rt: Int, rd: Int) : Instruction()
data class Xori(rs: Int, rt: Int, immediate: Int) : Instruction()

// FPU (floating-point unit) instruction set
