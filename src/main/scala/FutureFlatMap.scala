import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.Source

object FutureFlatMap extends App {
  val netiquetteUrl = "http://www.ietf.org/rfc/rfc1855.txt"
  val netiquette = Future { Source.fromURL(netiquetteUrl).mkString }
  val urlSpecUrl = "http://www.w3.org/Addressing/URL/url-spec.txt"
  val urlSpec = Future { Source.fromURL(urlSpecUrl).mkString }
  val answer = netiquette.flatMap { nettext =>
    urlSpec.map { urltext =>
      "Check this out: " + nettext + ". And check out: " + urltext
    }
  }

  val nicerAnswer = for {
    nettext <- netiquette
    urltext <- urlSpec
  } yield {
    s"First read this: $nettext. Now, try this: $urltext"
  }

  val niceButNotTheSame = for {
    nettext <- Future { Source.fromURL(netiquetteUrl).mkString }
    urltext <- Future { Source.fromURL(urlSpecUrl).mkString }
  } yield {
    s"First read this: $nettext. Now, try this: $urltext"
  }

  niceButNotTheSame foreach { case contents => println(contents) }

  Thread.sleep(2000)

}
