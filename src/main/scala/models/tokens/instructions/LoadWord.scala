package models.tokens.instructions

import models.tokens.misc.Register.RegisterT
import models.traits.{AtomicParser, Token}

object LoadWord extends AtomicParser {

  import parser.GenericParser._

  override type selfT = LoadWordImpl

  case class LoadWordImpl(target: RegisterT, offset: Int, source: RegisterT) extends Token {
    override def toString: String = s"lw $target, $offset($source)"
  }

  def parse(): Parser[selfT] = literal("lw") ~ WhiteSpace ~ registerP ~ separatorP ~ numberP ~ literal("(") ~ registerP ~ literal(")") ^^ {
    case _ ~ target ~ _ ~ offset ~ _ ~ source ~ _ => LoadWordImpl(target, offset, source)
  }
}
