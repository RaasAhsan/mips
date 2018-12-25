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

fun main(args: Array<String>) {
    val code = readProgram("program.asm")

    val lexer = MipsAssemblyLexer(ANTLRInputStream(code))
    val tokens = CommonTokenStream(lexer)
    val parser = MipsAssemblyParser(tokens)

    val context = parser.prog()
    context.line().forEach { lineContext ->
        val instructionContext = lineContext.instruction()
        println(instructionContext.addi().text)
    }
}
