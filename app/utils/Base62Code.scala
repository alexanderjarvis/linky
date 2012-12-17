package utils

import scala.util.Random
import scala.concurrent.Future
import scala.concurrent.ExecutionContext

object Base62Code {

  private val Chars = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')

  private val Length = 5

  private def uniqueRandom(chars: String, length: Int, uniqueFunc: String => Future[Boolean])(implicit ec: ExecutionContext): Future[String] = {
    val newKey = (1 to length).map(x => chars(Random.nextInt(chars.length))).mkString
    uniqueFunc(newKey).flatMap(unique => if (unique) Future(newKey) else uniqueRandom(chars, length, uniqueFunc)(ec))
  }

  def generate(isUnique: String => Future[Boolean])(implicit ec: ExecutionContext) = uniqueRandom(Chars.mkString, Length, isUnique)

}