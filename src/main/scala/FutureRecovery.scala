import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.Source

object FutureRecovery extends App {
  val netiquetteUrl = "http://www.ietf.org/rfc/rfc1855.txt"
  val netiquette = Future { Source.fromURL(netiquetteUrl).mkString }

  val answer = netiquette recover {
    case e: java.io.FileNotFoundException =>
      "You might be interested to know that ftp links " +
        "can also point to regular files we keep on our servers."
  }

  answer foreach { case contents => println(contents) }
  Thread.sleep(2000)
}
