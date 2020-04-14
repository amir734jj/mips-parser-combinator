package parser

import models.{AsciiDirective, AsciizDirective, Comment, DataDirective, Directive, LoadAddress, LoadImmediate, Move, NothingToken, Register, Token, WordDirective}

import scala.language.implicitConversions
import scala.util.parsing.combinator.RegexParsers
import scala.util.parsing.input.{CharSequenceReader, Reader}
import scala.language.postfixOps

class MipsParser extends RegexParsers {

  override def skipWhitespace = false

  def separator: Parser[Any] = literal(",") ~ whiteSpace | whiteSpace

  def word: Parser[String] = """[\w]+""".r ^^ { x => x }

  def number: Parser[Int] = """^-?[0-9][0-9]*""".r ^^ {
    _.toInt
  }

  def register: Parser[Register] = Register.names
    .foldRight(literal(Register.names.head).map(new Register(_)))((x, xs) => literal(x).map(new Register(_)) | xs)

  def loadAddress: Parser[LoadAddress] = literal("la") ~ separator ~ register ~ separator ~ word ^^ {
    case _ ~ _ ~ register ~ _ ~ label => new LoadAddress(register, label)
  }

  def loadImmediate: Parser[LoadImmediate] = literal("li") ~ separator ~ register ~ separator ~ number ^^ {
    case _ ~ _ ~ register ~ _ ~ i => new LoadImmediate(register, i)
  }

  def move: Parser[Move] = literal("move") ~ separator ~ register ~ separator ~ register ^^ {
    case _ ~ _ ~ register1 ~ _ ~ register2 => new Move(register1, register2)
  }

  def nothing: Parser[Token] = """\s*""".r ^^ { _ => new NothingToken }

  def directive: Parser[Directive] = (whiteSpace ?) ~ literal(".") ~ word ~ whiteSpace ^^ {
    case _ ~ _ ~ "asciiz" => new AsciizDirective
    case _ ~ _ ~ "data" => new DataDirective
    case _ ~ _ ~ "word" => new WordDirective
    case _ ~ _ ~ "ascii" => new AsciiDirective
  }

  def comment: Parser[Comment] = literal("#") ~ ("""[^\n]+""".r ?) ^^ { case _ ~ comment => new Comment(comment.getOrElse("")) }

  def instruction: Parser[Token] = loadAddress ||| loadImmediate ||| move ||| comment ||| nothing

  def program: Parser[Seq[Token]] = repsep(instruction, "\n") ^^ {
    _.toList
  }

  def parseCode(code: Reader[Char]): Seq[Token] = {
    parse(program, code) match {
      case Success(matched, _) => matched
      case Failure(msg, _) => throw new Exception(s"FAILURE: $msg")
      case Error(msg, _) => throw new Exception(s"ERROR: $msg")
    }
  }
}
