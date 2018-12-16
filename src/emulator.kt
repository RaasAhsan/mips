import java.io.File

/*
 * MIPS is a RISC (Reduced Instruction Set Computer), which means that there are generally fewer CPU cycles per instruction.
 * MIPS is a load-store architecture, which means only load and store instructions access main memory. Common for RISCs.
 * The remaining logical instructions that are performed by the ALU only operate on registers.
 * It is a register machine, which means that it uses a collection of registers to perform operations (as opposed to a stack machine).
 *
 * MIPS has 32-bit addresses to access memory. Therefore there are 2^32 bytes, or 4 GiB, of addressable main memory.
 *
 * A register file is a collection of high-speed storage cells located inside the CPU.
 * CPU registers can be accessed in a single CPU cycle, whereas memory accesses take several CPU cycles.
 * General-purpose registers and special-purpose registers (IR, PC).
 *
 * There are exactly 32 registers in each file, so only 5 bits are needed to encode a register number.
 *
 * The general-purpose register file contains 32 32-bit GPRs, are called $0 to $31.
 * Another floating-point register file contains 32 32-bit FPRs, called $f0 to $f31.
 * Each register has a designated purpose.
 *
 * Reference for registers:
 * - https://en.wikibooks.org/wiki/MIPS_Assembly/Register_File
 * - http://www.cs.uwm.edu/classes/cs315/Bacon/Lecture/HTML/ch05s03.html
 */

class Emulator {

    // 64K memory
    val memory: IntArray = IntArray(0xFFFF)

    // The program counter holds the 32-bit address of the current instruction word fetched from memory.
    var pc: Int = 0x00000000

    // The stack pointer holds the 32-bit address of the current top of the stack located anywhere in RAM.
    var sp: Int = 0x00000000

    val gpr: IntArray = intArrayOf(32)
    val fpr: IntArray = intArrayOf(32)

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
