package models.tokens.instructions

import models.tokens.misc.Register.RegisterT
import models.traits.{AtomicParser, Token}

object LoadImmediate extends AtomicParser {

  import parser.GenericParser._

  override type selfT = LoadImmediateImpl

  class LoadImmediateImpl(val register: RegisterT, val value: Int) extends Token {
    override def toString: String = s"li ${register}, ${value}"
  }

  def parse(): Parser[selfT] = literal("li") ~ separatorP ~ registerP ~ separatorP ~ numberP ^^ {
    case _ ~ _ ~ register ~ _ ~ value => new LoadImmediateImpl(register, value)
  }
}
