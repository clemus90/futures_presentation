import scala.concurrent.Promise
import scala.concurrent.ExecutionContext.Implicits.global

object PromisesCreate extends App {
  val p = Promise[String]
  val q = Promise[String]
  p.future foreach { case x => println(s"p succeeded with '$x'") }
  Thread.sleep(1000)
  p success "assigned"
  q failure new Exception("not kept")
  q.future.failed foreach { case t => println(s"q failed with $t") }
  Thread.sleep(1000)
}
