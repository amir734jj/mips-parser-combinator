package models.tokens.instructions

import models.tokens.misc.Register.RegisterT
import models.traits.{AtomicParser, Token}

object LoadImmediate extends AtomicParser {

  import parser.GenericParser._

  override type selfT = LoadImmediateImpl

  case class LoadImmediateImpl(register: RegisterT, value: Int) extends Token {
    override def toString: String = s"li $register, $value"
  }

  def parse(): Parser[selfT] = literal("li") ~ WhiteSpace ~ registerP ~ separatorP ~ numberP ^^ {
    case _ ~ register ~ _ ~ value => LoadImmediateImpl(register, value)
  }
}
