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

  def indent(): String = (for (_ <- 0 to indentCounter) yield "  ").mkString

  def formatToken(t: Token, lookahead: Option[Token]): Unit = {
    t match {
      case Data() => {
        sb.append('\n')
          .append('\t')
          .append(t)
        indentCounter = 0
      }
      case Text() => {
        sb.append('\n')
          .append('\t')
          .append(t)
        indentCounter = 0
      }
      case Label(name) => {
        sb.append('\n')
        name match {
          case "main" => {
            indentCounter = 0
            sb.append(t)
          }
          case _ => {
            lookahead match {
              case Some(Ascii(_)) | Some(Asciiz(_)) | Some(Word(_)) => sb.append(t)
              case _ => {
                sb.append(indent()).append(t)
                indentCounter += 1
              }
            }
          }
        }
      }
      case Word(value) => sb.append('\t').append(t)
      case Ascii(value) => sb.append('\t').append(t)
      case Asciiz(value) => sb.append('\t').append(t)
      case Comment(comment) => ()
      case Syscall() => sb.append('\n').append(indent()).append(t).append('\n')
      case Move(r1, r2) => sb.append('\n').append(indent()).append(t)
      case LoadAddress(register, label) => sb.append('\n').append(indent()).append(t)
      case LoadImmediate(register, value) => sb.append('\n').append(indent()).append(t)
      case LoadWord(target, offset, source) => sb.append('\n').append(indent()).append(t)
    }
  }
}
