
//
// Constants
//

val mainAccumulator: Int = 0b111
val HL: Int = 0b110

//
// Memory
//

val memory: IntArray = intArrayOf(0xFFFF)

//
// Registers
//

// The program counter holds the 16-bit address of the current instruction being fetched from memory.
val programCounter: Int = 0x0000

// The stack pointer holds the 16-bit address of the current top of the stack located anywhere in RAM.
var stackPointer: Int = 0x0000

// The instruction register holds the 8-bit value of an instruction read from memory.
var instructionRegister: Int = 0x00

// The independent index registers hold a 16-bit base address used in indexed addressing modes.
val indexXRegister: Int = 0x0000
val indexYRegister: Int = 0x0000

val mainRegisters: IntArray = intArrayOf(8)

/*
 * The flag register provides information about the Z80 CPU during runtime.
 *
 * Bit 0 - C - Carry Flag
 * Bit 1 - N - Add/Subtract
 * Bit 2 - P/V - Parity/Overflow Flag
 * Bit 3 - X - Not Used
 * Bit 4 - H - Half Carry Flag
 * Bit 5 - X - Not Used
 * Bit 6 - Z - Zero Flag
 * Bit 7 - S - Sign Flag
 *
 * The register and its flags are described on Page 65.
 */
var flagRegister: Int = 0x00

fun main(args: Array<String>) {
    println("Hello world!")
    println(0b10000000)

    val instruction = memory[programCounter] and 0xFF

    if (instruction and 0b11000000 == 0b01000000) {
        // LD r, r'

        val sourceRegister = instruction and 0b111
        val destinationRegister = instruction shr 3 and 0b111

        mainRegisters[destinationRegister] = mainRegisters[sourceRegister]
    } else if (instruction and 0b11111000 == 0b10000000) {
        // ADD A, r

        val sourceRegister = instruction and 0b111

        val result = mainRegisters[mainAccumulator] + mainRegisters[sourceRegister]
        mainRegisters[mainAccumulator] = result

        val signBit = if (result < 0) 1 else 0
        val zeroBit = if (result == 0) 1 else 0

        val flagMask = signBit
        // TODO: Change flags
    } else if (instruction == 0xC6) {
        // ADD A, n
    } else if (instruction == 0x86) {
        // ADD A, (HL)
    }
}
