
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

sealed class MipsInstruction

// CPU MipsInstruction Set

data class Add(val rs: Int, val rt: Int, val rd: Int) : MipsInstruction()
data class Addi(val rs: Int, val rt: Int, val immediate: Int) : MipsInstruction()
data class Addiu(val rs: Int, val rt: Int, val immediate: Int) : MipsInstruction()
data class Addu(val rs: Int, val rt: Int, val rd: Int) : MipsInstruction()

data class And(val rs: Int, val rt: Int, val rd: Int) : MipsInstruction()
data class Andi(val rs: Int, val rt: Int, val immediate: Int) : MipsInstruction()

data class Beq(val rs: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Beql(val rs: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Bgez(val rs: Int, val offset: Int) : MipsInstruction()
data class Bgezal(val rs: Int, val offset: Int) : MipsInstruction()
data class Bgezall(val rs: Int, val offset: Int) : MipsInstruction()
data class Bgezl(val rs: Int, val offset: Int) : MipsInstruction()
data class Bgtz(val rs: Int, val offset: Int) : MipsInstruction()
data class Bgtzl(val rs: Int, val offset: Int) : MipsInstruction()
data class Blez(val rs: Int, val offset: Int) : MipsInstruction()
data class Blezl(val rs: Int, val offset: Int) : MipsInstruction()
data class Bltz(val rs: Int, val offset: Int) : MipsInstruction()
data class Bltzal(val rs: Int, val offset: Int) : MipsInstruction()
data class Bltzall(val rs: Int, val offset: Int) : MipsInstruction()
data class Bltzl(val rs: Int, val offset: Int) : MipsInstruction()
data class Bne(val rs: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Bnel(val rs: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Break(val code: Int) : MipsInstruction()

data class Copz(val z: Int, val copFun: Int) : MipsInstruction()

data class Div(val rs: Int, val rt: Int) : MipsInstruction()
data class Divu(val rs: Int, val rt: Int) : MipsInstruction()

data class J(val instrIndex: Int) : MipsInstruction()
data class Jal(val instrIndex: Int) : MipsInstruction()
data class Jalr(val rs: Int, val rd: Int) : MipsInstruction()
data class Jr(val rs: Int) : MipsInstruction()

data class Lb(val base: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Lbu(val base: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Ld(val base: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Ldcz(val z: Int, val base: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Ldl(val base: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Ldr(val base: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Lh(val base: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Lhu(val base: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Ll(val base: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Lld(val base: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Lui(val rt: Int, val immediate: Int) : MipsInstruction()
data class Lw(val base: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Lwcz(val z: Int, val base: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Lwl(val base: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Lwr(val base: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Lwu(val base: Int, val rt: Int, val offset: Int) : MipsInstruction()

data class Mfhi(val rd: Int) : MipsInstruction()
data class Mflo(val rd: Int) : MipsInstruction()
data class Movn(val rs: Int, val rt: Int, val rd: Int) : MipsInstruction()
data class Movz(val rs: Int, val rt: Int, val rd: Int) : MipsInstruction()
data class Mthi(val rs: Int) : MipsInstruction()
data class Mtlo(val rs: Int) : MipsInstruction()

data class Mult(val rs: Int, val rd: Int) : MipsInstruction()
data class Multu(val rs: Int, val rd: Int) : MipsInstruction()
data class Nor(val rs: Int, val rt: Int, val rd: Int) : MipsInstruction()
data class Or(val rs: Int, val rt: Int, val rd: Int) : MipsInstruction()
data class Ori(val rs: Int, val rt: Int, val immediate: Int) : MipsInstruction()

data class Pref(val base: Int, val hint: Int, val offset: Int) : MipsInstruction()

data class Sb(val base: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Sc(val base: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Scd(val base: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Sd(val base: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Sdcz(val z: Int, val base: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Sdl(val base: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Sdr(val base: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Sh(val base: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Sll(val rt: Int, val rd: Int, val sa: Int) : MipsInstruction()
data class Sllv(val rs: Int, val rt: Int, val rd: Int) : MipsInstruction()

data class Slt(val rs: Int, val rt: Int, val rd: Int) : MipsInstruction()
data class Slti(val rs: Int, val rt: Int, val immediate: Int) : MipsInstruction()
data class Sltiu(val rs: Int, val rt: Int, val immediate: Int) : MipsInstruction()
data class Sltu(val rs: Int, val rt: Int, val rd: Int) : MipsInstruction()

data class Sra(val rt: Int, val rd: Int, val sa: Int) : MipsInstruction()
data class Srav(val rs: Int, val rt: Int, val sa: Int) : MipsInstruction()

data class Slr(val rt: Int, val rd: Int, val sa: Int) : MipsInstruction()
data class Slrv(val rs: Int, val rt: Int, val rd: Int) : MipsInstruction()
data class Sub(val rs: Int, val rt: Int, val rd: Int) : MipsInstruction()
data class Subu(val rs: Int, val rt: Int, val rd: Int) : MipsInstruction()

data class Sw(val base: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Swcz(val z: Int, val base: Int, val rt: Int, val offset: Int) : MipsInstruction()

data class Swl(val base: Int, val rt: Int, val offset: Int) : MipsInstruction()
data class Swr(val base: Int, val rt: Int, val offset: Int) : MipsInstruction()

data class Sync(val stype: Int) : MipsInstruction()
data class Syscall(val code: Int) : MipsInstruction()

data class Teq(val rs: Int, val rt: Int, val code: Int) : MipsInstruction()
data class Teqi(val rs: Int, val immediate: Int) : MipsInstruction()
data class Tge(val rs: Int, val rt: Int, val code: Int) : MipsInstruction()
data class Tgei(val rs: Int, val immediate: Int) : MipsInstruction()
data class Tgeiu(val rs: Int, val immediate: Int) : MipsInstruction()
data class Tgeu(val rs: Int, val rt: Int, val code: Int) : MipsInstruction()
data class Tlt(val rs: Int, val rt: Int, val code: Int) : MipsInstruction()
data class Tlti(val rs: Int, val immediate: Int) : MipsInstruction()
data class Tltiu(val rs: Int, val immediate: Int) : MipsInstruction()
data class Tltu(val rs: Int, val rt: Int, val code: Int) : MipsInstruction()
data class Tne(val rs: Int, val rt: Int, val code: Int) : MipsInstruction()
data class Tnei(val rs: Int, val immediate: Int) : MipsInstruction()

data class Xor(val rs: Int, val rt: Int, val rd: Int) : MipsInstruction()
data class Xori(val rs: Int, val rt: Int, val immediate: Int) : MipsInstruction()

// FPU (floating-point unit) instruction set
