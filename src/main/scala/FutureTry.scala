import scala.util.{Try, Success, Failure}

object FuturesTry extends App {
  def handleMessage(t: Try[String]) = t match {
    case Success(msg) => println(msg)
    case Failure(error) => println(s"unexpected failure - $error")
  }


  val threadName: Try[String] = Try(Thread.currentThread.getName)
  val someText: Try[String] = Try("Try objects are synchronous")
  val message: Try[String] = for {
    tn <- threadName
    st <- someText
  } yield s"Message $st was created on t = $tn"

  threadName.flatMap(tn => someText.map(st => s"Message $st was created on t = $tn"))
  handleMessage(message)
}
