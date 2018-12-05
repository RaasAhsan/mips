
sealed class Instruction

// 8-bit load group
data class LoadRegToReg(val source: Int, val destination: Int) : Instruction()
data class LoadIntToReg(val n: Int, val destination: Int) : Instruction()
data class LoadHlToReg(val destination: Int) : Instruction()
data class LoadIxToReg(val destination: Int) : Instruction()
data class LoadIyToReg(val destination: Int) : Instruction()
data class LoadRegToHl(val source: Int) : Instruction()
data class LoadRegToIx(val source: Int) : Instruction()
data class LoadRegToIy(val source: Int) : Instruction()
data class LoadIntToHl(val n: Int) : Instruction()
data class LoadIntToIx(val source: Int) : Instruction()
data class LoadIntToIy(val source: Int) : Instruction()
object LoadBcToAcc : Instruction()
object LoadDeToAcc : Instruction()
data class LoadMemToAcc(val address: Int) : Instruction()
object LoadAccToBc : Instruction()
object LoadAccToDe : Instruction()
data class LoadAccToMem(val address: Int) : Instruction()
object LoadIvToAcc : Instruction()
object LoadMrrToAcc : Instruction()
object LoadAccToIv : Instruction()
object LoadAccToMrr : Instruction()

// 16-bit load group

// 8-bit arithmetic group

data class Add(val source: ArithSrc) : Instruction()
data class Adc(val source: ArithSrc) : Instruction()
data class Sub(val source: ArithSrc) : Instruction()
data class Sbc(val source: ArithSrc) : Instruction()
data class And(val source: ArithSrc) : Instruction()
data class Or(val source: ArithSrc) : Instruction()
data class Xor(val source: ArithSrc) : Instruction()
data class Compare(val source: ArithSrc) : Instruction()
data class Increment(val dest: ArithDest) : Instruction()
data class Decrement(val dest: ArithDest) : Instruction()

// General-purpose arithmetic and CPU control group

object Complement : Instruction()
object Negate : Instruction()
object ComplementCarryFlag : Instruction()
object SetCarryFlag : Instruction()
object Nop : Instruction()
object Halt : Instruction()
object DisableInterrupt : Instruction()
object EnableInterrupt : Instruction()
object SetInterruptMode0 : Instruction()
object SetInterruptMode1 : Instruction()
object SetInterruptMode2 : Instruction()

// 16-bit arithmetic group

// Jump group

data class JumpMem(val address: Int) : Instruction()
data class JumpMemCond(val address: Int, val condition: Int) : Instruction()
data class JumpDisp(val displacement: Int) : Instruction()
data class JumpDispCarry(val displacement: Int) : Instruction()
data class JumpDispNoCarry(val displacement: Int) : Instruction()
data class JumpDispZero(val displacement: Int) : Instruction()
data class JumpDispNoZero(val displacement: Int) : Instruction()
object JumpHl : Instruction()
object JumpIx : Instruction()
object JumpIy : Instruction()
data class JumpDispB(val displacement: Int) : Instruction()

// Call and return group

data class CallMem(val address: Int) : Instruction()
data class CallMemCond(val address: Int, val condition: Int) : Instruction()
object Return : Instruction()
data class ReturnCond(val condition: Int) : Instruction()
object ReturnInterrupt : Instruction()
object ReturnNonmaskInterrupt : Instruction()

// Sources

sealed class ArithSrc
data class ArithFromReg(val register: Int) : ArithSrc()
data class ArithFromInt(val n: Int) : ArithSrc()
object ArithFromHl : ArithSrc()
data class ArithFromIx(val displacement: Int) : ArithSrc()
data class ArithFromIy(val displacement: Int) : ArithSrc()

// Destinations

sealed class ArithDest
data class ArithToReg(val register: Int) : ArithDest()
object ArithToHl : ArithDest()
data class ArithToIx(val displacement: Int) : ArithDest()
data class ArithToIy(val displacement: Int) : ArithDest()
