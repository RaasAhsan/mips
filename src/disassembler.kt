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

    // TODO: Decode function per prefix
    return if (opcode.value == 0xED) {
        val nextOpcode = Opcode(memory[pc + 1])

        // 8-bit load group
        if (nextOpcode.value == 0x57) {
            // LD A, I
            Decoded(LoadIToA, pc + 2)
        } else if (nextOpcode.value == 0x5F) {
            // LD A, R
            Decoded(LoadRToA, pc + 2)
        } else if (nextOpcode.value == 0x47) {
            // LD I, A
            Decoded(LoadAToI, pc + 2)
        } else if (nextOpcode.value == 0x4F) {
            // LD R, A
            Decoded(LoadAToR, pc + 2)
        }
        // 16-bit load group
        else if (nextOpcode.x == 0b01 && nextOpcode.q == 0b1 && nextOpcode.z == 0b011) {
            // LD dd, (nn)
            val addr = read16BitsLowHigh(memory, pc + 2)
            Decoded(Load16MemToRegPair(nextOpcode.p, addr), pc + 4)
        } else if (nextOpcode.x == 0b01 && nextOpcode.q == 0b0 && nextOpcode.z == 0b011) {
            // LD (nn), dd
            val addr = read16BitsLowHigh(memory, pc + 2)
            Decoded(Load16RegPairToMem(nextOpcode.p, addr), pc + 4)
        }

        // Exchange, Block Transfer, and Search Group

        else if (nextOpcode.value == 0xA0) {
            // LDI
            Decoded(Ldi, pc + 2)
        } else if (nextOpcode.value == 0xB0) {
            // LDIR
            Decoded(Ldir, pc + 2)
        } else if (nextOpcode.value == 0xA8) {
            // LDD
            Decoded(Ldd, pc + 2)
        } else if (nextOpcode.value == 0xB8) {
            // LDDR
            Decoded(Lddr, pc + 2)
        } else if (nextOpcode.value == 0xA1) {
            // CPI
            Decoded(Cpi, pc + 2)
        } else if (nextOpcode.value == 0xB1) {
            // CPIR
            Decoded(Cpir, pc + 2)
        } else if (nextOpcode.value == 0xA9) {
            // CPD
            Decoded(Cpd, pc + 2)
        } else if (nextOpcode.value == 0xB9) {
            // CPDR
            Decoded(Cpdr, pc + 2)
        } else {
            null
        }
    } else if (opcode.value == 0xDD) {
        val nextOpcode = Opcode(memory[pc + 1])

        // 8-bit load group
        if (nextOpcode.x == 0b01 && isRegisterABCDEHL(nextOpcode.y) && nextOpcode.z == 0b110) {
            // LD r, (IX+d)
            Decoded(LoadIxToReg(nextOpcode.y, memory[pc + 2]), pc + 3)
        } else if (nextOpcode.x == 0b01 && nextOpcode.y == 0b110 && isRegisterABCDEHL(nextOpcode.z)) {
            // LD (IX+d), r
            Decoded(LoadRegToIx(nextOpcode.z, memory[pc + 2]), pc + 3)
        } else if (nextOpcode.value == 0x36) {
            // LD (IX+d), n
            Decoded(LoadIntToIx(memory[pc + 2], memory[pc + 3]), pc + 4)
        }
        // 16-bit load group
        else if (nextOpcode.value == 0x21) {
            // LD IX, nn
            val n = read16BitsLowHigh(memory, pc + 2)
            Decoded(Load16IntToIx(n), pc + 4)
        } else if (nextOpcode.value == 0x2A) {
            // LD IX, (nn)
            val n = read16BitsLowHigh(memory, pc + 2)
            Decoded(Load16MemToIx(n), pc + 4)
        } else if (nextOpcode.value == 0x22) {
            // LD (nn), IX
            val n = read16BitsLowHigh(memory, pc + 2)
            Decoded(Load16IxToMem(n), pc + 4)
        } else if (nextOpcode.value == 0xF9) {
            // LD SP, IX
            Decoded(Load16IxToSp, pc + 2)
        } else if (nextOpcode.value == 0xE5) {
            // PUSH IX
            Decoded(PushIx, pc + 2)
        } else if (nextOpcode.value == 0xE1) {
            // POP IX
            Decoded(PopIx, pc + 2)
        }

        // Exchange, Block Transfer, and Search Group

        else if (nextOpcode.value == 0xE3) {
            // EX (SP), IX
            Decoded(ExchangeSpIx, pc + 2)
        } else {
            null
        }
    } else if (opcode.value == 0xFD) {
        val nextOpcode = Opcode(memory[pc + 1])

        // 8-bit load group
        if (nextOpcode.x == 0b01 && isRegisterABCDEHL(nextOpcode.y) && nextOpcode.z == 0b110) {
            // LD r, (IY+d)
            Decoded(LoadIyToReg(nextOpcode.y, memory[pc + 2]), pc + 3)
        } else if (nextOpcode.x == 0b01 && nextOpcode.y == 0b110 && isRegisterABCDEHL(nextOpcode.z)) {
            // LD (IY+d), r
            Decoded(LoadRegToIy(nextOpcode.z, memory[pc + 2]), pc + 3)
        } else if (nextOpcode.value == 0x36) {
            // LD (IY+d), n
            Decoded(LoadIntToIy(memory[pc + 2], memory[pc + 3]), pc + 4)
        }
        // 16-bit load group
        else if (nextOpcode.value == 0x21) {
            // LD IX, nn
            val n = read16BitsLowHigh(memory, pc + 2)
            Decoded(Load16IntToIy(n), pc + 4)
        } else if (nextOpcode.value == 0x2A) {
            // LD IY, (nn)
            val n = read16BitsLowHigh(memory, pc + 2)
            Decoded(Load16MemToIy(n), pc + 4)
        } else if (nextOpcode.value == 0x22) {
            // LD (nn), IY
            val n = read16BitsLowHigh(memory, pc + 2)
            Decoded(Load16IyToMem(n), pc + 4)
        } else if (nextOpcode.value == 0xF9) {
            // LD SP, IY
            Decoded(Load16IyToSp, pc + 2)
        } else if (nextOpcode.value == 0xE5) {
            // PUSH IY
            Decoded(PushIy, pc + 2)
        } else if (nextOpcode.value == 0xE1) {
            // POP IY
            Decoded(PopIy, pc + 2)
        }

        // Exchange, Block Transfer, and Search Group

        else if (nextOpcode.value == 0xE3) {
            // EX (SP), IX
            Decoded(ExchangeSpIy, pc + 2)
        } else {
            null
        }
    } else {
        if (opcode.x == 0b01 && isRegisterABCDEHL(opcode.y) && isRegisterABCDEHL(opcode.z)) {
            // LD r, r'
            Decoded(LoadRegToReg(opcode.y, opcode.x), pc + 1)
        } else if (opcode.x == 0b00 && isRegisterABCDEHL(opcode.y) && opcode.z == 0b110) {
            // LD r, n
            Decoded(LoadIntToReg(opcode.y, memory[pc + 1]), pc + 2)
        } else if (opcode.x == 0b01 && isRegisterABCDEHL(opcode.y) && opcode.z == 0b110) {
            // LD r, (HL)
            Decoded(LoadHlToReg(opcode.y), pc + 2)
        } else if (opcode.x == 0b01 && opcode.y == 0b110 && isRegisterABCDEHL(opcode.z)) {
            // LD (HL), r
            Decoded(LoadRegToHl(opcode.z), pc + 1)
        } else if (opcode.value == 0b00110110) {
            // LD (HL), n
            Decoded(LoadIntToHl(memory[pc + 1]), pc + 2)
        } else if (opcode.value == 0x0A) {
            // LD A, (BC)
            Decoded(LoadBcToAcc, pc + 1)
        } else if (opcode.value == 0x1A) {
            // LD A, (DE)
            Decoded(LoadDeToAcc, pc + 1)
        } else if (opcode.value == 0x3A) {
            // LD A, (nn)
            val addr = read16BitsLowHigh(memory, pc + 1)
            Decoded(LoadMemToAcc(addr), pc + 3)
        } else if (opcode.value == 0x02) {
            // LD (BC), A
            Decoded(LoadAccToBc, pc + 1)
        } else if (opcode.value == 0x12) {
            // LD (DE), A
            Decoded(LoadAccToDe, pc + 1)
        } else if (opcode.value == 0x32) {
            // LD (nn), A
            val addr = read16BitsLowHigh(memory, pc + 1)
            Decoded(LoadAccToMem(addr), pc + 3)
        }
        //
        // 16-bit load group
        //
        else if (opcode.x == 0b11 && opcode.q == 0b1 && opcode.z == 0b001) {
            // LD dd, nn
            val nn = read16BitsLowHigh(memory, pc + 1)
            Decoded(Load16IntToRegPair(opcode.p, nn), pc + 3)
        } else if (opcode.value == 0x2A) {
            // LD HL, (nn)
            val addr = read16BitsLowHigh(memory, pc + 1)
            Decoded(Load16MemToHl(addr), pc + 3)
        } else if (opcode.value == 0b00101010) {
            // LD (nn), HL
            val addr = read16BitsLowHigh(memory, pc + 1)
            Decoded(Load16HlToMem(addr), pc + 3)
        } else if (opcode.value == 0b11111001) {
            // LD SP, HL
            Decoded(Load16HlToSp, pc + 1)
        } else if (opcode.x == 0b11 && opcode.q == 0b0 && opcode.z == 0b101) {
            // PUSH qq
            Decoded(Push(opcode.q), pc + 1)
        } else if (opcode.x == 0b11 && opcode.q == 0b0 && opcode.z == 0b001) {
            // POP qq
            Decoded(Pop(opcode.q), pc + 1)
        }
        //
        // Exchange, Block Transfer, and Search Group
        //
        else if (opcode.value == 0xEB) {
            // EX DE, HL
            Decoded(ExchangeDeHl, pc + 1)
        } else if (opcode.value == 0x08) {
            // EX AF, AF'
            Decoded(ExchangeAf, pc + 1)
        } else if (opcode.value == 0xD9) {
            // EXX
            Decoded(ExchangeRegisterPairs, pc + 1)
        } else if (opcode.value == 0xE3) {
            // EX (SP), HL
            Decoded(ExchangeSpHl, pc + 1)
        } else if (false) {
            // EX (SP), IX
            null
        } else if (false) {
            // EX (SP), IY
            null
        }
        //
        // 8-bit arithmetic group
        //
        else if (opcode.x == 0b10 && opcode.y == 0b000 && isRegisterABCDEHL(opcode.z)) { // ADD A, r
            Decoded(Add(ArithFromReg(opcode.z)), pc + 1)
        } else if (opcode.value == 0b11000110) { // ADD A, n
            Decoded(Add(ArithFromInt(memory[pc + 1])), pc + 2)
        } else if (opcode.value == 0b10000110) { // ADD A, (HL)
            Decoded(Add(ArithFromHl), pc + 1)
        } else if (false) { // ADD A, (IX + d)
            null
        } else if (false) { // ADD A, (IY + d)
            null
        } else if (opcode.x == 0b00 && isRegisterABCDEHL(opcode.y) && opcode.z == 0b100) { // INC r
            Decoded(Inc(ArithToReg(opcode.y)), pc + 1)
        } else if (opcode.value == 0b00110100) { // INC (HL)
            Decoded(Inc(ArithToHl), pc + 1)
        } else {
            null
        }
    }
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
