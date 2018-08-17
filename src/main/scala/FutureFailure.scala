import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.Source

object FutureFailure extends App {
  val urlSpec: Future[String] = Future {
    val invalidUrl = "http://www.w3.org/non-existent-url-spec.txt"
    Source.fromURL(invalidUrl).mkString
  }
  urlSpec.failed foreach {
    case t => println(s"exception occurred - $t")
  }
  Thread.sleep(1000)
}
