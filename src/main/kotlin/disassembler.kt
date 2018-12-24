// Disassembler for MIPS assembly

data class InstructionWord(val value: Int) {

    val opcode = (value shr 27) and 0x3f
    val function = value and 0x3f

    val rs = (value shr 21) and 0x1f
    val rt = (value shr 16) and 0x1f
    val rd = (value shr 11) and 0x1f
    val sa = (value shl 6) and 0x1f
    val code = (value shr 6) and 0xfffff // TODO: Is this correct?
    val immediate = value and 0xffff
    val offset = value and 0xffff
    val instrIndex = value and 0x3ffffff

    val base = rt

}

const val OPCODE_SPECIAL = 0b000000
const val OPCODE_ADDI    = 0b001000
const val OPCODE_ADDIU   = 0b001001
const val OPCODE_ANDI    = 0b001100
const val OPCODE_BEQ     = 0b000100
const val OPCODE_BEQL    = 0b010100
const val OPCODE_REGIMM  = 0b000001
const val OPCODE_BGTZ    = 0b000111
const val OPCODE_BGTZL   = 0b010111
const val OPCODE_BLEZ    = 0b000110
const val OPCODE_BLEZL   = 0b010110
const val OPCODE_BNE     = 0b000101
const val OPCODE_BNEL    = 0b010101
const val OPCODE_CACHE   = 0b101111
const val OPCODE_SPECIAL3 = 0b011111
const val OPCODE_SPECIAL2 = 0b011100
const val OPCODE_COP0    = 0b010000
const val OPCODE_COP1    = 0b010001
const val OPCODE_COP2    = 0b010010
const val OPCODE_COP3    = 0b010011
const val OPCODE_J       = 0b000010
const val OPCODE_JAL     = 0b000011

const val OPCODE_LB      = 0b100000
const val OPCODE_LBU     = 0b100100

const val OPCODE_LD      = 0b110111
const val OPCODE_LDC1    = 0b110101
const val OPCODE_LDC2    = 0b110110
const val OPCODE_LDL     = 0b011010
const val OPCODE_LDR     = 0b011011
const val OPCODE_LH      = 0b100001
const val OPCODE_LHU     = 0b100101
const val OPCODE_LL      = 0b110000
const val OPCODE_LLD     = 0b110100
const val OPCODE_LUI     = 0b001111
const val OPCODE_LW      = 0b100011
const val OPCODE_LWC1    = 0b110001
const val OPCODE_LWC2    = 0b110010
const val OPCODE_LWC3    = 0b110011
const val OPCODE_LWL     = 0b100010
const val OPCODE_LWR     = 0b100110
const val OPCODE_LWU     = 0b100111
const val OPCODE_ORI     = 0b001101
const val OPCODE_PREF    = 0b110011
const val OPCODE_SB      = 0b101000
const val OPCODE_SC      = 0b111000
const val OPCODE_SCD     = 0b111100
const val OPCODE_SD      = 0b111111
const val OPCODE_SDC1    = 0b111101
const val OPCODE_SDC2    = 0b111110
const val OPCODE_SDL     = 0b101100
const val OPCODE_SDR     = 0b101101
const val OPCODE_SH      = 0b101001
const val OPCODE_SLTI    = 0b001010
const val OPCODE_SLTIU   = 0b001011
const val OPCODE_SW      = 0b101011
const val OPCODE_SWC1    = 0b111001
const val OPCODE_SWC2    = 0b111010
const val OPCODE_SWC3    = 0b111011
const val OPCODE_SWL     = 0b101010
const val OPCODE_SWR     = 0b101110
const val OPCODE_XORI    = 0b001110


// Functions under SPECIAL opcode
const val FUNCTION_ADD     = 0b100000
const val FUNCTION_ADDU    = 0b100001
const val FUNCTION_AND     = 0b100100
const val FUNCTION_BREAK   = 0b001101

const val FUNCTION_DIV     = 0b011010
const val FUNCTION_DIVU    = 0b011011

const val FUNCTION_JALR    = 0b001001
const val FUNCTION_JR      = 0b001000

const val FUNCTION_MFHI    = 0b010000
const val FUNCTION_MFLO    = 0b010010
const val FUNCTION_MOVN    = 0b001011
const val FUNCTION_MOVZ    = 0b001010
const val FUNCTION_MTHI    = 0b010001
const val FUNCTION_MTLO    = 0b010011
const val FUNCTION_MULT    = 0b011000
const val FUNCTION_MULTU   = 0b011001
const val FUNCTION_NOR     = 0b100111
const val FUNCTION_OR      = 0b100101
const val FUNCTION_SLL     = 0b000000
const val FUNCTION_SLLV    = 0b000100
const val FUNCTION_SLT     = 0b101010
const val FUNCTION_SRA     = 0b000011
const val FUNCTION_SRAV    = 0b000111
const val FUNCTION_SRL     = 0b000010
const val FUNCTION_SRLV    = 0b000110
const val FUNCTION_SUB     = 0b100010
const val FUNCTION_SUBU    = 0b100011
const val FUNCTION_SYNC    = 0b001111
const val FUNCTION_SYSCALL = 0b001100
const val FUNCTION_TEQ     = 0b110100
const val FUNCTION_TGE     = 0b110000
const val FUNCTION_TGEU    = 0b110001
const val FUNCTION_TLT     = 0b110010
const val FUNCTION_TLTU    = 0b110011
const val FUNCTION_TNE     = 0b110110
const val FUNCTION_XOR     = 0b100110


// Functions under REGIMM opcode
const val RT_BGEZ    = 0b00001
const val RT_BGEZAL  = 0b10001
const val RT_BGEZALL = 0b10011
const val RT_BGEZL   = 0b00011
const val RT_BLTZ    = 0b00000
const val RT_BLTZAL  = 0b10000
const val RT_BLTZALL = 0b10010
const val RT_BLTZL   = 0b00010

const val RT_TEQI    = 0b01100
const val RT_TGEI    = 0b01000
const val RT_TGEIU   = 0b01001
const val RT_TLTI    = 0b01010
const val RT_TLTIU   = 0b01011
const val RT_TNEI    = 0b01110


// Functions under SPECIAL2 opcode
const val FUNCTION_CLO = 0b100001
const val FUNCTION_CLZ = 0b100000

// Functions under SPECIAL3 opcode
const val FUNCTION_CACHE3 = 0b011011
const val FUNCTION_EXT    = 0b000000
const val FUNCTION_INS    = 0b000100

// Functions under COP0 opcode
const val FUNCTION_DERET = 0b011111
const val FUNCTION_ERET  = 0b011000


fun decode(pc: Int, memory: IntArray): Instruction? {
    val i = readWord(pc, memory)

    return when (i.opcode) {
        OPCODE_SPECIAL -> {
            when (i.function) {
                FUNCTION_ADD -> Add(i.rs, i.rt, i.rd)
                FUNCTION_ADDU -> Addu(i.rs, i.rt, i.rd)
                FUNCTION_AND -> And(i.rs, i.rt, i.rd)
                FUNCTION_BREAK -> Break(i.code)
                FUNCTION_DIV -> Div(i.rs, i.rt)
                FUNCTION_DIVU -> Divu(i.rs, i.rt)
                FUNCTION_JALR -> null
                FUNCTION_JR -> null
                FUNCTION_MFHI -> Mfhi(i.rd)
                FUNCTION_MFLO -> Mflo(i.rd)
                FUNCTION_MOVN -> Movn(i.rs, i.rt, i.rd)
                FUNCTION_MOVZ -> Movz(i.rs, i.rt, i.rd)
                FUNCTION_MTHI -> Mthi(i.rs)
                FUNCTION_MTLO -> Mtlo(i.rs)
                FUNCTION_MULT -> Mult(i.rs, i.rt)
                FUNCTION_MULTU -> Multu(i.rs, i.rt)
                FUNCTION_NOR -> Nor(i.rs, i.rt, i.rd)
                FUNCTION_OR -> Or(i.rs, i.rt, i.rd)
                FUNCTION_SLL -> Sll(i.rt, i.rd, i.sa)
                FUNCTION_SLLV -> Sllv(i.rs, i.rt, i.rd)
                FUNCTION_SLT -> Slt(i.rs, i.rt, i.rd)
                FUNCTION_SRA -> Sra(i.rt, i.rd, i.sa)
                FUNCTION_SRAV -> Srav(i.rs, i.rt, i.rd)
                FUNCTION_SRL -> null
                FUNCTION_SRLV -> null
                FUNCTION_SUB -> Sub(i.rs, i.rt, i.rd)
                FUNCTION_SUBU -> Subu(i.rs, i.rt, i.rd)
                FUNCTION_SYNC -> null
                FUNCTION_SYSCALL -> Syscall(i.code)
                FUNCTION_TEQ -> null
                FUNCTION_TGE -> null
                FUNCTION_TGEU -> null
                FUNCTION_TLT -> null
                FUNCTION_TLTU -> null
                FUNCTION_TNE -> null
                FUNCTION_XOR -> null
                else -> null
            }
        }
        OPCODE_ADDI -> Addi(i.rs, i.rt, i.immediate)
        OPCODE_ADDIU -> Addiu(i.rs, i.rt, i.immediate)
        OPCODE_ANDI -> Andi(i.rs, i.rt, i.immediate)
        OPCODE_BEQ -> Beq(i.rs, i.rt, i.offset)
        OPCODE_BEQL -> Beql(i.rs, i.rt, i.offset)
        OPCODE_REGIMM -> {
            when (i.rt) {
                RT_BGEZ -> Bgez(i.rs, i.offset)
                RT_BGEZAL -> Bgezal(i.rs, i.offset)
                RT_BGEZALL -> Bgezall(i.rs, i.offset)
                RT_BGEZL -> Bgezl(i.rs, i.offset)
                RT_BLTZ -> Bltz(i.rs, i.offset)
                RT_BLTZAL -> Bltzal(i.rs, i.offset)
                RT_BLTZALL -> Bltzall(i.rs, i.offset)
                RT_BLTZL -> Bltzl(i.rs, i.offset)
                RT_TEQI -> null
                RT_TGEI -> null
                RT_TGEIU -> null
                RT_TLTI -> null
                RT_TLTIU -> null
                RT_TNEI -> null
                else -> null
            }
        }
        OPCODE_BGTZ -> Bgtz(i.rs, i.offset)
        OPCODE_BGTZL -> Bgtzl(i.rs, i.offset)
        OPCODE_BLEZ -> Blez(i.rs, i.offset)
        OPCODE_BLEZL -> Blezl(i.rs, i.offset)
        OPCODE_BNE -> Bne(i.rs, i.rt, i.offset)
        OPCODE_BNEL -> Bnel(i.rs, i.rt, i.offset)
        OPCODE_COP0 -> null
        OPCODE_COP1 -> null
        OPCODE_COP2 -> null
        OPCODE_COP3 -> null
        OPCODE_J -> J(i.instrIndex)
        OPCODE_JAL -> Jal(i.instrIndex)
        OPCODE_LB -> Lb(i.base, i.rt, i.offset)
        OPCODE_LBU -> Lbu(i.base, i.rt, i.offset)
        OPCODE_LD -> null
        OPCODE_LDC1 -> null
        OPCODE_LDC2 -> null
        OPCODE_LDL -> null
        OPCODE_LDR -> null
        OPCODE_LH -> Lh(i.base, i.rt, i.offset)
        OPCODE_LHU -> Lhu(i.base, i.rt, i.offset)
        OPCODE_LL -> Ll(i.base, i.rt, i.offset)
        OPCODE_LLD -> null
        OPCODE_LUI -> Lui(i.rt, i.immediate)
        OPCODE_LW -> Lw(i.base, i.rt, i.offset)
        OPCODE_LWC1 -> Lwcz(1, i.base, i.rt, i.offset)
        OPCODE_LWC2 -> Lwcz(2, i.base, i.rt, i.offset)
        OPCODE_LWC3 -> Lwcz(3, i.base, i.rt, i.offset)
        OPCODE_LWL -> Lwl(i.base, i.rt, i.offset)
        OPCODE_LWR -> Lwr(i.base, i.rt, i.offset)
        OPCODE_LWU -> null
        OPCODE_ORI -> Ori(i.rs, i.rt, i.immediate)
        OPCODE_PREF -> null
        OPCODE_SB -> Sb(i.base, i.rt, i.offset)
        OPCODE_SC -> Sc(i.base, i.rt, i.offset)
        OPCODE_SCD -> null
        OPCODE_SD -> null
        OPCODE_SDC1 -> null
        OPCODE_SDC2 -> null
        OPCODE_SDL -> null
        OPCODE_SDR -> null
        OPCODE_SH -> Sh(i.base, i.rt, i.offset)
        OPCODE_SLTI -> Slti(i.rs, i.rt, i.immediate)
        OPCODE_SLTIU -> Sltiu(i.rs, i.rt, i.immediate)
        OPCODE_SW -> Sw(i.base, i.rt, i.offset)
        OPCODE_SWC1 -> null
        OPCODE_SWC2 -> null
        OPCODE_SWC3 -> null
        OPCODE_SWL -> Swl(i.base, i.rt, i.offset)
        OPCODE_SWR -> Swr(i.base, i.rt, i.offset)
        OPCODE_XORI -> null
        else -> null
    }
}

fun readWord(index: Int, memory: IntArray): InstructionWord {
    // Encoded as little-endian 32-bit words
    val b0 = memory[index]
    val b1 = memory[index + 1]
    val b2 = memory[index + 2]
    val b3 = memory[index + 3]

    val word = (b3 shl 24) + (b2 shl 16) + (b1 shl 8) + b0

    return InstructionWord(word)
}
