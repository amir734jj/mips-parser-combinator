package models.tokens.misc

import models.traits.{AtomicParser, Token}

object Label extends AtomicParser {

  import parser.GenericParser._

  override type selfT = LabelImpl

  class LabelImpl(val name: String) extends Token {
    override def toString: String = s"$name:"
  }

  def parse(): Parser[selfT] = wordP ~ literal(":") ^^ {
    case name ~ _ => new LabelImpl(name)
  }
}