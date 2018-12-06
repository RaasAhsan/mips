// Disassembler for Z80 Assembly

data class Decoded(val instruction: Instruction, val nextIp: Int)

data class Opcode(val instruction: Int) {

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
fun decode(ip: Int, memory: IntArray): Decoded? {
    val byte = memory[ip]

    // If the byte is a single, contained instruction then can always do ip + 1

    return when (byte) {
        0xCB -> null
        0xED -> {
            val opcode = Opcode(memory[ip + 1])

            when (opcode.x) {
                1 -> when (opcode.z) {
                    4 -> Decoded(Negate, ip + 2)
                    else -> null
                }
                else -> null
            }
        }
        0xDD -> null
        0xFD -> null
        else -> {
            val opcode = Opcode(byte)

            when (opcode.x) {
                0 -> when (opcode.z) {
                    0 -> when (opcode.y) {
                        0 -> Decoded(Nop, ip + 1)
                        1 -> Decoded(ExchangeAf, ip + 1)
                        2 -> Decoded(JumpDispNoZero(memory[ip + 1]), ip + 2)
                        3 -> Decoded(JumpDisp(memory[ip + 1]), ip + 2)
                        else -> null
                    }
                    2 -> when (opcode.q) {
                        0 -> when (opcode.p) {
                            0 -> Decoded(LoadAccToBc, ip + 1)
                            1 -> Decoded(LoadAccToDe, ip + 1)
                            2 -> {
                                val addr = read16BitOperand(memory, ip + 1)

                                Decoded(Load16HlToMem(addr), ip + 3)
                            }
                            3 -> {
                                val addr = read16BitOperand(memory, ip + 1)

                                Decoded(LoadAccToMem(addr), ip + 3)
                            }
                            else -> null
                        }
                        1 -> when (opcode.p) {
                            0 -> Decoded(LoadBcToAcc, ip + 1)
                            1 -> Decoded(LoadDeToAcc, ip + 1)
                            2 -> {
                                val addr = read16BitOperand(memory, ip + 1)

                                Decoded(Load16MemToHl(addr), ip + 3)
                            }
                            3 -> {
                                val addr = read16BitOperand(memory, ip + 1)

                                Decoded(LoadMemToAcc(addr), ip + 3)
                            }
                            else -> null
                        }
                        else -> null
                    }
                    3 -> when (opcode.q) {
                        0 -> Decoded(Inc16RegPair(opcode.p), ip + 1)
                        1 -> Decoded(Dec16RegPair(opcode.p), ip + 1)
                        else -> null
                    }
                    4 -> Decoded(Inc(ArithToReg(opcode.y)), ip + 1)
                    5 -> Decoded(Dec(ArithToReg(opcode.y)), ip + 1)
                    6 -> Decoded(LoadIntToReg(opcode.y, memory[ip + 1]), ip + 2)
                    7 -> when (opcode.y) {
                        0 -> null
                        5 -> Decoded(Complement, ip + 1)
                        6 -> Decoded(SetCarryFlag, ip + 1)
                        7 -> Decoded(ComplementCarryFlag, ip + 1)
                        else -> null
                    }
                    else -> null
                }
                1 -> when (opcode.z) {
                    6 -> when (opcode.y) {
                        6 -> Decoded(Halt, ip + 1)
                        else -> null
                    }
                    else -> Decoded(LoadRegToReg(opcode.y, opcode.z), ip + 1)
                }
                2 -> when (opcode.y) {
                    0 -> Decoded(Add(ArithFromReg(opcode.z)), ip + 1)
                    1 -> Decoded(Adc(ArithFromReg(opcode.z)), ip + 1)
                    2 -> Decoded(Sub(ArithFromReg(opcode.z)), ip + 1)
                    3 -> Decoded(Sbc(ArithFromReg(opcode.z)), ip + 1)
                    4 -> Decoded(And(ArithFromReg(opcode.z)), ip + 1)
                    5 -> Decoded(Xor(ArithFromReg(opcode.z)), ip + 1)
                    6 -> Decoded(Or(ArithFromReg(opcode.z)), ip + 1)
                    7 -> Decoded(Compare(ArithFromReg(opcode.z)), ip + 1)
                    else -> null
                }
                3 -> when (opcode.z) {
                    1 -> when (opcode.q) {
                        0 -> Decoded(Pop(opcode.p), ip + 1)
                        1 -> when (opcode.p) {
                            0 -> Decoded(Return, ip + 1)
                            1 -> Decoded(ExchangeRegisterPairs, ip + 1)
                            2 -> Decoded(JumpHl, ip + 1)
                            3 -> Decoded(Load16HlToSp, ip + 1)
                            else -> null
                        }
                        else -> null
                    }
                    2 -> {
                        val addr = read16BitOperand(memory, ip + 1)

                        Decoded(JumpMemCond(opcode.y, addr), ip + 3)
                    }
                    3 -> when (opcode.y) {
                        0 -> {
                            val addr = read16BitOperand(memory, ip + 1)

                            Decoded(JumpMem(addr), ip + 3)
                        }
                        4 -> Decoded(ExchangeSpHl, ip + 1)
                        5 -> Decoded(ExchangeDeHl, ip + 1)
                        else -> null
                    }
                    4 -> {
                        val addr = read16BitOperand(memory, ip + 1)

                        Decoded(CallMemCond(addr, opcode.y), ip + 3)
                    }
                    5 -> when (opcode.q) {
                        0 -> Decoded(Push(opcode.p), ip + 1)
                        1 -> when (opcode.p) {
                            0 -> {
                                val addr = read16BitOperand(memory, ip + 1)

                                Decoded(CallMem(addr), ip + 3)
                            }
                            else -> null
                        }
                        else -> null
                    }
                    6 -> when(opcode.y) { // TODO: ALU table
                        0 -> Decoded(Add(ArithFromInt(memory[ip + 1])), ip + 2)
                        1 -> Decoded(Adc(ArithFromInt(memory[ip + 1])), ip + 2)
                        2 -> Decoded(Sub(ArithFromInt(memory[ip + 1])), ip + 2)
                        3 -> Decoded(Sbc(ArithFromInt(memory[ip + 1])), ip + 2)
                        4 -> Decoded(And(ArithFromInt(memory[ip + 1])), ip + 2)
                        5 -> Decoded(Xor(ArithFromInt(memory[ip + 1])), ip + 2)
                        6 -> Decoded(Or(ArithFromInt(memory[ip + 1])), ip + 2)
                        7 -> Decoded(Compare(ArithFromInt(memory[ip + 1])), ip + 2)
                        else -> null
                    }
                    else -> null
                }
                else -> null
            }
        }
    }
}

fun read16BitOperand(memory: IntArray, index: Int): Int {
    val lo = memory[index]
    val hi = memory[index + 1]

    return hi.shl(8) + lo
}
