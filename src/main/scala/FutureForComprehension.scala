import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.Source

object FutureForComprehension extends App {

  val buildFile = Future {
    Source.fromFile("build.sbt").getLines
  }

  val longest = for(ls <- buildFile) yield ls.maxBy(_.length)
  buildFile.map(ls => ls.maxBy(_.length))

  longest.foreach {
    case line => println(s"longest line: $line")
  }

  Thread.sleep(1000)

}
