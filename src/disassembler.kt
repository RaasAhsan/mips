// Disassembler for Z80 Assembly

data class Decoded(val instruction: Instruction, val nextIp: Int)

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
fun decode(ip: Int, memory: IntArray): Decoded? {
    val byte = memory[ip]

    // If the byte is a single, contained instruction then can always do ip + 1

    return when (byte) {
        0xCB -> null
        0xED -> {
            val ib = InstructionByte(memory[ip + 1])

            when (ib.x) {
                1 -> when (ib.z) {
                    4 -> Decoded(Negate, ip + 2)
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
                        0 -> Decoded(Nop, ip + 1)
                        1 -> Decoded(ExchangeAf, ip + 1)
                        2 -> Decoded(JumpDispNoZero(memory[ip + 1]), ip + 2)
                        3 -> Decoded(JumpDisp(memory[ip + 1]), ip + 2)
                        else -> null
                    }
                    2 -> when (ib.q) {
                        0 -> when (ib.p) {
                            0 -> Decoded(LoadAccToBc, ip + 1)
                            1 -> Decoded(LoadAccToDe, ip + 1)
                            2 -> {
                                val lo = memory[ip + 1]
                                val hi = memory[ip + 2]
                                val addr = hi.shl(8) + lo

                                Decoded(Load16HlToMem(addr), ip + 3)
                            }
                            3 -> {
                                val lo = memory[ip + 1]
                                val hi = memory[ip + 2]
                                val addr = hi.shl(8) + lo

                                Decoded(LoadAccToMem(addr), ip + 3)
                            }
                            else -> null
                        }
                        1 -> when (ib.p) {
                            0 -> Decoded(LoadBcToAcc, ip + 1)
                            1 -> Decoded(LoadDeToAcc, ip + 1)
                            2 -> {
                                val lo = memory[ip + 1]
                                val hi = memory[ip + 2]
                                val addr = hi.shl(8) + lo

                                Decoded(Load16MemToHl(addr), ip + 3)
                            }
                            3 -> {
                                val lo = memory[ip + 1]
                                val hi = memory[ip + 2]
                                val addr = hi.shl(8) + lo

                                Decoded(LoadMemToAcc(addr), ip + 3)
                            }
                            else -> null
                        }
                        else -> null
                    }
                    4 -> Decoded(Increment(ArithToReg(ib.y)), ip + 1)
                    5 -> Decoded(Decrement(ArithToReg(ib.y)), ip + 1)
                    6 -> Decoded(LoadIntToReg(ib.y, memory[ip + 1]), ip + 2)
                    7 -> when (ib.y) {
                        0 -> null
                        5 -> Decoded(Complement, ip + 1)
                        6 -> Decoded(SetCarryFlag, ip + 1)
                        7 -> Decoded(ComplementCarryFlag, ip + 1)
                        else -> null
                    }
                    else -> null
                }
                1 -> when (ib.z) {
                    6 -> when (ib.y) {
                        6 -> Decoded(Halt, ip + 1)
                        else -> null
                    }
                    else -> Decoded(LoadRegToReg(ib.y, ib.z), ip + 1)
                }
                2 -> when (ib.y) {
                    0 -> Decoded(Add(ArithFromReg(ib.z)), ip + 1)
                    1 -> Decoded(Adc(ArithFromReg(ib.z)), ip + 1)
                    2 -> Decoded(Sub(ArithFromReg(ib.z)), ip + 1)
                    3 -> Decoded(Sbc(ArithFromReg(ib.z)), ip + 1)
                    4 -> Decoded(And(ArithFromReg(ib.z)), ip + 1)
                    5 -> Decoded(Xor(ArithFromReg(ib.z)), ip + 1)
                    6 -> Decoded(Or(ArithFromReg(ib.z)), ip + 1)
                    7 -> Decoded(Compare(ArithFromReg(ib.z)), ip + 1)
                    else -> null
                }
                3 -> when (ib.z) {
                    1 -> when (ib.q) {
                        0 -> Decoded(Pop(ib.p), ip + 1)
                        1 -> when (ib.p) {
                            0 -> Decoded(Return, ip + 1)
                            1 -> Decoded(ExchangeRegisterPairs, ip + 1)
                            2 -> Decoded(JumpHl, ip + 1)
                            3 -> Decoded(Load16HlToSp, ip + 1)
                            else -> null
                        }
                        else -> null
                    }
                    2 -> {
                        val lo = memory[ip + 1]
                        val hi = memory[ip + 2]
                        val addr = hi.shl(8) + lo

                        Decoded(JumpMemCond(ib.y, addr), ip + 3)
                    }
                    3 -> when (ib.y) {
                        0 -> {
                            val lo = memory[ip + 1]
                            val hi = memory[ip + 2]
                            val addr = hi.shl(8) + lo

                            Decoded(JumpMem(addr), ip + 3)
                        }
                        4 -> Decoded(ExchangeSpHl, ip + 1)
                        5 -> Decoded(ExchangeDeHl, ip + 1)
                        else -> null
                    }
                    4 -> {
                        val lo = memory[ip + 1]
                        val hi = memory[ip + 2]
                        val addr = hi.shl(8) + lo

                        Decoded(CallMemCond(addr, ib.y), ip + 3)
                    }
                    5 -> when (ib.q) {
                        0 -> Decoded(Push(ib.p), ip + 1)
                        1 -> when (ib.p) {
                            0 -> {
                                val lo = memory[ip + 1]
                                val hi = memory[ip + 2]
                                val addr = hi.shl(8) + lo

                                Decoded(CallMem(addr), ip + 3)
                            }
                            else -> null
                        }
                        else -> null
                    }
                    6 -> when(ib.y) { // TODO: ALU table
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
