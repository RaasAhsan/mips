
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

data class AddRegToAcc(val source: Int) : Instruction()
data class AddIntToAcc(val n: Int) : Instruction()
object AddHlToAcc : Instruction()
object AddIxToAcc : Instruction()
object AddIyToAcc : Instruction()
data class AdcRegToAcc(val source: Int) : Instruction()
data class AdcIntToAcc(val n: Int) : Instruction()
object AdcHlToAcc : Instruction()
object AdcIxToAcc : Instruction()
object AdcIyToAcc : Instruction()
data class SubRegToAcc(val source: Int) : Instruction()
data class SubIntToAcc(val n: Int) : Instruction()
object SubHlToAcc : Instruction()
object SubIxToAcc : Instruction()
object SubIyToAcc : Instruction()
data class SbcRegToAcc(val source: Int) : Instruction()
data class SbcIntToAcc(val n: Int) : Instruction()
object SbcHlToAcc : Instruction()
object SbcIxToAcc : Instruction()
object SbcIyToAcc : Instruction()

// General-purpose arithmetic and CPU control group

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
