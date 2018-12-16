# mips

This project provides a full-featured environment for MIPS CPUs:
- An assembler
- A disassembler
- An emulator with various mock I/O components
- A programming language named Ciel that compiles to MIPS assembly

Kotlin is the choice of language for this codebase for several reasons:
1. Principled and disciplined language
2. Targets JVM
3. Targets LLVM -- we can also target WASM and use the codebase for browser applications

## Ciel

Ciel is a toy programming language built to compile to MIPS. This section will describe its features. Its grammar and operational semantics will be described in another file.

The language will change often, so programs are not guaranteed to be source backwards compatible.

### Basics
Ciel is a statement-oriented language -- every statement is not necessarily an expression (however the intention is to transform it into an expression-oriented language).

### Types
Ciel only has one type: integers.

### Variable assignment
```
x = 3
```

### Arithmetic
Ciel supports basic arithmetic that adhere to the order of operations.

```
x = 4 + 5
y = x * 3 + 5
z = y / 4 + x % 2
```
