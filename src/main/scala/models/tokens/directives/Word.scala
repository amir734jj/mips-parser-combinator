package models.tokens.directives

import models.traits.{AtomicParser, Token}

object Word extends AtomicParser {

  import parser.GenericParser._

  override type selfT = WordImpl

  class WordImpl(var value: Int) extends Token {
    override def toString: String = s".word ${value}"
  }

  def parse(): Parser[selfT] = literal(".word") ~ WhiteSpace ~ numberP ^^ {
    case _ ~ _ ~ number => new WordImpl(number)
  }
}