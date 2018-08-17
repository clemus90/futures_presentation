import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.control.NonFatal

object FutureNonFatal extends App {
  val f = Future { throw new InterruptedException }
  val g = Future { throw new IllegalArgumentException }
  f.failed foreach { case t => println(s"error - $t") }
  g.failed foreach { case t => println(s"error - $t") }

  f.failed foreach {
    case NonFatal(t) => println(s"$t is non fatal")
  }

  Thread.sleep(1000)
}
