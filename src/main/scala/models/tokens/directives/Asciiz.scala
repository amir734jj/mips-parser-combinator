package models.tokens.directives

import models.traits.{AtomicParser, Token}

object Asciiz extends AtomicParser {

  import parser.GenericParser._

  override type selfT = AsciizImpl

  class AsciizImpl(var value: String) extends Token {
    override def toString: String = s".asciiz $value"
  }

  def parse(): Parser[selfT] = literal(".asciiz") ~ """"([^\\"]|\\.)*"""".r ^^ {
    case _ ~ str => new AsciizImpl(str)
  }
}