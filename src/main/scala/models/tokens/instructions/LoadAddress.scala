package models.tokens.instructions

import models.tokens.misc.Register.RegisterT
import models.traits.{AtomicParser, Token}

object LoadAddress extends AtomicParser {

  import parser.GenericParser._

  override type selfT = LoadAddressImpl

  case class LoadAddressImpl(register: RegisterT, label: String) extends Token {
    override def toString: String = s"la $register, $label"
  }

  def parse(): Parser[selfT] = literal("la") ~ WhiteSpace ~ registerP ~ separatorP ~ wordP ^^ {
    case _ ~ register ~ _ ~ label => LoadAddressImpl(register, label)
  }
}
