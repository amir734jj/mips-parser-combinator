package models.tokens.instructions

import models.tokens.misc.Register.RegisterT
import models.traits.{AtomicParser, Token}

object LoadAddress extends AtomicParser {

  import parser.GenericParser._

  override type selfT = LoadAddressImpl

  class LoadAddressImpl(val register: RegisterT, val label: String) extends Token {
    override def toString: String = s"la $register, $label"
  }

  def parse(): Parser[selfT] = literal("la") ~ registerP ~ separatorP ~ wordP ^^ {
    case _ ~ register ~ _ ~ label => new LoadAddressImpl(register, label)
  }
}
