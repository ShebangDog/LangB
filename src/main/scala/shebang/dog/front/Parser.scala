package shebang.dog.front

import scala.util.parsing.combinator.JavaTokenParsers

object Parser extends JavaTokenParsers {
  def programs: Parser[String] = ident
}
