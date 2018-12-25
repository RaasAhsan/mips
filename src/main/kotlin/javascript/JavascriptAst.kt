package javascript

sealed class JavascriptAst

data class VarDeclaration(val name: String) // Can declare multiple variables and assign at the same time

data class VarAssignment(val identifier: String, val rhs: Int)

data class FunctionDefinition(val name: String, val arguments: List<String>, val body: Int)

data class FunctionInvocation(val name: String, val arguments: List<Int>)

data class Return(val expression: Int)

// Arithmetic

data class Add(val n: Int)

data class Multiply(val n: Int)

data class Subtract(val n: Int)

data class Divide(val n: Int)

// TODO: If-else and if-else if-else expressions