package models.tokens.misc

import models.traits.{AtomicParser, Token}

object Syscall extends AtomicParser {

  import parser.GenericParser._

  override type selfT = SyscallImpl

  case class SyscallImpl() extends Token {
    override def toString: String = "syscall"
  }

  def parse(): Parser[selfT] = literal("syscall") ^^ (_ => new SyscallImpl)
}