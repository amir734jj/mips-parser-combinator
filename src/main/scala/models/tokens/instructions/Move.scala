package models.tokens.instructions

import models.tokens.misc.Register.RegisterT
import models.traits.{AtomicParser, Token}

object Move extends AtomicParser {

  import parser.GenericParser._

  override type selfT = MoveImpl

  def apply(): MoveImpl = new MoveImpl()

  case class MoveImpl(val r1: RegisterT, val r2: RegisterT) extends Token {
    override def toString: String = s"move $r1, $r2"
  }

  override def parse(): Parser[selfT] = literal("move") ~ registerP ~ separatorP ~ registerP ^^ {
    case _ ~ register1 ~ _ ~ register2 => new MoveImpl(register1, register2)
  }
}