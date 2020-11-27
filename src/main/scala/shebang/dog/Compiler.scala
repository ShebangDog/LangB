package shebang.dog

import shebang.dog.front.Parser
import shebang.dog.front.Parser.programs

import scala.io.Source
import scala.util.Using

object Compiler {
  def main(args: Array[String]): Unit = {
    val optFileName = args.toList match {
      case ::(head, _) => Option(head)
      case Nil => None
    }

    val optProgramSeq = optFileName.flatMap(fileName => {
      Using(Source.fromFile(fileName)) { file =>
        Option(file.getLines().toSeq)
      }.getOrElse(None)
    })

    val optX86Code = optProgramSeq.map(programSeq => Parser.parseAll(programs, programSeq.mkString).getOrElse("error"))

    val result = optProgramSeq.map(_.mkString)

    result.foreach(println)
  }
}
