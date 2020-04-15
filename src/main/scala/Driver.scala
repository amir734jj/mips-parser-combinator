import parser.MipsParser

import scala.util.parsing.input.CharArrayReader

object Driver {

  def main(args: Array[String]): Unit = {
    val parser = new MipsParser

    val source = scala.io.Source.fromFile(args.headOption match {
      case Some(value) => value
      case None => "src/test.s"
    })
    val code = try source.mkString finally source.close()

    val instructions = parser.parseCode(new CharArrayReader(code.toArray, 0))

    println(instructions.mkString("\n"))
  }
}