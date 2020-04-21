import models.tokens.Program
import models.traits.Token

class SimpleSimulator(val program: Program) {

  import Token._

  def getLabelBound(target: String): Int = program.tokens.indexWhere(x => x match {
    case Label(l) => target == l
    case _ => false
  })

  def simulate(): Unit = {
    var registers = Map[String, Int]()

    for (token <- program.tokens) {
      token match {
        case Data() => ()
        case Text() => ()
        case Label(name) => ()
        case Word(value) => ()
        case Ascii(value) => ()
        case Asciiz(value) => ()
        case Comment(comment) => ()
        case Syscall() =>
          registers.get("$v0") match {
            case Some(4) =>
              registers.get("$a0") match {
                case Some(v) => program.tokens.lift(v) match {
                  case Some(Ascii(w)) => print(w)
                  case Some(Asciiz(w)) => println(w)
                  case None => ()
                }
                case None => ()
              }
            case Some(1) => registers.get("$a0") match {
              case Some(v) => program.tokens.lift(v) match {
                case Some(Word(w)) => print(w)
                case None => print(v)
              }
              case None => ()
            }
            case Some(10) => System.exit(0)
            case None => ()
          }
        case Move(r1, r2) => registers += r1 -> registers.getOrElse(r2, 0)
        case LoadAddress(register, label) => registers += register -> (getLabelBound(label) + 1)
        case LoadImmediate(register, value) => registers += register -> value
        case LoadWord(target, offset, source) => registers += target -> program.tokens.indexOf(program.tokens(registers.getOrElse(source, 0) + offset))
      }
    }
  }
}
