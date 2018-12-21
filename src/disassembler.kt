// Disassembler for MIPS assembly

data class Decoded(val instruction: Instruction)

data class InstructionWord(val value: Int) {

    val opcode = (value >> 26) and 0x3F
    val function = value and 0x3F

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
const val OPCODE_COP0    = 0b010000
const val OPCODE_COP1    = 0b010001
const val OPCODE_COP2    = 0b010010
const val OPCODE_COP3    = 0b010011
const val OPCODE_DADDI   = 0b011000
const val OPCODE_DADDIU  = 0b011001
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
const val FUNCTION_DADD    = 0b101100
const val FUNCTION_DADDU   = 0b101101
const val FUNCTION_DDIV    = 0b011110
const val FUNCTION_DDIVU   = 0b011111
const val FUNCTION_DIV     = 0b011010
const val FUNCTION_DIVU    = 0b011011
const val FUNCTION_DMULT   = 0b011100
const val FUNCTION_DMULTU  = 0b011101
const val FUNCTION_DSLL    = 0b111000
const val FUNCTION_DSLL32  = 0b111100
const val FUNCTION_DSLLV   = 0b010100
const val FUNCTION_DSRA    = 0b111011
const val FUNCTION_DSRA32  = 0b111111
const val FUNCTION_DSRAV   = 0b010111
const val FUNCTION_DSRL    = 0b111010
const val FUNCTION_DSRL32  = 0b111110
const val FUNCTION_DSRLV   = 0b010110
const val FUNCTION_DSUB    = 0b101110
const val FUNCTION_DSUBU   = 0b101111
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



fun decode(pc: Int, memory: IntArray): Decoded? {
    val instruction = InstructionWord(memory[pc])

    when (instruction.opcode) {
        OPCODE_SPECIAL -> {
            when (instruction.function) {
                FUNCTION_ADD -> null
                FUNCTION_ADDU -> null
                FUNCTION_ADD -> null
                FUNCTION_BREAK -> null
                FUNCTION_DADD -> null
                FUNCTION_DADDU -> null
                FUNCTION_DDIV -> null
                FUNCTION_DDIVU -> null
                FUNCTION_DIV -> null
                FUNCTION_DIVU -> null
                FUNCTION_DMULT -> null
                FUNCTION_DMULTU -> null
                FUNCTION_DSLL -> null
                FUNCTION_DSLL32 -> null
                FUNCTION_DSLLV -> null
                FUNCTION_DSRA -> null
                FUNCTION_DSRA32 -> null
                FUNCTION_DSRAV -> null
                FUNCTION_DSRL -> null
                FUNCTION_DSRL32 -> null
                FUNCTION_DSRLV -> null
                FUNCTION_DSUB -> null
                FUNCTION_DSUBU -> null
                FUNCTION_JALR -> null
                FUNCTION_JR -> null
                FUNCTION_MFHI -> null
                FUNCTION_MFLO -> null
                FUNCTION_MOVN -> null
                FUNCTION_MOVZ -> null
                FUNCTION_MTHI -> null
                FUNCTION_MTLO -> null
                FUNCTION_MULT -> null
                FUNCTION_MULTU -> null
                FUNCTION_NOR -> null
                FUNCTION_OR -> null
                FUNCTION_SLL -> null
                FUNCTION_SLLV -> null
                FUNCTION_SLT -> null
                FUNCTION_SRA -> null
                FUNCTION_SRAV -> null
                FUNCTION_SRL -> null
                FUNCTION_SRLV -> null
                FUNCTION_SUB -> null
                FUNCTION_SUBU -> null
                FUNCTION_SYNC -> null
                FUNCTION_SYSCALL -> null
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
        OPCODE_ADDI -> null
        OPCODE_ADDIU -> null
        OPCODE_ANDI -> null
        OPCODE_BEQ -> null
        OPCODE_BEQL -> null
        OPCODE_REGIMM -> {
            when (instruction.rt) {
                RT_BGEZ -> null
                RT_BGEZAL -> null
                RT_BGEZALL -> null
                RT_BGEZL -> null
                RT_BLTZ -> null
                RT_BLTZAL -> null
                RT_BLTZALL -> null
                RT_BTLZL -> null
                RT_TEQI -> null
                RT_TGEI -> null
                RT_TGEIU -> null
                RT_TLTI -> null
                RT_TLTIU -> null
                RT_TNEI -> null
                else -> null
            }
        }
        OPCODE_BGTZ -> null
        OPCODE_BGTZL -> null
        OPCODE_BLEZ -> null
        OPCODE_BLEZL -> null
        OPCODE_BNE -> null
        OPCODE_BNEL -> null
        OPCODE_ADDI -> null
        OPCODE_COP0 -> null
        OPCODE_COP1 -> null
        OPCODE_COP2 -> null
        OPCODE_COP3 -> null
        OPCODE_DADDI -> null
        OPCODE_DADDIU -> null
        OPCODE_J -> null
        OPCODE_JAL -> null
        OPCODE_LB -> null
        OPCODE_LBU -> null
        OPCODE_LD -> null
        OPCODE_LDC1 -> null
        OPCODE_LDC2 -> null
        OPCODE_LDL -> null
        OPCODE_LDR -> null
        OPCODE_LH -> null
        OPCODE_LHU -> null
        OPCODE_LL -> null
        OPCODE_LLD -> null
        OPCODE_LUI -> null
        OPCODE_LW -> null
        OPCODE_LWC1 -> null
        OPCODE_LWC2 -> null
        OPCODE_LWC3 -> null
        OPCODE_LWL -> null
        OPCODE_LWR -> null
        OPCODE_LWU -> null
        OPCODE_ORI -> null
        OPCODE_PREF -> null
        OPCODE_SB -> null
        OPCODE_SC -> null
        OPCODE_SCD -> null
        OPCODE_SD -> null
        OPCODE_SDC1 -> null
        OPCODE_SDC2 -> null
        OPCODE_SDL -> null
        OPCODE_SDR -> null
        OPCODE_SH -> null
        OPCODE_SLTI -> null
        OPCODE_SLTIU -> null
        OPCODE_SW -> null
        OPCODE_SWC1 -> null
        OPCODE_SWC2 -> null
        OPCODE_SWC3 -> null
        OPCODE_SWL -> null
        OPCODE_SWR -> null
        OPCODE_XORI -> null
        else -> null
    }

    return null
}

fun readWord(index: Int, memory: IntArray): InstructionWord {
    val b0 = memory[index]
    val b1 = memory[index + 1]
    val b2 = memory[index + 2]
    val b3 = memory[index + 3]

    val word = (b0 shl 24) + (b1 shl 16) + (b2 shl 8) + b3

    InstructionWord(word)
}
