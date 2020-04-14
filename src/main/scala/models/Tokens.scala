package models

class Token

class NothingToken extends Token {
  override def toString = s""
}

class Comment(comment: String) extends Token {
  override def toString = s"# $comment"
}

class Move(r1: Register, r2: Register) extends Token {
  override def toString = s"move $r1 $r2"
}

class LoadImmediate(r: Register, i: Int) extends Token {
  override def toString = s"li $r $i"
}

class SystemCall() extends Token {
  override def toString = "syscall"
}

class LoadAddress(r: Register, label: String) extends Token {
  override def toString = s"la $r $label"
}

class Directive extends Token

class DataDirective() extends Directive {
  override def toString = ".data"
}

class WordDirective() extends Directive {
  override def toString = ".word"
}

class AsciiDirective() extends Directive {
  override def toString = ".asciiz"
}

class AsciizDirective() extends Directive {
  override def toString = ".asciiz"
}