import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.Source

object FutureCallbacks extends App {
  def getUrlSpec(): Future[List[String]] = Future {
    val url = "http://www.w3.org/Addressing/URL/url-spec.txt"
    val f = Source.fromURL(url)
    try f.getLines.toList finally f.close()
  }

  def find(lines: List[String], keyword: String): String = lines.zipWithIndex collect {
    case (line, n) if line.contains(keyword) => (n, line)
  } mkString("\n")


  val urlSpec: Future[List[String]] = getUrlSpec()

  urlSpec foreach {
    case lines => println(find(lines, "telnet"))
  }
  println("callback registered, continuing with other work")

  urlSpec foreach {
    case lines => println(find(lines, "password"))
  }
  Thread.sleep(1000)

}