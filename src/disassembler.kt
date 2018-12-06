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

    // If the byte is a single, contained instruction then can always do ip + 1

    return when (byte) {
        0xCB -> null
        0xED -> {
            val ib = InstructionByte(memory[ip + 1])

            when (ib.x) {
                1 -> when (ib.z) {
                    4 -> DecodeResult(Negate, ip + 2)
                    else -> null
                }
                else -> null
            }
        }
        0xDD -> null
        0xFD -> null
        else -> {
            val ib = InstructionByte(byte)

            when (ib.x) {
                0 -> when (ib.z) {
                    0 -> when (ib.y) {
                        0 -> DecodeResult(Nop, ip + 1)
                        1 -> DecodeResult(ExchangeAf, ip + 1)
                        2 -> DecodeResult(JumpDispNoZero(memory[ip + 1]), ip + 2)
                        3 -> DecodeResult(JumpDisp(memory[ip + 1]), ip + 2)
                        else -> null
                    }
                    4 -> DecodeResult(Increment(ArithToReg(ib.y)), ip + 1)
                    5 -> DecodeResult(Decrement(ArithToReg(ib.y)), ip + 1)
                    6 -> DecodeResult(LoadIntToReg(ib.y, memory[ip + 1]), ip + 2)
                    7 -> when (ib.y) {
                        0 -> null
                        5 -> DecodeResult(Complement, ip + 1)
                        6 -> DecodeResult(SetCarryFlag, ip + 1)
                        7 -> DecodeResult(ComplementCarryFlag, ip + 1)
                        else -> null
                    }
                    else -> null
                }
                1 -> when (ib.z) {
                    6 -> when (ib.y) {
                        6 -> DecodeResult(Halt, ip + 1)
                        else -> null
                    }
                    else -> DecodeResult(LoadRegToReg(ib.y, ib.z), ip + 1)
                }
                2 -> when (ib.y) {
                    0 -> DecodeResult(Add(ArithFromReg(ib.z)), ip + 1)
                    1 -> DecodeResult(Adc(ArithFromReg(ib.z)), ip + 1)
                    2 -> DecodeResult(Sub(ArithFromReg(ib.z)), ip + 1)
                    3 -> DecodeResult(Sbc(ArithFromReg(ib.z)), ip + 1)
                    4 -> DecodeResult(And(ArithFromReg(ib.z)), ip + 1)
                    5 -> DecodeResult(Xor(ArithFromReg(ib.z)), ip + 1)
                    6 -> DecodeResult(Or(ArithFromReg(ib.z)), ip + 1)
                    7 -> DecodeResult(Compare(ArithFromReg(ib.z)), ip + 1)
                    else -> null
                }
                3 -> when (ib.z) {
                    1 -> when (ib.q) {
                        0 -> DecodeResult(Pop(ib.p), ip + 1)
                        1 -> when (ib.p) {
                            0 -> DecodeResult(Return, ip + 1)
                            1 -> DecodeResult(ExchangeRegisterPairs, ip + 1)
                            2 -> DecodeResult(JumpHl, ip + 1)
                            3 -> DecodeResult(Load16HlToSp, ip + 1)
                            else -> null
                        }
                        else -> null
                    }
                    2 -> {
                        val lo = memory[ip + 1]
                        val hi = memory[ip + 2]
                        val addr = hi.shl(8) + lo

                        DecodeResult(JumpMemCond(ib.y, addr), ip + 3)
                    }
                    3 -> when (ib.y) {
                        0 -> {
                            val lo = memory[ip + 1]
                            val hi = memory[ip + 2]
                            val addr = hi.shl(8) + lo

                            DecodeResult(JumpMem(addr), ip + 3)
                        }
                        4 -> DecodeResult(ExchangeSpHl, ip + 1)
                        5 -> DecodeResult(ExchangeDeHl, ip + 1)
                        else -> null
                    }
                    6 -> when(ib.y) { // TODO: ALU table
                        0 -> DecodeResult(Add(ArithFromInt(memory[ip + 1])), ip + 2)
                        1 -> DecodeResult(Adc(ArithFromInt(memory[ip + 1])), ip + 2)
                        2 -> DecodeResult(Sub(ArithFromInt(memory[ip + 1])), ip + 2)
                        3 -> DecodeResult(Sbc(ArithFromInt(memory[ip + 1])), ip + 2)
                        4 -> DecodeResult(And(ArithFromInt(memory[ip + 1])), ip + 2)
                        5 -> DecodeResult(Xor(ArithFromInt(memory[ip + 1])), ip + 2)
                        6 -> DecodeResult(Or(ArithFromInt(memory[ip + 1])), ip + 2)
                        7 -> DecodeResult(Compare(ArithFromInt(memory[ip + 1])), ip + 2)
                        else -> null
                    }
                    else -> null
                }
                else -> null
            }
        }
    }
}
