package parser

import models.tokens.Program
import models.tokens.directives._
import models.tokens.instructions.{LoadAddress, LoadImmediate, Move}
import models.tokens.misc.{Comment, Label, Syscall}
import models.traits.Token

import scala.language.{implicitConversions, postfixOps}
import scala.util.parsing.input.Reader

class MipsParser {

  import GenericParser._

  def directive: Parser[Token] = Text.parse ||| Word.parse ||| Data.parse ||| Ascii.parse ||| Asciiz.parse

  def instruction: Parser[Token] = LoadAddress.parse ||| LoadImmediate.parse ||| Move.parse ||| Label.parse

  def misc: Parser[Token] = Label.parse ||| Comment.parse ||| Syscall.parse

  def item: Parser[Token] = directive ||| instruction ||| misc

  def program: Parser[Program] = repsep(item, """[\s\t\n]+""".r) ^^ { Program(_) }

  def parseCode(code: Reader[Char]): Program = {
    parse(program, code) match {
      case Success(matched, _) => matched
      case Failure(msg, _) => throw new Exception(s"FAILURE: $msg")
      case Error(msg, _) => throw new Exception(s"ERROR: $msg")
    }
  }
}
