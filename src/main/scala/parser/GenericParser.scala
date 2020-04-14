package parser

import models.tokens.misc.Register.{RegisterT, names}

import scala.language.{implicitConversions, postfixOps}
import scala.util.matching.Regex
import scala.util.parsing.combinator.RegexParsers

object GenericParser extends RegexParsers {

  // Override default behavior to not skip whitespaces
  override def skipWhitespace = false

  type ParserT[E] = Parser[E]

  val WhiteSpace: Regex = whiteSpace

  // Separator which is a comma followed by whitespace or just whitespace
  def separatorP: ParserT[Serializable] = literal(",") ~ (whiteSpace ?) | whiteSpace

  // Word parser
  def wordP: Parser[String] = """[\w]+""".r ^^ { x => x }

  // Number parser
  def numberP: Parser[Int] = """^-?[\d][\d]*""".r ^^ { _.toInt }

  // Register parser
  def registerP: Parser[RegisterT] = names
    .foldRight(literal(names.head))((x, xs) => literal(x) ||| xs)
}
