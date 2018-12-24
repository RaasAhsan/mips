
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

data class Add(val rs: Int, val rt: Int, val rd: Int) : Instruction()
data class Addi(val rs: Int, val rt: Int, val immediate: Int) : Instruction()
data class Addiu(val rs: Int, val rt: Int, val immediate: Int) : Instruction()
data class Addu(val rs: Int, val rt: Int, val rd: Int) : Instruction()

data class And(val rs: Int, val rt: Int, val rd: Int) : Instruction()
data class Andi(val rs: Int, val rt: Int, val immediate: Int) : Instruction()

data class Beq(val rs: Int, val rt: Int, val offset: Int) : Instruction()
data class Beql(val rs: Int, val rt: Int, val offset: Int) : Instruction()
data class Bgez(val rs: Int, val offset: Int) : Instruction()
data class Bgezal(val rs: Int, val offset: Int) : Instruction()
data class Bgezall(val rs: Int, val offset: Int) : Instruction()
data class Bgezl(val rs: Int, val offset: Int) : Instruction()
data class Bgtz(val rs: Int, val offset: Int) : Instruction()
data class Bgtzl(val rs: Int, val offset: Int) : Instruction()
data class Blez(val rs: Int, val offset: Int) : Instruction()
data class Blezl(val rs: Int, val offset: Int) : Instruction()
data class Bltz(val rs: Int, val offset: Int) : Instruction()
data class Bltzal(val rs: Int, val offset: Int) : Instruction()
data class Bltzall(val rs: Int, val offset: Int) : Instruction()
data class Bltzl(val rs: Int, val offset: Int) : Instruction()
data class Bne(val rs: Int, val rt: Int, val offset: Int) : Instruction()
data class Bnel(val rs: Int, val rt: Int, val offset: Int) : Instruction()
data class Break(val code: Int) : Instruction()

data class Copz(val z: Int, val copFun: Int) : Instruction()

data class Div(val rs: Int, val rt: Int) : Instruction()
data class Divu(val rs: Int, val rt: Int) : Instruction()

data class J(val instrIndex: Int) : Instruction()
data class Jal(val instrIndex: Int) : Instruction()
data class Jalr(val rs: Int, val rd: Int) : Instruction()
data class Jr(val rs: Int) : Instruction()

data class Lb(val base: Int, val rt: Int, val offset: Int) : Instruction()
data class Lbu(val base: Int, val rt: Int, val offset: Int) : Instruction()
data class Ld(val base: Int, val rt: Int, val offset: Int) : Instruction()
data class Ldcz(val z: Int, val base: Int, val rt: Int, val offset: Int) : Instruction()
data class Ldl(val base: Int, val rt: Int, val offset: Int) : Instruction()
data class Ldr(val base: Int, val rt: Int, val offset: Int) : Instruction()
data class Lh(val base: Int, val rt: Int, val offset: Int) : Instruction()
data class Lhu(val base: Int, val rt: Int, val offset: Int) : Instruction()
data class Ll(val base: Int, val rt: Int, val offset: Int) : Instruction()
data class Lld(val base: Int, val rt: Int, val offset: Int) : Instruction()
data class Lui(val rt: Int, val immediate: Int) : Instruction()
data class Lw(val base: Int, val rt: Int, val offset: Int) : Instruction()
data class Lwcz(val z: Int, val base: Int, val rt: Int, val offset: Int) : Instruction()
data class Lwl(val base: Int, val rt: Int, val offset: Int) : Instruction()
data class Lwr(val base: Int, val rt: Int, val offset: Int) : Instruction()
data class Lwu(val base: Int, val rt: Int, val offset: Int) : Instruction()

data class Mfhi(val rd: Int) : Instruction()
data class Mflo(val rd: Int) : Instruction()
data class Movn(val rs: Int, val rt: Int, val rd: Int) : Instruction()
data class Movz(val rs: Int, val rt: Int, val rd: Int) : Instruction()
data class Mthi(val rs: Int) : Instruction()
data class Mtlo(val rs: Int) : Instruction()

data class Mult(val rs: Int, val rd: Int) : Instruction()
data class Multu(val rs: Int, val rd: Int) : Instruction()
data class Nor(val rs: Int, val rt: Int, val rd: Int) : Instruction()
data class Or(val rs: Int, val rt: Int, val rd: Int) : Instruction()
data class Ori(val rs: Int, val rt: Int, val immediate: Int) : Instruction()

data class Pref(val base: Int, val hint: Int, val offset: Int) : Instruction()

data class Sb(val base: Int, val rt: Int, val offset: Int) : Instruction()
data class Sc(val base: Int, val rt: Int, val offset: Int) : Instruction()
data class Scd(val base: Int, val rt: Int, val offset: Int) : Instruction()
data class Sd(val base: Int, val rt: Int, val offset: Int) : Instruction()
data class Sdcz(val z: Int, val base: Int, val rt: Int, val offset: Int) : Instruction()
data class Sdl(val base: Int, val rt: Int, val offset: Int) : Instruction()
data class Sdr(val base: Int, val rt: Int, val offset: Int) : Instruction()
data class Sh(val base: Int, val rt: Int, val offset: Int) : Instruction()
data class Sll(val rt: Int, val rd: Int, val sa: Int) : Instruction()
data class Sllv(val rs: Int, val rt: Int, val rd: Int) : Instruction()

data class Slt(val rs: Int, val rt: Int, val rd: Int) : Instruction()
data class Slti(val rs: Int, val rt: Int, val immediate: Int) : Instruction()
data class Sltiu(val rs: Int, val rt: Int, val immediate: Int) : Instruction()
data class Sltu(val rs: Int, val rt: Int, val rd: Int) : Instruction()

data class Sra(val rt: Int, val rd: Int, val sa: Int) : Instruction()
data class Srav(val rs: Int, val rt: Int, val sa: Int) : Instruction()

data class Slr(val rt: Int, val rd: Int, val sa: Int) : Instruction()
data class Slrv(val rs: Int, val rt: Int, val rd: Int) : Instruction()
data class Sub(val rs: Int, val rt: Int, val rd: Int) : Instruction()
data class Subu(val rs: Int, val rt: Int, val rd: Int) : Instruction()

data class Sw(val base: Int, val rt: Int, val offset: Int) : Instruction()
data class Swcz(val z: Int, val base: Int, val rt: Int, val offset: Int) : Instruction()

data class Swl(val base: Int, val rt: Int, val offset: Int) : Instruction()
data class Swr(val base: Int, val rt: Int, val offset: Int) : Instruction()

data class Sync(val stype: Int) : Instruction()
data class Syscall(val code: Int) : Instruction()

data class Teq(val rs: Int, val rt: Int, val code: Int) : Instruction()
data class Teqi(val rs: Int, val immediate: Int) : Instruction()
data class Tge(val rs: Int, val rt: Int, val code: Int) : Instruction()
data class Tgei(val rs: Int, val immediate: Int) : Instruction()
data class Tgeiu(val rs: Int, val immediate: Int) : Instruction()
data class Tgeu(val rs: Int, val rt: Int, val code: Int) : Instruction()
data class Tlt(val rs: Int, val rt: Int, val code: Int) : Instruction()
data class Tlti(val rs: Int, val immediate: Int) : Instruction()
data class Tltiu(val rs: Int, val immediate: Int) : Instruction()
data class Tltu(val rs: Int, val rt: Int, val code: Int) : Instruction()
data class Tne(val rs: Int, val rt: Int, val code: Int) : Instruction()
data class Tnei(val rs: Int, val immediate: Int) : Instruction()

data class Xor(val rs: Int, val rt: Int, val rd: Int) : Instruction()
data class Xori(val rs: Int, val rt: Int, val immediate: Int) : Instruction()

// FPU (floating-point unit) instruction set
