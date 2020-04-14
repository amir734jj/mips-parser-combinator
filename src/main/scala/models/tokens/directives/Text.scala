package models.tokens.directives

import models.traits.{AtomicParser, Token}

object Text extends AtomicParser {

  import parser.GenericParser._

  override type selfT = TextImpl

  class TextImpl extends Token {
    override def toString: String = ".text"
  }

  def parse(): Parser[selfT] = literal(".text") ^^ (_ => new TextImpl)
}