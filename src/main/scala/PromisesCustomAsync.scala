import scala.util.control.NonFatal
import scala.concurrent.Promise
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object PromisesCustomAsync extends App {
  def myFuture[T](b: =>T): Future[T] = {
    val p = Promise[T]
    global.execute(new Runnable {
      def run() = try {
        p.success(b)
      } catch {
        case NonFatal(e) => p.failure(e)
      }
    })
    p.future
  }
  val f = myFuture { "naa" + "na" * 8 + " Katamari Damacy!" }
  f foreach { case text => println(text) }
  println("RACE CONDITION")
  Thread.sleep(1000)
}
