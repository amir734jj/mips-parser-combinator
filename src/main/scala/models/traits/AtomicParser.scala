package models.traits

trait AtomicParser {
  type selfT
  import parser.GenericParser.ParserT

  def parse(): ParserT[selfT]
}