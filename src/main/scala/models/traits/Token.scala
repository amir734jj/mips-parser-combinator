package models.traits

import models.tokens.directives.Ascii._
import models.tokens.directives.Asciiz._
import models.tokens.directives.Data._
import models.tokens.directives.Text._
import models.tokens.directives.Word._
import models.tokens.instructions.LoadAddress._
import models.tokens.instructions.LoadImmediate._
import models.tokens.instructions.Move._
import models.tokens.misc.Comment._
import models.tokens.misc.Label._
import models.tokens.misc.Syscall._

trait Token {
  override def toString: String = "<nil>"
}

object Token {

  val Data: DataImpl.type = DataImpl
  val Text: TextImpl.type = TextImpl
  val Syscall: SyscallImpl.type = SyscallImpl
  val Label: LabelImpl.type = LabelImpl
  val Word: WordImpl.type = WordImpl
  val Ascii: AsciiImpl.type = AsciiImpl
  val Move: MoveImpl.type = MoveImpl
  val Asciiz: AsciizImpl.type = AsciizImpl
  val Comment: CommentImpl.type = CommentImpl
  val LoadAddress: LoadAddressImpl.type = LoadAddressImpl
  val LoadImmediate: LoadImmediateImpl.type = LoadImmediateImpl

}
