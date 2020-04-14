package models.tokens.directives

import models.traits.{AtomicParser, Token}

object Data extends AtomicParser {

  import parser.GenericParser._

  override type selfT = DataImpl

  class DataImpl extends Token {
    override def toString: String = ".data"
  }

  def parse(): Parser[selfT] = literal(".data") ^^ (_ => new DataImpl)
}