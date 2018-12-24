import java.io.File

/*
 * MIPS, x86, x86-64, etc. are all instruction set architectures.
 * A CPU is a physical implementation of an instruction set architecture. Ryzen chips are amd64 CPUs.
 * A microprocessor is a CPU within a single chip.
 *
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
 *
 *
 * ------------------
 * -- Coprocessors --
 * ------------------
 *
 * Coprocessors are alternate execution units from the CPU, which possess their own register files.
 * Coprocessor 0 (CP0) is designated to the virtual memory system and exception handling.
 * CP1 is designated for floating-point operations.
 *
 *
 */

const val GP_INDEX = 28
const val SP_INDEX = 29
const val FP_INDEX = 30
const val RA_INDEX = 31

class Emulator {

    val memory: Memory = Memory()

    fun start() {

    }

    fun loadProgram(file: String) {
//        val file = File(file)
//        val bytes = file.readBytes()
//
//        bytes.forEachIndexed { index, byte ->
//            memory[index] = byte.toInt() and 0xFF
//        }
    }

}

class Memory {

    val bytes: IntArray = IntArray(0xFFFF)

}

class Cpu {

    // 32 32-bit general-purpose registers
    // r0 is always 0 no matter what
    // r31 is the link register
    val gpr: IntArray = intArrayOf(32)

    // 32-bit program counter
    val pc: Int = 0x00000000

    // 32-bit hi and lo for multiply/divide operations
    val hi: Int = 0x00000000
    val lo: Int = 0x00000000

}

class SysControlCoprocessor {

    val registers: IntArray = intArrayOf(32)

}
