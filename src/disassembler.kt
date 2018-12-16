// Disassembler for MIPS assembly

data class Decoded(val instruction: Instruction, val nextPc: Int)

data class Opcode(val value: Int) {

}

// Return: value, address of next value
fun decode(pc: Int, memory: IntArray): Decoded? {
    val opcode = Opcode(memory[pc])

    return null
}

private fun isRegisterABCDEHL(operand: Int): Boolean {
    return operand != 0b110
}

private fun read16BitsLowHigh(memory: IntArray, index: Int): Int {
    val lo = memory[index]
    val hi = memory[index + 1]

    return hi.shl(8) + lo
}
