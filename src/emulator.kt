import java.io.File

/*
 * MIPS is a load-store architecture, which means only load and store instructions can access main memory.
 *
 * MIPS has 32-bit addresses to access memory. Therefore there are 2^32 bytes, or 4 GiB, of addressable main memory.
 *
 * A register file is a collection of high-speed storage cells located inside the CPU.
 * CPU registers can be accessed in a single CPU cycle, whereas memory accesses take several CPU cycles.
 * General-purpose registers and special-purpose registers (IR, PC).
 *
 * There are only 32 registers in each file, so only 5 bits are needed to encode a register number.
 *
 * The general-purpose register file contains 32 32-bit GPRs, are called $0 to $31.
 * Another floating-point register file contains 32 32-bit FPRs, called $f0 to $f31.
 * Each register has a designated purpose.
 *
 * More information on registers:
 * - https://en.wikibooks.org/wiki/MIPS_Assembly/Register_File
 * - http://www.cs.uwm.edu/classes/cs315/Bacon/Lecture/HTML/ch05s03.html
 */

class Emulator {

    // 64K memory
    val memory: IntArray = IntArray(0xFFFF)

    // The program counter holds the 16-bit address of the current value being fetched from memory.
    var pc: Int = 0x0000

    // The stack pointer holds the 16-bit address of the current top of the stack located anywhere in RAM.
    var sp: Int = 0x0000

    // The independent index registers hold a 16-bit base address used in indexed addressing modes.
    val ix: Int = 0x0000
    val iy: Int = 0x0000

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

    fun start() {
        var done = false
        while (!done) {
            val decoded = decode(pc, memory)

            if (decoded != null) {
                if (decoded.instruction == Nop) {
                    done = true
                } else {
                    println(decoded.instruction)
                    pc = decoded.nextPc
                }
            } else {
                done = true
            }
        }
    }

    fun loadProgram(file: String) {
        val file = File(file)
        val bytes = file.readBytes()

        bytes.forEachIndexed { index, byte ->
            memory[index] = byte.toInt() and 0xFF
        }
    }

}
