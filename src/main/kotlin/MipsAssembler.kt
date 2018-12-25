import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import java.io.File
import java.nio.charset.Charset

// Assembler for Z80 Assembly
// Converts text code to object code

fun readProgram(filename: String): String {
    val file = File(filename)
    return file.readText(Charset.forName("UTF-8"))
}

val registerTable = mapOf(
    "$0" to 0,
    "\$at" to 1,
    "\$v0" to 2,
    "\$v1" to 3,
    "\$a0" to 4,
    "\$a1" to 5,
    "\$a2" to 6,
    "\$a3" to 7,
    "\$t0" to 8,
    "\$t1" to 9,
    "\$t2" to 10,
    "\$t3" to 11,
    "\$t4" to 12,
    "\$t5" to 13,
    "\$t6" to 14,
    "\$t7" to 15,
    "\$s0" to 16,
    "\$s1" to 17,
    "\$s2" to 18,
    "\$s3" to 19,
    "\$s4" to 20,
    "\$s5" to 21,
    "\$s6" to 22,
    "\$s7" to 23,
    "\$t8" to 24,
    "\$t9" to 25,
    "\$k0" to 26,
    "\$k1" to 27,
    "\$sp" to 28,
    "\$gp" to 29,
    "\$fp" to 30,
    "\$ra" to 31
)

fun main(args: Array<String>) {
    val code = readProgram("program.asm")

    val lexer = MipsAssemblyLexer(ANTLRInputStream(code))
    val tokens = CommonTokenStream(lexer)
    val parser = MipsAssemblyParser(tokens)

    val context = parser.prog()
    context.line().forEach { lineContext ->
        val instructionContext = lineContext.instruction()
        println(instructionContext.add())
    }
}
