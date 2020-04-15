package models.tokens.directives

import models.traits.{AtomicParser, Token}

object Ascii extends AtomicParser {

  import parser.GenericParser._

  override type selfT = AsciiImpl

  case class AsciiImpl(var value: String) extends Token {
    override def toString: String = s".ascii $value"
  }

  def parse(): Parser[selfT] = literal(".ascii") ~ """"([^\\"]|\\.)*"""".r ^^ {
    case _ ~ str => new AsciiImpl(str)
  }
}