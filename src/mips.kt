
fun main(args: Array<String>) {
    val emulator = Emulator()

    emulator.loadProgram("program.hecx")
    emulator.start()
}
