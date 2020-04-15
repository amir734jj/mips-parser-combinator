import models.tokens.Program
import models.traits.Token

class PrettyPrinter(val program: Program) {

  import Token._

  def format() = program.tokens.mkString("\n")

  def token(t: Token) = {
    t match {
      case Data() => ()
      case Text() =>()
      case Syscall() =>()
      case Label(name) =>()
      case Word(value) =>()
      case Ascii(value) =>()
      case Move(r1, r2) =>()
      case Asciiz(value) =>()
      case Comment(comment) =>()
      case LoadAddress(register, label) =>()
      case LoadImmediate(register, value) => ()
    }
  }
}
