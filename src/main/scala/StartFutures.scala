import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object StartFutures extends App {


  Future{ println("the future is here") }
  println("the future is coming")
  Thread.sleep(1000)
}
