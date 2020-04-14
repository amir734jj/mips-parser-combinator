package models.tokens.directives

import models.traits.{AtomicParser, Token}

object Ascii extends AtomicParser {

  import parser.GenericParser._

  override type selfT = AsciiImpl

  class AsciiImpl(var value: String) extends Token {
    override def toString: String = s".ascii $value"
  }

  def parse(): Parser[selfT] = literal(".ascii") ~ WhiteSpace ~ """"([^\\"]|\\.)*"""".r ^^ {
    case _ ~ _ ~ str => new AsciiImpl(str)
  }
}