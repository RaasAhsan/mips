// Disassembler for Z80 Assembly

data class DecodeResult(val instruction: Instruction, val nextIp: Int)

data class InstructionByte(val instruction: Int) {

    // Components are specified here: http://www.z80.info/decoding.htm

    // bits 7-6
    val x: Int = instruction.and(0b11000000).shr(6)
    // bits 5-3
    val y: Int = instruction.and(0b00111000).shr(3)
    // bits 2-0
    val z: Int = instruction.and(0b00000111)
    // bit 3
    val p: Int = y.shr(1)
    // bits 5-4
    val q: Int = y % 2

}

// Return: instruction, address of next instruction
fun decode(ip: Int, memory: IntArray): DecodeResult? {
    val byte = memory[ip]
    val ib = InstructionByte(byte)

    // If the byte is a single, contained instruction then can always do ip + 1

    return when (byte) {
        0xCB -> null
        0xED -> null
        0xDD -> null
        0xFD -> null
        else -> when (ib.x) {
            0 -> when (ib.z) {
                0 -> when (ib.y) {
                    0 -> DecodeResult(Nop, ip + 1)
                    1 -> null
                    2 -> null
                    3 -> null
                    else -> null
                }
                4 -> null
                5 -> null
                else -> null
            }
            1 -> when (ib.z) {
                6 -> when (ib.y) {
                    6 -> DecodeResult(Halt, ip + 1)
                    else -> null
                }
                else -> DecodeResult(LoadRegToReg(ib.y, ib.z), ip + 1)
            }
            2 -> null // TODO: ALU operations
            else -> null
        }
    }
}
