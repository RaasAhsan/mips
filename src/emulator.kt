import java.io.File

class Emulator {

    // 64K memory
    val memory: IntArray = IntArray(0xFFFF)

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

    fun start() {
        var ip = 0
        var done = false
        while (!done) {
            val decoded = decode(ip, memory)

            if (decoded != null) {
                if (decoded.instruction == Nop) {
                    done = true
                } else {
                    println(decoded.instruction)
                    ip = decoded.nextIp
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
