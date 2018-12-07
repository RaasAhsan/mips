// Disassembler for Z80 Assembly

data class Decoded(val instruction: Instruction, val nextPc: Int)

data class Opcode(val value: Int) {

    // Components are specified here: http://www.z80.info/decoding.htm

    // bits 7-6
    val x: Int = value.and(0b11000000).shr(6)
    // bits 5-3
    val y: Int = value.and(0b00111000).shr(3)
    // bits 2-0
    val z: Int = value.and(0b00000111)
    // bit 3
    val p: Int = y.shr(1)
    // bits 5-4
    val q: Int = y % 2

}

// Return: value, address of next value
fun decode(pc: Int, memory: IntArray): Decoded? {
    val opcode = Opcode(memory[pc])

    val result =
        //
        // 8-bit load group
        //
        if (opcode.x == 0b01 && isRegisterABCDEHL(opcode.y) && isRegisterABCDEHL(opcode.z)) { // LD r, r'
            Decoded(LoadRegToReg(opcode.y, opcode.x), pc + 1)
        } else if (opcode.x == 0b00 && isRegisterABCDEHL(opcode.y) && opcode.z == 0b110) { // LD r, n
            Decoded(LoadIntToReg(opcode.y, memory[pc + 1]), pc + 2)
        } else if (opcode.x == 0b01 && isRegisterABCDEHL(opcode.y) && opcode.z == 0b110) { // LD r, (HL)
            Decoded(LoadHlToReg(opcode.y), pc + 2)
        } else if (false) { // LD r, (IX+d)
            null
        } else if (false) { // LD r, (IY+d)
            null
        } else if (opcode.x == 0b01 && opcode.y == 0b110 && isRegisterABCDEHL(opcode.z)) { // LD (HL), r
            Decoded(LoadRegToHl(opcode.z), pc + 1)
        } else if (false) { // LD (IX+d), r
            null
        } else if (false) { // LD (IY+d), r
            null
        } else if (opcode.value == 0b00110110) { // LD (HL), n
            Decoded(LoadIntToHl(memory[pc + 1]), pc + 2)
        } else if (false) { // LD (IX+d), n
            null
        } else if (false) { // LD (IY+d), n
            null
        } else if (opcode.value == 0b00001010) { // LD A, (BC)
            Decoded(LoadBcToAcc, pc + 1)
        } else if (opcode.value == 0b00011010) { // LD A, (DE)
            Decoded(LoadBcToAcc, pc + 1)
        } else if (opcode.value == 0b00111010) { // LD A, (nn)
            val addr = read16BitsLowHigh(memory, pc + 1)
            Decoded(LoadMemToAcc(addr), pc + 3)
        } else if (opcode.value == 0b00000010) { // LD (BC), A
            Decoded(LoadAccToBc, pc + 1)
        } else if (opcode.value == 0b00010010) { // LD (DE), A
            Decoded(LoadAccToDe, pc + 1)
        } else if (opcode.value == 0b00110010) { // LD (nn), A
            val addr = read16BitsLowHigh(memory, pc + 1)
            Decoded(LoadAccToMem(addr), pc + 3)
        } else if (false) { // LD A, I
            null
        } else if (false) { // LD A, R
            null
        } else if (false) { // LD I, A
            null
        } else if (false) { // LD R, A
            null
        }
        //
        // 16-bit load group
        //
        else if (opcode.x == 0b11 && opcode.q == 0b1 && opcode.z == 0b001) { // LD dd, nn
            val nn = read16BitsLowHigh(memory, pc + 1)
            Decoded(Load16IntToRegPair(opcode.p, nn), pc + 3)
        } else if (false) { // LD IX, nn
            null
        } else if (false) { // LD IY, nn
            null
        } else if (opcode.value == 0b00101010) { // LD HL, (nn)
            val addr = read16BitsLowHigh(memory, pc + 1)
            Decoded(Load16MemToHl(addr), pc + 3)
        } else if (false) { // LD dd, (nn)
            null
        } else if (false) { // LD IX, (nn)
            null
        } else if (false) { // LD IY, (nn)
            null
        } else if (opcode.value == 0b00101010) { // LD (nn), HL
            val addr = read16BitsLowHigh(memory, pc + 1)
            Decoded(Load16HlToMem(addr), pc + 3)
        } else if (false) { // LD (nn), dd
            null
        } else if (false) { // LD (nn), IX
            null
        } else if (false) { // LD (nn), IY
            null
        } else if (opcode.value == 0b11111001) { // LD SP, HL
            Decoded(Load16HlToSp, pc + 1)
        } else if (false) { // LD SP, IX
            null
        } else if (false) { // LD SP, IY
            null
        } else if (opcode.x == 0b11 && opcode.q == 0b0 && opcode.z == 0b101) { // PUSH qq
            Decoded(Push(opcode.q), pc + 1)
        } else if (false) { // PUSH IX
            null
        } else if (false) { // PUSH IY
            null
        } else if (opcode.x == 0b11 && opcode.q == 0b0 && opcode.z == 0b001) { // POP qq
            Decoded(Pop(opcode.q), pc + 1)
        } else if (false) { // POP IX
            null
        } else if (false) { // POP IY
            null
        }
        //
        // Exchange, Block Transfer, and Search Group
        //
        else {
            null
        }

    return result
}

// operand is a 3-bit value
/*
 * A - 111
 * B - 000
 * C - 001
 * D - 010
 * E - 011
 * H - 100
 * L - 101
 */
private fun isRegisterABCDEHL(operand: Int): Boolean {
    return operand != 0b110
}

private fun read16BitsLowHigh(memory: IntArray, index: Int): Int {
    val lo = memory[index]
    val hi = memory[index + 1]

    return hi.shl(8) + lo
}
