package models.tokens.misc

import models.traits.{AtomicParser, Token}

object Comment extends AtomicParser {

  import parser.GenericParser._

  override type selfT = CommentImpl

  class CommentImpl(val comment: String) extends Token {
    override def toString: String = s"#$comment"
  }

  def parse(): Parser[selfT] = literal("#") ~ ("""[^\n]+""".r ?) ^^ {
    case _ ~ comment => new CommentImpl(comment.getOrElse(""))
  }
}