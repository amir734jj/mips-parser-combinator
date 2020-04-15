import models.tokens.Program
import models.traits.Token

class PrettyPrinter(val program: Program) {

  import Token._

  var sb = new StringBuilder()

  def format(): String = {
    program.tokens.zipWithIndex.foreach {
      case (token, i) => formatToken(token, program.tokens.lift(i + 1))
    }

    sb.toString
  }

  var indentCounter = 0

  def indent(): String = (for (_ <- 0 to indentCounter) yield "").mkString

  def formatToken(t: Token, lookahead: Option[Token]): Unit = {
    t match {
      case Data() => sb.append('\t').append(t).append('\n')
      case Text() => sb.append('\t').append(t).append('\n')
      case Syscall() => sb.append(t).append('\n')
      case Label(name) => name match {
        case "main" => {
          indentCounter = 0
          sb.append(t).append('\n')
        }
        case _ => {
          sb.append(indent()).append(t)
          indentCounter += 1
        }
      }
      case Word(value) => sb.append(t).append('\n')
      case Ascii(value) => sb.append(t).append('\n')
      case Move(r1, r2) => sb.append(indent()).append(t).append('\n')
      case Asciiz(value) => sb.append('\t').append(t).append('\n')
      case Comment(comment) => sb.append(t).append('\n')
      case LoadAddress(register, label) => sb.append(indent()).append(t).append('\n')
      case LoadImmediate(register, value) => sb.append(indent()).append(t).append('\n')
      case LoadWord(target, offset, source) => sb.append(indent()).append(t).append('\n')
    }
  }
}
