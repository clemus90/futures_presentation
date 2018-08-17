import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.Source
import java.io.File
import org.apache.commons.io.FileUtils._
import scala.collection.convert.decorateAsScala._


object FutureClumsyCallback extends App {
  def blacklistFile(name: String): Future[List[String]] = Future {
    val lines = Source.fromFile(name).getLines
    lines.filter(x => !x.startsWith("#") && !x.isEmpty).toList
  }

  def findFiles(patterns: List[String]): List[String] = {
    val root = new File(".")
    for {
      f <- iterateFiles(root, null, true).asScala.toList
      pat <- patterns
      abspat = root.getCanonicalPath + File.separator + pat
      if f.getCanonicalPath.contains(abspat)
    } yield f.getCanonicalPath
  }

  blacklistFile(".gitignore") foreach {
    case lines =>
      val files = findFiles(lines)
      println(s"matches: ${files.mkString("\n")}")
  }



  Thread.sleep(1000)

}
