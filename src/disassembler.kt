// Disassembler for MIPS assembly

data class Decoded(val instruction: Instruction, val nextPc: Int)

data class Opcode(val value: Int) {

}

// Return: value, address of next value
fun decode(pc: Int, memory: IntArray): Decoded? {
    val opcode = Opcode(memory[pc])

    return null
}
